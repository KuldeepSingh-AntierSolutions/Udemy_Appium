package tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.ProductsPage;
import pages.WebViewPage;
import testUtils.BaseTest;

public class OrderFlow extends BaseTest
{
	@Test(priority = 1,enabled=true, groups="errorsTest")
	public void toVerifyFillingIncorrectDetails() throws InterruptedException
	{
		firstPage.selectCountry("Australia");
		//*****skipped name field********
		firstPage.selectGender("female");
		firstPage.clickOnShopButton();
		String toastMessage=firstPage.getToastMessage();
		Assert.assertEquals(toastMessage,"Please enter your name");
	}
	@Test(enabled=true,priority = 2,dataProvider = "getData", groups="positiveTest")
	public void toVerifyFillingCorrectDetails(HashMap<String,String> formData)
	{
		firstPage.selectCountry(formData.get("country"));
		firstPage.setName(formData.get("name"));
		firstPage.selectGender(formData.get("gender"));
		firstPage.clickOnShopButton();
	}
	@Test(priority=3,enabled=true)
	public void addProductToCart() throws InterruptedException
	{
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.addProductToCart("Converse All Star");
		Thread.sleep(500);
		productsPage.goToCartPage();
	}
	@Test(priority=4,enabled=true)
	public void proceedToCheckout() throws InterruptedException
	{
		CartPage cartPage=new CartPage(driver);
		cartPage.selectCheckbox();
		cartPage.readTermsAndConditions();
		cartPage.proceedButton();
		Thread.sleep(5000);
	}
	@Test(priority=5,enabled=true)
	public void makeSearch() throws InterruptedException
	{
		switchToWeb();
		WebViewPage webViewPage=new WebViewPage(driver);
		webViewPage.search("rahul shetty academy");
		webViewPage.moveBack();
		switchToNative();
		Thread.sleep(3000);
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//send test data  to the tests directly
//		return new Object[][] {{"Australia","Amit","Male"}};
		//send test data from json file
		String jsonFilePath=System.getProperty("user.dir")+"\\\\src\\\\test\\\\java\\\\testData\\\\formDetails.json";
		List<HashMap<String,String>> formData=readJsonFile(jsonFilePath);
		return new Object[][] {{formData.get(0)}};
	}
	
	
}
