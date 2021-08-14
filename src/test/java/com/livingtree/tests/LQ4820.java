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
public class LQ4820 extends ApplicationFuncs
{	

	@Test(testName="LQ4820",dataProvider="getExcelTestData",description ="Web: On de-selecting schedule option for a schedule post during edit of a schedule post, the post still displayed as scheduled")
	public void test_LQ4820(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("Subject"));
			postScheduleFile2(USERDIR+rowMap.get("FilePath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String subjectEdit=getRandomString1(rowMap.get("Subject"));
			editschedulePostFile(subject,rowMap.get("Message"),rowMap.get("GroupName"),subjectEdit);			
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			likeAndCommentPost(subjectEdit, rowMap.get("Comment1"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES1"));
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			likeAndCommentPost(subjectEdit, rowMap.get("Comment2"));			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
			delete1stPost(subject);
			logout();
			//getWebDriver().quit();
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





