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
import utils.DateAndTime;
import utils.ExcelTestDataReader;
@Listeners(CustomListener.class)
public class LA12063 extends ApplicationFuncs
{	

	@Test(testName="API Testing",dataProvider="getExcelTestData",description ="Delete Event")
	public void test_LA12063(HashMap<String, String> rowMap) 
	{
		try {
			
			String token = loginPost(rowMap.get("UserName"),rowMap.get("Password"));
			HashMap<String, String> createEvent=new HashMap<String, String>();
			String startdate=DateAndTime.getFutureDateTime24(1);
			pause(3000);
			createEvent.put("start_time",startdate);
			String enddate=DateAndTime.getFutureDateTime24(1);
			createEvent.put("end_time",enddate);
			createEvent.put("title",getRandomString("API Automation"));
			createEvent.put("method",rowMap.get("method"));
			createEvent.put("ltws_version",rowMap.get("ltws_version"));
			createEvent.put("title",rowMap.get("title"));
			createEvent.put("description",rowMap.get("description"));
			createEvent.put("container_guid",rowMap.get("container_guid"));
			createEvent.put("location",rowMap.get("location"));
			createEvent.put("visibility",rowMap.get("visibility"));
			createEvent.put("owner_guid",rowMap.get("owner_guid"));
			createEvent.put("edit_series",rowMap.get("edit_series"));
			createEvent.put("notify",rowMap.get("notify"));
			createEvent.put("auth_token",token);
			createEvent.put("event_action",rowMap.get("event_action"));
			
			Response response= apiPost(createEvent);
			//System.out.println(response.getBody().prettyPrint());
			
			String myBlob = response.asString();
			ArrayList<String> data = JsonPath.with(myBlob).get("result.calendar_events.id");
			String guid=null;
			
			// guid = JsonPath.with(myBlob).get("result.calendar_events.id").toString();
			for(String value: data){
				 guid=value;
			}
			HashMap<String, String> deleteEvent=new HashMap<String, String>();
			
			deleteEvent.put("method",rowMap.get("method2"));
			deleteEvent.put("guid",guid);
			deleteEvent.put("auth_token",token);
			deleteEvent.put("ltws_version",rowMap.get("ltws_version"));
			
			apiPost(deleteEvent);
			
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
