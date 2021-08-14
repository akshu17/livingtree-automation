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
public class LQ4343 extends ApplicationFuncs
{	

	@Test(testName="LA_12037",dataProvider="getExcelTestData",description ="Verify the link in email notifications to mute a post")
	public void test_LQ4343(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			logout();
			//gmailLogin();
			//mutePostFromMail(subject);//Its checking for only Studenttwo785 two785 user
			openLoginPage();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			//likeAndCommentPost(subject, rowMap.get("Comment"));
			//unmutePost(subject);
			
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),"LA12037");
		return dataBeans.iterator();
	}
}





