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
public class LQ4266 extends ApplicationFuncs
{	

	@Test(testName="LA_11876",dataProvider="getExcelTestData",description ="Verify that the lion icon is removed from the auto-translated email notification for the created campaign")
	public void test_LQ4266(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			String s= getRandomString(rowMap.get("Title"));
			rowMap.put("Subject", s);
			createCampaign(rowMap.get("Subject"), rowMap.get("Amount"),rowMap.get("Description"),true);
			//gmailLogin(rowMap);
			//gmailLogin(rowMap.get("Subject"));
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




