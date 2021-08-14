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
public class LQ4921 extends ApplicationFuncs
{	

	@Test(testName="LA-13588",dataProvider="getExcelTestData",description ="LA-13588 TC-Web: User is not able to answer to a poll post when it is searched by 'Search conversations'")
	public void test_LQ4921(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			deleteAllPosts();
			String subject=getRandomString1(rowMap.get("Subject"));
			postPoll(rowMap.get("Answer1"),rowMap.get("Answer2"),rowMap.get("Answer3"),rowMap.get("Answer4"),rowMap.get("Answer5"),rowMap.get("Answer6"),subject,rowMap.get("message"),rowMap.get("GroupName"),rowMap.get("type_message"));		
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			//visibilityPollPost(subject,true,"edit option is not available");
			searchPost(subject);
			likeAndAnswerPollPost(subject,rowMap.get("StudentOneAns"),rowMap.get("message"));						
			logout();
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			searchPost(subject);
			//visibilityPollPost(subject,true,"edit option is not available");
			likeAndAnswerPollPost(subject,rowMap.get("StudentTwoAns"),rowMap.get("message"));
			logout();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			String subject1=getRandomString1(rowMap.get("Subject"));
			editPoll(subject,rowMap.get("message"),rowMap.get("GroupName") );
			logout();
			/*doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//calculateFESScoreForPost(subject);
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			likePost(subject);
			logout();			
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//calculateFESScoreForPost(subject);
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