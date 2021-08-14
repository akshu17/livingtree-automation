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
public class LQ4737 extends ApplicationFuncs
{	

	@Test(testName="LQ4737",dataProvider="getExcelTestData",description ="TC-Mirrored Groups: UI to split a group")
	public void test_LQ4737(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("User_name"),rowMap.get("Password"));
			create_mirror_group(rowMap.get("family_name"),rowMap.get("group_owner_mail_id"),rowMap.get("groupowner_first_name"),rowMap.get("groupowner_last_name"),rowMap.get("groupowner_phone"));
			System.out.println("going to logout");
			logout();
//			doLogin(rowMap.get("group_owner_mail_id"),rowMap.get("Password"));
//			checkleftbarschool(rowMap.get("family_name"));
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
		System.out.println(sheetname);
		ExcelTestDataReader excelReader = new ExcelTestDataReader();
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname.split("_")[0]);
		return dataBeans.iterator();
	}
	
}