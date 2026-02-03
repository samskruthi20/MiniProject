package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumFramework.generic.BaseClass;

public class OrderDetailsPage extends BaseClass{
	
	WebDriver driver;
	
	public OrderDetailsPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	

	//driver.findElements(By.cssSelector(".cartSection h3"));
	
	@FindBy(css =".cartSection h3")
	private List<WebElement> cartProductList;
	
	//driver.findElement(By.cssSelector(".totalRow button"))
	@FindBy(css=".totalRow button")
	private WebElement checkOutButton;
	
	
	public boolean checkOutProceed(String productName) {
		
		boolean match =  cartProductList.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	    return match;
		
	}
	
	public CheckOutPage clickCheckOut() {
		
		checkOutButton.click();
		
		CheckOutPage  checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
