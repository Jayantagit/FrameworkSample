package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.Base.TestBase;

public class TestUtil extends TestBase
{
    public static String screenShotPath;
    public static String screenShotName;
    
    
	public static void captureScreenshot() throws IOException
	{
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d=new Date();
		screenShotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		//screenShotName="error.jpg";
		screenShotPath=System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenShotName;
		System.out.println(screenShotPath);
		
		//FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenShotName));
		FileUtils.copyFile(srcFile, new File(screenShotPath));
	}
	
	
@DataProvider(name="dp")

/*
	
	public  Object[][] getData(Method m)
	{
		String sheetname=m.getName();
		int rows=excel.getRowCount(sheetname);
		int cols=excel.getColumnCount(sheetname);
		Object [][] data=new Object[rows][cols];
		System.out.println(rows+" "+cols);
		
		for(int rownum=1;rownum<=rows;rownum++)
		{
			for(int colnum=0;colnum<cols;colnum++)
			{
				data[rownum-1][colnum]=excel.GetCellData(sheetname,rownum,colnum);
			}
		}
		
		return data;
	}
	*/
public  Object[][] getData(Method m)
{
	String sheetname=m.getName();
	int rows=excel.getRowCount(sheetname);
	int cols=excel.getColumnCount(sheetname);
	Object [][] data=new Object[rows][1];
	System.out.println(rows+" "+cols);
	
	Hashtable<String,String> table=null;
	
	for(int rownum=1;rownum<=rows;rownum++)
	{
		table=new Hashtable<String,String>();
		for(int colnum=0;colnum<cols;colnum++)
		{
			table.put(excel.GetCellData(sheetname, 0, colnum), excel.GetCellData(sheetname, rownum, colnum));
			data[rownum-1][0]=table;
		}
	}
	
	return data;
}
       
   public static boolean isRunnable(String testname,ExcelReader excel)
   {
	   String sheetName="TestSuite";
	   int rowcnt=excel.getRowCount("TestSuite");
	   System.out.println(rowcnt);
		   
	   for(int rowcount=1;rowcount<=rowcnt;rowcount++)
	   {
		   String testCase=excel.GetCellData(sheetName, rowcount,0 );
		   System.out.println("Is Runnable Test Name"+testCase);
		  
		   if(testCase.equalsIgnoreCase(testname)) 
		   {
			   String runMode=excel.GetCellData(sheetName, rowcount,1);
			   System.out.println(runMode);
			   
			   if(runMode.equalsIgnoreCase("Y"))
			   
				   return true;			   
			   else
			   
				   return false;
			   
		   }
	   }
	   
	   
	   return false;
	   
   }
     
	
}
