package Appium.Udemy;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ApiDemos extends BaseTestOld
{
	@Test(enabled=false)
	public void wifiSetting() throws MalformedURLException, InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popupname=driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
		Assert.assertEquals(popupname, "WiFi settings");
		driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("kuldeep");
		driver.findElement(AppiumBy.id("android:id/button1")).click();
	}
	@Test(enabled=false)
	public void longPressGesture() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement ele=driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		longPress(ele);
		WebElement menuitem1=driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id='android:id/title'])[1]"));
		Assert.assertTrue(menuitem1.isDisplayed());
		String item1text=menuitem1.getText();
		System.out.println(item1text);
		Assert.assertEquals(item1text,"Sample menu");
		Thread.sleep(2000);
	}
	@Test(enabled=false)
	public void scrollGesture() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		//for scrolling when we have a certain text value upto which we want to scroll
//		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", 
				 ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down", "percent", 3.0));
		}while(canScrollMore);
		Thread.sleep(2000);
	}
	@Test(enabled=false)
	public void swipe()
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		WebElement firstImage=driver.findElement(AppiumBy.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]"));
		Assert.assertEquals(firstImage.getAttribute("focusable"),"true");
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement)firstImage).getId(),
						"direction", "left", "percent",0.75));
		Assert.assertEquals(firstImage.getAttribute("focusable"),"false");
	}
	@Test(enabled=false)
	public void dragAndDrop() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement source=driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture",ImmutableMap.of(
				"elementId",((RemoteWebElement)source).getId(),
				"endX",650,
				"endY",690
				));
		Thread.sleep(2000);
		String resultText=driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(resultText,"Dropped!");
	}
	@Test(enabled=false)
	public void rotateScreen() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		
		DeviceRotation rotation=new DeviceRotation(0, 0, 90);
		driver.rotate(rotation);
		Thread.sleep(2000);
	}
	@Test(enabled=false)
	public void getAndSetClipboardText()
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.setClipboardText("Kuldeep");
		driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(driver.getClipboardText());
		driver.findElement(AppiumBy.id("android:id/button1")).click();
	}
	@Test(enabled=false)
	public void buttonsAndKeyPress() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.setClipboardText("Kuldeep");
		driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(driver.getClipboardText());
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		Thread.sleep(2000);
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	@Test(enabled=false)
	public void jumpToSpecificScreen()
	{
		//to jump we are using Activity class in which we give App package and App Activity
		// to get these two things, go to terminal and give command "adb devices" to get all active devices list
		// and then give command "adb shell dumpsys window | find "mCurrentFocus"" to know package and activity
		// the content is like packagename/activityname
//		Activity activity=new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
//		driver.startActivity(activity);
	}
	
	
	
}
