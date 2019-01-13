package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties 
{
       public static void  main(String args[]) throws IOException
       {
    	   Properties config=new Properties();
    	   Properties OR=new Properties();
    	   FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\config.properties");
    	   config.load(fis);
    	   
    	   System.out.println(config.getProperty("Browser"));
    	   
    	   fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\org.properties");
    	   OR.load(fis);
    	   System.out.println(OR.getProperty("btnBankMgr"));
    	   
    	   
    	   
       }
}
