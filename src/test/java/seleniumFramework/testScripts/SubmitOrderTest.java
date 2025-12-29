package seleniumFramework.testScripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFramework.pageObjects.OrderDetailsPage;
import seleniumFramework.pageObjects.OrderHistoryPage;
import seleniumFramework.pageObjects.CheckOutPage;
import seleniumFramework.pageObjects.LandingPage;
import seleniumFramework.pageObjects.OrderConfirmationPage;
import seleniumFramework.pageObjects.ProductCatalog;
import seleniumFramework.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{


	    String productName = "ZARA COAT 3";
		String number = "1234567890";
		String month = "11";
		String date = "08";
		String cvv = "458";
		String holdername = "SAMSKRUTHI";
		String countryName = "neth";
		String expectedCountryName = "Netherlands Antilles";
		String expectedMessage = "Thankyou for the order.";

		
		@Test(dataProvider="getData", groups="Purchase")
		public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		//Login page implementations
		ProductCatalog productCatalog = LandingPage.loginApplication(input.get("Email"), input.get("password"));
		

		//productCatalog page implementations
		productCatalog.addProductToCart(input.get("productName"));
		OrderDetailsPage orderDetailsPage = productCatalog.goToCartPage();
    

        //OrderDetailsPage page implementations
        boolean match = orderDetailsPage.checkOutProceed(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage  checkOutPage = orderDetailsPage.clickCheckOut();
        
        
        //CheckoutPage page implementations
        checkOutPage.personalDetails(number, month, date, cvv, holdername);
        checkOutPage.shippingDetails(countryName, expectedCountryName);
        OrderConfirmationPage orderConfirmationPage = checkOutPage.placeOrder();
        

         //OrderConfirmation page implementations
         String orderId = orderConfirmationPage.getorderId();
         System.out.println(orderId);
         
         String confirmMessage = orderConfirmationPage.getConfirmationMessage();
         Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMessage));
}
		

		@Test(dependsOnMethods = {"SubmitOrder"} )
        public void OrderHistoryTest() {
	
			ProductCatalog productCatalog = LandingPage.loginApplication("samsk@gmail.com", "Sam@123456789");
			OrderHistoryPage orderHistoryPage  = productCatalog.goToOrdersPage();
			
			Assert.assertTrue(orderHistoryPage.verifyOrderDisplay(productName));
	
             }
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/seleniumFramework/testdata/PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
			
			
			/*HashMap<String,String> map = new HashMap<String,String>();
			map.put("Email", "samsk@gmail.com");
			map.put("password", "Sam@123456789");
			map.put("productName", "ZARA COAT 3");
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("Email", "samskr@gmail.com");
			map1.put("password", "Sam@123456789");
			map1.put("productName", "ADIDAS ORIGINAL");*/
			
		}

}