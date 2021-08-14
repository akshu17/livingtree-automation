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
public class LQ4127 extends ApplicationFuncs
{	

	@Test(testName="LQ4127",dataProvider="getExcelTestData",description ="Verify the Sign up with Coach")
	public void test_LQ4127(HashMap<String, String> rowMap,Method method) {
		try {
			openLoginPage();
			signUp();
			coachAccount();
			String email=getRandomString1(rowMap.get("Email"));
			profileInformation(email, rowMap.get("Password"), rowMap.get("ConfirmPassword"), rowMap.get("FirstName"), rowMap.get("LastName"), rowMap.get("Year"),rowMap.get("Month"),rowMap.get("Date"), rowMap.get("ZipCode"),
					rowMap.get("Address"), rowMap.get("City"), rowMap.get("State"), rowMap.get("Country"), rowMap.get("TimeZone"), rowMap.get("Gender"), rowMap.get("Telephone"));
			groupNext();
			groupName(rowMap.get("Group Name"));
			skipGroupPopup();
			
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
