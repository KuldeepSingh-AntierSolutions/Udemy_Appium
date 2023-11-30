package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageUtils.PageUtils;

public class FirstPage extends PageUtils
{
	AndroidDriver driver;
	
	public FirstPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement name;
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement radioFemale;
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement radioMale;
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	public void selectCountry(String countryName)
	{
		countryDropdown.click();
		scrollToElement(countryName);
		WebElement userCountry=driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\""+countryName+"\"]"));
		userCountry.click();
	}
	public void setName(String userName)
	{
		name.click();
		name.sendKeys(userName);
		driver.hideKeyboard();
	}
	public void selectGender(String gender)
	{
		if(!gender.equalsIgnoreCase("male"))
			radioFemale.click();
	}
	public ProductsPage clickOnShopButton()
	{
		shopButton.click();
		return new ProductsPage(driver);
	}
	public String getToastMessage() throws InterruptedException
	{
		WebElement toaster=driver.findElement(AppiumBy.xpath("//android.widget.Toast"));
		String toastMessage=toaster.getAttribute("name");
		return toastMessage;
	}
	




}
