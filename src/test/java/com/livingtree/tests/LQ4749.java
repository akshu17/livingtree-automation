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
public class LQ4749 extends ApplicationFuncs
{	

	@Test(testName="LQ4749",dataProvider="getExcelTestData",description ="Configure emergency default box disappearing after being idle for some time")
	public void test_LQ4749(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Distric_admin"),rowMap.get("Password"));
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			save_default_message(subject,rowMap.get("default message"));
			postEmergenceyAlert(subject,rowMap.get("default message"));
			//String SchoolName,String UserName,String new_user_value_n,String button_name_value,String filepath,String new_user_value_y
			//navigateToAddlayer(rowMap.get("SchoolName"),rowMap.get("UserName"),rowMap.get("new_user_value_n"),rowMap.get("button_name_value_other"),rowMap.get("button_name_value_teacher"),USERDIR+rowMap.get("filepath"),rowMap.get("new_user_value_y"));
			pause(6000);
			logout();
			doLogin(rowMap.get("Username"),rowMap.get("Password"));
			likePost(subject);
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
		return dataBeans.iterator();
	}
	
}