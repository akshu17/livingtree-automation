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
public class LQ1057 extends ApplicationFuncs
{	

	@Test(testName="LQ1057",dataProvider="getExcelTestData",description ="Verify the Sign up with Student")
	public void test_LQ1057(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			signUp();
			studentAccount();
			String email =getRandomEmail(rowMap.get("Email"));
			profileInformation(email, rowMap.get("Password"), rowMap.get("ConfirmPassword"), rowMap.get("FirstName"), rowMap.get("LastName"), rowMap.get("Year"),rowMap.get("Month"),rowMap.get("Date"), rowMap.get("ZipCode"),
					rowMap.get("Address"), rowMap.get("City"), rowMap.get("State"), rowMap.get("Country"), rowMap.get("TimeZone"), rowMap.get("Gender"), rowMap.get("Telephone"));
			studentNext();
			skipGroupPopup();
			logout();
			//doLogin(email, rowMap.get("Password"));
			//logout();
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
