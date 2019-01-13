package com.w2a.Listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.Base.TestBase;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.TestUtil;

public class CustomListener extends TestBase implements ITestListener,ISuiteListener
{
	public 	String messageBody;  
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) 
	{
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Cpaturing Screenshot");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Reporter.log("<a target=\"_blank\" href=\"D:\\Screenshot\\error.png\">Screenshot</a>");
		//Reporter.log("<a target=\"_blank\" href="+TestUtil.screenShotName+">Screenshot</a>");
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Failed with exception"+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenShotPath));
		
		
		
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenShotPath+">Screenshot</a>");
		System.out.println("<a target=\"_blank\" href="+TestUtil.screenShotPath+">Screenshot</a>");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+"Test Skipped");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestStart(ITestResult result) 
	{
		test=rep.startTest(result.getName().toUpperCase());
		System.out.println("Method Name="+result.getName());
		
		if(!TestUtil.isRunnable(result.getName(), excel))
		{
			System.out.println(TestUtil.isRunnable(result.getName(), excel));
			throw new SkipException("Skipped the test "+result.getName().toUpperCase());
		}
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		
		test.log(LogStatus.PASS, result.getName().toUpperCase()+"Passed");
		rep.endTest(test);
		rep.flush();
		
	}

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
		//MonitoringMail mail=new MonitoringMail();
		MonitoringMail mail = new MonitoringMail();
		 
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenLiveProject/Extent_Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
