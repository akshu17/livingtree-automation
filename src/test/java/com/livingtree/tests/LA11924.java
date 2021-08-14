package com.livingtree.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.livingtree.Web.ApplicationFuncs;

import controllers.InitMethod;
import listeners.CustomListener;
import utils.ConfigReader;
import utils.ExcelTestDataReader;
import utils.SSHDriver;
@Listeners(CustomListener.class)
public class LA11924 extends ApplicationFuncs
{	

	@Test(testName="Reporting",dataProvider="getExcelTestData",description ="Changes to reporting columns required for support team")
	public void test_LA11924(HashMap<String, String> rowMap) 
	{
		try {
			SSHDriver driver=new SSHDriver();
			driver.SSHConnection();
			driver.sendCommand(rowMap.get("Server"));
			pause(1000);
			driver.recvData();
			driver.sendCommand(rowMap.get("CD"));
			pause(1000);
			driver.recvData();
			driver.sendCommand(rowMap.get("Command"));
			pause(7000);
		//	driver.sendCommand("")
			driver.recvData();
			//System.out.println(driver.recvData());
			driver.close();		
			gmailLogin(rowMap.get("Subject"));			
			//logout();
			} catch (Exception e) {
				InitMethod.ErrorMsg = e.getMessage();
				Assert.fail(e.getMessage());
			}
	}
	
	@DataProvider
	public Iterator<Object[]> getExcelTestData() 
	{
		String sheetname = this.getClass().getSimpleName();
		ExcelTestDataReader excelReader = new ExcelTestDataReader();
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname);
		return dataBeans.iterator();
	}
}
