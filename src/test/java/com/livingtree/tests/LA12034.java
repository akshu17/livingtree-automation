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
public class LA12034 extends ApplicationFuncs
{	

	@Test(testName="LA_12034",dataProvider="getExcelTestData",description ="Web UI: Message posting reskin or flow change")
	public void test_LA12034(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			String subject4=getRandomString1(rowMap.get("Subject"));
			postPhoneAlert2(subject4 ,rowMap.get("Message"),rowMap.get("GroupName") );					
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String subject3=getRandomString1(rowMap.get("Subject"));
			postPhoneMessage2(subject3 ,rowMap.get("Message"),rowMap.get("GroupName") );								
			String subject=getRandomString1(rowMap.get("Subject"));
			postFile2(USERDIR+rowMap.get("FilePath"),subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			String subject1=getRandomString1(rowMap.get("Subject"));
			postPhoto2(USERDIR+rowMap.get("PhotoPath"),subject1 ,rowMap.get("Message"),rowMap.get("GroupName") );		
			String subject2=getRandomString1(rowMap.get("Subject"));
			postVideo2(USERDIR+rowMap.get("VideoPath"),subject2 ,rowMap.get("Message"),rowMap.get("GroupName") );		
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





