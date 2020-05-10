package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import main.CucumberRunner;

public class LanguagePage extends CucumberRunner{
	
	private WebDriver driver;
	
	@FindBy(xpath="//header/h3")
	public WebElement pageHeader;
	
	@FindBy(css=".toggle")
	public List<WebElement> toggleCTA;
	
	@FindBy(css=".new-todo")
	public WebElement todoPlaceholder;
	
	@FindBy(css=".clear-completed")
	public WebElement clearCompletedCTA;

	@FindBy(xpath="//ul[@class='todo-list']//label")
	public List<WebElement> todoList;
	
	@FindBy(xpath="//ul[@class='todo-list']//li[@class='completed']//label")
	public List<WebElement> completedElementList;
	
	@FindBy(xpath="//ul[@class='todo-list']//li[@class='hidden']//label")
	public List<WebElement> hiddenElementList;
	
	@FindBy(xpath="//ul[@class='todo-list']//li[@class='completed hidden']//label")
	public List<WebElement> completedHiddenElementList;
	
	@FindBy(xpath="//span[@class='todo-count']/strong")
	public WebElement todoCount;
	
	@FindBy(xpath="//label[@for='toggle-all']")
	public WebElement toggleAllCTA;
	
	int windowCount=0;
	List<WebElement> elementsList=new ArrayList<WebElement>();
	Set<String> set=new HashSet<String>();	
	ArrayList<String> tabs;
	
