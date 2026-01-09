package seleniumFramework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.generic.BaseClass;

public class LandingPage extends BaseClass {
	
	WebDriver driver;
	
	//initilising using constructor
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*   //using driver.findElement
	WebElement Email = driver.findElement(By.id("userEmail"));
	WebElement password = driver.findElement(By.id("userPassword"));
	WebElement loginbtn = driver.findElement(By.id("login"));
	*/
	
	
	//using PageFactory
	@FindBy(id="userEmail")
	private WebElement useremail;

	
	@FindBy(id="userPassword")
	private WebElement userpassword;
	
	@FindBy(id="login")
	private WebElement loginbtn;
	
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorMessage;
	
	
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		String actualErrorMessage = errorMessage.getText();
		return actualErrorMessage;
		
	}
	
	
	public ProductCatalog loginApplication(String Email, String password) {
		
		useremail.sendKeys(Email);
		userpassword.sendKeys(password);
		loginbtn.click();
		
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
		
		
	}
	
}
