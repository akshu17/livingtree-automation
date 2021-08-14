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
public class LQ4446 extends ApplicationFuncs
{	

	@Test(testName="LA_12044",dataProvider="getExcelTestData",description ="Inbox message checking Parent =>Teacher")
	public void test_LQ4446(HashMap<String, String> rowMap) {
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
			doLogin(rowMap.get("Parent"),rowMap.get("Password"));
			String subject= getRandomString(rowMap.get("Subject"));
			System.out.println("enter sent message code block");
			sentMessagetoPrinciple(rowMap.get("TeacherName"), subject, rowMap.get("Message"),rowMap.get("FilePath"));
			System.out.println("exit sent message code block");
			pause(50000);
			verifySentMessageOfteacher(rowMap.get("TeacherName"), subject, rowMap.get("Message"));
			logout();
			pause(30000);
			/*doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			receiveMessage(rowMap.get("ParentName"), rowMap.get("TeacherName"), subject, rowMap.get("Message"));
			deleteAReceiveMessage(subject);
			logout();*/	
			//temp for app is not working
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