	public LanguagePage(){
		this.driver = CucumberRunner.driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyHeader(String headerName){
		Assert.assertEquals(pageHeader.getText().trim(), headerName.trim());
    }

	public void addEntries(int num) {
		for(int i=0;i<num;i++){
			todoPlaceholder.sendKeys(generateRandomString(10)+Keys.ENTER);
		}
		elementsList.addAll(todoList);
	}
	
	public void verifyEntryCount(int num){
		Assert.assertTrue(num==Integer.parseInt(todoCount.getText()));
	}
	
	
	public void clickToggleAllCTA(){
		toggleAllCTA.click();
	}
	
	public void verifyToggleAll(){
		for(WebElement element:todoList){
			Assert.assertTrue(element.getCssValue("text-decoration-line").equals("line-through"));
		}
	}
	
	public String generateRandomString(int length)
	{
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ?/.,~!@#$%^&*():'";
        String b = "";
        Random random = new Random();
        for(int i=0;i<length;i++){
        	char a= alphabet.charAt(random.nextInt(alphabet.length()));
        	b+=a;
        }
        return b;
	}

	public void navigateToTab(String tabName) {
		if(tabName.equals("Back"))
			driver.navigate().back();
		else{
		driver.findElement(By.xpath("//a[text()='"+tabName+"']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+tabName+"' and @class='selected']")).isDisplayed());
		}
	}

	
	public void verifyEntries(){
		elementsList.equals(todoList);
	}
	
	
	public void verifyEntriesAreHidden() {
		for(WebElement wb:hiddenElementList){
			Assert.assertFalse(wb.isDisplayed());
		}
		Assert.assertTrue(hiddenElementList.size()==Integer.parseInt(todoCount.getText()));
	}
	
	public void clearCompleted(){
		clearCompletedCTA.click();
	}
	
	public void strikePrimeElement(int num){
		
		boolean flag=false;
		for(int i=2;i<num;i++){
			for(int j=2;j<i;j++){
				if(i%j==0){
					flag=false;
					break;
				}
				else{
					flag=true;
				}
			}
			if(flag){
				toggleCTA.get(i-1).click();
			}
		}
		
		boolean flag2=false;
		for(int i=2;i<num;i++){
			for(int j=2;j<i;j++){
				if(i%j==0){
					flag2=false;
					break;
				}
				else{
					flag2=true;
				}
			}
			if(flag2){
				Assert.assertTrue(todoList.get(i-1).getCssValue("text-decoration-line").equals("line-through"));	
			}
		}
		
	}
	
	public void editEntry(){
		Actions action=new Actions(driver);
		int length=todoList.get(0).getText().length();
		action.doubleClick(todoList.get(0)).build().perform();
		for(int i=0;i<length;i++){
			driver.findElement(By.cssSelector(".edit")).sendKeys(Keys.BACK_SPACE);
		}	
		driver.findElement(By.cssSelector(".edit")).sendKeys("test1234"+Keys.ENTER);
		Assert.assertFalse(driver.findElement(By.cssSelector(".edit")).isDisplayed());
		Assert.assertEquals(todoList.get(0).getText(), "test1234");
	}
	
	public void closeBrowser(){
//		driver.close();
	}
	
	public void quit(){
		driver.quit();
	}

	public void verifyDestroyCTA() {
		List<WebElement> entries=new ArrayList<WebElement>();
		entries=driver.findElements(By.xpath("//ul[@class='todo-list']//label"));
		Actions act=new Actions(driver);
		act.moveToElement(todoList.get(0)).build().perform();
		driver.findElement(By.cssSelector(".destroy")).click();
		Assert.assertTrue(entries.get(1).getText().equals(todoList.get(0).getText()));
		
	}

	public void enterText(String text) {
		todoPlaceholder.sendKeys(text+Keys.ENTER);
	}
	
	public void verifyText(String text){
		if(todoList.size()==1){
			Assert.assertEquals(todoList.get(0).getText(), text);
		}
	}

	public void entertextminutes(int time) {
		long finish = System.currentTimeMillis() + (time*1000); 
		String s="";
		while (System.currentTimeMillis() < finish) {
			String c=generateRandomString(1);
		    todoPlaceholder.sendKeys(c);
		    s+=c;
		}
		todoPlaceholder.sendKeys(Keys.ENTER);
		Assert.assertEquals(s, todoList.get(0).getText());
	}

	public void clickLink(String link) {
		set.addAll(driver.getWindowHandles());
		windowCount=set.size();
		WebElement element = driver.findElement(By.xpath("//a[.='"+link+"']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		element.click();
	}
	
	public void verifyPage(String pageName){
//		Set<String> currentSet=new HashSet<String>();
//		currentSet.addAll(driver.getWindowHandles());
//		switch(pageName){
//		case "TodoMVC":
//			Assert.assertTrue(windowCount==set.size());
//			Assert.assertEquals(currentSet, set);
//			Assert.assertEquals(driver.getTitle(), pageName);
//			Assert.assertTrue(driver.getCurrentUrl().contains(pageName.toLowerCase()));
//			break;
//		case "let us know":	
//			Assert.assertTrue(windowCount==set.size());
//			Assert.assertEquals(currentSet, set);	
//			Assert.assertTrue(driver.getTitle().contains(pageName));
//			Assert.assertTrue(driver.getCurrentUrl().contains("github"));
//			break;
//		case "backbone.js":
//			Assert.assertTrue(driver.getTitle().contains(pageName));
//			Assert.assertTrue(driver.getCurrentUrl().contains("backbone"));
//			driver.close();
//			driver.switchTo().window(tabs.get(0));
//			break;
//		case "addyosmani":
//			Assert.assertTrue(driver.getTitle().contains(pageName));
//			Assert.assertTrue(driver.getCurrentUrl().contains("github"));
//			driver.close();
//			driver.switchTo().window(tabs.get(0));
//			break;
//		}
	}

	public void clickLinkNewTab(String link) throws InterruptedException, AWTException {
		Robot robot = new Robot();
		explicitWait(driver.findElement(By.xpath("//a[.='"+link+"']")));
		Actions act=new Actions(driver);
		WebElement wb=driver.findElement(By.xpath("//a[.='"+link+"']"));
		act.contextClick(wb).build().perform();
		robot.keyPress(KeyEvent.VK_DOWN); 
		robot.keyPress(KeyEvent.VK_ENTER); 
		Thread.sleep(2000);
		tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public void clickForDifferentWindow(String link) throws InterruptedException, AWTException {
		Robot robot = new Robot();              
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		WebElement wb=driver.findElement(By.xpath("//a[.='"+link+"']"));
		act.contextClick(wb).build().perform();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN); 
		robot.keyPress(KeyEvent.VK_ENTER); 
	}
}


