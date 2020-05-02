package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class ElementPage {
	
	private WebDriver driver;
	
	@FindBy(xpath="//header/h3")
	public WebElement pageHeader;
	
	
	public ElementPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyHeader(String headerName){
		Assert.assertEquals(pageHeader.getText().trim(), headerName.trim());
    }
}
