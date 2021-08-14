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
public class LQ4759 extends ApplicationFuncs
{	

	@Test(testName="LQ4759",dataProvider="getExcelTestData",description ="High CPU issue in production environment => the group filter is not working")
	public void test_LQ4759(HashMap<String, String> rowMap) {
		try {			
			
			openLoginPage();
			doLogin(rowMap.get("User"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("Subject"));
			postPhotoOnlyParents2(USERDIR+rowMap.get("PhotoPath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			validatewithparentgroup(subject);
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