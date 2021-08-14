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
public class LQ4747 extends ApplicationFuncs
{	

	@Test(testName="LQ4747",dataProvider="getExcelTestData",description ="Web: Video upload failed")
	public void test_LQ4747(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postVideo2(USERDIR+rowMap.get("VideoPath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			pause(120000);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			likeAndCommentPost(subject, rowMap.get("Comment1"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES1"));
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			likeAndCommentPostedited(subject, rowMap.get("Comment2"));			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
			//check for now
			//delete1stPost(subject);
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
		System.out.println(sheetname.split("_")[0]);
		return dataBeans.iterator();
	}
	
}





