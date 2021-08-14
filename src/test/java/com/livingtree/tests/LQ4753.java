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
public class LQ4753 extends ApplicationFuncs
{	

	@Test(testName="LQ4753",dataProvider="getExcelTestData",description ="As a district or school admin, I need the ability to send welcome emails to users individually within my network")
	public void test_LQ4753(HashMap<String, String> rowMap) {
		try {			
			
			openLoginPage();
			doLogin(rowMap.get("School_admin"),rowMap.get("Password"));
			welcomemailtoindividually(rowMap.get("SchoolName"),rowMap.get("Lbllayer"),rowMap.get("ClassName"),rowMap.get("ParentName") );
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