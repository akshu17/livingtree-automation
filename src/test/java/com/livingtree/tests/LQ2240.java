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
public class LQ2240 extends ApplicationFuncs
{	

	@Test(testName="LQ2240",dataProvider="getExcelTestData",description ="Verify ownership transfer")
	public void test_LQ2240(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("PrimaryParent"),rowMap.get("Password"));
			
			String subject=getRandomString1(rowMap.get("subject"));
			//eventPost_disable_comment_trasferownership(subject,rowMap.get("location"),rowMap.get("Descrpt"),false);
			//createCampaign(rowMap.get("Subject"), rowMap.get("Amount"),rowMap.get("Description"),false);
			//navigate_to_conversation();
			postFileForFamily(USERDIR+rowMap.get("FilePath"),subject,rowMap.get("Message"),rowMap.get("FamilyName") );
			//postVideo2(USERDIR+rowMap.get("VideoPath"),rowMap.get("Subject") ,rowMap.get("Message"),rowMap.get("GroupName"));			
		//postvideo2()
			String subject1=null;
			String subject2=null;
			navigate_to_conversation();
			pause(5000);
			transfrownershipnew(rowMap.get("Invite"));
			
			
			logout();
			//transfer 
			pause(120000);
			doLogin(rowMap.get("transferparent"),rowMap.get("Password"));
			isvisibiltyafterownershipinFamily(rowMap.get("FamilyName"),subject,subject1,subject2);
			pause(5000);
			logout();
			doLogin(rowMap.get("PrimaryParent"),rowMap.get("Password"));
			transfrownershipnew(rowMap.get("Invite1"));
			
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
			


