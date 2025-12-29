package seleniumFramework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumFramework.generic.BaseClass;

public class OrderConfirmationPage extends BaseClass{
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//driver.findElement(By.cssSelector("tbody tr.ng-star-inserted td.em-spacer-1"));
	
	@FindBy(css="tbody tr.ng-star-inserted td.em-spacer-1")
	WebElement orderId;
	
	
	//driver.findElement(By.cssSelector(".hero-primary"));
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String getorderId() {
		
		return orderId.getText();
	}

	public String getConfirmationMessage() {
		
		return message.getText();
		
	}
}
