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
public class LQ4727 extends ApplicationFuncs
{	

	@Test(testName="LQ4727",dataProvider="getExcelTestData",description ="Non-admin user should not be allowed to create a scheduled post")
	public void test_LQ4727(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			check_secdule_post(true);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			check_secdule_post(false);
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			check_secdule_post(false);			
			logout();
			doLogin(rowMap.get("SonePParent"),rowMap.get("Password"));
			check_secdule_post(false);
			logout();
			doLogin(rowMap.get("StwoSParent"),rowMap.get("Password"));
			check_secdule_post(false);			
			logout();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			check_secdule_post(true);
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





