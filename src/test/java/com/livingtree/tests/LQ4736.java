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
public class LQ4736 extends ApplicationFuncs
{	

	@Test(testName="LQ4736",dataProvider="getExcelTestData",description ="Edlio Engage User: Problem uploading profile photo")
	public void test_LQ4736(HashMap<String, String> rowMap) {
		try {			
			
			openLoginPage_Edlio();
			doLogin(rowMap.get("email"),rowMap.get("Password"));
			updateProfilePhoto(USERDIR+rowMap.get("PhotoPath"));
			
			
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