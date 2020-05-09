package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import helpers.ReportHelper;

@CucumberOptions(strict = true, monochrome = true, features = "src/test/resources/features", glue = {"stepdefinition","main"}, format = {"pretty","json:target/cucumber.json"}, tags = { "@test1" })

public class CucumberRunner extends AbstractTestNGCucumberTests {

	public static Properties config = null;
	public static WebDriver driver = null;
	

	public void LoadConfigProperty() throws IOException {
		config = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
		config.load(ip);
	}
	
	
	
//	@Before
//	public void print1(){
//		System.out.println("In before 1");
//	}
//	
//	@BeforeClass
//	public void print2(){
//		System.out.println("In before class");
//	}
//	
//	@BeforeMethod
//	public void print3(){
//		System.out.println("In before method");
//	}
//	
//	@BeforeSuite
//	public void print4(){
//		System.out.println("In before suite");
//	}
//	
//	
	@After
	public void print5(){
		System.out.println("In after");
	}
	
	@AfterSuite
	public void print6(){
		System.out.println("In after suite");
	}
	
	@AfterMethod
	public void print7(){
		System.out.println("In after method");
	}
	
	@AfterClass
	public void print8(){
		System.out.println("In after class");
	}	
	
	@Before
	public void setBrowser() throws Exception {
		LoadConfigProperty();		
		if(System.getProperty("os.name").startsWith("Windows")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
		if (config.getProperty("browserType").equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (config.getProperty("browserType").equals("chrome")) {
			driver = new ChromeDriver();
			System.out.println("Chrome driver set.");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String cal1 = dateFormat.format(cal.getTime());
		return cal1;
	}

//	@AfterClass(alwaysRun = true)
//	public void takeScreenshot() throws IOException {
//		System.out.println("In after class");
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		File trgtFile = new File(System.getProperty("user.dir") + "//screenshots/screenshot.png");
//		trgtFile.getParentFile().mkdir();
//		trgtFile.createNewFile();
//		Files.copy(scrFile, trgtFile);
//	}
//
//	@AfterMethod(alwaysRun = true)
//	public void tearDown(ITestResult result) throws IOException {
//		System.out.println("In after method");
//		if (!result.isSuccess()) {
//			File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			String failureImageFileName = result.getMethod().getMethodName()
//					+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
//			File failureImageFile = new File(System.getProperty("user.dir") + "//screenshots//" + failureImageFileName);
//			failureImageFile.getParentFile().mkdir();
//			failureImageFile.createNewFile();
//			Files.copy(imageFile, failureImageFile);
//		}
//	
//	}
	
}
