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
public class LA12213 extends ApplicationFuncs
{	
	@Test(testName="LA_12213",dataProvider="getExcelTestData",description ="'Birthday' and 'Language' details are not saved in ‘Edit Profile’ page when user updates them in welcome screen (first login)")
	public void test_LA12213(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Email"),rowMap.get("Password"));
			String provisionEndNum=provisionBaseCSVFile(rowMap.get("baseCSVFile"));
			int newNumber=Integer.parseInt(provisionEndNum);
			newNumber=newNumber+1;
			String newCSVProvisionFile=createNewProvisionCSVFile(provisionEndNum, newNumber,rowMap.get("baseCSVFile"));
			provisionManually(newCSVProvisionFile);
			logout();
			/*gmailLogin();
			verifyNotificationForPost(rowMap.get("Email Subject"));
			*/
			pause(10000);
			openLoginPage();
			String username="Teacher"+newNumber+"@chanduschools.com";
			login(username,rowMap.get("Password"));
			updateWelcomeProfile(rowMap.get("NewPassword"),rowMap.get("ConfirmNewPassword"),rowMap.get("DOB"),rowMap.get("TimeZone"),rowMap.get("Language"));
			verifyEditProfile(rowMap.get("DOB"), rowMap.get("Language"));
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





