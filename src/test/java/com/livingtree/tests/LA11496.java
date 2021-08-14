package com.livingtree.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.livingtree.Web.ApplicationFuncs;

import controllers.InitMethod;
import listeners.CustomListener;
import utils.ConfigReader;
import utils.ExcelTestDataReader;

@Listeners(CustomListener.class)
public class LA11496 extends ApplicationFuncs
{	

	@Test(testName="API Testing",dataProvider="getExcelTestData",description ="API's : User can update a post")
	public void test_16619(HashMap<String, String> rowMap) 
	{
		try {
			String token = loginPost(rowMap.get("UserName"),rowMap.get("Password"));
			HashMap<String, String> parameters=new HashMap<String, String>();
			String title=getRandomString("API Automation");
			parameters.put("auth_token",token);
			parameters.put("method",rowMap.get("method"));
			parameters.put("ltws_version",rowMap.get("ltws_version"));
			parameters.put("title",title);
			parameters.put("text",rowMap.get("description"));
			parameters.put("container_guid",rowMap.get("container_guid"));
			parameters.put("post_access_id[]",rowMap.get("group_guids"));
			parameters.put("comments_allowed",rowMap.get("comments_allowed"));
			parameters.put("notifyall",rowMap.get("notifyall"));
			parameters.put("reshare_on",rowMap.get("reshare_on"));
			parameters.put("access_id",rowMap.get("access_id"));
			//parameters.put("guid",rowMap.get("edit_series"));
			parameters.put("post_type",rowMap.get("post_type"));
			
			/*For Schedule post 
			 * String schedule_for=DateAndTime.getUTCTimePlusMinuts(2);		
			parameters.put("schedule_for",schedule_for);*/
			
			
			Response postResponse= apiPost(parameters);
			//System.out.println(response.getBody().prettyPrint());
			
			String myBlob = postResponse.asString();
			//String guid = JsonPath.with(myBlob).get("result.posts.guid");
			ArrayList<Integer> data = JsonPath.with(myBlob).get("result.posts.guid");
			int guid=0;
			for(int value: data){
				 guid=value;
			}
			String titleEdited="Edited"+title;
			parameters.put("guid",String.valueOf(guid));
			parameters.put("title",titleEdited);
			parameters.put("text","Edited"+ rowMap.get("description"));
			
			Response editResponse= apiPost(parameters);
			String Json = editResponse.asString();
			//String expectedTitle = JsonPath.with(Json).get("result.posts.title");
			ArrayList<String> data2 = JsonPath.with(Json).get("result.posts.title");
			String expectedTitle=null;
			
			// guid = JsonPath.with(myBlob).get("result.calendar_events.id").toString();
			for(String value: data2){
				expectedTitle=value;
			}
			Assert.assertEquals(expectedTitle, titleEdited, "Failed to edit existing post");
			
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
