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
public class LQ4198 extends ApplicationFuncs
{	

	@Test(testName="LA_11876",dataProvider="getExcelTestData",description ="Verify that the lion icon is removed from the auto-translated email notification for an Invitation received.")
	public void test_LQ4198(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			inviteNotificationstudentfromadddstudent(rowMap.get("env"),rowMap.get("studentname"));
			logout();
			//gmailLogin(rowMap.get("Subject"));//having to check in gmail so check it later
			//logout();
			pause(5000);
			login(rowMap.get("InviteeName"),rowMap.get("Password"));
			visibiltyofgroupinstudent(rowMap.get("groupname"));
			//acceptinvitation(rowMap.get("childname"),rowMap.get("groupname"));
			logout();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			delete_student_from_group(rowMap.get("groupname"));
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