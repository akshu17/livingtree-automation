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
public class LQ4329_FilePost extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Verify the visibility of Engagement coefficient for a post (post score) in Conversations screen")
	public void test_LQ4329FilePost(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postFile(USERDIR+rowMap.get("FilePath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );		
			verifyPostFESScoreDisplayed(subject);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			verifyPostFESScoreNotDisplayed(subject);
			logout();
			doLogin(rowMap.get("StwoPParent"),rowMap.get("Password"));
			verifyPostFESScoreNotDisplayed(subject);
			logout();
			doLogin(rowMap.get("SonePParent"),rowMap.get("Password"));
			verifyPostFESScoreNotDisplayed(subject);			
			logout();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			verifyPostFESScoreDisplayed(subject);
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




