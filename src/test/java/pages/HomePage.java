package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;
	
    String elementXpath="//a[.='element']";
    
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
          
    public void clickOnElement(String elementName){
    	driver.findElement(By.xpath(elementXpath.replace("element", elementName))).click();
    }
}