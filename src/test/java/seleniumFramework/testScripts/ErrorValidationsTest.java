package seleniumFramework.testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumFramework.pageObjects.CheckOutPage;
import seleniumFramework.pageObjects.OrderConfirmationPage;
import seleniumFramework.pageObjects.OrderDetailsPage;
import seleniumFramework.pageObjects.ProductCatalog;
import seleniumFramework.testComponents.BaseTest;
import seleniumFramework.testComponents.Retry;

//Git test by Samskruthi

public class ErrorValidationsTest extends BaseTest{
	
	
		@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException {
		//Login page implementations
	    LandingPage.loginApplication("samsk@gmail.com", "Sam@1234567890");
	    String actualErrorMessage = LandingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", actualErrorMessage);
		
	}
		

		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException {

			
		//Login page implementations
		ProductCatalog productCatalog = LandingPage.loginApplication("samsk@gmail.com", "Sam@123456789");
		

		//productCatalog page implementations
		productCatalog.addProductToCart("ADIDAS ORIGINAL");
		OrderDetailsPage orderDetailsPage = productCatalog.goToCartPage();
    

        //OrderDetailsPage page implementations
        boolean match = orderDetailsPage.checkOutProceed("ADIDAS");
        Assert.assertFalse(match);
       
        
       
}
		

}