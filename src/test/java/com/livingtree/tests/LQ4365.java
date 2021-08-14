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
public class LQ4365 extends ApplicationFuncs
{	

	@Test(testName="LA_11957",dataProvider="getExcelTestData",description ="Verify whether posts appear for users in their selected language when the post is updated")
	public void test_LQ4365(HashMap<String, String> rowMap) {
		try {			
			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			String newSubject=getRandomString1(rowMap.get("Subject"));			
			editMessagePost(subject, newSubject);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			verifyPostStransaltion(rowMap.get("Translate Subject"),rowMap.get("Translate Message"),rowMap.get("See Original"),newSubject);
			logout();
			gmailLogin();
			verifyNotificationForPost(rowMap.get("Translate Subject"));
			
			
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





