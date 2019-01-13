package com.w2a.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.Base.TestBase;
import com.w2a.utilities.TestUtil;

public class OpenAccountTest extends TestBase
{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	
	/*
	
	public void openAccountTest(String Customer,String Currency) throws InterruptedException
	{
		click("OpenAccount_CSS");
		select("Customer_CSS",Customer);
		select("Currency_CSS",Currency);
		click("Process_CSS");
		Thread.sleep(2000);
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
	}
	
	*/
	
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException
	{
		click("OpenAccount_CSS");
		select("Customer_CSS",data.get("Customer"));
		select("Currency_CSS",data.get("Currency"));
		click("Process_CSS");
		Thread.sleep(2000);
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
	}

	
}
