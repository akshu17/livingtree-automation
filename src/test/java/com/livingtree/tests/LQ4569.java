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
public class LQ4569 extends ApplicationFuncs
{	

	@Test(testName="LA_12796",dataProvider="getExcelTestData",description ="SMS Alert Error")
	public void test_LQ4569(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			//deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postAddTextMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName"));			
			logout();
			//doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			//deleteAllPosts();
			//String subject=getRandomString1(rowMap.get("Subject"));
			//String subjectEdit=getRandomString1(rowMap.get("Subject"));
			//editschedulePostFileRemoveTimephone_alert(subject,rowMap.get("Message"),rowMap.get("GroupName"),subjectEdit);
			//postSchedulePhone(subject ,rowMap.get("Message"),rowMap.get("GroupName") );			
			//logout();
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			likeAndCommentPost(subject, rowMap.get("Comment1"));
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





