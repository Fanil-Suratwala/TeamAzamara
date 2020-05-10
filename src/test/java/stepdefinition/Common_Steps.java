package stepdefinition;

import java.awt.AWTException;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.LanguagePage;


public class Common_Steps {
	private CucumberRunner cucumberrunner=new CucumberRunner();
	private LanguagePage languagepage=new LanguagePage();

	@Given("^I launch \"(.*?)\" page$")
	public void launchURL(String url)  {			
			cucumberrunner.launchURL(url);
	}
	
	@Then("I should see \"(.*?)\" header")
	public void verifyHeader(String headerName){
		languagepage.verifyHeader(headerName);
	}

	@When("I enter \"(.*?)\" entries")
	public void enterEntries(int num){
		languagepage.addEntries(num);
	}

	@Then("I should see the count \"(.*?)\"")
	public void verifyEntryCount(int num){
		languagepage.verifyEntryCount(num);
	}

	@When("I click Toggle All CTA")
	public void verifyToggleAll(){
		languagepage.clickToggleAllCTA();
	}
	
	@Then("^I should see all entries Striked out$")
	public void verifyEntriesStrikedOut(){
		languagepage.verifyToggleAll();
	}
	
	@When("^I navigate to (All|Active|Completed|Back)(?: tab)?$")
	public void navigateTab(String tabName){
		languagepage.navigateToTab(tabName);
	}
	
	@Then("^I should see all entries$")
	public void verifyEntries(){
		languagepage.verifyEntries();
	}
	
	@Then("I should not see entries")
	public void emptyList() throws InterruptedException{
		languagepage.verifyEntriesAreHidden();
	}
	
	@Then("^I clear completed$")
	public void clearCompleted(){
		languagepage.clearCompleted();
	}
	
	@Then("^I verify every prime element is striked out of \"(.*?)\" entries$")
	public void stikeElements(String count){
		languagepage.strikePrimeElement(Integer.parseInt(count));
	}

	@Then("^I verify entry is editable$")
	public void editEntry(){
		languagepage.editEntry();
	}

	@Then("^I verify destroy CTA$")
	public void verifyDestroyCTA(){
		languagepage.verifyDestroyCTA();
	}
	
	
	@When("^I enter \"(.*?)\" text$")
	public void entertext(String text){
		languagepage.enterText(text);
	}
	
	@Then("^I should see \"(.*?)\" text$")
	public void verifytext(String text){
		languagepage.verifyText(text);
	}
	
	@Then("^I close Browser$")
	public void closeBrowser(){
		languagepage.closeBrowser();
	}
	
	@Then("^I quit Browser$")
	public void quitBrowser(){
		languagepage.quit();
	}
	
	@When("^I enter text for \"(.*?)\" seconds and verify$")
	public void entertextminutes(String time){
		languagepage.entertextminutes(Integer.parseInt(time));
	}
	
	@Then("^I should see page \"(.*?)\" on same tab$")
	public void verifyPageSameTab(String pageName){
		languagepage.verifyPage(pageName);
	}
	
	@Then("^I should see page \"(.*?)\" on new tab$")
	public void verifyNewTab(String pageName){
		languagepage.verifyPage(pageName);
	}
	
	@When("^I click the link \"(.*?)\"$")
	public void elementLink(String link){
		languagepage.clickLink(link);
	}
	
	@When("^I click the link \"(.*?)\" on new tab$")
	public void elementLinkNewTab(String link) throws InterruptedException, AWTException{
		languagepage.clickLinkNewTab(link);
	}
	
	@When("^I click the link \"(.*?)\" for different window$")
	public void linkForDifferentWindow(String link) throws InterruptedException, AWTException{
		languagepage.clickForDifferentWindow(link);
	}
	
	@When("^I should see page \"(.*?)\" on different window$")
	public void verifyDifferentTab(String link) throws InterruptedException, AWTException{
		languagepage.verifyPage(link);
	}
	
	
}
