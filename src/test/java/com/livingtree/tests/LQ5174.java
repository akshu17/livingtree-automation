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
public class LQ5174 extends ApplicationFuncs
{	

	@Test(testName="LQ5174",dataProvider="getExcelTestData",description ="TC-PHP-7.4: Issue with likes notifying user for photo show.")
	public void test_LQ5174(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postPhoto(USERDIR+rowMap.get("PhotoPath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );	
			verifyPost(rowMap.get("Message"),"Post present");
			logout();
			doLogin(rowMap.get("UserName1"),rowMap.get("Password"));
			likeAndCommentPost(subject, rowMap.get("Comment"));
			logout();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			verifyLikes("Likes Verified");
			verifyNotificationForLikes(rowMap.get("Name"),"Verify Like notification");
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
