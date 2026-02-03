package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumFramework.generic.BaseClass;

public class ProductCatalog extends BaseClass {

	WebDriver driver;
	

	public ProductCatalog(WebDriver driver) {
	
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}

	//List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']//h5/b"));
	@FindBy(css = ".mb-3")
	private List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	private WebElement spinner;
	
    
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");



    //getting list of products
     public List<WebElement> getProductList() 
     {
    	 waitForElementToAppear(productsBy);
    	 return products;
    }
     
     
     //getting required product by name
     public WebElement getProductByName(String productName) {
    	 
    	 WebElement prod =	getProductList().stream().filter(product->
 		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    	 
 		return prod;
     }
     
     //Adding required product to cart
     
     public void addProductToCart(String productName) throws InterruptedException {
    	 
    	 WebElement product = getProductByName(productName);
    	 product.findElement(addToCart).click();
    	 waitForElementToAppear(toastMessage);
    	 waitForElemetToDisappear(spinner);
     }
     
 
}
