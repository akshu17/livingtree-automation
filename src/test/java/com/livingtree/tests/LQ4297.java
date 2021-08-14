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
public class LQ4297 extends ApplicationFuncs
{	

	@Test(testName="LA_11431",dataProvider="getExcelTestData",description ="Verify that the post is edited by only owner")
	public void test_LQ4297(HashMap<String, String> rowMap) {
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			//rowMap.put("Subject", getRandomString(rowMap.get("PostSubject")));
			postMessage(rowMap.get("PostSubject") ,rowMap.get("Message"),rowMap.get("Group") );
			logout();
			doLogin(rowMap.get("UserName2"),rowMap.get("Password"));
			//scrollToup();
			pause(2000);
			checkPostNotEditable(rowMap.get("Group"),rowMap.get("Subject"));
			logout();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			scrollToup();
			pause(2000);
			editPostText( rowMap.get("PostSubject"),rowMap.get("Message"));		
			logout();
		} 
		catch (Exception e)
		{
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





