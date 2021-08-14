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
public class LQ5190 extends ApplicationFuncs
{	

	@Test(testName="LM913",dataProvider="getExcelTestData",description ="Improved Inbox/Messaging | I need to be able to search for inbox messages.")
	public void test_LQ5190(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			sentMessage(rowMap.get("Recipient1"), rowMap.get("Recipient2"), rowMap.get("Subject"), rowMap.get("Message"), rowMap.get("FilePath"));
			logout();			
			doLogin(rowMap.get("Username1"),rowMap.get("Password"));
			verifySearchInbox("Verify if Search box is present");
			searchReceivedMessage(rowMap.get("Subject"),"Search for sent message");
			searchByUsername("Search");
			searchByTitle("Search by title");
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
