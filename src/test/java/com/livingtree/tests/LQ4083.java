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
public class LQ4083 extends ApplicationFuncs
{	

	@Test(testName="LA_11876",dataProvider="getExcelTestData",description ="Verify All icon in Give event")
	public void test_LQ4083(HashMap<String, String> rowMap){
		try {
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			//deleteAllPosts();
			String s= getRandomString(rowMap.get("Title"));
			rowMap.put("Subject", s);
			System.out.println(rowMap.get("Subject"));
			createCampaign(rowMap.get("Subject"), rowMap.get("Amount"),rowMap.get("Description"),false);
			createcampainpageitem(rowMap.get("Subject"));
			verifyCampaignall(rowMap.get("Subject"));
			shareoptioncheck(rowMap.get("Subject"));
			//gmailLogin(rowMap);
			//gmailLogin(rowMap.get("Subject"));
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