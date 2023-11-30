package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pages.FirstPage;

public class BaseTest 
{
	AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FirstPage firstPage;
	@BeforeClass(alwaysRun = true)
	public void configure() throws IOException
	{
		String deviceName=System.getProperty("deviceName")!=null ? System.getProperty("deviceName") : readPropertiesFile("deviceName");
		UiAutomator2Options options=new UiAutomator2Options();
		options.setDeviceName(deviceName);
//		options.setDeviceName("Redmi Note 11");
		options.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\chromedriver.exe");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
		driver=new AndroidDriver(new URL(readPropertiesFile("ipAddress")),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		firstPage=new FirstPage(driver);
	}
	@AfterClass(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
	public void switchToWeb()
	{
		driver.context("WEBVIEW_com.androidsample.generalstore");
	}
	public void switchToNative()
	{
		driver.context("NATIVE_APP");
	}
	public List<HashMap<String,String>> readJsonFile(String jsonFilePath) throws IOException
	{
		String jsonContent= FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	public String readPropertiesFile(String key) throws IOException
	{
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\resources\\data.properties";
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}
	public String getScreenshotPath(String testcaseName, AndroidDriver driver) throws IOException
	{
		File src=driver.getScreenshotAs(OutputType.FILE);
		String loc=System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
		File dest=new File(loc);
		FileUtils.copyFile(src, dest);
		return loc;
	}
}
