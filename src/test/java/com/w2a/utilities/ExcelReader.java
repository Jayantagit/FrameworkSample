package com.w2a.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader 
{
	
	public String path;
	public FileInputStream fis=null;
	public FileOutputStream fileout=null;
	private XSSFWorkbook workbook=null;
	private XSSFSheet sheet=null;
	private XSSFRow row=null;
	private XSSFCell cell=null;
	
	public ExcelReader(String path)
	{
		this.path=path;
		try
		{
			fis=new FileInputStream(path);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheetAt(0);
			fis.close();
		}
		catch(Exception e)
		{
			
			
			
			
		}
	}
	
	public int getRowCount(String sheetname)
	{
		sheet=workbook.getSheet(sheetname);
		System.out.println(sheet.getLastRowNum());
		return sheet.getLastRowNum();
	}
	
	public int getColumnCount(String sheetname)
	{
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(0);
		return row.getLastCellNum();
	}
	
	public String GetCellData(String sheetname,int rownum,int colnum)
	{
		sheet=workbook.getSheet(sheetname);
		cell=sheet.getRow(rownum).getCell(colnum);
		return cell.getStringCellValue();
		
	}

}
