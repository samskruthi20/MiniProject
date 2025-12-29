package seleniumFramework.generic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFramework.pageObjects.OrderDetailsPage;
import seleniumFramework.pageObjects.OrderHistoryPage;
import seleniumFramework.pageObjects.ProductCatalog;

public class BaseClass {
	WebDriver driver;
	
	public BaseClass(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public void waitForElementToAppear(By findBy) {
		
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}

	
	public OrderDetailsPage goToCartPage() {
		cartHeader.click();
		OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
		return orderDetailsPage;
		
	}
	
	
	public OrderHistoryPage goToOrdersPage() {
		
		orderHeader.click();
		OrderHistoryPage orderHistoryPage  = new OrderHistoryPage(driver);
		return orderHistoryPage;
		
	}
	
	
	public void waitForWebElementToClick(WebElement element) {
		
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(element));
	
	}
	
	public void waitForElemetToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(2000);
	}

	
}
