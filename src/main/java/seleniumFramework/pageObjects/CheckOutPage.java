package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//driver.findElement(By.xpath("//div[contains(text(),'Card Number ')]/following-sibling::input"));
	
	@FindBy(xpath="//div[contains(text(),'Card Number ')]/following-sibling::input")
	private WebElement cardNumbertxtbx;
	
	
	//driver.findElement(By.xpath("//select[@class='input ddl'][1]"));
	
	@FindBy(xpath="//select[@class='input ddl'][1]")
	private WebElement monthtxtbx;
	
	//driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
	
	@FindBy(xpath="//select[@class='input ddl'][2]")
	private WebElement datetxtbx;
	
	//driver.findElement(By.xpath("//div[contains(text(),'CVV Code')]/following-sibling::input"));
	
	@FindBy(xpath="//div[contains(text(),'CVV Code')]/following-sibling::input")
	private WebElement cvvtxtbx;
	
	//driver.findElement(By.xpath("//div[contains(text(),'Name on Card')]/following-sibling::input"));
	
	@FindBy(xpath="//div[contains(text(),'Name on Card')]/following-sibling::input")
	private WebElement cardNametxtbx;
	
	//driver.findElement(By.xpath("//input[@placeholder='Select Country']"))
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement countrytxtbx;
	
	//driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	private List<WebElement> countrylist;
	
	//driver.findElement(By.cssSelector(".action__submit"));
	
	@FindBy(css=".action__submit")
	private WebElement placeOrderButton;
	
	
	
	
	 public void personalDetails(String number, String month, String date, String cvv,String holdername)
	 {
		
		cardNumbertxtbx.clear();
		cardNumbertxtbx.sendKeys(number);
		
		Select sm = new Select(monthtxtbx);
		sm.selectByVisibleText(month);
		
		Select sd = new Select(datetxtbx);
		sd.selectByVisibleText(date);
		
		cvvtxtbx.sendKeys(cvv);
		cardNametxtbx.sendKeys(holdername);
	}

	 
	 
	 
	 public void shippingDetails(String countryName, String expectedCountryName) 
	 {
		 
		 countrytxtbx.sendKeys(countryName);
		 
		 
			for(WebElement country : countrylist) {
				
				String actualCountryName = country.getText().trim();
				
				if(actualCountryName.equalsIgnoreCase(expectedCountryName)) {
					country.click();
					break;
					
				}

			} 
	 }
	 
	 public OrderConfirmationPage placeOrder() {
		 
		 placeOrderButton.click();
		 OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		 return orderConfirmationPage;
	 }
}
