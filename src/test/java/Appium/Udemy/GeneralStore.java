package Appium.Udemy;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class GeneralStore extends BaseTestOld 
{
	@Test(enabled=false)
	public void fillForm() throws InterruptedException
	{
		//selecting country from dropdown
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		//scrolling into view in dropdown
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Argentina\"]")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kuldeep");
		//hide keyboard
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		Thread.sleep(2000);
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(2000);
	}
	@Test(enabled=true)
	public void handlingToastMessages()
	{
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		WebElement toaster=driver.findElement(AppiumBy.xpath("//android.widget.Toast"));
		String toastMessage=toaster.getText();
		Assert.assertEquals(toastMessage, "Please enter your name");
	}
	@Test(enabled=false)
	public void verifyAddedProductToCart() throws InterruptedException
	{
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kuldeep");
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		// add product to cart
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Converse All Star\"));"));
		int productCount=driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<productCount;i++)
		{
			String productName=driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(productName.equalsIgnoreCase("Converse All Star"))
			{
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		// go to cart
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		// verify the product added to cart
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));
		String pageTitle=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(pageTitle,"Cart");
		String productName=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(productName, "Converse All Star");
	}
	@Test(enabled=false)
	public void verifyAmountOfProducts() throws InterruptedException
	{
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kuldeep");
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		// upon clicking first "add to cart", the text changes to "added to cart" and only one "add to cart" remains
		// so we can also write two same statements for clicking on two different products like-
		// driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
		driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(1000);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));
		List<WebElement> productsPrice=driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		int productsCount=productsPrice.size();
		Double amountsSum=0.0;
		for(int i=0;i<productsCount;i++)
		{
			String amountString=productsPrice.get(i).getText();
			String formattedString=amountString.substring(1);
			Double formattedAmount=Double.parseDouble(formattedString);
			amountsSum=amountsSum+formattedAmount;
		}
		String displayedAmount=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(2);
		Double formattedDisplayAmount=Double.parseDouble(displayedAmount);
		Assert.assertEquals(formattedDisplayAmount, amountsSum);
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		WebElement termsButton=driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
		longPress(termsButton);
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(10000);
		//to get the all active contexts in the application and then pass that to driver to switch to it
//		Set<String> contexts=driver.getContextHandles();
//		for(String context:contexts)
//		{
//			//to check will print all the contexts on console
//			System.out.println(context);
//		}
		//to perform some actions on the WebView context we need to download and configure chromedriver.exe file
		// and set it to driver in driver initialisation method in basetest so that we can switch our driver to it
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		// again switch back to native app
		driver.context("NATIVE_APP");
	}
	
	
}
