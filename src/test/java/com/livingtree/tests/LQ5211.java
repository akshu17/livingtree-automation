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
@Listeners(CustomListener.class)
public class LQ5211 extends ApplicationFuncs
{	

	@Test(testName="LA-14169",dataProvider="getExcelTestData",description ="TC-Improved Inbox/Messaging | When I create a new inbox message, I need to be able to copy/paste a picture image.")
	public void test_LQ5219(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			sentMessageCopyImage(rowMap.get("Recipient1"),rowMap.get("Recipient2"),rowMap.get("Subject"),rowMap.get("Message"));
			
			
			//continue
			logout();
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname.split("_")[0]);
		return dataBeans.iterator();
	}
	
}
