package stepdefinition;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.ElementPage;
import pages.HomePage;

public class Common_Steps extends CucumberRunner {

	HomePage homepage=new HomePage(driver);
	ElementPage elementpage=new ElementPage(driver);
	
	@Given("^I am on \"(.*?)\" page$")
	public void verifyPageTitle(String text) throws Throwable {
		Assert.assertTrue(driver.getTitle().contains(text),"Incorrect Webpage");		
	}
	
	@When("I click on \"(.*?)\" element")
	public void clickOnElement(String elementName){
		homepage.clickOnElement(elementName);
	}
	
	@Then("I should see \"(.*?)\" header")
	public void verifyHeader(String headerName){
		elementpage.verifyHeader(headerName);
	}
}
