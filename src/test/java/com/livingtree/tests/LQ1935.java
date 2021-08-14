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
public class LQ1935 extends ApplicationFuncs
{	
	@Test(testName="LQ1935",dataProvider="getExcelTestData",description ="Co-admin of school should be able to message Students" )
	public void test_LQ1935(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Co-admin"),rowMap.get("Password"));
			String subject= getRandomString(rowMap.get("Subject"));
			System.out.println(subject);
			//int count1=getAllSentMessagesCountBeforeSent();
			System.out.println("enter sent message code block");
			sentMessagetoPrinciple(rowMap.get("student"),subject, rowMap.get("Message"),rowMap.get("FilePath"));
			System.out.println("exit sent message code block");
			pause(50000);
			logout();
			doLogin(rowMap.get("studentMail"),rowMap.get("Password"));
			replyMessagetouser(subject,rowMap.get("Co-adminName"));
			//replyMessagetoAlluser(subject,rowMap.get("StudentOneName"),rowMap.get("PrincipalName"));
			/*int count2=getAllSentMessagesCountAfterSent();			
			if ((count1+1)!=count2) {
				Assert.fail("before sent count : "+count1+" After sent count: "+count2);
			}
			deleteASentMessage(subject);
			count2=getAllSentMessagesCountAfterSent();
			if (count1!=count2) {
				Assert.fail("before delete sent count : "+count1+" After delete sent count: "+count2);
			}*/
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






