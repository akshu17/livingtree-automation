package com.livingtree.tests;

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
public class LA11898 extends ApplicationFuncs
{	

	@Test(testName="API Testing",dataProvider="getExcelTestData",description ="Andriod: API wire.is_new_content_available")
	public void test_LA11898(HashMap<String, String> rowMap) 
	{
		try {
			
			String token = loginPost(rowMap.get("UserName"),rowMap.get("Password"));
			HashMap<String, String> parameters=new HashMap<String, String>();
			String check_after_date_utc=DateAndTime.getUTCTimeLessInMinutes(10);
			parameters.put("check_after_date_utc",check_after_date_utc);
			parameters.put("method",rowMap.get("method"));
			parameters.put("user_guid",rowMap.get("user_guid"));
			parameters.put("group_guid",rowMap.get("group_guid"));
			parameters.put("auth_token",token);
			Response response= apiPost(parameters);
			//System.out.println(response.getBody().prettyPrint());
			
			String myBlob = response.asString();
			String data = JsonPath.with(myBlob).get("result.data.status");
			String data2 = JsonPath.with(myBlob).get("result.data.is_new_content");
			Assert.assertEquals(data, "success");
			Assert.assertEquals(data2, "-1");
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
