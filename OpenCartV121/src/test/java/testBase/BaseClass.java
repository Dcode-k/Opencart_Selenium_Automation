package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public Logger logger;//log4j

	public WebDriver driver;
	
	public Properties p;
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException
   {
		//Loading config.properties file
		FileReader file=new FileReader(".src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		switch(browser.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox" : driver=new FirefoxDriver();break;
		default : System.out.println("Invalid Browser Name");return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL2"));//reading url from properties file
		driver.manage().window().maximize();
	}
	
//	@AfterClass
//	public void tearDown()
//	{
//		driver.close();
//	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public void scrollToBottomOfPage() {
		//to perform scroll on an application using Selenium

		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
}