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
public class LQ5275 extends ApplicationFuncs
{	

	@Test(testName="LA-14073",dataProvider="getExcelTestData",description ="TC-Web : While sharing the video post \"Type Message\" box along with content is displayed in Create Post screen.")
	public void test_LQ5275(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			postVideo(USERDIR+rowMap.get("VideoPath"),rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			reshareAsParents(rowMap.get("Subject"),rowMap.get("Comment"),rowMap.get("GroupName"),rowMap.get("Message"));
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
