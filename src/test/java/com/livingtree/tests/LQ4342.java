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
public class LQ4342 extends ApplicationFuncs
{	

	@Test(testName="LA_12037",dataProvider="getExcelTestData",description ="Verify Mute button for every post")
	public void test_LQ4342(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postMessage(subject ,rowMap.get("Message"),rowMap.get("GroupName") );
			
			String subjectPhoto=getRandomString1(rowMap.get("Subject"));
			postPhoto(USERDIR+rowMap.get("PhotoPath"),subjectPhoto ,rowMap.get("Message"),rowMap.get("GroupName") );		
			//searchUser(rowMap.get("TeacherName"));
			verifyAllMutesForPost();
			mutePost(subjectPhoto);
			mutePost(subject);
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
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),"LA12037");
		return dataBeans.iterator();
	}
}





