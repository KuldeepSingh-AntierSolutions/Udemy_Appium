package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageUtils.PageUtils;

public class CartPage extends PageUtils
{
	AndroidDriver driver;
	public CartPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_back")
	private WebElement backButton;
	@AndroidFindBy(className ="android.widget.CheckBox")
	private WebElement checkbox;
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	@AndroidFindBy(id="android:id/button1")
	private WebElement closeTerms;
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	
	public void goBackButton()
	{
		backButton.click();
	}
	public void selectCheckbox()
	{
		checkbox.click();
	}
	public void readTermsAndConditions() throws InterruptedException
	{
		longPressElement(terms);
		Thread.sleep(1000);
		closeTerms.click();
	}
	public void proceedButton()
	{
		proceedButton.click();
	}

}
