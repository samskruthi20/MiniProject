package seleniumFramework.testScripts;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFramework.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		String productName = "ZARA COAT 3";
		String Email = "samsk@gmail.com";
		WebDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage LandingPage = new LandingPage(driver);
		
		
		driver.findElement(By.id("userEmail")).sendKeys(Email);
		driver.findElement(By.id("userPassword")).sendKeys("Sam@123456789");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/h5/b")));
		
		
		List<WebElement> products = driver.findElements(By.xpath("//div/div/div/div/h5/b"));
		
		//using for-each loop 
		for(WebElement product : products) {
			
			
			String text = product.getText();
			
			if(text.equals(productName)) {
	
		    product.findElement(By.xpath("//div/button[contains(text(),' Add To Cart')]")).click();
			
		}
	}
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		driver.findElement(By.xpath("//li/button/i[@class='fa fa-shopping-cart']")).click();

		
		
		List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
		
		
		//using streams
		boolean match =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		driver.findElement(By.xpath("//div[contains(text(),'Card Number ')]/following-sibling::input")).clear();
		driver.findElement(By.xpath("//div[contains(text(),'Card Number ')]/following-sibling::input")).sendKeys("1234567890");
		
		WebElement month = driver.findElement(By.xpath("//select[@class='input ddl'][1]"));
		WebElement date = driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
		
		Select s = new Select(month);
		s.selectByVisibleText("12");
		
		Select s1 = new Select(date);
		s1.selectByVisibleText("17");
		
		
		driver.findElement(By.xpath("//div[contains(text(),'CVV Code')]/following-sibling::input")).sendKeys("123");
		driver.findElement(By.xpath("//div[contains(text(),'Name on Card')]/following-sibling::input")).sendKeys("SAMSKRUTHI");
		//driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("FIRST");
		
		
		//driver.findElement(By.cssSelector(".ng-touched.ng-dirty")).sendKeys(Email);
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
	
		
		List<WebElement> countrys  = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		
		for(WebElement country : countrys) {
			
			String text = country.getText();
			
			if(text.equalsIgnoreCase("india")) {
				
				country.click();
				break;
				
			}

		}
		
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
		
		System.out.println("order id = " + driver.findElement(By.cssSelector("tbody tr.ng-star-inserted td.em-spacer-1")).getText());
		String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
}}