package main;

import java.io.File;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(strict = true, monochrome = true, features = "src/test/resources/features",plugin={"html:target/cucumber-html-report"} ,glue = {"stepdefinition","main"}, format = {"pretty","json:target/cucumber.json"}, tags = { "@test1" })

public class CucumberRunner extends AbstractTestNGCucumberTests{

	public static Properties config = null;
	public static WebDriver driver = null;
	public static Logger log = LogManager.getLogger(CucumberRunner.class);
	
	@Before
	public void setBrowser() throws Exception {	
		String chromeDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		System.out.println("Chrome driver set.");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@After
	public void endTest(Scenario scenario) throws IOException{
		if(scenario.isFailed()){
			String failureImageFileName = currentDateTime();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File trgtFile = new File(System.getProperty("user.dir") + "//screenshots/screenshot"+failureImageFileName+".png");
			Files.copy(scrFile, trgtFile);
		}
		driver.quit();
	}
	
	public void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void launchURL(String url){
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public static String currentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		String cal1 = dateFormat.format(cal.getTime());
		return cal1;
	}
}
