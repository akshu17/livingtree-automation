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
public class LQ4746 extends ApplicationFuncs
{	

	@Test(testName="LQ4746",dataProvider="getExcelTestData",description ="email trigger not happening when we try to send email invitation from my network group page")
	public void test_LQ4746(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			inviteNotification(rowMap.get("InviteeName"),rowMap.get("env"));
			logout();
			/*gmailLogin(rowMap.get("Subject"));//having to check in gmail so check it later
			logout();*/
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





