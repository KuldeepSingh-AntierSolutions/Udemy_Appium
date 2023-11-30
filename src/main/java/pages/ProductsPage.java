package pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageUtils.PageUtils;

public class ProductsPage extends PageUtils
{
	AndroidDriver driver;
	public ProductsPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_back")
	private WebElement backButton;
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement pageName;
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCartButtons;
	
	public void goBackButton()
	{
		backButton.click();
	}
	public String getPageTitle()
	{
		String pageTitle=pageName.getAttribute("text");
		return pageTitle;
	}
	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public void addProductToCart(String productName)
	{
		scrollToElement(productName);
		int productCount=driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<productCount;i++)
		{
			String product=driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(product.equalsIgnoreCase("Converse All Star"))
			{
				addToCartButtons.get(i).click();
			}
		}
	}
	
	
}
