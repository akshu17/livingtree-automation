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
public class LQ4292 extends ApplicationFuncs
{	

	@Test(testName="LA_11431",dataProvider="getExcelTestData",description ="Edit Post (text/photo/file).")
	public void test_LQ4292(HashMap<String, String> rowMap) {
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			rowMap.put("Subject", getRandomString(rowMap.get("PostSubject")));
			postPhoto(USERDIR+rowMap.get("PhotoFile"), rowMap.get("Subject"),rowMap.get("Message"));
			//rowMap.put("Subject2", rowMap.get("PostSubject")+getRandomString5());	
			editPostPhoto(USERDIR+rowMap.get("PhotoFile2"), rowMap.get("Subject"),rowMap.get("Message"));
			//gmailLogin(rowMap,(rowMap.get("Subject")));
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





