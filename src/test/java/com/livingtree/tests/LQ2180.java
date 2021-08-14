package com.livingtree.tests;

import java.lang.reflect.Method;
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
public class LQ2180 extends ApplicationFuncs
{	

	@Test(testName="LQ2180",dataProvider="getExcelTestData",description ="User should be able to register as Youth org Admin")
	public void test_LQ2180(HashMap<String, String> rowMap,Method method) {
		try {
			openLoginPage();
			doLogin(rowMap.get("user"),rowMap.get("Password"));
			verifyphonenumbererrormessage(rowMap.get("newphonenoinvalid"),rowMap.get("excepted_error_message"),rowMap.get("newphonenovalid"));
			
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
