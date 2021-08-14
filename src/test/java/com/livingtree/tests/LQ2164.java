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
public class LQ2164 extends ApplicationFuncs
{	

	@Test(testName="LQ2164",dataProvider="getExcelTestData",description ="Verify the Edit option icons in Actions column")
	public void test_LQ2164(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("User"),rowMap.get("Password"));
			//deleteAllPosts();
			Navigatetosupport();
			enableTestmoodeforschoolformsupport(rowMap.get("schoolname"));
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
	