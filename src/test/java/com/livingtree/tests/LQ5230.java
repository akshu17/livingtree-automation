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
public class LQ5230 extends ApplicationFuncs
{	

	@Test(testName="LA-14128",dataProvider="getExcelTestData",description ="TC-UI/UX | As a user, when clicking another user's profile image, I should routed to his or her profile")
	public void test_LQ5230(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			postPhoto(USERDIR+rowMap.get("PhotoPath"),rowMap.get("Subject"),rowMap.get("Message"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			resharePostParentstoStudents(rowMap.get("Subject"),rowMap.get("Comment"),rowMap.get("GroupName"),rowMap.get("Message"));
			logout();
			doLogin(rowMap.get("Student"),rowMap.get("Password"));
			resharePostToProfile(rowMap.get("Subject"));
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
