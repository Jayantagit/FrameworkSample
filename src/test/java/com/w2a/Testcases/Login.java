package com.w2a.Testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.Base.TestBase;

public class Login extends TestBase
{
	@Test
	public void login() throws InterruptedException
	{
		log.debug("Login Started");
		
		//System.out.println(OR.getProperty("btnBankMgr"));
		//driver.findElement(By.cssSelector(OR.getProperty("btnBankMgr"))).click();
		click("btnBankMgr_CSS");
		Thread.sleep(1000);
		Assert.assertTrue(IsElementPresence(By.cssSelector(OR.getProperty("addCustbtn_CSS"))),"Login not Successful");
		log.debug("Login succcessfull");
		//Assert.fail("Login Failed");
		
	}

}
