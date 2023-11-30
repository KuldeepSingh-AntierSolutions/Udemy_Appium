package Appium.Udemy;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTestOld 
{
	AppiumDriverLocalService service;
	AndroidDriver driver;
	@BeforeClass
	public void configure() throws MalformedURLException
	{
//		DesiredCapabilities caps = new DesiredCapabilities();
//      caps.setCapability("PLATFORM_NAME", "Android");
//      caps.setCapability("PLATFORM_VERSION", "11.0");
//      caps.setCapability("UDID", "emulator-5554");
//      caps.setCapability("automationName", "UiAutomator2");
//      caps.setCapability("APP", System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
//      caps.setCapability("APP_PACKAGE", "com.androidsample.generalstore");
//      caps.setCapability("APP_ACTIVITY", "com.androidsample.generalstore.MainActivity");
		
		
//		service= new AppiumServiceBuilder()
//		.withAppiumJS(new File("C:\\Users\\user\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//		.withIPAddress("127.0.0.1")
//		.usingPort(4723)
//		.build();
//		service.start();
		
		// **********FOR HYBRID APP USE THE CONFIGURATION BELOW*************
		UiAutomator2Options options=new UiAutomator2Options();
		options.setDeviceName("Pixel 4 API 30");
		//add the chromedriver executable, when the app to be tested is hybrid
		options.setChromedriverExecutable("C:\\Users\\user\\eclipse-workspace\\Udemy\\src\\test\\java\\resources\\chromedriver.exe");
//		options.setApp("C:\\Users\\user\\eclipse-workspace\\Udemy\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp("C:\\Users\\user\\eclipse-workspace\\Udemy\\src\\test\\java\\resources\\General-Store.apk");
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// ***********FOR WEBVIEW APP USE THE CONFIGURATION BELOW**********
//		UiAutomator2Options options=new UiAutomator2Options();
//		//for emulators
//		options.setDeviceName("Pixel 4 API 30");
//		// for real devices
////		options.setDeviceName("Android Device");
//		options.setChromedriverExecutable("C:\\Users\\user\\eclipse-workspace\\Udemy\\src\\test\\java\\resources\\chromedriver.exe");
//		options.setCapability("browserName", "Chrome");
//		driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
//		service.stop();
	}
	public void longPress(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",2000));
	}
	
}
