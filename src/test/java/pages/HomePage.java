package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import main.CucumberRunner;

public class HomePage extends CucumberRunner{

	private WebDriver driver;
	
    String elementXpath="//a[.='element']";
    
	public HomePage(){
		this.driver = CucumberRunner.driver;
		PageFactory.initElements(driver, this);
	}
	
          
    public LanguagePage selectLanguage(String elementName){
    	driver.findElement(By.xpath(elementXpath.replace("element", elementName))).click();
    	return new LanguagePage();
    }
}