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
public class LQ5242 extends ApplicationFuncs
{	

	@Test(testName="LM-1016",dataProvider="getExcelTestData",description ="TC-Improved Inbox/Messaging | As an inbox mail sender, I need a BCC option/don't want recipients of my email to see other recipients")
	public void test_LQ5242(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
//			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
//			sentMessageToCCBCC(rowMap.get("Recipient1"),rowMap.get("ToCC"), rowMap.get("ToBCC"),rowMap.get("Subject"),rowMap.get("Message"));
//			logout();
			
			doLogin(rowMap.get("RecID"),rowMap.get("Password"));
			logStep("Logged in as: "+rowMap.get("RecID"));
			checkRec();
			pause(3000);
			logout();
			doLogin(rowMap.get("CCID"),rowMap.get("Password"));
			logStep("Logged in as: "+rowMap.get("CCID"));
			checkRec();
			pause(3000);
			logout();
			doLogin(rowMap.get("BCCID"),rowMap.get("Password"));
			logStep("Logged in as: "+rowMap.get("BCCID"));
			checkRec();
			pause(3000);
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
