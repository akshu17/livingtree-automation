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
public class LQ4093 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Verify ownership transfer")
	public void test_LQ4093(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			
			/*String subject=getRandomString1(rowMap.get("subject"));
			eventPost_disable_comment_trasferownership(subject,rowMap.get("location"),rowMap.get("Descrpt"),false);
			createCampaign(rowMap.get("Subject"), rowMap.get("Amount"),rowMap.get("Description"),false);
			navigate_to_conversation();
			postFile2(USERDIR+rowMap.get("FilePath"),rowMap.get("Subject2"),rowMap.get("Message"),rowMap.get("GroupName") );*/
			postPhoto2(USERDIR+rowMap.get("PhotoPath"),rowMap.get("Subject3") ,rowMap.get("Message"),rowMap.get("GroupName"));
			//postVideo2(USERDIR+rowMap.get("VideoPath"),rowMap.get("Subject3") ,rowMap.get("Message"),rowMap.get("GroupName"));
			postMessage(rowMap.get("Subject2") ,rowMap.get("Message"),rowMap.get("GroupName") );
		//postvideo2()
			String subject1=null;
			//String subject2=null;

			transfrownership(rowMap.get("Invite"));
			
			
			logout();
			//transfer 
			pause(120000);
			doLogin(rowMap.get("Userinvite1"),rowMap.get("Password"));
			isvisibiltyafterownership(rowMap.get("GroupName"),rowMap.get("Subject2"),subject1,rowMap.get("Subject3"));
			pause(5000);
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			transfrownership(rowMap.get("Invite1"));
			
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
			


