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
public class LQ4345 extends ApplicationFuncs
{	

	@Test(testName="LA_12037",dataProvider="getExcelTestData",description ="Verify mute a post functionality from mailbox")
	public void test_LQ4345(HashMap<String, String> rowMap) {
		try {			
			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			String comment1=getRandomString1(rowMap.get("Comment1"));
			likeAndCommentPost(subject, comment1);
			logout();
			System.out.println("enter gmail");
			gmailLogin();
			System.out.println("exit some stuff done gmail");
			System.out.println("enter comment in gmail");
			verifyNotificationForPost(comment1);
			System.out.println("exit comment in gmail");
			System.out.println("enter mute in gmail");
			mutePostFromMail(subject);
			System.out.println("exit mute in gmail");
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String comment2=getRandomString1(rowMap.get("Comment1"));
			likeAndCommentPost(subject, comment2);
			logout();
			refreshGmail(); 
			noNotificationForMutePostFromMail(comment2);
			
			
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





