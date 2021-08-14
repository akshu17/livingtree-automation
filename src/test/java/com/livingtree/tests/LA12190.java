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
public class LA12190 extends ApplicationFuncs
{	

	@Test(testName="LA12190",dataProvider="getExcelTestData",description ="FES: FES score is not enabled for scheduled posts")
	public void test_LA12190(HashMap<String, String> rowMap) {
		try {			
			openLoginPage();
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			//deleteAllPosts();
			
			String subjectMessagePost=getRandomString1("MessagePost");
			postScheduleMessage(subjectMessagePost ,rowMap.get("Message"),rowMap.get("GroupName") );
			
			String subjectPhotoPost=getRandomString1("PhotoPost");
			postSchedulePhoto(USERDIR+rowMap.get("PhotoPath"),subjectPhotoPost ,rowMap.get("Message"),rowMap.get("GroupName") );
			
			String subjectVideoPost=getRandomString1("VideoPost");
			postScheduleVideo(USERDIR+rowMap.get("VideoPath"),subjectVideoPost ,rowMap.get("Message"),rowMap.get("GroupName") );
			
			String subjectFilePost=getRandomString1("FilePost");
			postScheduleFile(USERDIR+rowMap.get("FilePath"),subjectFilePost ,rowMap.get("Message"),rowMap.get("GroupName") );
			
			String subjectPhonePost=getRandomString1("PhonePost");
			postSchedulePhone(subjectPhonePost ,rowMap.get("Message"),rowMap.get("GroupName") );
			
			logout();
			doLogin(rowMap.get("StudentOne"),rowMap.get("Password"));
			searchPost(subjectMessagePost);
			likeAndCommentPost(subjectMessagePost, rowMap.get("Comment1"));
			searchPost(subjectPhotoPost);
			likeAndCommentPost(subjectPhotoPost, rowMap.get("Comment1"));
			searchPost(subjectVideoPost);
			likeAndCommentPost(subjectVideoPost, rowMap.get("Comment1"));
			searchPost(subjectFilePost);
			likeAndCommentPost(subjectFilePost, rowMap.get("Comment1"));
			searchPost(subjectPhonePost);
			likeAndCommentPost(subjectPhonePost, rowMap.get("Comment1"));
			logout();
			
			doLogin(rowMap.get("StudentTwo"),rowMap.get("Password"));
			searchPost(subjectMessagePost);
			likeAndCommentPost(subjectMessagePost, rowMap.get("Comment2"));			
			searchPost(subjectPhotoPost);
			likeAndCommentPost(subjectPhotoPost, rowMap.get("Comment2"));			
			searchPost(subjectVideoPost);
			likeAndCommentPost(subjectVideoPost, rowMap.get("Comment2"));			
			searchPost(subjectFilePost);
			likeAndCommentPost(subjectFilePost, rowMap.get("Comment2"));			
			searchPost(subjectPhonePost);
			likeAndCommentPost(subjectPhonePost, rowMap.get("Comment2"));			
			logout();
			
			doLogin(rowMap.get("Teacher"),rowMap.get("Password"));
			searchPost(subjectMessagePost);
			calculateFESScoreForPost(subjectMessagePost);
			searchPost(subjectPhotoPost);
			calculateFESScoreForPost(subjectPhotoPost);
			searchPost(subjectVideoPost);
			calculateFESScoreForPost(subjectVideoPost);
			searchPost(subjectFilePost);
			calculateFESScoreForPost(subjectFilePost);
			searchPost(subjectPhonePost);
			calculateFESScoreForPost(subjectPhonePost);
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




