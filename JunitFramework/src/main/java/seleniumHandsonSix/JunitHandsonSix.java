package seleniumHandsonSix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JunitHandsonSix {
	static WebDriver driver;
	
	@BeforeClass()
	public static void initializeDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/main/java/seleniumHandsonSix/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		//delete file if exists
		String filePath = System.getProperty("user.dir") + "\\flatfile";
		File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			} 
		
	}
	

	@Before
	public void login() throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li//a[contains(text(),'Login')]")).click();
		Thread.sleep(2000);

		// Step3:Enter Email Address and Password and click on "Login" Button.
		driver.findElement(By.id("input-email")).sendKeys("veena.bhat@getnada.com");
		driver.findElement(By.id("input-password")).sendKeys("veena123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(5000);
	}
	
	
	
	@Test
	public void TestExecution() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("HP LP3065");
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='caption']//h4//a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-quantity")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("input-quantity")).sendKeys("3");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='button-cart']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'shopping cart')]")).click();
		Thread.sleep(2000);
		
		String total = driver.findElement(By.xpath("(//div[@class='table-responsive']//td[@class='text-right'])[4]")).getText();
		String filePath = System.getProperty("user.dir") + "\\flatfile";
		// create a new file
		File file = new File(filePath);

		try {
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("File is created");
			} else {
				System.out.println("File already exist");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileWriter FW = new FileWriter(filePath);
		BufferedWriter BW = new BufferedWriter(FW);
		BW.write(total); // Writing In To File. 
		BW.close();
		
		total=total.replace("$", "");
		total=total.replace(",", "");
		double totalCost= Double.parseDouble(total);
		if(totalCost<200)
		{
			driver.findElement(By.xpath("//a[contains(text(),'Continue Shopping')]")).click();
			Thread.sleep(5000);
		}
		
	}
	
	
	
	

	@After
	public void logout() throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		Thread.sleep(2000);
		
	}
	
	@AfterClass()
	public static void tearDown() {
		driver.close();
	}
}
