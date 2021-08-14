package com.livingtree.tests;

import java.lang.reflect.Method;
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
import utils.DateAndTime;
import utils.ExcelTestDataReader;
@Listeners(CustomListener.class)
public class LQ5259 extends ApplicationFuncs
{

@Test(testName="LA14229",dataProvider="getExcelTestData",description ="Show Post Type\" section moved to left nav")
public void test_LQ5259(HashMap<String, String> rowMap,Method method) throws Exception
{
try {
openLoginPage();
doLogin(rowMap.get("Principal"),rowMap.get("Password"));
showFilter();
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