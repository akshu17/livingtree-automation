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
public class LQ2067 extends ApplicationFuncs
{		
	
	@Test(testName="LQ2067",dataProvider="getExcelTestData",description ="Inviting a Principal to District",groups= {"group1"})
	public void test_LQ2067(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			inviteNotification_admistrator(rowMap.get("InviteeName"),rowMap.get("groupname"));
			logout();
			//gmailLogin(rowMap.get("Subject"));//having to check in gmail so check it later
			//logout();
			login(rowMap.get("InviteeName"),rowMap.get("Password"));
			aceptinvitationPrincipal();
//			visibiltyofgroupinTeacher(rowMap.get("groupname"));
			logout();
//			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
//			//delete_principal_from_distric(rowMap.get("groupname"));
//			logout();
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





