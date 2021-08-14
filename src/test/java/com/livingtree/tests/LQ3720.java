package com.livingtree.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
public class LQ3720 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Verify ownership transfer")
	public void test_LQ3720(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			postVideo2(USERDIR+rowMap.get("VideoPath"),rowMap.get("Subject3") ,rowMap.get("Message"),rowMap.get("GroupName"));			
			logout();
			doLogin(rowMap.get("Userinvite1"),rowMap.get("Password"));
			delete1stPost(rowMap.get("Subject3"));
			pause(5000);
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









			
			
			
			
			
			//Owner 
			/*
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			likeAndCommentPost(subject, rowMap.get("Comment1"));
			logout();
			
			
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			likeAndCommentPost(subject, rowMap.get("Comment2"));			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
			
			String subject2=getRandomString1(rowMap.get("Subject"));
			postVideoOnlyParents(USERDIR+rowMap.get("VideoPath"),subject2 ,rowMap.get("Message"),rowMap.get("GroupName") );			
			logout();
			doLogin(rowMap.get("SonePParent"),rowMap.get("Password"));
			likeAndCommentPost(subject2, rowMap.get("Comment1"));
			logout();			
			doLogin(rowMap.get("StwoSParent"),rowMap.get("Password"));
			likeAndCommentPost(subject2, rowMap.get("Comment2"));			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject2, rowMap.get("ParentCountFES2"));
			//verifyFESScoreOnTeacherProfile(rowMap.get("TeacherFESScore"));
			logout();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));			
			//verifyFESScoreOnPrincipalProfile(rowMap.get("TeacherName"),rowMap.get("TeacherFESScore"));
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


*/
			


