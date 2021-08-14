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
public class LQ4344 extends ApplicationFuncs
{	

	@Test(testName="LA_12037",dataProvider="getExcelTestData",description ="Verify mute a post functionality from LT application")
	public void test_LQ4344(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			mutePost(subject);
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			String comment=getRandomString1(rowMap.get("Comment1"));
			likeAndCommentPost(subject, comment);
			logout();
			gmailLogin();
			noNotificationForMutePostFromMail(comment);
			
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),"LA12037");
		return dataBeans.iterator();
	}
}





