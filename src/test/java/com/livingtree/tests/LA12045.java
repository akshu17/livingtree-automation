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
public class LA12045 extends ApplicationFuncs
{	
	@Test(testName="LA_12045",dataProvider="getExcelTestData",description ="Web UI: Fix invite new users screen")
	public void test_LA12045(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			inviteNewUser(rowMap.get("Invitee"),rowMap.get("Message"));
			
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname);
		return dataBeans.iterator();
	}
}





