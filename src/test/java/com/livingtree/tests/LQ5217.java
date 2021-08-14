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
public class LQ5217 extends ApplicationFuncs
{	

	@Test(testName="LA-14101",dataProvider="getExcelTestData",description ="Change \"Pinned\" posts to \"Saved\" posts")
	public void test_LQ5217(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			pinnedToSaved("Search for Saved filter in 'Show post type'");
			postPhoto(USERDIR+rowMap.get("PhotoPath"), rowMap.get("Subject"), rowMap.get("Message"), rowMap.get("GroupName"));
			logout();
			doLogin(rowMap.get("UserName1"),rowMap.get("Password"));
			pinToSave(rowMap.get("Subject"),"Save post feature");
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
