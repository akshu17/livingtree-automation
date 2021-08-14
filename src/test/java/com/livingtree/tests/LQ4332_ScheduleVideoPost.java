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
public class LQ4332_ScheduleVideoPost extends ApplicationFuncs
{	

	@Test(testName="LA_12190",dataProvider="getExcelTestData",description ="Verify the scores on commenting a post")
	public void test_LQ4332ScheduleVideoPost(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postScheduleVideo(USERDIR+rowMap.get("VideoPath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );		
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			commentPost(subject, rowMap.get("Comment1"));			
			
			logout();			
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			calculateFESScoreForPost(subject);
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





