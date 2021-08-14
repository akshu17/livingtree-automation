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
public class LQ4328_FilePost extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Verify Overall Teacher Engagement Score")
	public void test_LQ4328FilePost(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postFile(USERDIR+rowMap.get("FilePath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );			
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			VerifyAllOption(subject);//add for LQ4082
			likeAndCommentPost(subject, rowMap.get("Comment1"));
			logout();			
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			likeAndCommentPost(subject, rowMap.get("Comment2"));	
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
			
			String subject2=getRandomString1(rowMap.get("Subject"));
			postFileOnlyParents(USERDIR+rowMap.get("FilePath"),subject2 ,rowMap.get("Message"),rowMap.get("GroupName") );			
			logout();
			doLogin(rowMap.get("SonePParent"),rowMap.get("Password"));
			likeAndCommentPost(subject2, rowMap.get("Comment1"));
			logout();			
			doLogin(rowMap.get("StwoSParent"),rowMap.get("Password"));
			likeAndCommentPost(subject2, rowMap.get("Comment2"));			
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			likeAndCommentPost(subject2, rowMap.get("Comment1"));
			//verifyPostFESScore(subject2, rowMap.get("ParentCountFES2"));
			//verifyFESScoreOnTeacherProfile(rowMap.get("TeacherFESScore"));
			logout();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));			
			//verifyFESScoreOnPrincipalProfile(rowMap.get("TeacherName"),rowMap.get("TeacherFESScore"));
			//change for checking
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





