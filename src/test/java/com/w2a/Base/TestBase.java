package com.w2a.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;

public class TestBase 
{
	public static WebDriver driver;
	//http://www.way2automation.com/angularjs-protractor/banking/#/login
	
	 public static  Properties config=new Properties();
	 public static   Properties OR=new Properties();
	 public static  FileInputStream fis;
	 public static WebDriverWait wait;
	 public static WebElement dropdown;
	 public static String browser;
	 
	// public static Logger log=Logger.getLogger("devpinoyLogger");
	 public static Logger log=Logger.getLogger("rootLogger");
	 public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\TestData.xlsx");
	 
	 
	 
	 public static ExtentReports rep=ExtentManager.getInstance();
	 public static ExtentTest test;

	@BeforeSuite
	public void setUp()
	{
		if(driver==null)
		{
			    try {
					fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	   try {
				config.load(fis);
				log.debug("Config File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	    	   System.out.println(config.getProperty("Browser"));
	    	   
	    	   try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\org.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   try
	    	   {
				OR.load(fis);
				log.debug("OR File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   System.out.println(OR.getProperty("btnBankMgr"));
	    	   
	    	   if(System.getenv("Browser")!=null && !System.getenv("Browser").isEmpty())
	    	   {
	    		   browser=System.getenv("Browser");
	    	   }
	    	   else
	    	   {
	    		   browser=config.getProperty("Browser");
	    	   }
	    	   
	    	   config.setProperty("Browser", browser);
	    		   
	    
	    	   
	    	   if(config.getProperty("Browser").equals("Chrome"))
	    	   {
	    		  // System.out.println(System.getProperty("user.dir")+"executables\\chromedriver.exe");
	    		   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
	    		   driver=new ChromeDriver();
	    		   System.out.println("Chrome Driver Luanched");
	    	   }
	    	   
	    	   driver.get(config.getProperty("TestsuiteURL"));
	    	   driver.manage().window().maximize();
	    	   driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("ImplicitTime")), TimeUnit.SECONDS);
	    	   wait=new WebDriverWait(driver,5);
	    	  
	    	   
	    	   
	    	   
		}
	}
	
	public boolean IsElementPresence(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		
	}
	
	public void click(String locator)
	{
		if(locator.endsWith("_CSS"))
		{			
		  driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		  
		}
		else if(locator.endsWith("_XPATH"))
		{
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on :"+locator);
	}
	
	public void SendKeys(String locator,String value)
	{
		if(locator.endsWith("_CSS"))
		{	
		   driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		   
		}  
		else if(locator.endsWith("_XPATH"))
		{
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Type in :"+locator +"Entered value as :"+value);
	}
	
	public void select(String locator,String value)
	{
		if(locator.endsWith("_CSS"))
		{	
		   dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
		   
		}  
		else if(locator.endsWith("_XPATH"))
		{
			dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
		}
		
		Select s=new Select(dropdown);
		s.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selected from :"+locator +"value as :"+value);
	}
	
	
	
	@AfterSuite
	public void TearDown()
	{
		if(driver !=null)
		{
			driver.quit();
		    log.debug("Driver Closed");
		}
	}
}
