package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageUtils.PageUtils;

public class WebViewPage extends PageUtils
{
	AndroidDriver driver;
	public WebViewPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@FindBy(name ="q")
	private WebElement searchbox;
	@FindBy(xpath="//span[@class='yKd8Hd ob9lvb']")
	private WebElement site;
	
	public void search(String keyword) throws InterruptedException
	{
		searchbox.sendKeys(keyword);
		searchbox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
	public void moveBack()
	{
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

}
