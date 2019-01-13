package com.w2a.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager 
{

	private static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		if(extent==null)
		{
			extent=new ExtentReports(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+"Extent.html",true,DisplayOrder.NEWEST_FIRST);
			//extent.loadConfig(configFile);
		}
		return extent;
	}
	
	
	
	
}
