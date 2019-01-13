package com.w2a.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.Base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase
{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	
	/*
	
	public void addCustomerTest(String firstName,String lastName,String postCode,String Alerttext,String runmode) throws InterruptedException
	{
		if(!runmode.equalsIgnoreCase("Y"))
		{
			
			throw new SkipException("Skipped the Test case with Test the Test Data with Runmore as 'N' ");
		}
		
		//driver.findElement(By.cssSelector(OR.getProperty("addCustbtn"))).click();
		click("addCustbtn_CSS");
		//driver.findElement(By.cssSelector(OR.getProperty("FirstName"))).sendKeys(firstName);
		SendKeys("FirstName_CSS", firstName);
		//driver.findElement(By.cssSelector(OR.getProperty("LastName"))).sendKeys(lastName);
		SendKeys("LastName_CSS", lastName);
		//driver.findElement(By.cssSelector(OR.getProperty("PostCode"))).sendKeys(postCode);
		SendKeys("PostCode_XPATH", postCode);
		
		//driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
		click("addbtn_CSS");
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(Alerttext));
		Thread.sleep(3000);
		alert.accept();
		Thread.sleep(3000);
	}

	*/
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException
	{
		if(!data.get("runmode").equalsIgnoreCase("Y"))
		{
			
			throw new SkipException("Skipped the Test case with Test the Test Data with Runmore as 'N' ");
		}
		
		//driver.findElement(By.cssSelector(OR.getProperty("addCustbtn"))).click();
		click("addCustbtn_CSS");
		//driver.findElement(By.cssSelector(OR.getProperty("FirstName"))).sendKeys(firstName);
		SendKeys("FirstName_CSS", data.get("firstName"));
		System.out.println(data.get("firstName"));
		//driver.findElement(By.cssSelector(OR.getProperty("LastName"))).sendKeys(lastName);
		SendKeys("LastName_CSS", data.get("lastName"));
		//driver.findElement(By.cssSelector(OR.getProperty("PostCode"))).sendKeys(postCode);
		SendKeys("PostCode_XPATH", data.get("postCode"));
		
		//driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
		click("addbtn_CSS");
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		Thread.sleep(3000);
		alert.accept();
		Thread.sleep(3000);
	}

}
