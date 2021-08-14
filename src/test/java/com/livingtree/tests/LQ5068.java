package com.livingtree.tests;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import com.livingtree.Web.ApplicationFuncs;
//
//import controllers.InitMethod;
//import listeners.CustomListener;
//import utils.ConfigReader;
//import utils.ExcelTestDataReader;
//@Listeners(CustomListener.class)
//public class LQ2021 extends ApplicationFuncs
//{	
//
//	@Test(testName="LQ2021",dataProvider="getExcelTestData",description ="Comment on District Principals post which is made by District Admin")
//	public void test_LQ2021(HashMap<String, String> rowMap) {
//		try {			
//			openLoginPage();
//			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
//			String subject=getRandomString1(rowMap.get("Subject"));
//			postFileforElementaryPrincipal(USERDIR+rowMap.get("FilePath"),subject ,rowMap.get("Message"));			
//			logout();
//			doLogin(rowMap.get("Distric_Admin"),rowMap.get("Password"));
//			likeAndCommentPost(subject, rowMap.get("Comment1"));
//			//verifyPostFESScore(subject2, rowMap.get("ParentCountFES2"));
//			//verifyFESScoreOnTeacherProfile(rowMap.get("TeacherFESScore"));
//			logout();
//			doLogin(rowMap.get("Principal"),rowMap.get("Password"));			
//			//verifyFESScoreOnPrincipalProfile(rowMap.get("TeacherName"),rowMap.get("TeacherFESScore"));
//			//change for checking
//			delete1stPost(subject);
//			logout();
//		} catch (Exception e) {
//			InitMethod.ErrorMsg = e.getMessage();
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@DataProvider
//	public Iterator<Object[]> getExcelTestData() 
//	{
//		String sheetname = this.getClass().getSimpleName();
//		ExcelTestDataReader excelReader = new ExcelTestDataReader();
//		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(USERDIR+ConfigReader.getValue("TestData"),sheetname);
//		return dataBeans.iterator();
//	}
//}
//package com.livingtree.tests;


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
public class LQ5068 extends ApplicationFuncs
{	

	@Test(testName="LQ5068",dataProvider="getExcelTestData",description ="Group types in the Group Filter section and filter posts by that group")
	public void test_LQ5068(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("UserName"),rowMap.get("Password"));
			pause(5000);
			verifyClasses("Classes list present");
			verifyGroup("Group list present");
			verifySchools("School list present");
			verifyDistricts("District list present");
			postTypeFilterCheck();
			
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
