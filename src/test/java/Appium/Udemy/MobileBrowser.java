package Appium.Udemy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowser extends BaseTestOld
{
	@Test
	public void browserTest() throws InterruptedException
	{
//		driver.get("http://google.com");
//		System.out.println(driver.getTitle());
//		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		Thread.sleep(4000);
		
		driver.get("http://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		driver.findElement(By.xpath("//a[text()='Products ']")).click();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,900)");
		String courseName=driver.findElement(By.xpath("(//a[@class='mt-0 font-weight-bold mb-2'])[3]")).getText();
		Assert.assertEquals(courseName, "Devops");
		Thread.sleep(2000);
	}
}
