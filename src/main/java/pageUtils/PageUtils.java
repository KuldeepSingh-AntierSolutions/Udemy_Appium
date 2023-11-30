package pageUtils;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PageUtils 
{
	AndroidDriver driver;
	public PageUtils(AndroidDriver driver) 
	{
		this.driver=driver;
	}
	public void longPressElement(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",2000));
	}
	public void scrollToElement(String elementName)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementName+"\"));"));
	}
	public void swipeOnElement(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
						"direction", "left", "percent",0.75));
	}
	public void dragAndDropElement(WebElement ele,int x,int y)
	{
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture",ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(),
				"endX",x,
				"endY",y
				));
	}
	public void rotateDeviceScreen(int angle)
	{
		DeviceRotation rotation=new DeviceRotation(0, 0, angle);
		driver.rotate(rotation);
	}
	public void setActivity()
	{
		//to jump we are using Activity class in which we give App package and App Activity
		// to get these two things, go to terminal and give command "adb devices" to get all active devices list
		// and then give command "adb shell dumpsys window | find "mCurrentFocus"" to know package and activity
		// the content is like packagename/activityname
//		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//		driver.startActivity(activity);
	}
}
