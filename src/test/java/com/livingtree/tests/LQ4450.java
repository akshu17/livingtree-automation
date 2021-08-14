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
public class LQ4450 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Selected group disappears when uploading photo")
	public void test_LQ4450(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("Subject"));
			postPhoto_disablecomment(USERDIR+rowMap.get("PhotoPath"),subject ,rowMap.get("Message"),rowMap.get("GroupName"),true);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			commentSearchPostnotavailablpost(subject);
			logout();
			doLogin(rowMap.get("SonePParent"),rowMap.get("Password"));
			commentSearchPostnotavailablpost(subject);
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			childlabelfilter(subject,rowMap.get("GroupName"));
			//verifyPostFESScore(subject, rowMap.get("CountFES2"));
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname.split("_")[0]);
		System.out.println(sheetname.split("_")[0]);
		return dataBeans.iterator();
	}
	
}