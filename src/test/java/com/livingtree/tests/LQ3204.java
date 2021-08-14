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
public class LQ3204 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="If parent is having two children ,remove one child and post a message to parents from district head")
	public void test_LQ3204(HashMap<String, String> rowMap)
	{
		try {			
			openLoginPage();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			addTeacherinGroup(rowMap.get("Schoolname"),rowMap.get("Teacher"));
			logout();
			login(rowMap.get("Teacher"),rowMap.get("Password"));
			acceptinvitationTeacher(rowMap.get("GroupName"));
			skipGroupPopupTeacher();
			visibiltyofgroupinTeacher(rowMap.get("GroupName"));
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
