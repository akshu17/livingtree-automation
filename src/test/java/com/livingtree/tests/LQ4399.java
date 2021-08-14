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
public class LQ4399 extends ApplicationFuncs
{	

	@Test(testName="LA_12044",dataProvider="getExcelTestData",description ="Verify the sent message at receiver's end")
	public void test_LQ4399(HashMap<String, String> rowMap) 
	{
		try {			
			openLoginPage();
			doLogin(rowMap.get("User"),rowMap.get("Password"));
			Self_service_add_grade(USERDIR+rowMap.get("FilePath"),rowMap.get("scholl_name"));
			logout();
			}
			 catch (Exception e) {
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