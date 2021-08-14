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
public class LQ5188 extends ApplicationFuncs
{	
	@Test(testName="LQ5188",dataProvider="getExcelTestData",description ="when I click on another user's name link within a post, I should be routed to the inbox to compose a direct message")
	public void test_LQ5188(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("Subject"));
			postPhoto(USERDIR+rowMap.get("PhotoPath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );	
			logout();
			doLogin(rowMap.get("UserName1"),rowMap.get("Password"));
			verifyPost(rowMap.get("Message"),"Post present");
			navigateToInboxFromPost("Click on Poster's name");
			verifyInbox("Verify if the user is landed on Inbox page");
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
