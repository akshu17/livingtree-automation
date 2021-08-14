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
public class LQ4364 extends ApplicationFuncs
{	

	@Test(testName="LA_11957",dataProvider="getExcelTestData",description ="Verify whether posts appear for users in their selected language")
	public void test_LQ4364(HashMap<String, String> rowMap) {
		try {			
			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			//String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName") );
			logout();
			doLogin(rowMap.get("ParentName1"),rowMap.get("Password"));
			//verifyPostStransaltion(rowMap.get("Translate Subject"),rowMap.get("Translate Message"),rowMap.get("See Original"),rowMap.get("Subject"));
			logout();
			//gmailLogin();
			//verifyNotificationForPost(rowMap.get("Translate Subject"));
				
			

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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),"LA11957");
		return dataBeans.iterator();
	}
}





