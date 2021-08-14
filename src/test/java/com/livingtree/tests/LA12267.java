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
public class LA12267 extends ApplicationFuncs
{	

	@Test(testName="LA_12267",dataProvider="getExcelTestData",description ="When user update the post, Share option is disappearing at receivers end")
	public void test_LA12267(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName"));			
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			sharePost(subject, rowMap.get("GroupName"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String newSubject=getRandomString1(rowMap.get("Subject"));
			editMessagePost(subject, newSubject);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			sharePost(newSubject, rowMap.get("GroupName"));
			
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





