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
import utils.DateAndTime;
import utils.ExcelTestDataReader;
@Listeners(CustomListener.class)
public class LA11829 extends ApplicationFuncs
{	

	@Test(testName="LA11829",dataProvider="getExcelTestData",description ="Web API: Calendar events need to support image upload")
	public void test_LA11829(HashMap<String, String> rowMap,Method method) throws Exception
	{
		try {
			
			String token = loginPost(rowMap.get("UserName"),rowMap.get("Password"));
			rowMap.put("auth_token",token);
			String startdate=DateAndTime.getFutureDateTime24(1);
			pause(3000);
			rowMap.put("start_time",startdate);
			String enddate=DateAndTime.getFutureDateTime24(1);
			rowMap.put("end_time",enddate);
			rowMap.put("title",getRandomString("API"));
			
			apiPost(rowMap);
			
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
