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
public class LQ3280 extends ApplicationFuncs
{	

	@Test(testName="LA_11993",dataProvider="getExcelTestData",description ="Update email id-(LQ2179) and timezone-(LQ3280) of the user")
	public void test_LQ3280TimezonefieldinEditprofile (HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//verifyFESScoreOnUserProfile(rowMap.get("TeacherName"));
			updateprofile_new(rowMap.get("Timezone"),rowMap.get("email"),rowMap.get("newphoneno"));
			
			//rowMap.get("newphoneno")
			//Update phone number-Deepak
			//updateprofile(rowMap.get("newphoneno"));
			//updateProfilePhoto(rowMap.get("photoPath"));
			
			logout();
			
			openLoginPage();
			doLogin(rowMap.get("email"),rowMap.get("Password"));
			updateProfilePhoto(USERDIR+rowMap.get("PhotoPath"));
			
			
			logout();
			
			doLogin(rowMap.get("email"),rowMap.get("Password"));
			updateprofile_new(rowMap.get("Timezone1"),rowMap.get("Teacher"),rowMap.get("oldphoneno"));
			
					
					//rowMap.get("newphoneno1"));
			//updateprofile(rowMap.get("newphoneno1"));
			
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





