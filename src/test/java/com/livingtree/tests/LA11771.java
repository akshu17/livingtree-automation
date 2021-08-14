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
public class LA11771 extends ApplicationFuncs
{	

	@Test(testName="Reporting",dataProvider="getExcelTestData",description ="Auto provisioning: Auto provisioning is failing when student and parent email is same in file")
	public void test_LA11771(HashMap<String, String> rowMap) 
	{
		try {
			SSHDriver driver=new SSHDriver();
			driver.SSHConnection();
			driver.recvData();
			//driver.sendCommand("ssh -i prodw1a.pem ubuntu@10.0.3.191");
			driver.sendCommand(rowMap.get("Server"));
			pause(1000);
			driver.recvData();
			driver.sendCommand(rowMap.get("CD"));
			pause(1000);
			driver.recvData();
			//driver.put("/tmplocal/testUpload.txt", "/tmpremote/testUpload.txt");  
			
			driver.sendCommand("sudo vi test.csv");
			pause(2000);
			driver.sendCommand("i");
			
			
			driver.recvData();
			driver.sendCommand(USERDIR+rowMap.get("Command"));
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
