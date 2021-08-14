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
public class LQ3122 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Verify the Post with URL, Hyperlinks and Email IDs in it")
	public void test_LQ3122(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			visibilityPollPost(subject,true,"edit option is not available");
			checktext(subject);
			likeAndCommentPostedited(subject, rowMap.get("Comment1"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//visibilityPollPost(subject,true,"edit option is not available");
			//verifyPostFESScore(subject, rowMap.get("CountFES1"));
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			visibilityPollPost(subject,true,"edit option is not available");
			likeAndCommentPostedited(subject, rowMap.get("Comment2"));			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
			delete1stPost(subject);
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





