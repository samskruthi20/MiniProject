package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.generic.BaseClass;

public class OrderHistoryPage extends BaseClass{
	
	WebDriver driver;
	
	 public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	 @FindBy(css ="tr td:nth-child(3)")
	 private List<WebElement> productNames;
		
		//driver.findElement(By.cssSelector(".totalRow button"))
		@FindBy(css=".totalRow button")
		private WebElement checkOutButton;
		
		
		public boolean verifyOrderDisplay(String productName) {
			
			Boolean match =  productNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
			return match;
		    
			
		}
		


	}
