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
public class LQ4893 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="LA-13533-TC-Inappropriate Content Filter: Add check to user posts to make sure title/content is appropriate")
	public void test_LQ4893(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			deleteAllPosts();
			//String subject=getRandomString1(rowMap.get("Subject"));
			postMessageOnlyParentsInapproiatecontent(rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName"),rowMap.get("error message"));
			navigate_to_conversation();
			logout();
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			postPhotoOnlyParentsInapproiatecontent(USERDIR+rowMap.get("PhotoPath"),rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName"),rowMap.get("error message"));
			navigate_to_conversation();
			logout();
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			postVideoOnlyParentsInapproiatecontent(USERDIR+rowMap.get("videoPath"),rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName"),rowMap.get("error message"));
			navigate_to_conversation();
			logout();
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			postFileOnlyParentsInapproiatecontent(USERDIR+rowMap.get("FilePath"),rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName"),rowMap.get("error message"));
			navigate_to_conversation();
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





