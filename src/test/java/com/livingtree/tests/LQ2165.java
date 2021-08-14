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
public class LQ2165 extends ApplicationFuncs
{	

	@Test(testName="LA_12894",dataProvider="getExcelTestData",description ="change the password")
	public void test_LQ2165(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("OldPassword"));
			changePassword(rowMap.get("OldPassword"),rowMap.get("NewPassword"));//change to new password
			//inviteNotification(rowMap.get("InviteeName"));
			logout();
			doLogin(rowMap.get("UserName"),rowMap.get("NewPassword"));
			changePassword(rowMap.get("NewPassword"),rowMap.get("OldPassword"));//change to old one password
			//gmailLogin(rowMap.get("Subject"));//having to check in gmail so check it later
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