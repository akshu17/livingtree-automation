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
public class LQ4726 extends ApplicationFuncs
{	

	@Test(testName="LQ4726",dataProvider="getExcelTestData",description ="Edlio Engage User: Problem uploading profile photo")
	public void test_LQ4726(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("TeacherName"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("eventType"));
			createEventPast_date(subject,rowMap.get("location"),rowMap.get("Descrpt"),false);
			logout();
			doLogin(rowMap.get("ParentName1"),rowMap.get("Password"));
			verifycalenderall(subject);
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