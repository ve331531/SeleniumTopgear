package seleniumHandsonOne;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandsonOne {

	
	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/java/seleniumHandsonOne/chromedriver.exe");
		
		WebDriver driver=new ChromeDriver(); 
		driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li//a[contains(text(),'Login')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-email")).sendKeys("veena.bhat@getnada.com");
		driver.findElement(By.id("input-password")).sendKeys("veena123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//li//a[contains(text(),'Logout')]")).click();
		Thread.sleep(5000);
		driver.close();
		
	}
}
