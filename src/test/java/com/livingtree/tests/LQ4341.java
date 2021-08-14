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
public class LQ4341 extends ApplicationFuncs
{	

	@Test(testName="LA_12044",dataProvider="getExcelTestData",description ="Verify Parent are able  to sent message to teacher")
	public void test_LQ4341(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			/*doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String subject= getRandomString(rowMap.get("Subject"));
			sentMessage(rowMap.get("StudentOneName"), rowMap.get("StudentTwoName"), subject, rowMap.get("Message"),rowMap.get("FilePath"));
			verifySentMessage(rowMap.get("StudentOneName"), rowMap.get("StudentTwoName"), subject, rowMap.get("Message"));
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			receiveMessage(rowMap.get("StudentOneName"), rowMap.get("StudentTwoName"), subject, rowMap.get("Message"));
			deleteAReceiveMessage(subject);
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			receiveMessage(rowMap.get("StudentOneName"), rowMap.get("StudentTwoName"), subject, rowMap.get("Message"));
			deleteAReceiveMessage(subject);
			logout();
			*/
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			String subject= getRandomString(rowMap.get("Subject"));
			System.out.println("enter sent message code block");
			sentMessage(rowMap.get("TeacherName"), rowMap.get("PrincipalName"), subject, rowMap.get("Message"),rowMap.get("FilePath"));
			System.out.println("exit sent message code block");
			verifySentMessage(rowMap.get("TeacherName"), rowMap.get("PrincipalName"), subject, rowMap.get("Message"));
			logout();
			pause(30000);
			doLogin(rowMap.get("Principal"),rowMap.get("Password"));
			receiveMessage(rowMap.get("StudentOneName"), rowMap.get("TeacherName"), subject, rowMap.get("Message"));
			deleteAReceiveMessage(subject);
			logout();	
			/*doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			receiveMessage(rowMap.get("StudentOneName"), rowMap.get("PrincipalName"), subject, rowMap.get("Message"));
			deleteAReceiveMessage(subject);
			logout();*/
			
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





