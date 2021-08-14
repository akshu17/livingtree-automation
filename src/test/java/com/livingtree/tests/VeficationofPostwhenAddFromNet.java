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
public class VeficationofPostwhenAddFromNet extends ApplicationFuncs
{	

	@Test(testName="LA_11876",dataProvider="getExcelTestData",description ="Verify that add parent and add student and check the post on the same group")
	public void test_VeficationofPostwhenAddFromNet(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//inviteNotificationfromAddParentpop(rowMap.get("parents"),rowMap.get("env"));
			String subject=getRandomString1(rowMap.get("Subject"));
			//isnavigationtoMainPage();
			postFile(USERDIR+rowMap.get("FilePath"),subject ,rowMap.get("Message"),rowMap.get("groupname") );
			logout();
			doLogin(rowMap.get("Parents"),rowMap.get("Password"));
			visibiltyofgroupinstudent(rowMap.get("groupname"));
			likeAndCommentPost(subject, rowMap.get("Comment1"));
			logout();
			doLogin(rowMap.get("Studentone"),rowMap.get("Password"));
			visibiltyofgroupinstudent(rowMap.get("groupname"));
			likeAndCommentPost(subject, rowMap.get("Comment2"));
			logout();
			
			//gmailLogin(rowMap.get("Subject_Gmail"));//having to check in gmail so check it later
			//logout();
			login(rowMap.get("InviteeName"),rowMap.get("Password"));
			visibiltyofgroupinstudent(rowMap.get("groupname"));
			likeAndCommentPost(subject, rowMap.get("Comment2"));
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