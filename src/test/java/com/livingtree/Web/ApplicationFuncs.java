package com.livingtree.Web;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.livingtree.Web.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.opencsv.CSVReader;

import utils.ConfigReader;

public class ApplicationFuncs extends ObjectOR{

	
public void openLoginPage_Edlio() {
		
		navigateToUrl(ConfigReader.getValue("BASE_URL_EDLIO"));
		pause(8000);
	}
	
	public void openLoginPage() {
		
		navigateToUrl(ConfigReader.getValue("BASE_URL"));
		pause(8000);
	}

	public String loginPost(String UserName,String Password) {
		RestAssured.baseURI = ConfigReader.getValue("BASE_URI");

		RequestSpecification request = RestAssured.given().queryParam("device_token", "sdsafsaf")
		.queryParam("method", "auth.getusertoken")
		.queryParam("username", UserName)
		
		.queryParam("password", Password);
		Response response = request.post("");	
		String myBlob = response.asString();
		String token = JsonPath.with(myBlob).get("result.authentication.token");
		return token;
				
	}
	public boolean apiPost2(HashMap<String, String> rowMap){
		EncoderConfig ec = new EncoderConfig();
		RequestSpecification request = RestAssured.given()
		.config(RestAssured.config().encoderConfig(ec.encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))	
		.formParams(rowMap); // requestParamsMap here.
		Response response = request.post("");	
		logStep("Response : "+response.getBody().prettyPrint());	
		// int statusCode = response2.getStatusCode();	
		Assert.assertEquals(response.getStatusCode() /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		JsonPath jsonPathEvaluator = response.jsonPath();
		int status = jsonPathEvaluator.get("status");
		Assert.assertEquals(String.valueOf(status), "0", "Correct status received in the Response");
		//System.out.println(response.getBody().prettyPrint());
		
		return true;
				
	}
	public boolean isnavigationtoMainPage()
	{
		click(HomePage.img_LivingTreeLogonew,"navigate to main page");
		new WebDriverWait(getWebDriver(), 60).until(ExpectedConditions.elementToBeClickable((Conversations.shareMessage)));
		pause(5000);
		getWebDriver().navigate().refresh();
		pause(5000);
		new WebDriverWait(getWebDriver(), 30).until(ExpectedConditions.elementToBeClickable((Conversations.shareMessage)));
		waitForVisible(Conversations.shareMessage);
		return true;
	}
	public boolean isnavigationtoMainPage_edlio()
	{
		click(HomePage.img_EdlioEngagednew,"navigate to main page");
		new WebDriverWait(getWebDriver(), 60).until(ExpectedConditions.elementToBeClickable((Conversations.shareMessage)));
		pause(5000);
		getWebDriver().navigate().refresh();
		pause(5000);
		new WebDriverWait(getWebDriver(), 30).until(ExpectedConditions.elementToBeClickable((Conversations.shareMessage)));
		waitForVisible(Conversations.shareMessage);
		return true;
	}
	
	public boolean EmailReporting()
	{
		click(HomePage.lnk_UserOptions,"Click on Options");
		pause(2000);
		click(HomePage.lnk_User_Settings,"Click on settings");
		click(HomePage.lnk_User_MyProfile,"Click on MyProfile");
		pause(2000);
		//click(HomePage.lnk_EditProfile,"click on Edit Profile & Preferences");
		click(MyProfile.lbl_EditProfilePreferences,"click on Edit Profile & Preferences");
		pause(6000);
		scrollToBottom();
		isWebElementVisible(EditProfile.lbl_Email_Reporting, "Element Reporting label is display");
		pause(3000);
		isWebElementVisible(EditProfile.chk_weekly_signup_report, "weekly signup report check box visiable");
		isWebElementVisible(EditProfile.chk_weekly_activity_report, "weekly activity report check box visiable");
		isWebElementVisible(EditProfile.chk_district_provision_report, "check box district provision report visiable");
		isWebElementVisible(EditProfile.chk_DISTRICT_ACTIVITY_REPORT_WEEKLY, "check box DISTRICT ACTIVITY REPORT WEEKLY visiable");
		return true;
	}
	public Response apiPost(HashMap<String, String> rowMap){
		EncoderConfig ec = new EncoderConfig();
		RequestSpecification request = RestAssured.given()
		.config(RestAssured.config().encoderConfig(ec.encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))	
		.formParams(rowMap); // requestParamsMap here.

		Response response = request.post("");	
		logStep("Response : "+response.getBody().prettyPrint());
		
		// int statusCode = response2.getStatusCode();	
		Assert.assertEquals(response.getStatusCode() /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		JsonPath jsonPathEvaluator = response.jsonPath();
		int status = jsonPathEvaluator.get("status");
		Assert.assertEquals(String.valueOf(status), "0", "Correct status received in the Response");
		//System.out.println(response.getBody().prettyPrint());
		
		return response;
				
	}
	public boolean isLoginPageOpened() {
		return getWebDriver().
				getCurrentUrl().
				equalsIgnoreCase(ConfigReader.getValue("BASE_URL"));
	}	
	public boolean doLogin(String userName, String password) {
		//click(LoginPage.lnk_Login, "Click on Login link");
		
		inputText(LoginPage.input_UserName, userName,"Enter Email");
		inputText(LoginPage.input_Password, password,"Enter Password");
		click(LoginPage.btn_SignIn, "Click on Login button");
		new WebDriverWait(getWebDriver(), 120).until(ExpectedConditions.elementToBeClickable((Conversations.shareMessage)));
		pause(5000);
		getWebDriver().navigate().refresh();
		pause(5000);
		new WebDriverWait(getWebDriver(), 30).until(ExpectedConditions.elementToBeClickable((Conversations.shareMessage)));
		waitForVisible(Conversations.shareMessage);
		return true; //isWebElementVisible(HomePage.img_LivingTreeLogo);C:\LivingTree Workspace\LivingTree_TestAutomation\src\test\resources\Files\Uploads\Videos\video.mp4
		
	}
	
	public boolean login(String userName, String password) {
		//click(LoginPage.lnk_Login, "Click on Login link");
		inputText(LoginPage.input_UserName, userName,"Enter Email");
		inputText(LoginPage.input_Password, password,"Enter Password");
		click(LoginPage.btn_SignIn, "Click on Login button");
		ifAlertDismiss();
		return true; 
	}
	public boolean clickRememberMe() {
		click(LoginPage.chk_RememberMe,"Click on Remember Me checkbox");
		return true; 
	}
	public boolean logout() {
		
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
//		pause(3000);
		click(HomePage.lnk_User_Logout,"Click on Logout");	
		return true;
	}

	public String actualEmail(String email) {
		String actualEmail=getWebDriver().findElement(LoginPage.input_UserName).getAttribute("value");
		compareTwoValuesIgnoreCase(actualEmail, email);
		
		return actualEmail; 
	}
	public boolean postSubject(String OptinalSubject, String TypeMessage)  {
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, OptinalSubject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, TypeMessage, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(5000);
		click(Conversations.selectChildGroup,"click On sub child");
		click(Conversations.clickPostButton,"Click Post");
		
		return true;
	
	}
	public boolean Navigatetosupport()
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		pause(2000);
		click(LoginPage.lnk_Support,"Click on support");
		return true;
	}
	public boolean SearchSchoolinSupportPortal(String schoolname)
	{
		inputText(Support.txt_search_Groups__by_name, schoolname,"Enter school");
		click(Support.btn_search,"serach for the school spport portal");
		pause(10000);
		isWebElementVisible(Support.img_pencil_edit, "Verify the actions column");
		return true;
	}
	public boolean SearchEmailinSupportPortal(String email,String userid)
	{
		clear_locator(Support.txt_search_Members_by_email);
		inputText(Support.txt_search_Members_by_email, email,"Enter school");
		click(Support.btn_search_email,"serach for the school spport portal");
		pause(10000);
		String user_guid=getText(Support.lbl_user_guid);
		System.out.println(user_guid);
		if(user_guid.equalsIgnoreCase(userid))
		{
		return true;
		}
		else
		{
			return false;
		}
	}
	public boolean enableTestmoodeforschoolformsupport(String schoolname)
	{
		inputText(Support.txt_search_Groups__by_name, schoolname,"Enter school");
		click(Support.btn_search,"serach for the school spport portal");
		pause(10000);
		isWebElementVisible(Support.img_pencil_edit, "Verify the actions column");
		click(Support.img_pencil_edit,"click to edit the support");
		pause(8000);
		isWebElementVisible(Support.chk_test_mode, "Verify the test mode option");
		click(Support.chk_test_mode,"enable the testmode in Maple");
		pause(1000);
		click(Support.bth_save_manage_school,"save the testmode in Maple");
		pause(1000);
		  if(isAlertPresent())
		    {
		    alertAccept();
		    }
		pause(5000);
		click(Support.img_pencil_edit,"click to edit the support");
		pause(8000);
		isWebElementVisible(Support.chk_test_mode, "Verify the test mode option");
		click(Support.chk_test_mode,"disable the testmode in Maple");
		pause(1000);
		click(Support.bth_save_manage_school,"save the testmode in Maple");
		pause(1000);
		  if(isAlertPresent())
		    {
		    alertAccept();
		    }
		pause(5000);
		return true;
		
	}
	public boolean eventPost(String eventType,String location,String Descrpt) 
	{
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.add_Event_creatBtn,"Click on Event");
		inputText(Calendar.add_Enter_Event, eventType, "Enter Event");
		click(Calendar.add_Click_DropDown,"Click on Dropdown");
	    pause(3000);
	    click(Calendar.select_Evnt_ChildGroup,"click on just group");
	    click(Calendar.select_Evnt_ChildGroupsub,"click on just Parent");
	    click(Calendar.add_Disble_Comment,"Click on Disble comment");
	    pause(1000);
	    click(Calendar.add_AllDaysCheckBox,"click on All Day Event");
	    inputText(Calendar.add_WhereLocation, location, "Enter Location");
	    click(Calendar.add_RSVP,"Select RSVP");
	    pause(3000);
	    inputText(Calendar.add_Enter_Event_Description, Descrpt, "Enter Description");
	    pause(3000);
	    click(Calendar.click_Save_Exit_Btn,"click save button");
	    click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
	    pause(10000);
		return true;
	}
	public boolean eventPost_with_all_option(String eventType,String location,String Descrpt) 
	{
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.add_Event_creatBtn,"Click on Event");
		inputText(Calendar.add_Enter_Event, eventType, "Enter Event");
		click(Calendar.add_Click_DropDown,"Click on Dropdown");
	    pause(3000);
	    click(Calendar.select_Evnt_ChildGroup,"click on just group");
	    click(Calendar.select_Evnt_ChildGroupsub,"click on just Parent");
	    click(Calendar.add_Disble_Comment,"Click on Disble comment");
	    pause(1000);
	    click(Calendar.add_AllDaysCheckBox,"click on All Day Event");
	    inputText(Calendar.add_WhereLocation, location, "Enter Location");
	    click(Calendar.add_RSVP,"Select RSVP");
	    pause(3000);
	    click(Calendar.add_Require_Sign_Up_for_Tasks,"add reminder in Event");
	    pause(1000);
	    click(Calendar.add_Reminders,"add reminder in Event");
	    pause(3000);
	    inputText(Calendar.add_Enter_Event_Description, Descrpt, "Enter Description");
	    pause(3000);
	    click(Calendar.click_Save_Exit_Btn,"click save button");
	    click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
	    pause(10000);
		return true;
	}
	public boolean eventPost_disable_comment_trasferownership(String eventType,String location,String Descrpt,Boolean Condition) 
	{
		//String enableordisablecomment=null; 
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.add_Event_creatBtn,"Click on Event");
		inputText(Calendar.add_Enter_Event, eventType, "Enter Event");
		pause(3000);
		click(Calendar.add_Click_DropDown,"Click on Dropdown");
	    pause(3000);
	    click(Calendar.select_Evnt_ChildGroup_trasferwoenrship,"click on just group");
	    pause(2000);
	    click(Calendar.select_Evnt_ChildGroupsub_trasnferownership,"clcik on parents");
	    if(Condition)
	    {
	    click(Calendar.add_Disble_Comment,"Click on Disble comment");
	    pause(1000);
	    }
	    else
	    {
	    	//do nothing
	    	System.out.println("do noithing");
	    }
	    click(Calendar.add_AllDaysCheckBox,"click on All Day Event");
	    inputText(Calendar.add_WhereLocation, location, "Enter Location");
	    click(Calendar.add_RSVP,"Select RSVP");
	    pause(3000);
	    inputText(Calendar.add_Enter_Event_Description,Descrpt , "Enter Description");
	    pause(3000);
	    click(Calendar.click_Save_Exit_Btn,"click save button");
	    pause(3000);
	    if(isAlertPresent())
	    {
	    alertAccept();
	    }
	    pause(30000);
	    click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
	    pause(5000);
	    
		return true;
	}
	public boolean navigate_to_conversation()
	{
		 click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
		   pause(8000);
		   return true;
		   
	}
	
	public boolean eventPost_disable_comment(String eventType,String location,String Descrpt,Boolean Condition) 
	{
		//String enableordisablecomment=null; 
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.add_Event_creatBtn,"Click on Event");
		inputText(Calendar.add_Enter_Event, eventType, "Enter Event");
		pause(3000);
		click(Calendar.add_Click_DropDown,"Click on Dropdown");
	    pause(3000);
	    click(Calendar.select_Evnt_ChildGroup,"click on just group");
	    pause(2000);
	    click(Calendar.select_Evnt_ChildGroupsub,"clcik on parents");
	    ifAlertDismiss();
	    if(Condition)
	    {
	    click(Calendar.add_Disble_Comment,"Click on Disble comment");
	    pause(1000);
	    }
	    else
	    {
	    	//do nothing
	    	System.out.println("do noithing");
	    }
	    click(Calendar.add_AllDaysCheckBox,"click on All Day Event");
	    inputText(Calendar.add_WhereLocation, location, "Enter Location");
	    click(Calendar.add_RSVP,"Select RSVP");
	    pause(3000);
	    inputText(Calendar.add_Enter_Event_Description,Descrpt , "Enter Description");
	    pause(3000);
	    click(Calendar.click_Save_Exit_Btn,"click save button");
	    pause(3000);
	    if(isAlertPresent())
	    {
	    alertAccept();
	    }
	    pause(30000);
	    click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
	    pause(5000);
	    
		return true;
	}
	public boolean eventPost_add_reminder(String eventType,String location,String Descrpt) 
	{
		//String enableordisablecomment=null; 
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.add_Event_creatBtn,"Click on Event");
		inputText(Calendar.add_Enter_Event, eventType, "Enter Event");
		pause(3000);
		click(Calendar.add_Click_DropDown,"Click on Dropdown");
	    pause(3000);
	    click(Calendar.select_Evnt_ChildGroup,"click on just group");
	    pause(2000);
	    click(Calendar.select_Evnt_ChildGroupsub,"clcik on parents");
	    ifAlertDismiss();
	    
	    click(Calendar.add_Reminders,"add reminder in Event");
	    
	    click(Calendar.add_AllDaysCheckBox,"click on All Day Event");
	    inputText(Calendar.add_WhereLocation, location, "Enter Location");
	    click(Calendar.add_RSVP,"Select RSVP");
	    pause(3000);
	    inputText(Calendar.add_Enter_Event_Description,Descrpt , "Enter Description");
	    pause(3000);
	    click(Calendar.click_Save_Exit_Btn,"click save button");
	    pause(3000);
	    if(isAlertPresent())
	    {
	    alertAccept();
	    }
	    pause(30000);
	    click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
	    pause(5000);
	    
		return true;
	}
	
	public boolean createEventPast_date(String eventType,String location,String Descrpt,Boolean Condition) 
	{
		//String enableordisablecomment=null; 
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.add_Event_creatBtn,"Click on Event");
		inputText(Calendar.add_Enter_Event, eventType, "Enter Event");
		pause(3000);
		click(Calendar.add_Click_DropDown,"Click on Dropdown");
	    pause(3000);
	    click(Calendar.select_Evnt_ChildGroup,"click on just group");
	    pause(2000);
	    click(Calendar.select_Evnt_ChildGroupsub,"clcik on parents");
	    click(Calendar.img_start_date,"clcik on start date");
	    click(Calendar.Previous_month,"clcik on previos month");
	    click(Calendar.Past_date,"clcik on  start Past date");
	    click(Calendar.img_end_date,"clcik on previos month");
	    click(Calendar.Previous_month,"clcik on start date");
	    click(Calendar.Past_date,"clcik on  end Past date");
	    
	    if(Condition)
	    {
	    click(Calendar.add_Disble_Comment,"Click on Disble comment");
	    pause(1000);
	    }
	    else
	    {
	    	//do nothing
	    	System.out.println("do noithing");
	    }
	    //click(Calendar.add_AllDaysCheckBox,"click on All Day Event");
	    inputText(Calendar.add_WhereLocation, location, "Enter Location");
	    click(Calendar.add_RSVP,"Select RSVP");
	    pause(3000);
	    inputText(Calendar.add_Enter_Event_Description,Descrpt , "Enter Description");
	    pause(3000);
	    click(Calendar.click_Save_Exit_Btn,"click save button");
	    alertAccept();
	    pause(30000);
	    click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
	    pause(5000);
		return true;
	}

	public boolean postEmail(String OptinalSubject, String TypeMessage) {
		click(Conversations.shareMessage,"Click on Post");
		
		inputText(Conversations.postSubject, OptinalSubject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, TypeMessage, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(5000);
		/*JavascriptExecutor scroll = ((JavascriptExecutor) getWebDriver());
		scroll.executeScript("window.scrollTo(0, document.body.scrollHeight);");*/
	    click(Conversations.selectChildGroup,"click On sub child");
		click(Conversations.clickPostButton,"Click Post");
		return true;
	}
	public boolean Edit_Post_Verfy(String OptinalSubject1, String TypeMessage1)  {
		click(Conversations.shareMessage,"Click on Post");
		
		inputText(Conversations.postSubject, OptinalSubject1, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, TypeMessage1, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(5000);
		/*JavascriptExecutor scroll = ((JavascriptExecutor) getWebDriver());
		scroll.executeScript("window.scrollTo(0, document.body.scrollHeight);");*/
	    click(Conversations.selectChildGroup,"click On sub child");
		click(Conversations.clickPostButton,"Click Post");
		return true;
	}
/*public boolean selectStartDate(String year, String month,String date )  {
		
		
		click(SignUpPage.input_Child_DOB,"open DOB popup");
			
			selectByVisibleText(SignUpPage.select_Year, year,"Select year");
			selectByVisibleText(SignUpPage.select_Month, month,"Select month");
			String dateXpath="//a[text()='"+date+"']";
			click(By.xpath(dateXpath),"Click on date");
			
				return true;
			}*/
/*public boolean selectToDate(String year, String month,String date )  {
	
	
	click(SignUpPage.input_Child_DOB,"open DOB popup");
		
		selectByVisibleText(SignUpPage.select_Year, year,"Select year");
		selectByVisibleText(SignUpPage.select_Month, month,"Select month");
		String dateXpath="//a[text()='"+date+"']";
		click(By.xpath(dateXpath),"Click on date");
		
			return true;
		}*/
	
	public boolean inviteNotificationfromAddParentpop(String parents,String env)
	{
		
		click(HomePage.img_mynetwork,"Click on my network");
		pause(2000);
		if(env.equalsIgnoreCase("maple_auto"))
		{
		click(HomePage.lnk_group_maple_auto,"clcik on a group");
		pause(1000);
		}
		else
		{
			click(HomePage.lnk_group_maple,"clcik on a group");
			pause(1000);
		}
		click(HomePage.lnk_Add_Parents,"click on add Parents ");
		pause(2000);
		click(HomePage.addstudentBox,"clcik the box to open the student list");
		pause(2000);
		By poststudent=By.xpath("//div[contains(text(),'"+parents+"')]");//Christina Adkinson (Craig Evans)
		click(poststudent,"Click On add student");
		click(HomePage.add_student_title,"click on add parents title");
		click(HomePage.btn_add_student_done_from_add_student,"click to add student from add parents section done");
		pause(5000);
		return true;
	}
	
	public boolean inviteNotification(String Invite,String env)//old one static value if you want to use dynamic value not use it
	{
		
		click(HomePage.img_mynetwork,"Click on my network");
		pause(2000);
		if(env.equalsIgnoreCase("maple_auto"))
		{
		click(HomePage.lnk_group_maple_auto,"clcik on a group");
		pause(1000);
		}
		else
		{
			click(HomePage.lnk_group_maple,"clcik on a group");
			pause(1000);
		}
		click(HomePage.lnk_invite_parent,"click on paret link ");
		pause(1000);
		click(HomePage.lnk_invitee_mail,"clcik on ivite via email");
		pause(5000);
		inputText(HomePage.InviteeEmail, Invite, "Enter Invitee");
		//getWebDriver().findElement(HomePage.InviteeEmail).sendKeys(Keys.ENTER); 
		pause(4000);
		//WebElement element = getWebDriver().findElement((HomePage.sendInvitee));
		//JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		//executor.executeScript("arguments[0].click();", element);
		click(HomePage.sendInvitee,"Click On Send Invite");
		return true;
	}
	public boolean inviteNotificationnew(String Invite,String groupname)//if you want a dynamic group selection in network page use this chinmoy(author)
	{
		
		click(HomePage.img_mynetwork,"Click on my network");
		pause(2000);
		String Groupname="//a[contains(text(),'"+groupname+"')]";
		click(By.xpath(Groupname),"Click on a  gorup ");
		
		click(HomePage.lnk_invite_parent,"click on paret link ");
		pause(1000);
		click(HomePage.lnk_invitee_mail,"clcik on ivite via email");
		pause(5000);
		inputText(HomePage.InviteeEmail, Invite, "Enter Invitee");
		getWebDriver().findElement(HomePage.InviteeEmail).sendKeys(Keys.TAB); 
		pause(4000);
		WebElement element = getWebDriver().findElement(By.xpath("//*[@class='doneit btn btn-primary pull-right']"));
		JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		executor.executeScript("arguments[0].click();", element);
		//click(HomePage.sendInvitee,"Click On Send Invite");
		return true;
	}
	public boolean inviteNotification_admistrator(String Invite,String groupname)//if you want a dynamic group selection in network page use this chinmoy(author)
	{
		
		click(HomePage.img_mynetwork,"Click on my network");
		pause(2000);
		String Groupname="//li[@id='Schools']/ul/li[2]";
		click(By.xpath(Groupname),"Click on a  gorup ");
		click(mynetwrok.lnk_invite_admisistrator,"click on administrator link ");
		pause(1000);
		inputText(HomePage.InviteeEmail, Invite, "Enter Invitee");
		getWebDriver().findElement(HomePage.InviteeEmail).sendKeys(Keys.TAB); 
		pause(4000);
		WebElement element = getWebDriver().findElement(By.xpath("//*[@class='doneit btn btn-primary pull-right']"));
		JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		executor.executeScript("arguments[0].click();", element);
		//click(HomePage.sendInvitee,"Click On Send Invite");
		return true;
	}	
		public boolean inviteNotificationstudentfromadddstudent(String env,String studentname ){
			
			click(HomePage.img_mynetwork,"click on my network section");
			pause(2000);
			if(env.equalsIgnoreCase("maple_auto"))
			{
			click(HomePage.lnk_group_maple_auto,"clcik on a group");
			pause(1000);
			}
			else
			{
				click(HomePage.lnk_group_maple,"clcik on a group");
				pause(1000);
			}
			click(HomePage.lnk_Add_Students,"click on add student ");
			pause(2000);
			click(HomePage.addstudentBox,"click the box to open the student list");
			pause(2000);
			By poststudent=By.xpath("//div[contains(text(),'"+studentname+"')]");//Aaliyah Smalls
			//a(poststudent, Invite, "Enter Invitee");
			click(poststudent,"Click On add student");
			click(HomePage.add_student_title,"click on add student title");
			click(HomePage.btn_add_student_done_from_add_student,"click to add student fro add student section done");
			
		
		
		
//		click(HomePage.inviteBox,"click on Invitee");
//		//click(HomePage.InviteeEmail,"Click In Email Box");
//		inputText(HomePage.InviteeEmail, Invite, "Enter Invitee");
//		//getWebDriver().switchTo().defaultContent();
//		click(HomePage.sendInvitee,"Click On Send Invite");
		pause(10000);
		return true;
	}
	
	public boolean visibiltyofgroupinstudent(String groupname)
	{
		By group_left_nav_condition= By.xpath("(//ul[@class='left-nav-tree']//a[contains(text(),'"+groupname+"')])[1]");
		isWebElementVisible(group_left_nav_condition, "group is displayed");
		return true;
	}
	public boolean visibiltyofgroupinTeacher(String groupname)
	{
		By group_left_nav_condition= By.xpath("//a[contains(text(),'"+groupname+"')]");
		isWebElementVisible(group_left_nav_condition, "group is displayed");
		return true;
	}
	public boolean acceptinvitation(String ChildFirstName,String ChildLastName, String groupname)
	{
//		new WebDriverWait(getWebDriver(), 30).until(ExpectedConditions.elementToBeClickable((HomePage.btn_accept_invitation)));
//		By chk_childname_checkbox= By.xpath("//label[contains(text(),'"+childname+"')]/input[@class='childname_checkbox']");
		By group_left_nav_condition= By.xpath("(//ul[@class='left-nav-tree']/li[@id='Family']//a[contains(text(),'"+groupname+"')])[1]");
//		
//		click(chk_childname_checkbox,"click to select the children");
		
		if(getWebDriver().findElement(By.id("invitee_new_group_name_0")).isDisplayed())
		{
			inputText(HomePage.grp_child_fname,ChildFirstName,"Enter child first name");
			inputText(HomePage.grp_child_lname,ChildLastName,"Enter child last name");
		}
		else
			click(HomePage.grp_checkbox,"Click on check box");
		click(HomePage.btn_accept_join,"click on acept to join the group");
//		
//		click(HomePage.btn_accept_invitation,"Acept the invitaion to join");
		//isWebElementDisplayed(group_left_nav_condition, "group is displayed");
		//System.out.println(group_left_nav_condition);
//		isWebElementVisible(group_left_nav_condition, "group is displayed");
		return true;
	}
	public boolean aceptinvitationPrincipal()
	{
		pause(5000);
		click(HomePage.btn_accept_invitation,"Acept the invitaion to join");
		getWebDriver().navigate().refresh();
		return true;
	}
	
	public boolean acceptinvitationTeacher(String groupname)
	{
		//new WebDriverWait(getWebDriver(), 30).until(ExpectedConditions.elementToBeClickable((HomePage.btn_accept_invitation)));
		By chk_group_checkbox= By.xpath("//label[contains(text(),'"+groupname+"')]/input[@class='childname_checkbox']");
		click(chk_group_checkbox,"click to select the children");
		click(HomePage.btn_accept_invitation,"Acept the invitaion to join");
		return true;
	}
	public boolean createCampaign(String giveTitle, String amount,String usefund,boolean on)  
	{
		click(HomePage.give_icon_img,"click on Give");
		pause(1000);
		click(Campaign.campaignClick,"Campaign click done");
		inputText(Campaign.campaignTitle, giveTitle, "Data Enter");
		inputText(Campaign.campaignTargetAmt, amount, "Amount Enter");
		pause(6000);
		//changedate();
		//click(Campaign.cmpgn_target_option,"click Option");
		getWebDriver().switchTo().frame(0);
		inputText(Campaign.campaignDescrption, usefund, "Data Enter");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		click(Campaign.cmpgn_DropDown,"Click DropDown");
		click(Campaign.cmpgn_element,"click Element");
		click(Campaign.select_Cmpgn_ChildGroup,"Click Just Parent");
		click(Campaign.select_Cmpgn_ChildGroup,"Click  Parent");//change made by chinmoy
		if(on)
		{
		click(Campaign.disable_Comment,"Click Diable comment");
		}
		else
		{
			//donothing
			System.out.println("do nothing");
		}
        click(Conversations.clickCreateCampaianButton,"Click create");
        pause(60000);
		return true;
		
	}
	public boolean forgotPassword(String emailAddress) 
	{
		isWebElementVisible(LoginPage.forgtpaswrd_Link, "Link Verify succesfull");
		click(LoginPage.forgtpaswrd_Link,"Click on Forgot Password");
		pause(2000);
		inputText(LoginPage.recovry_Mail_Address, emailAddress, "Enter Recovery Mail ");
		pause(1000);
		click(LoginPage.request_Btn,"Click on Request");
		
		return true;
	}
	
	public boolean commentEmail(String OptSub,String typeMsg,String Comment)  {
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, OptSub, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, typeMsg, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(5000);
		click(Conversations.selectChildGroup,"click On sub child");
		click(Conversations.selectSpeficChildFromGroup,"click on spefic childin a group");
		pause(5000);
		click(Conversations.clickPostButton,"Click Post");
		pause(10000);
		 /* List<WebElement> allEmails = getWebDriver().findElements(By.xpath("//span[contains(text(),'"+Comment+"')]"));
		  Iterator<WebElement> itr = allEmails.iterator();
	        System.out.println(itr.next());
	        itr.next().click();*/
	       pause(5000);
		return true;
		
	}
	
	public boolean gmailLogin(String Subject )  {
		  //verify email is received
		    getWebDriver().manage().deleteAllCookies();
		    pause(1000);
		   getWebDriver().get(ConfigReader.getValue("Gmail_URL"));
		    pause(1000);
		    inputText(GmailOR.EmailTextbox,ConfigReader.getValue("GmailUsername"),"Email Textbox");
		    click(GmailOR.EmailNext,"Next button");
		     pause(3000);
		    inputText(GmailOR.PwdTextbox,ConfigReader.getValue("GmailPassword"),"Password Textbox");
		    click(GmailOR.PasswordNext,"Next button");
		     pause(3000);
		    
		    //verify gmail home page
		    if(!isWebElementVisible(GmailOR.GmailHome,"Gmail Home Page")) {
		    	
		     pause(10000);
		     System.out.println("I am in");
		     }
		    else { 
		   // System.out.println("Gmail login is successful");
		    pause(5000);
		    click(GmailOR.InboxRefresh,"Click On Inbox Refresh");
		    pause(10000);
		    
		    }
		    List<WebElement> allEmails = getWebDriver().findElements(By.xpath("//span[contains(text(),'"+Subject+"')]"));
		    Iterator<WebElement> itr = allEmails.iterator();
		        System.out.println(itr.next());
		        itr.next().click();
		        pause(4000);
		     
		        List<WebElement> elements = getWebDriver().findElements(By.xpath(".//*[@src ='https://ci3.googleusercontent.com/proxy/xGPI4dNeUbAYN0nhyLRvOWee2c7dNCYOB4-1Ts0kBXfDNGQlw7mdF-27clNi3KXVviQutDRAuTvVjWs1VXJsnN71a0xA4Fc4MhVlRB12YihGY-WqItjoDXTRFdIEYkKe=s0-d-e1-ft#https://d2bjyl80rberi2.cloudfront.net/graphics_v3/email_notification/lion.png']"));
		        java.util.Iterator<WebElement> program = elements.iterator();
		        while (program.hasNext()) {
		            String values = program.next().getText();

		            if(!values.equals("null"))
		            {
		               // System.out.println("Test Pass");
		            }
		            else
		            {
		              //  System.out.println("Test Fail");
		            }

		        }
			return true;	
	}
		
	public boolean gmailLogin()  
	{
		  //verify email is received
		    getWebDriver().manage().deleteAllCookies();
		    pause(1000);
		   getWebDriver().get(ConfigReader.getValue("Gmail_URL"));
		    pause(1000);
		    inputText(GmailOR.EmailTextbox,ConfigReader.getValue("GmailUsername"),"Email Textbox");
		    click(GmailOR.EmailNext,"Next button");
		     pause(3000);
		    inputText(GmailOR.PwdTextbox,ConfigReader.getValue("GmailPassword"),"Password Textbox");
		    click(GmailOR.PasswordNext,"Next button");
		     pause(3000);
		    
		    //verify gmail home page
		    if(!isWebElementVisible(GmailOR.GmailHome,"Gmail Home Page")) {
		    	
		     pause(10000); }
		    else { 
		   // System.out.println("Gmail login is successful");
		    pause(5000);
		    click(GmailOR.InboxRefresh,"Click On Inbox Refresh");
		    pause(10000);
		    isWebElementVisible(GmailOR.GmailHome,"Gmail Home Page");
		    }
			return true;	
	}
	public boolean refreshGmail()  {
		  //verify email is received
		    getWebDriver().manage().deleteAllCookies();
		    pause(1000);
		   getWebDriver().get(ConfigReader.getValue("Gmail_URL"));
		    pause(1000);
		    click(GmailOR.InboxRefresh,"Click On Inbox Refresh");
		    return true;	
	}
	



	public boolean signUp(){
		click(LoginPage.btn_SignUp,"Click on SignUp");
		click(LoginPage.btn_singup_engaged,"Click on SignUp");
		getWebDriver().get(ConfigReader.getValue("SIGN_UP_URL_LIVINGTREE_MAPLE"));
		pause(3000);
		return true;
		//return isWebElementVisible(SignUpPage.txt_Student);
	}
	
	public boolean studentAccount() {
		
		click(SignUpPage.txt_Student,"Click on Student");
		return isWebElementVisible(SignUpPage.input_Student_Email);
	}
	public boolean parentAccount() {
			
			click(SignUpPage.txt_Parent,"Click on Parent");
			return isWebElementVisible(SignUpPage.input_Student_Email);
		}
	public boolean coachAccount() {
		
		click(SignUpPage.txt_Group,"Click on Group");
		click(CreateGroupPage.rbn_Group_Youth,"Click on Youth radiobox");
		click(CreateGroupPage.btn_Group_Next,"Click on Next");
		return true;
		
	}
	public boolean adultAccount()
	{
		click(SignUpPage.txt_Group,"Click on Group");
		click(CreateGroupPage.rbn_Group_NoChild,"Click on Youth radiobox");
		click(CreateGroupPage.btn_Group_Next,"Click on Next");
		return true;
	}
	
	public boolean studentNext() {
		
		click(SignUpPage.chk_Student_IAgreeToLivingTree,"Click on Agree To LivingTree checkbox");
		click(SignUpPage.btn_Next,"Click on Next button");
	
		return true;
	}
	public boolean parentNext() {
	//click(SignUpPage.chk_Student_IAgreeToLivingTree,"Click on Agree To LivingTree checkbox");
	click(SignUpPage.btn_Next,"Click on Next button");
	return true;
}

	public void  create_group_from_add_conversation(String groupName)
	{
		click(Conversations.add_group__from_plus_icon,"click on + icon to add group");
		pause(5000);
		click(Conversations.add_group_form_create_group_page,"click to add a group");
		pause(5000);
		click(Conversations.btn_next,"click on next button");
		pause(5000);
		inputText(Conversations.txt_input_group_name,groupName ,"Enter groupName "+groupName);
		pause(1000);
		Select se = new Select(getWebDriver().findElement(By.xpath("//select[@name='profile_type']")));
		se.selectByVisibleText("Elementary School Class");
		pause(3000);
		click(Conversations.chk_organization_in_group_under,"click the organozation");
		pause(5000);
		click(Conversations.btn_done_group,"add new group");
		pause(10000);
		click(Conversations.btn_skip_add_people,"skip add pelple");
		pause(8000);
		
	}
	public boolean selectDOB(String year, String month,String date )  {
		
		
		click(SignUpPage.input_Child_DOB,"open DOB popup");
			
			selectByVisibleText(SignUpPage.select_Year, year,"Select year");
			selectByVisibleText(SignUpPage.select_Month, month,"Select month");
			String dateXpath="//a[text()='"+date+"']";
			click(By.xpath(dateXpath),"Click on date");
			
				return true;
			}
	public boolean profileInformation(String email, String password, String confirmPassword, String firstName, String lastName, String  year, String month, String date, String zipCode, String address , String  city, String  state, String country, String timeZone,String gender, String telephone )  {
		
		
		inputText(SignUpPage.input_Student_Email,email ,"Enter Email "+email);
		inputText(SignUpPage.input_Student_Password, password,"Enter Password");
		inputText(SignUpPage.input_Student_ConfirmPassword, confirmPassword,"Enter Confirm Password");
		inputText(SignUpPage.input_Student_FirstName, firstName,"Enter firstName");
		inputText(SignUpPage.input_Student_LastName, lastName,"Enter lastName");
		
		click(SignUpPage.input_Student_DOB,"open DOB popup");
		
		selectByVisibleText(SignUpPage.select_Year, year,"Select year");
		selectByVisibleText(SignUpPage.select_Month, month,"Select month");
		String dateXpath="//a[text()='"+date+"']";
		click(By.xpath(dateXpath),"Click on date");
	
		//inputText(SignUpPage.input_Student_DOB, DOB,"Enter DOB");
		click(SignUpPage.input_Student_Zipcode,"remove DOB popup");
		
		inputText(SignUpPage.input_Student_Zipcode, zipCode,"Enter Zipcode");
		inputText(SignUpPage.input_Student_Address1, address,"Enter Address");
		inputText(SignUpPage.input_Student_City, city,"Enter City");	
		selectByVisibleText(SignUpPage.select_Student_Country, country,"Select Country");
		selectByValue(SignUpPage.select_Student_CountryTimeZone, timeZone,"Select TimeZone");
		
		selectByVisibleText(SignUpPage.select_Student_State, state,"Select State");
		selectByVisibleText(SignUpPage.select_Student_Gender, gender,"Select Gender");	
		inputText(SignUpPage.input_Student_Telephone, telephone,"Enter Telephone");
		pause(5000);
		return true;
	}
public boolean skipGroupPopup() {
		
		
		if(isWebElementVisible(Common.btn_Close,10)){
			click(Common.btn_Close,"Click on Close button");
		}
		if(isWebElementVisible(SignUpPage.btn_Group_Skip,10)){
			click(SignUpPage.btn_Group_Skip,"Click on Skip button");
		}
		if(isWebElementVisible(Common.img_ClickHereToClose,10)){
			click(Common.img_ClickHereToClose,"Click on Close button");
		}
		
		
		//isWebElementVisible(HomePage.tab_LeftNavigation_ALL,"Verify ALL label in Left Navigation");
		//isWebElementVisible(HomePage.tab_LeftNavigation_Me,"Verify Me label in Left Navigation");
		/*isWebElementVisible(HomePage.tab_LeftNavigation_Family,"Verify Family label in Left Navigation");
		isWebElementVisible(HomePage.tab_LeftNavigation_Groups,"Verify Groups label in Left Navigation");
		isWebElementVisible(HomePage.tab_LeftNavigation_Organizations,"Verify Organizations label in Left Navigation");
		*/
		isWebElementVisible(HomePage.tab_TopNavigation_Conversations,"Verify Conversations label in Top Navigation");
		isWebElementVisible(HomePage.tab_TopNavigation_Calendar,"Verify Calendar label in Top Navigation");
		//isWebElementVisible(HomePage.tab_TopNavigation_Give,"Verify Give label in Top Navigation");
		isWebElementVisible(HomePage.tab_TopNavigation_Inbox,"Verify Inbox label in Top Navigation");
		
		isWebElementVisible(HomePage.tab_TopNavigation_MyNetwork,"Verify My Network label in Top Navigation");
		//isWebElementVisible(HomePage.tab_TopNavigation_Directory,"Verify Directory label in Top Navigation");
		
			return true;
		}

	public boolean skipGroupPopupTeacher() {
		
		By btn_Close=By.xpath("(//button[@class='close close_btn'])[5]");
		if(isWebElementVisible(btn_Close,10)){
			click(btn_Close,"Click on Close button");
		}
		
			return true;
		}

	public boolean familyInformation(String email, String message )  {
			
			
			inputText(SignUpPage.input_FamilyName, getRandomString(email),"Enter Family Name");
			inputText(SignUpPage.input_InviteEmail, getRandomEmail(email),"Enter Invite Name");
			inputText(SignUpPage.input_InviteMessage, message,"Enter Message");
			pause(3000);
			click(SignUpPage.btn_Family_Next,"Click on Next button");
			return true;
		}
	public boolean childInformation(String firstname, String lastname ,String year, String month,String date,String gender)  {
		
		
		inputText(SignUpPage.input_Child_FirstName, firstname,"Enter Child First Name");
		inputText(SignUpPage.input_Child_LastName, lastname,"Enter Child Last Name");
		selectDOB(year, month, date);
		click(SignUpPage.input_Child_LastName,"Remove DOB Pop Up");
		selectByVisibleText(SignUpPage.select_Child_Gender, gender,"Select Gender");
		click(SignUpPage.btn_Child_Done,"Click on Done button");
		return true;
	}
	public boolean groupNext() {
		
		//click(SignUpPage.chk_Student_IAgreeToLivingTree,"Click on Agree To LivingTree checkbox");
		click(CreateGroupPage.btn_Group_Profile_Next,"Click on Next button");
		return true;
	}

	public boolean groupName(String profile) {
			
		inputText(CreateGroupPage.input_Group_Profile_Name, getRandomString(profile),"Enter profile(Group Name)");
		click(Common.btn_Done,"Click on Done");
		
		
		//return isWebElementVisible(SignUpPage.input_Student_Email);
		return true;
		
	}
	
	public boolean postText(String subject, String message)
	{
		click(Conversations.shareMessage,"Click on Post");
		
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		try {
			scrollDowntoEnd("scroll to end");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pause(5000);
		click(Conversations.selectChildGroup,"click On sub child");
		click(Conversations.selectSpeficChildFromGroup,"Click on Group Auto Test ");//change according  to test script made by chinmoy
		//By groupXpath=By.xpath("//span/a[text()='"+group+"']");
		//click(Conversations.picker_Group_AutoTest,"Click on Group Auto Test ");
		
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(60000);
		//By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		//isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean editPostText(String subject, String message)  {
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		
		System.out.println(EditPostXpath);
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].click();", getWebElement(EditPostXpath));
		//click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		String subjectEdit= subject+getRandomString2();
		
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");
		
		isWebElementVisible(PostXpath);
		isWebElementPresent(Conversations.lbl_FirstUpdatedPost,"Verify post updated Top on Conversations");
		
		return true;
	}
	public boolean editDeleteAddGroupPostText(String group, String group2,String subject, String message) 
	{
		
		By groupXpath2=By.xpath("//a[text()='"+group2+"']");
		try {
			scrolltoElement(groupXpath2, "scroll to element");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pause(5000);
		System.out.println(groupXpath2);
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].click();", getWebElement(groupXpath2));
		click(groupXpath2,"Click on Group");	
		By EditPostXpath=By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::a[@class='post-edit'][1]");
		isWebElementNotPresent2(Conversations.btn_EditFirstPost,"Verify the post edit icon not available");
		By groupXpath=By.xpath("//a[text()='"+group+"']");
		click(groupXpath,"Click on Group");
		click(HomePage.lnk_livingtree,"Refresh the living tree");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		isWebElementVisible(PostXpath);
		isWebElementVisible(Conversations.btn_EditFirstPost);
		click(EditPostXpath,"Click on post edit Icon ");
		String subjectEdit= subject+getRandomString2();
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"click on dropdown");
		//By groupCloseXpath=By.xpath("//span[contains(text(),'"+group+"')]/preceding-sibling::a");//old one use in maple-auto
		//By groupCloseXpath=By.xpath("//a[a[@class='group_listings'][contains(text(),'"+group+"')]/preceding-sibling::a");
		By groupCloseXpath=By.xpath("//a[@class='group_listings'][contains(text(),'"+group+"')]");//newly define
		click(groupCloseXpath,"Click on Group ");
		By groupCloseXpathchild=By.xpath("//a[@class='group_listings'][contains(text(),'"+group+"')]/parent::span/following-sibling::div/ul/li/span[1]");
		click(groupCloseXpathchild,"Click on Group child ");
		//click(Conversations.selectDropDown,"Click on Option");
		
		//By group2Xpath=By.xpath("//span/a[text()='"+group2+"']");//a[@class='group_listings'][contains(text(),'"+group+"')] old one
		By group2Xpath=By.xpath("//a[@class='group_listings'][contains(text(),'"+group2+"')]");
		click(group2Xpath,"Click on Group ");	
		
		click(Conversations.selectChildGroup,"click On sub child");
		
		pause(2000);
		ifAlertDismiss();
		//span[contains(text(),'Automation')]/preceding-sibling::a
		//click(Conversations.selectSpeficchildFromGroupAfterSelectingGroup,"click on sepfic child");
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath2=By.xpath("//span[text()='"+subjectEdit+"']");
		
		isWebElementVisible(PostXpath2);
		isWebElementPresent(Conversations.lbl_FirstUpdatedPost,"Verify post updated Top on Conversations");
		
		return true;
	}
	public boolean editDeleteAddGroupPostText2(String group2,String subject, String message,String subjectEdit) 
	{
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage,message+getRandomString2(), "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		click(Conversations.selectDropDown,"Click on Option");
		
		String groupname="//a[@class='group_listings' and text()='"+group2+"']";
		click(By.xpath(groupname),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+group2+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+group2+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		//postSchedule_4min();
		pause(2000);
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(120000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		By PostXpath2=By.xpath("//span[text()='"+subjectEdit+"']");
		
		isWebElementVisible(PostXpath2);
		isWebElementPresent(Conversations.lbl_FirstUpdatedPost,"Verify post updated Top on Conversations");
		
		return true;
	}
	public boolean postPhone(String subject, String message)  {
		click(Conversations.shareMessage,"Click on Post");
		click(Conversations.btn_Phone,"Click on Phone");
		
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		//getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_Post_Message, message, "Enter Message");
		//getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		click(Conversations.selectSpeficChildFromGroup,"select a child item from group");//change made by chinmoy to ensure the test case pass
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		isWebElementNotPresent2(Conversations.btn_EditFirstPost,"Verify Phone post edit Icon not Available");
		
		return true;
	}
	public boolean postPhoto(String photoPath, String subject, String message)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Photos,"Click on Photos");
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		//click(Conversations.selectChildGroupauto,"click On sub child");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(1000);
		click(Conversations.selectSpeficChildFromGroup,"click On a Sricfic element in a group");
		pause(2000);
		
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		ifAlertDismiss();
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postPoll(String Answer1,String Answer2,String Answer3,String Answer4,String Answer5,String Answer6,String subject, String message, String groupName,String type_message)
	{
		pause(5000);
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		click(Conversations.btn_add_polls,"click on add poll to add poll post");
		isWebElementPresent(Conversations.btn_Photos, "check the photo button");
		isWebElementPresent(Conversations.btn_Video, "check the video button");
		isWebElementPresent(Conversations.btn_Files, "check the file button");
		getWebDriver().switchTo().frame(0);
		isWebElementgettextvalidate(Conversations.placeholderafteraddPollOptions,type_message);
		getWebDriver().switchTo().defaultContent();
		WebElement element = getWebDriver().findElement((Conversations.btn_ans_add_answer));
		inputText(Conversations.type_poll_ans1, Answer1, "Enter first Answer");
		inputText(Conversations.type_poll_ans2, Answer2, "Enter Second Answer");
		if(element.isDisplayed())
		{
			JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
			executor.executeScript("arguments[0].click();", element);
			logStepPass("click on add answer button");
		}
		//click(Conversations.btn_ans_add_answer,"click on add answer button");
		inputText(Conversations.type_poll_ans3, Answer3, "Enter third Answer");
		if(element.isDisplayed())
		{
			JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
			executor.executeScript("arguments[0].click();", element);
			logStepPass("click on add answer button");
		}
		//click(Conversations.btn_ans_add_answer,"click on add answer button");
		inputText(Conversations.type_poll_ans4, Answer4, "Enter forth Answer");
		if(element.isDisplayed())
		{
			JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
			executor.executeScript("arguments[0].click();", element);
			logStepPass("click on add answer button");
		}
		//click(Conversations.btn_ans_add_answer,"click on add answer button");
		inputText(Conversations.type_poll_ans5, Answer5, "Enter fifth Answer");
		if(element.isDisplayed())
		{
			JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
			executor.executeScript("arguments[0].click();", element);
			logStepPass("click on add answer button");
		}
		//click(Conversations.btn_ans_add_answer,"click on add answer button");
		inputText(Conversations.type_poll_ans6, Answer6, "Enter fifth Answer");
		
		getWebDriver().switchTo().defaultContent();
		try {
			scrolltoElement(Conversations.btn_Submit, "scroll submoit button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(Conversations.selectDropDown,"select the dropdown");
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");	
		isWebElementVisible(PostXpath);	
		return true;
	}
	public boolean editPoll(String subject, String message, String groupName)
	{
		pause(6000);
		By postFESScroe= By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"+subject+"']/following::div[@class='engage-score']/p)[1]");
		isWebElementVisible(postFESScroe,"Check FES Score is available");
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		pause(3000);
		String subjectEdit= subject+getRandomString2();
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		//inputText(Conversations.input_PostMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		/*click(Conversations.btn_add_polls,"click on add poll to add poll post");
		inputText(Conversations.type_poll_ans1, Answer1, "Enter first Answer");
		inputText(Conversations.type_poll_ans2, Answer2, "Enter Second Answer");*/
		getWebDriver().switchTo().defaultContent();
		try {
			scrolltoElement(Conversations.btn_Submit, "scroll submit button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(Conversations.selectDropDown,"select the dropdown");
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");	
		isWebElementVisible(PostXpath);	
		return true;
	}
	public boolean postVideo_disablecomment(String videoPath, String subject, String message, String groupName,boolean on)  
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		/*inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);*/
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		try {
			scrollDown(By.xpath(groupParents),"scroll to element");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pause(5000);
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		
		/*click(Conversations.btn_Photos,"Click on Photos");
		fileUpload(photoPath);*/
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		fileUpload(videoPath);
		pause(45000);
		scrollTo(Conversations.txt_comntDisbleCheckBox);
		if(on)
		{
		click(Conversations.txt_comntDisbleCheckBox,"enable the comment box  through text");
		click(Conversations.txt_comntDisbleCheckBox,"disable the comment box  through text");
		click(Conversations.chk_DisableBox,"enable the comment box  through check box");
		}
		else
		{
			System.out.println("do nothing");
		}
		getWebDriver().switchTo().defaultContent();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(120000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postPhoto_disablecomment(String photoPath, String subject, String message, String groupName,boolean on)  
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		try {
			scrollDown(By.xpath(groupParents),"scroll to element");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pause(5000);
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		
		click(Conversations.btn_Photos,"Click on Photos");
		fileUpload(photoPath);
		pause(10000);
		scrollTo(Conversations.txt_comntDisbleCheckBox);
		if(on)
		{
		click(Conversations.txt_comntDisbleCheckBox,"enable the comment box  through text");
		click(Conversations.txt_comntDisbleCheckBox,"disable the comment box  through text");
		click(Conversations.chk_DisableBox,"enable the comment box  through check box");
		}
		else
		{
			System.out.println("do nothing");
		}
		getWebDriver().switchTo().defaultContent();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}	
	public boolean postPhoto(String photoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Photos,"Click on Photos");
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		try {
			scrollDown(By.xpath(groupParents),"scroll to element");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pause(5000);
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean shareoptioncheck(String subject)
	{
		boolean eleSelected= getWebDriver().findElements(By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Share']")).size() >0;
		System.out.println(eleSelected);
		if(!eleSelected)
		{
			printLog("share option not diaplayed");
		}
		else
		{
			printLog("share option is displayed");
		}
		return true;
	}
	public boolean postPhoto2(String photoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		
		click(Conversations.btn_Photos,"Click on Add Photo");
		fileUpload(photoPath);
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(55000);
		isWebElementVisible(Conversations.btn_RemovePhoto,"Verify Remove Photo button");
		ifAlertDismiss();
		
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		
		return true;
	}
	public boolean postPhotoOnlyParents(String photoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Photos,"Click on Photos");
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	
	public boolean postPhotoOnlyParents2(String photoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Photos,"Click on Photos");
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		pause(3000);
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		/*By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);*/
		
		return true;
	}
	public void isDownloadOptionavailableinPhoto()
	{
		click(Conversations.lbl_first_photo,"click on the photo");
		pause(8000);
		verifyWebElementPresent(Conversations.lbl_download_option,"download option is available");
		click(Conversations.lbl_close_in_photo_galary_screen,"close the galary screen");
	}
	public boolean isClickAndCommentonIndividualPhoto(String comment,String error) throws Exception
	{
		click(Conversations.lbl_first_photo,"click on the photo");
		pause(8000);
		inputText(Conversations.input_comment_photo_gallery, comment, "inappropriate comment on photo");
		pause(2000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_post_comment_photo_gallery,"post comment on photo");
		pause(2000);
		String alert=alertText();
		System.out.println(alert);
		compareTwoValues(alert,error);
		alertAccept();
		pause(5000);
		click(Conversations.lbl_close_in_photo_galary_screen,"close the galary screen");
		return true;
	}
	public boolean isAttendance_Alert(String filePath,String School_name)
	{
	
		click(Conversations.lnk_send_attendance_alerts,"click on attendance alerts");
		pause(5000);
		isWebElementgettextvalidate(Conversations.lbl_attendance_alert_final, "Attendance Alerts");
		isWebElementPresent(Conversations.lbl_attendance_alert_final,"Atendace Alert page is displayed");
		click(Conversations.sel_list_school_for_attendance,"select drop down achool");
		Select drpschl = new Select(getWebDriver().findElement(By.id("list_schools")));
		drpschl.selectByVisibleText(School_name);
		//drpschl.selectByValue("28572243");
		pause(6000);
		//click(Conversations.btn_upload_attendance_file,"button click to upload file");
		//WebElement btn_upload_attendance_file = getWebDriver().findElement(By.id("attendance_file"));
		//btn_upload_attendance_file.sendKeys(Keys.ENTER);
	/*	Actions actions = new Actions(getWebDriver());
	    actions.sendKeys(btn_upload_attendance_file, Keys.RETURN).perform();*/
		//click(btn_upload_attendance_file,"button click to upload file");
	    inputText2(Conversations.btn_upload_attendance_file, filePath, "Enter file Path");
		fileUpload(filePath);
		pause(15000);
		drpschl.selectByVisibleText(School_name);
		click(Conversations.btn_upload_button,"upload the file");
		isWebElementgettextvalidate(Conversations.lbl_Attendance_Alerts_upload_sucessful, "Preview - Alerts will be sent for a total of 1 students of hinduschool:");
		scrollTo(Conversations.btn_next_attendace);
		scrollToBottom();
		click(Conversations.btn_next_attendace,"click on next button o attendance alert");
		pause(5000);
		isWebElementPresent(Conversations.lbl_attendance_alert_final,"check submoission page is displayed");
		isWebElementgettextvalidate(Conversations.lbl_attendance_alert_final, "Attendance Alerts");
		isWebElementPresent(Conversations.btn_cancel_Attendance_Alerts, "cancel button is diaplayed");
		isWebElementPresent(Conversations.btn_previous_Attendance_Alerts,"previous button is displayed");
		isWebElementPresent(Conversations.btn_Approve_and_Send_Attendance_Alerts,"Approve and Send Attendance Alert is displayed");
		click(Conversations.btn_Approve_and_Send_Attendance_Alerts,"send attendance alert");
		pause(5000);
		isWebElementPresent(Conversations.lbl_attendance_alert_final,"Final submoission page is displayed");
		isWebElementgettextvalidate(Conversations.lbl_attendance_alert_final, "Attendance Alerts");
		click(Conversations.btn_Continue_final_submission_page,"final submission of attendance alert");
		pause(5000);
		//
		//isWebElementVisible(Conversations.lbl_error, "label is visiable");
		return true;
	}
	public boolean postSchedulePhotoOnlyParents(String photoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Photos,"Click on Photos");
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/postSchedule();
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postVideo(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(2000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(20000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(15000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(3000);		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postVideo2(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		/*inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		
		click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
	*/	fileUpload(videoPath);
		pause(45000);
		//pause0(3000);
		isWebElementVisible(Conversations.btn_RemoveVideo,"Verify Remove Video button");
		ifAlertDismiss();
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		ifAlertDismiss();
		pause(15000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(3000);		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		pause(10000);
		return true;
	}
	
	
	public boolean postPhoneMessage2(String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();		
		click(Conversations.btn_AddPhoneMessage,"Click on Add Phone Text Message");
		
		isWebElementVisible(Conversations.btn_RemovePhoneMessage,"Verify Remove Text Message button");
		
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on I want to share this with Option");
		click(Conversations.txt_JustMe,"click On Just Me");		
		pause(2000);
		ifAlertDismiss();	
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(3000);		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		
		return true;
	}
	public boolean postPhoneAlert2( String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		
		click(Conversations.btn_AddPhoneAlert,"Click on Add Phone Alert button");
		isWebElementVisible(Conversations.btn_RemovePhoneAlert,"Verify Remove Phone Alert button");		
		click(Conversations.selectDropDown,"Click on I want to share this with Option");
		click(Conversations.txt_JustMe,"click On Just Me");		
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(3000);		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		
		return true;
	}
	public boolean postVideoOnlyParents(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(15000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/pause(15000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(8000);				
		isWebElementVisible(PostXpath);
		
		return true;
	}
	
	public boolean postVideoOnlyParents2(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		//click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(15000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/pause(15000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(8000);
		pause(180000);
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleVideoOnlyParents(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(15000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);		
		click(Conversations.selectDropDown,"Click on Option");
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/postSchedule();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);		
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(3000);				
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleVideoOnlyParents2(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		//click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(15000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);		
		click(Conversations.selectDropDown,"Click on Option");
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
				//+ "/parent::span/following-sibling::ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/postSchedule();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);		
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(8000);				
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postFile(String filePath, String subject, String message, String groupName)  {
		pause(5000);
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Files,"Click on File");
		pause(2000);
		inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		pause(10000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 2)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		ifAlertDismiss();
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postFile2(String filePath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(2000);
		click(Conversations.btn_Files,"Click on Add File");
		fileUpload(filePath);
		
		//inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		pause(10000);
		//pause(3000);
		isWebElementVisible(Conversations.btn_RemoveFile,"Verify Remove File button");
		ifAlertDismiss();
		
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 2)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		ifAlertDismiss();
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		ifAlertDismiss();
		
		return true;
	}
	public boolean postFileforElementaryPrincipal(String filePath, String subject, String message)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(2000);
		click(Conversations.btn_Files,"Click on Add File");
		fileUpload(filePath);
		
		//inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		pause(10000);
		//pause(3000);
		isWebElementVisible(Conversations.btn_RemoveFile,"Verify Remove File button");
		ifAlertDismiss();
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		click((Conversations.selectGroupDropdownForelementtaryPrincipal),"Click on Group");
		if(!isWebElementPresent((Conversations.selectGroupDropdownForelementtaryPrincipal), 2)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		click((Conversations.selectGroupDropdownForelementtaryPrincipal),"Click on Group");
		}
		pause(1000);
		String groupstaff="(//span[contains(text(),'Aberdeen Elementary School Staff')])[1]";
		click(By.xpath(groupstaff),"Click on Parents");
		String groupParents="(//span[contains(text(),'Aberdeen Elementary School Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//span[contains(text(),'Aberdeen Elementary School Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		
		return true;
	}

	public boolean postFileForFamily(String filePath, String subject, String message, String FamilyName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(2000);
		click(Conversations.btn_Files,"Click on Add File");
		fileUpload(filePath);
		
		//inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		pause(10000);
		//pause(3000);
		isWebElementVisible(Conversations.btn_RemoveFile,"Verify Remove File button");
		
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		pause(2000);
		click(Conversations.txt_JustMe,"Click on just me");
		String groupParents="//a[@class='group_listings' and text()='"+FamilyName+"']";
		click(By.xpath(groupParents),"Click on family");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath,"Verify new post subject "+subject);
		
		return true;
	}
	public boolean postFileOnlyParents(String filePath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Files,"Click on File");
		pause(2000);
		inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		pause(10000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 4)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/pause(2000);
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postPhone(String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Phone,"Click on Phone");				
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean likeAndAnswerPollPost(String  subject, String answernumber,String message) {
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		By postLike= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postAns=null;
		if(isWebElementVisible(Conversations.btn_add_poll_ans, "Ans option is not visiable"))
		{
		postAns=By.xpath("(//span[@class='post_description' ] /p[text()='"+message+"']/parent::span/following-sibling::div/div//input)["+answernumber+"]");
		System.out.println(postAns);
		}
		By postTotalLikes= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		scrollTo(postTitle);
		isWebElementVisible(postLike,"Verify Like");
		isWebElementVisible(postTitle,"Verify Post");
		click(postLike,"Click on Like");
		if(postAns!=null && isWebElementPresent(postAns))
		{
		click(postAns,"click on ans");
		//logStepFail("post ans is not available");
		WebElement element = getWebDriver().findElement(Conversations.btn_add_poll_ans);
		if(element.isDisplayed())
		{
		JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		executor.executeScript("arguments[0].click();", element);
		}
		}
		else
		{
			isWebElementVisible(Conversations.btn_add_poll_ans, "not able to ans it");
		}
		//click(Conversations.btn_add_poll_ans,"ans the qus");
		pause(5000);
		getText(postTotalLikes);
		
		return true;
	}
	
	public boolean likeAndCommentPost(String  subject, String comment)  {
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		By postLike= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postComment= By.xpath("//span[contains(text(),subject)]/following::li/a[@title='Click to comment on this']");
		By postTotalLikes= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		//scrollTo(postTitle);
		
		ifAlertDismiss();
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		isWebElementVisible(postComment,"Verify comment");
		System.out.println(postLike);
		pause(5000);
		//isWebElementVisible(postTotalLikes,"Verify Total Likes");
		click(postLike,"Click on Like");
		
		By enterComment = By.xpath("//textarea[@placeholder='Write a comment...']");
		inputTextWithEnter(enterComment, comment, "Enter Comment");
		// hitEnter();
		pause(2000);
//		getText(postTotalLikes);
	
		//isWebElementVisible(postTotalLikes);
		
		return true;
	}


public boolean likeAndCommentPostedited(String  subject, String comment)  {
	By postTitle= By.xpath("//span[contains(text(),subject)]");
	By postLike= By.xpath("(//span[contains(text(),subject)]/following::span[text()='Like'])[1]");
	By postComment= By.xpath("(//span[contains(text(),subject)]/following::li/a[@title='Click to comment on this']");
	//By postComment= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::li/a[@title='Click to comment on this']");
	By postTotalLikes= By.xpath("(//span[contains(text(),subject)]/following::span[starts-with(@id,'like-count')])[1]");
	isWebElementVisible(postTitle,"Verify Post");
	isWebElementVisible(postLike,"Verify Like");
	isWebElementVisible(postComment,"Verify comment");
	System.out.println(postLike);
	pause(5000);
	//isWebElementVisible(postTotalLikes,"Verify Total Likes");
	click(postLike,"Click on Like");
	
	inputTextWithEnter(postComment, comment, "Enter Comment");
	// hitEnter();
	pause(2000);
	getText(postTotalLikes);

	//isWebElementVisible(postTotalLikes);
	
	return true;
		
		
	}
public boolean likeAndCommentPostedited1(String  subject, String comment) 
{
	
	pause(6000);
	
	//span[@class="post_title" and text()='post for all']
	//(//span[@class="post_title" and text()='post for all']/following::span[text()='Like'])[1]
	//		(//span[@class="post_title" and text()='post for all']/following::textarea[@id='textarea_comment'])[1]
	//(//span[@class="post_title" and text()='post for all']/following::span[starts-with(@id,'like-count')])[1]
	//(//ul[@class='elgg-list elgg-list-entity']//span[@class="post_title" and text()='fh']/following::div[@class='engage-score']/p)[1]
	By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");	
	By postLike= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Like'])[1]");
	//System.out.println("postLike");
	By postComment= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::textarea[@id='textarea_comment'])[1]");
	By postTotalLikes= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
	scrollTo(postTitle);
	isWebElementVisible(postTitle,"Verify Post");
	isWebElementVisible(postLike,"Verify Like");
	isWebElementVisible(postComment,"Verify comment");
	pause(7000);
	//isWebElementVisible(postTotalLikes,"Verify Total Likes");
	click(postLike,"Click on Like");
	try {
		scrollDown(postComment, "scroll to post comment");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pause(5000);
	inputTextWithEnter(postComment, comment, "Enter Comment");
	
	// hitEnter();
	pause(2000);
	getText(postTotalLikes);

	//isWebElementVisible(postTotalLikes);
	
	return true;
}
public boolean likeAndCommentPostpinitem(String  subject, String comment)  {
		
		//span[@class="post_title" and text()='post for all']
		//(//span[@class="post_title" and text()='post for all']/following::span[text()='Like'])[1]
		//		(//span[@class="post_title" and text()='post for all']/following::textarea[@id='textarea_comment'])[1]
		//(//span[@class="post_title" and text()='post for all']/following::span[starts-with(@id,'like-count')])[1]
		//(//ul[@class='elgg-list elgg-list-entity']//span[@class="post_title" and text()='fh']/following::div[@class='engage-score']/p)[1]
		click(Conversations.lnk_Saved,"click on save item");
		pause(15000);
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		By postLike= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postComment= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::textarea[@id='textarea_comment'])[1]");
		By postTotalLikes= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		scrollTo(postTitle);
		
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		isWebElementVisible(postComment,"Verify comment");
		//isWebElementVisible(postTotalLikes,"Verify Total Likes");
		click(postLike,"Click on Like");
		
		inputTextWithEnter(postComment, comment, "Enter Comment");
		// hitEnter();
		pause(2000);
		getText(postTotalLikes);
	
		//isWebElementVisible(postTotalLikes);
		
		return true;
	}
	public boolean  resharePostParentstoStudents(String  subject,String comment,String groupName,String message)
	{
		By postsharebefore=By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Share'])[1]");//span[@class='post_title' and text()='rkxzecmrhdSubject test automation']/following::span[text()='Share']
		isWebElementPresent(postsharebefore, "Verify post reshare");
		click(postsharebefore,"click on share link");
		pause(8000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
//		getWebDriver().switchTo().frame(0);
//		inputText(Conversations.typeMessage, message, "Enter Message");
//		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 2)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean mutePost(String  subject)  {
			By postBeforeMute= By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::a[@title='Click to stop notifications']");
			isWebElementPresent(postBeforeMute, "Verify post unmuted");
			By postMute= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Mute'])[1]");
			click(postMute,"Click on Mute");	
			By postAfterMute= By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::a[@title='Click to receive notifications']");
			isWebElementPresent(postAfterMute, "Verify post muted");
			return true;
		}
	public boolean unmutePost(String  subject)  {
		By postAfterMute= By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::a[@title='Click to receive notifications']");
		isWebElementPresent(postAfterMute, "Verify post muted");
		By postMute= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Mute'])[1]");
		click(postMute,"Click on UnMute");	
		By postBeforeMute= By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::a[@title='Click to stop notifications']");
		isWebElementPresent(postBeforeMute, "Verify post unmuted");
		return true;
	}
	public boolean mutePostFromMail(String  subject)  {
		//span[starts-with(text(),'Hi Studenttwo785 two785, aateejvuqySubject test automation')]
		String parentWindow=getWebDriver().getWindowHandle();
		pause(60000);
		By message= By.xpath("(//span[starts-with(text() ,'"+subject+"')])[2]");		
		//isWebElementPresent(message, "Click on Email of subject");
		click(message,"Click on Email of subject");	
		pause(3000);		
		click(GmailOR.lnk_Mute,"Click on click here ");	
		pause(2000);
		Set<String> allWindows=getWebDriver().getWindowHandles();
		allWindows.remove(parentWindow);
		for(String window: allWindows) {
			getWebDriver().switchTo().window(window);
			isWebElementPresent(GmailOR.txt_Mute_successMsg, "Verify post muted success message");
			getWebDriver().close();
		}
		getWebDriver().switchTo().window(parentWindow);
		
		return true;
	}
	public boolean mutePostFromMail2(String  subject)  {
		//span[starts-with(text(),'Hi Studenttwo785 two785, aateejvuqySubject test automation')]
		String parentWindow=getWebDriver().getWindowHandle();
		
		By message= By.xpath("//span[starts-with(text(),'Hi Studenttwo785 two785, "+subject+"')]");		
		//isWebElementPresent(message, "Click on Email of subject");
		click(message,"Click on Email of subject");	
		pause(3000);
		click(GmailOR.lnk_Mute,"Click on click here ");	
		Set<String> allWindows=getWebDriver().getWindowHandles();
		allWindows.remove(parentWindow);
		for(String window: allWindows) {
			getWebDriver().switchTo().window(window.toString());
			isWebElementPresent(GmailOR.txt_Mute_successMsg, "Verify post muted success message");
			getWebDriver().close();
		}
		getWebDriver().switchTo().window(parentWindow);
		
		return true;
	}
	public boolean noNotificationForMutePostFromMail(String  comment)  {
		By email= By.xpath("//span[starts-with(text(),'"+comment+"')]");		
		isWebElementNotPresent2(email, "Verify Email notification for comment"+comment+" not present");
		return true;
	}
	public boolean verifyNotificationForPost(String  comment)  {
		By email= By.xpath("//span[contains(text(),'"+comment+"')]");		
		isWebElementPresent(email, "Verify Email notification for comment"+comment+" present ");
		return true;
	}
	public boolean verifyAllMutesForPost()  {
		List<WebElement>  allMutes=getWebDriver().findElements(Conversations.txt_Post_Mute);
		List<WebElement>  allPosts=getWebDriver().findElements(Conversations.txt_Post_Title);
		if( allPosts.size()==allMutes.size()) {
			logStepPass("All posts "+allPosts.size() +" and �ll mutes "+allMutes.size());
			
		}else {
			logStepFail("All posts "+allPosts.size() +" and �ll mutes "+allMutes.size());
			Assert.fail("All posts "+allPosts.size() +" and �ll mutes "+allMutes.size());
		}
		
		return true;
	}
	public boolean likeAndCommentSearchPost(String  subject, String comment)  {
		
		//span[@class="post_title" and text()='post for all']
		//(//span[@class="post_title" and text()='post for all']/following::span[text()='Like'])[1]
		//		(//span[@class="post_title" and text()='post for all']/following::textarea[@id='textarea_comment'])[1]
		//(//span[@class="post_title" and text()='post for all']/following::span[starts-with(@id,'like-count')])[1]
		//(//ul[@class='elgg-list elgg-list-entity']//span[@class="post_title" and text()='fh']/following::div[@class='engage-score']/p)[1]
		By postTitle= By.xpath("//font[text()='"+subject+"']");
		By postLike= By.xpath("(//font[text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postComment= By.xpath("(//font[text()='"+subject+"']/following::textarea[@id='textarea_comment'])[1]");
		By postTotalLikes= By.xpath("(//font[text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		scrollTo(postTitle);
		
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		isWebElementVisible(postComment,"Verify comment");
		//isWebElementVisible(postTotalLikes,"Verify Total Likes");
		click(postLike,"Click on Like");
		
		inputTextWithEnter(postComment, comment, "Enter Comment");
		// hitEnter();
		pause(2000);
		getText(postTotalLikes);
	
		//isWebElementVisible(postTotalLikes);
		
		return true;
	}
	public boolean likePost(String  subject)  {
		
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		By postLike= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postTotalLikes= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		scrollTo(postTitle);
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		System.out.println(postLike);
		WebElement element = getWebDriver().findElement(postLike);
		JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		executor.executeScript("arguments[0].click();", element);
		pause(8000);
		//click(postLike,"Click on Like");
		
		getText(postTotalLikes);
		return true;
	}
public boolean likeSearchPost(String  subject)  {
		
		By postTitle= By.xpath("//font[text()='"+subject+"']");
		By postLike= By.xpath("(//font[text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postTotalLikes= By.xpath("(//font[text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		scrollTo(postTitle);
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		click(postLike,"Click on Like");		
		getText(postTotalLikes);
		return true;
	}
	public boolean searchPost(String subject)  {
		inputText(Conversations.input_Post_Search, subject, "Enter post Subject");
		click(Conversations.btn_Post_Search,"Click on Search");
		pause(2000);
		By PostXpath=By.xpath("//font[text()='"+subject+"']");		
		isWebElementVisible(PostXpath);
		return true;
	}
	public boolean visibilityPollPost(String subject,boolean on,String logStep)
	{
		By postTitle = By.xpath("//span[@class='post_title' and text()='" + subject + "']");
		//By EditPostXpath=By.xpath("/");
		List<WebElement> ele=getWebDriver().findElements(By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]"));
		int ele_size=ele.size();
		isWebElementVisible(postTitle, "Verify Post");
		if(on && ele_size==0)
		{
			
			logStepPass(logStep );
			System.out.println("no such element");
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean checktext(String subject)
	{
		By postTitle = By.xpath("//span[@class='post_title' and text()='" + subject + "']/following-sibling::span[@class='post_description']");
		String TagName = getWebDriver().findElement(postTitle).getTagName();
		String gettext=getText(postTitle);
		System.out.println(TagName);
		System.out.println("=============================");
		System.out.println(gettext);
		System.out.println("=============================");
		List<WebElement> allLinks = getWebDriver().findElements(By.tagName("a"));

		for(WebElement specificlink : allLinks ) {

			if(specificlink.getText().contains("https://www.facebook.com/"))
			{

			System.out.println("link found");

			break;

			}

			}
		//System.out.println(getattribute);
	    return true;
	}//span[@class='post_title' and text()='zoqaoafytcLQ3123 message post']/following-sibling::span[@class='post_description']
	public boolean commentPostafterchange(String subject, String comment) 
	{
		isWebElementVisible(Conversations.lbl_orginal_text, "label is display");
		click(Conversations.lbl_orginal_text,"change to orginal text");
		
		pause(5000);
		By postTitle = By.xpath("//span[@class='post_title' and text()='" + subject + "']");
		By postComment = By.xpath("(//span[@class='post_title' and text()='" + subject
				+ "']/following::textarea[@id='textarea_comment'])[1]");
		scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify comment");
		inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean User_Self_Service_provisioning(String School_name,String information)
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_Support,"Click on Support");
		pause(8000);
		click(HomePage.lnk_Auto_Provision_Status,"Click on Auto Provision status");
		pause(5000);
		List<WebElement> allColumn=getWebDriver().findElements(By.xpath("//table[@id='table_prov_status']/thead/tr/th"));
		int Sizeofcolumn=allColumn.size();
		//System.out.println(Sizeofcolumn);
		Assert.assertEquals(Sizeofcolumn, 7, "size is same for table header is same");
		List<WebElement> allTabledataRow1=getWebDriver().findElements(By.xpath("//table[@id='table_prov_status']/tbody/tr/td[1]"));
		for(WebElement element: allTabledataRow1)
		{
			String data=element.getText();
			//System.out.println(data);
			//System.out.println(School_name);
			if(data.equals(School_name))
			{
				
				//String parentWindow = getWebDriver().getWindowHandle();
				By moreindatarow=By.xpath("//td[text()='" + School_name + "']/following-sibling::td[6]/a");
				click(moreindatarow,"click on more to load more item");
				WebElement ele_table_data=getWebDriver().findElement(By.xpath("//div[@id='autoProvisionDetailsModel']//div//td[contains(text(),'Auto Provisioning')]/parent::tr/following-sibling::tr/td[3]"));
				String text=ele_table_data.getText();
				pause(5000);
				//System.out.println(text);
				//System.out.println(information);
				if(text.equalsIgnoreCase(information))
				{
				 System.out.println("pass");
				 Assert.assertEquals(text, information,"information is not matching");
				}
				else
				{
					System.out.println("fail");
				   }
				   click(HomePage.btn_close_autoprovision,"close the autoprovision table");
			   }		   

		}
		return true;
	}

	public boolean commentPost(String subject, String comment) {
		By postTitle = By.xpath("//span[@class='post_title' and text()='" + subject + "']");
		By postComment = By.xpath("(//span[@class='post_title' and text()='" + subject
				+ "']/following::textarea[@id='textarea_comment'])[1]");
		scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify comment");
		inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean createcampainpageitem(String subject) {
		By postTitle = By.xpath("//span[text()='" + subject + "']");
		By postComment = By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Comment')])[1]");
		By postlike = By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Like')])[1]");
		//By postpinitem=By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Pin')])[1]");
		//scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify Comment");
		isWebElementVisible(postlike, "Verify Like");
		//isWebElementVisible(postpinitem, "Verify Save");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean VerifyAllOption(String subject) {
		By postTitle = By.xpath("//span[text()='" + subject + "']");
		By postComment = By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Comment')])[1]");
		By postlike = By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Like')])[1]");
		By postpinitem=By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Save')])[1]");
		//scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify Comment");
		isWebElementVisible(postlike, "Verify Like");
		isWebElementVisible(postpinitem, "Verify Save");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean verifycalenderall(String subject) {
		By postTitle = By.xpath("//a[text()='" + subject + "']");
		System.out.println(postTitle);
		By postComment = By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Comment')])[1]");
		By postlike = By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Like')])[1]");
		By postpinitem=By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Save')])[1]");
		//scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify Comment");
		isWebElementVisible(postlike, "Verify Like");
		isWebElementVisible(postpinitem, "Verify Save");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean verifycalerdereventoncalenderdetailspage(String subject)
	{
		click(HomePage.tab_TopNavigation_Calendar,"Click Done");
		pause(3000);
		click(Calendar.lbl_week,"click on week");
		pause(5000);
		By postTitle = By.xpath("//b[contains(text(),'" + subject + "')]");
		System.out.println(postTitle);
		scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		return true;
		
	}
	public boolean verifycalenderall_translation(String subject) {
		By postTitle = By.xpath("(//div[@class='list_title']/a[contains(text(),'" + subject + "')])[1]");
		By postComment = By.xpath("(//a[contains(text(),'" + subject + "')]/following::div[@class='comment-like-bg']//span[contains(text(),'Comment')])[1]");
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify Comment");
		click(postTitle,"click on the event");
		pause(8000);
		By lnk_postevent_translation= By.xpath("//i[@class='post-translate']/following::span");
		isWebElementVisible(lnk_postevent_translation,"translation link is available");
		click(Conversations.btn_close_event_details_popup,"close the details screen");
		//By postlike = By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Like')])[1]");
		//By postpinitem=By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Pin')])[1]");
		//scrollTo(postTitle);
		
		//isWebElementVisible(postlike, "Verify Like");
		//isWebElementVisible(postpinitem, "Verify Save");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean Edit_calerderevent_off_reminder(String subject)
	{
		By eventPostTitle = By.xpath("(//div[@class='list_title']/a[contains(text(),'" + subject + "')])[1]");
		click(eventPostTitle,"click on the event");
		pause(8000);
		click(Calendar.event_owner_edit_option,"click on event owner edit option");
		pause(3000);
		click(Calendar.add_Reminders,"remove reminder in Event");
		 pause(3000);
		 click(Calendar.click_Save_Exit_Btn,"click save button");
		 pause(3000);
		 if(isAlertPresent())
		 {
			 alertAccept();
		 }
		 pause(3000);
		 click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
		 pause(5000);
		return true;
	}
	public boolean Edit_calerderevent_off_allday(String subject)
	{
		By eventPostTitle = By.xpath("(//div[@class='list_title']/a[contains(text(),'" + subject + "')])[1]");
		click(eventPostTitle,"click on the event");
		pause(8000);
		click(Calendar.event_owner_edit_option,"click on event owner edit option");
		pause(3000);
		click(Calendar.add_AllDaysCheckBox,"click on All Day Event off");
		 pause(3000);
		 click(Calendar.click_Save_Exit_Btn,"click save button");
		 pause(3000);
		 if(isAlertPresent())
		 {
			 alertAccept();
		 }
		 pause(3000);
		 click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
		 pause(5000);
		return true;
	}
	public boolean Edit_calerderevent_off_rsvp(String subject)
	{
		By eventPostTitle = By.xpath("(//div[@class='list_title']/a[contains(text(),'" + subject + "')])[1]");
		click(eventPostTitle,"click on the event");
		pause(8000);
		click(Calendar.event_owner_edit_option,"click on event owner edit option");
		pause(3000);
		click(Calendar.add_RSVP,"Deselect RSVP");
		 pause(3000);
		 click(Calendar.click_Save_Exit_Btn,"click save button");
		 pause(3000);
		 if(isAlertPresent())
		 {
			 alertAccept();
		 }
		 pause(3000);
		 click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
		 pause(5000);
		return true;
	}
	public boolean Edit_calerderevent_on_comment(String subject)
	{
		By eventPostTitle = By.xpath("(//div[@class='list_title']/a[contains(text(),'" + subject + "')])[1]");
		click(eventPostTitle,"click on the event");
		pause(8000);
		click(Calendar.event_owner_edit_option,"click on event owner edit option");
		pause(3000);
		click(Calendar.add_Disble_Comment,"Click on enable comment");
		 pause(3000);
		 click(Calendar.click_Save_Exit_Btn,"click save button");
		 pause(3000);
		 if(isAlertPresent())
		 {
			 alertAccept();
		 }
		 pause(3000);
		 click(HomePage.tab_TopNavigation_Conversations,"Click On Conversation page");
		 pause(5000);
		return true;
	}
	public boolean verify_conversation_translation(String subject,String traslationcomment)
	{
		By postTitle= By.xpath("//span[@class='post_title' and contains(text(),'" + subject + "')]");
		System.out.println(postTitle);
		By postComment= By.xpath("(//span[@class='post_title' and contains(text(),'" + subject + "]/following::textarea[@id='textarea_comment'])[1]");
		System.out.println(postComment);
		isWebElementVisible(postTitle, "Verify Post");
		By postcommenttranslation=By.xpath("(//li[@class='comment-items']/span[@class='comment-text'])[1]");
		String text=getText(postcommenttranslation);
		Assert.assertEquals(text, traslationcomment, "verify the translation");
		return true;
	}
	public boolean verifyCampaignall(String subject) {
		By postTitle = By.xpath("//span[text()='" + subject + "']");
		By postComment = By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Comment')])[1]");
		By postlike = By.xpath("(//span[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Like')])[1]");
		//By postpinitem=By.xpath("(//a[text()='" + subject + "']/following::div[@class='comment-like-bg']//span[contains(text(),'Pin')])[1]");
		//scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify Comment");
		isWebElementVisible(postlike, "Verify Like");
		//isWebElementVisible(postpinitem, "Verify Save");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean commentSearchPost(String subject, String comment) {
		By postTitle = By.xpath("//font[text()='" + subject + "']");
		By postComment = By.xpath("(//font[text()='" + subject+ "']/following::textarea[@id='textarea_comment'])[1]");
		scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		isWebElementVisible(postComment, "Verify comment");
		inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean commentSearchPostnotavailablecalender(String subject)
	{
		By postTitle = By.xpath("//a[text()='" + subject + "']");
		By postComment = By.xpath("(//a[text()='" + subject+ "']/following::textarea[@id='textarea_comment'])[1]");
		//scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		pause(3000);
		isWebElementVisible(Conversations.img_no_comments, "Verify  no comment");
		//isWebElementNotPresent1(postComment, "Verify comment");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean commentSearchPostnotavailablpost(String subject)
	{
		By postTitle = By.xpath("//span[text()='" + subject + "']");
		By postComment = By.xpath("(//span[text()='" + subject+ "']/following::textarea[@id='textarea_comment'])[1]");
		//scrollTo(postTitle);
		isWebElementVisible(postTitle, "Verify Post");
		pause(3000);
		isWebElementVisible(Conversations.img_no_comments, "Verify  no comment");
		//isWebElementNotPresent1(postComment, "Verify comment");
		//inputTextWithEnter(postComment, comment, "Enter Comment");
		return true;
	}
	public boolean childlabelfilter(String subject,String GroupName)
	{
		click(Conversations.lnk_clear_filter,"clcik on clear filter");
		pause(5000);
		By postgroup=By.xpath("//a[text()='" + GroupName + "']");
		click(postgroup,"click on that partcular group");
		By postTitle = By.xpath("//span[text()='" + subject + "']");
		isWebElementVisible(postTitle, "Verify Post");
		return true;
	}
	public boolean verifyPostFESScore(String  subject, String countFES)  {
		
		//span[@class="post_title" and text()='post for all']
		//(//span[@class="post_title" and text()='post for all']/following::span[text()='Like'])[1]
		//		(//span[@class="post_title" and text()='post for all']/following::textarea[@id='textarea_comment'])[1]
		//(//span[@class="post_title" and text()='post for all']/following::span[starts-with(@id,'like-count')])[1]
		//(//ul[@class='elgg-list elgg-list-entity']//span[@class="post_title" and text()='fh']/following::div[@class='engage-score']/p)[1]
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		By postLike= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postComment= By.xpath("(//span[@class='post_title' and text()='post for all']/following::textarea[@id='textarea_comment'])[1]");
		By postTotalLikes= By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		By postFESScroe= By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"+subject+"']/following::div[@class='engage-score']/p)[1]");
		//scrollTo(postTitle);
		
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		//isWebElementVisible(postComment,"Verify comment");
		isWebElementVisible(postTotalLikes,"Verify Total Likes");
		isWebElementVisible(postFESScroe,"Verify post FES Scroe");
		getText(postTotalLikes);
		String actualFES=getText(postFESScroe);
		compareTwoValues(actualFES, countFES);
		//isWebElementVisible(postTotalLikes);
		
		return true;
	}

	public boolean verifyPostFESScoreDisplayed(String subject) {
		By PostXpath = By.xpath("//span[text()='" + subject + "']");
		isWebElementVisible(PostXpath);
		By postFESScroe = By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"
				+ subject + "']/following::div[@class='engage-score']/p)[1]");
		isWebElementVisible(postFESScroe, "Verify post FES Scroe on Conversation page");
		return true;
	}

	public boolean verifyPostFESScoreNotDisplayed(String subject) {
		By PostXpath = By.xpath("//span[text()='" + subject + "']");
		isWebElementVisible(PostXpath);
		By postFESScroe = By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"
				+ subject + "']/following::div[@class='engage-score']/p)[1]");
		isWebElementNotPresent2(postFESScroe, "Verify post FES Scroe not displayed on Conversation page");
		return true;
	}
	public boolean verifySearchPostFESScore(String  subject, String countFES)  {
		
		By postTitle= By.xpath("//font[text()='"+subject+"']");
		By postLike= By.xpath("(//font[text()='"+subject+"']/following::span[text()='Like'])[1]");
		By postComment= By.xpath("(//font[text()='post for all']/following::textarea[@id='textarea_comment'])[1]");
		By postTotalLikes= By.xpath("(//font[text()='"+subject+"']/following::span[starts-with(@id,'like-count')])[1]");
		By postFESScroe= By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"+subject+"']/following::div[@class='engage-score']/p)[1]");
		scrollTo(postTitle);
		isWebElementVisible(postTitle,"Verify Post");
		isWebElementVisible(postLike,"Verify Like");
		isWebElementVisible(postComment,"Verify comment");
		isWebElementVisible(postTotalLikes,"Verify Total Likes");
		isWebElementVisible(postFESScroe,"Verify post FES Scroe");
		getText(postTotalLikes);
		String actualFES=getText(postFESScroe);
		compareTwoValues(actualFES, countFES);
		//isWebElementVisible(postTotalLikes);
		return true;
	}
	public boolean verifyFESScoreOnProfile(String teacherName)  {
		
		click(Conversations.lnk_Users,"Click on Users");
		inputText(Conversations.input_FilterUser, teacherName, "Enter Teacher name");
		pause(3000);
		By txt_UserName = By.xpath("//a[@class='filter_username' and text()='"+teacherName+"']");
		click(txt_UserName,"Click on Name "+teacherName);
		
		
		By txt_FirstPostUserName = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@title='"+teacherName+"']");
		
		//a[@class='filter_username' and text()='Teacher785 test785']
		click(txt_FirstPostUserName,"Click on first post Name");
		
		//getText(postTotalLikes);
		//String actualFES=getText(postFESScroe);
		//compareTwoValues(actualFES, countFES);
		isWebElementVisible(Directory.txt_FESScore,"Check FES Score is available");
		isWebElementVisible(Directory.txt_UserName,"Check Username is available");
		return true;
	}
public boolean verifyFESScoreOnPrincipalProfile(String teacherName,String profileFESScore)  {
		
		click(Conversations.lnk_Users,"Click on Users");
		inputText(Conversations.input_FilterUser, teacherName, "Enter Teacher name");
		pause(3000);
		By txt_UserName = By.xpath("//a[@class='filter_username' and text()='"+teacherName+"']");
		click(txt_UserName,"Click on Name "+teacherName);
		
		
		By txt_FirstPostUserName = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@title='"+teacherName+"']");
		
		//a[@class='filter_username' and text()='Teacher785 test785']
		click(txt_FirstPostUserName,"Click on first post Name");
		
		String actualFES=getText(Directory.txt_FESScore);
		compareTwoValues(actualFES, profileFESScore);
		isWebElementVisible(Directory.txt_FESScore,"Check FES Score is available");
		isWebElementVisible(Directory.txt_UserName,"Check Username is available");
		return true;
	}
public boolean verifyFESScoreOnTeacherProfile(String profileFESScore)  {
		
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_MyProfile,"Click on My Profile");
		isWebElementVisible(Directory.txt_FESScore,"Check FES Score is available");
		isWebElementVisible(Directory.txt_UserName,"Check Username is available");
		
		String actualFES=getText(Directory.txt_FESScore);
		compareTwoValues(actualFES, profileFESScore);
		return true;
	}
	
public boolean verifyFESScoreOnUserProfile(String teacherName)  {
		
		click(HomePage.lnk_UserOptions,"Click on User Options");
		
		By txt_FirstPostUserName = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@title='"+teacherName+"']");
		
		//a[@class='filter_username' and text()='Teacher785 test785']
		click(txt_FirstPostUserName,"Click on first post Name");
		
		String actualFES=getText(Directory.txt_FESScore);
		isStringInt(actualFES, "Verify the value is an Integer with whole number");
		//compareTwoValues(actualFES, countFES);
		isWebElementVisible(Directory.txt_FESScore,"Check FES Score is available");
		isWebElementVisible(Directory.txt_UserName,"Check Username is available");
		return true;
	}
	
public boolean Replace_Group_Code_left_nav(String groupName){
		By add_member=By.xpath("//div[@class='div-left']//a[text()='"+groupName+"']/parent::h3//parent::div/following-sibling::div/a/span");
		click(add_member,"click on add memeber hover");
		pause(2000);
		click(HomePage.lnk_invite_parent_via_group_code,"link to invite the parents via group code");
		pause(8000);
		if(isWebElementPresent(HomePage.btn_generate_group_code))
		{
		click(HomePage.btn_generate_group_code,"generate group code");
		}
		String group_code=isvalidategroupname(Manage_group_code.lbl_gi_group_name,Manage_group_code.lbl_gi_group_invitecode,"group name selected",groupName);
		click(Manage_group_code.btn_print,"click the print button");
		String parentWindow = getWebDriver().getWindowHandle();
		String subWindowHandler1 = "";
		Set<String> allWindows = getWebDriver().getWindowHandles();
		Iterator<String> iterator = allWindows.iterator();
		while (iterator.hasNext())
		{
		   subWindowHandler1 = iterator.next();
		   if(!parentWindow.equalsIgnoreCase(subWindowHandler1))
		   {
			   
			   getWebDriver().switchTo().window(subWindowHandler1);
			   highlightElement(Manage_group_code.lbl_print_groupname);
			   WebElement element_groupName=getWebElement(Manage_group_code.lbl_print_groupname);
			   pause(5000);
			   String text_groupname=element_groupName.getText();			 
			   highlightElement(Manage_group_code.lbl_print_groupcode);
				WebElement element_groupcode=getWebElement(Manage_group_code.lbl_print_groupcode);
				 pause(5000);
				String text_groupcode=element_groupcode.getText();
				//System.out.println(text_groupname);
				//System.out.println(text_groupcode);
				//System.out.println(group_code);
				if(text_groupname.equalsIgnoreCase(groupName) && text_groupcode.equalsIgnoreCase(group_code))
				{
					System.out.println("pass");
					Assert.assertEquals(text_groupname, groupName,"both are not equal");
					Assert.assertEquals(text_groupcode, group_code,"both are not equal");
					//logStep(passed:+"both are equal");
				}
				else
				{
					System.out.println("fail");
				}
				getWebDriver().close();
		    
		   }
		   
		   
		 //  isWebElementVisible(Manage_group_code.btn_print, "btton print is visible");
		   
		   
		}
		
		getWebDriver().switchTo().window(parentWindow);
		pause(5000);
		click(Manage_group_code.btn_close,"close the group code section");
		 pause(5000);
		   //System.out.println(getWebDriver().getTitle());
		 // getWebDriver().switchTo().defaultContent();
		 
		return true;
	}
public String Group_Code_left_nav(String groupName) 
{
	//String Group_code=null;
	By add_member=By.xpath("//div[@class='div-left']//a[text()='"+groupName+"']/parent::h3//parent::div/following-sibling::div/a/span");
	click(add_member,"click on add memeber hover");
	pause(2000);
	click(HomePage.lnk_invite_parent_via_group_code,"link to invite the parents via group code");
	pause(8000);
	if(isWebElementPresent(HomePage.btn_generate_group_code))
	{
	click(HomePage.btn_generate_group_code,"generate group code");
	}
	String group_code=isvalidategroupname(Manage_group_code.lbl_gi_group_name,Manage_group_code.lbl_gi_group_invitecode,"group name selected",groupName);
	
	return group_code;	
}
	public boolean calculateFESScoreForPost(String postTitle)  {
	
	By commentsXpath = By.xpath("//span[@class='post_title' and text()='"+postTitle+"']/parent::div/parent::div/parent::div/parent::div/following-sibling::ul[@class='wire-comments']/li/span[1]/a");
	By likesXpath = By.xpath("//span[@class='post_title' and text()='"+postTitle+"']/parent::div/parent::div/parent::div/following-sibling::div//ul[starts-with(@id,'likes-list-')]/li/a");
	By postFESScroe= By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"+postTitle+"']/following::div[@class='engage-score']/p)[1]");
	List<WebElement> allComments=getWebDriver().findElements(commentsXpath);
	List<WebElement> allLikes=getWebDriver().findElements(likesXpath);
	 Set<String> uniqueCommentUser = new LinkedHashSet<>();	 
	 Set<String> uniqueLikeUser = new LinkedHashSet<>();	 
	 	
	 for(WebElement element: allComments)
		{
			String commentUsername=element.getText();
			uniqueCommentUser.add(commentUsername);
		}
	for(WebElement element: allLikes)
	{
		String likeUsername=element.getAttribute("title");
		uniqueLikeUser.add(likeUsername);
	}
	uniqueLikeUser.removeAll(uniqueCommentUser);
	
	int commentsCount=uniqueCommentUser.size()*2;
	int likesCount=uniqueLikeUser.size()*1;
	
	int totalPoints=commentsCount+likesCount;
	System.out.println(totalPoints);//chage to valoidate by chinmoy
	double FESScore=(double)totalPoints*100/(double)16;
	int TotalcalculatedFESScore=(int)Math.round(FESScore);
	System.out.println(TotalcalculatedFESScore);//chage to valoidate by chinmoy
		String actualFES=getText(postFESScroe);
		isStringInt(actualFES, "Verify the value is an Integer with whole number");
		compareTwoValues(actualFES, String.valueOf(TotalcalculatedFESScore));
		return true;
	}
	public boolean calculateFESScoreForSearchPost(String postTitle)  {
		
		By commentsXpath = By.xpath("//font[text()='"+postTitle+"']/parent::div/parent::div/parent::div/parent::div/following-sibling::ul[@class='wire-comments']/li/span[1]/a");
		By likesXpath = By.xpath("//font[text()='"+postTitle+"']/parent::div/parent::div/parent::div/following-sibling::div//ul[starts-with(@id,'likes-list-')]/li/a");
		By postFESScroe= By.xpath("(//ul[@class='elgg-list elgg-list-entity']//span[@class='post_title' and text()='"+postTitle+"']/following::div[@class='engage-score']/p)[1]");
		
		//span[@class='post_title' and text()='drodpfcerjLA-11993 LQ-4327']/parent::div/parent::div/parent::div/parent::div/following-sibling::ul[@class='wire-comments']/li/span[1]/a
			
	//(//span[@class='post_title' and text()='nvviddlctnLA-11993 LQ-4327']//following::ul[starts-with(@id,'likes-list-')]/li[1]/a)[1]			
		List<WebElement> allComments=getWebDriver().findElements(commentsXpath);
		List<WebElement> allLikes=getWebDriver().findElements(likesXpath);
		 Set<String> uniqueCommentUser = new LinkedHashSet<>();	 
		 Set<String> uniqueLikeUser = new LinkedHashSet<>();	 
		 	
		 for(WebElement element: allComments)
			{
				String commentUsername=element.getText();
				uniqueCommentUser.add(commentUsername);
			}
		for(WebElement element: allLikes)
		{
			String likeUsername=element.getAttribute("title");
			uniqueLikeUser.add(likeUsername);
		}
		uniqueLikeUser.removeAll(uniqueCommentUser);
		
		int commentsCount=uniqueCommentUser.size()*2;
		int likesCount=uniqueLikeUser.size()*1;
		
		int totalPoints=commentsCount+likesCount;
		double FESScore=(double)totalPoints*100/(double)14;
//		FESScore=round(FESScore, 1);
		int TotalcalculatedFESScore=(int)Math.round(FESScore);
		// FESScore=FESScore*100;
		
			
			String actualFES=getText(postFESScroe);
			isStringInt(actualFES, "Verify the value is an Integer with whole number");
			compareTwoValues(actualFES, String.valueOf(TotalcalculatedFESScore));
			//isWebElementVisible(Directory.txt_FESScore,"Check FES Score is available");
			//isWebElementVisible(Directory.txt_UserName,"Check Username is available");
			return true;
		}
	public boolean calculateFESScore(String teacherName)  {
		
		click(Conversations.lnk_Users,"Click on Users");
		inputText(Conversations.input_FilterUser, teacherName, "Enter Teacher name");
		pause(3000);
		By txt_UserName = By.xpath("//a[@class='filter_username' and text()='"+teacherName+"']");
		click(txt_UserName,"Click on Name "+teacherName);
		
		
		By txt_FirstPostUserName = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@title='"+teacherName+"']");
		
		//a[@class='filter_username' and text()='Teacher785 test785']
		click(txt_FirstPostUserName,"Click on first post Name");
		
		//getText(postTotalLikes);
		//String actualFES=getText(postFESScroe);
		//compareTwoValues(actualFES, countFES);
		isWebElementVisible(Directory.txt_FESScore,"Check FES Score is available");
		isWebElementVisible(Directory.txt_UserName,"Check Username is available");
		return true;
	}
	
public boolean searchUser(String userName)  {
		
		click(Conversations.lnk_Users,"Click on Users");
		inputText(Conversations.input_FilterUser, userName, "Enter name");
		pause(3000);
		By txt_UserName = By.xpath("//a[@class='filter_username' and text()='"+userName+"']");
		click(txt_UserName,"Click on Name "+userName);				
		//By txt_FirstPostUserName = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@title='"+userName+"']");		
		return true;
	}
	
	public boolean editPostPhoto(String photoPath, String subject , String message) 
	{
		
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		String subjectEdit= subject+getRandomString2();
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message+getRandomString2(), "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		click(Conversations.selectSpeficChildFromGroup,"click On a Sricfic element in a group");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean editPostFile(String filePath, String subject , String message) 
	{
		
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		String subjectEdit= subject+getRandomString2();
		click(Conversations.btn_Files,"Click on Files");
		fileUpload(filePath);
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message+getRandomString2(), "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		click(Conversations.selectSpeficChildFromGroup,"click On a Sricfic element in a group");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean editschedulePostFileRemoveTime(String subject , String message,String groupName,String subjectEdit) 
	{
		
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		//String subjectEdit= subject+getRandomString2();
		//click(Conversations.btn_Files,"Click on Files");
		//fileUpload(filePath);
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message+getRandomString2(), "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
//		remove_postScheduleoption();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_update,"Click Post Update button");
		pause(12000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean editschedulePostFileRemoveTimephone_alert(String subject , String message,String groupName,String subjectEdit) 
	{
		
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		//String subjectEdit= subject+getRandomString2();
		//click(Conversations.btn_Files,"Click on Files");
		//fileUpload(filePath);
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		//getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage_phone_alert, message+getRandomString2(), "Enter Message");
		//getWebDriver().switchTo().defaultContent();
		//pause(3000);
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
//		remove_postScheduleoption();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_update2,"Click Post update button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean editschedulePostFile(String subject , String message,String groupName,String subjectEdit) 
	{
		
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		//String subjectEdit= subject+getRandomString2();
		//click(Conversations.btn_Files,"Click on Files");
		//fileUpload(filePath);
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message+getRandomString2(), "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		//postSchedule_4min();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(120000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean checkPostNotEditable(String group, String subject)  {
		
		By groupXpath=By.xpath("//a[text()='"+group+"']");
		click(groupXpath,"Click on Group ");	
		By PostXpath=By.xpath("//span[text()='"+subject+"']");		
		isWebElementVisible(PostXpath);

		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		isWebElementNotPresent2(Conversations.btn_EditFirstPost,"Verify the post edit icon not available");
		return true;
	}
	
	public boolean postVideo(String videoPath, String subject, String message)  
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		click(Conversations.btn_UploadVideo, "Click on Plus(+) Icon");
		
		fileUpload(videoPath);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		click(Conversations.selectSpeficChildFromGroup,"click any group");//change done by chinmoy for the project
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(5000);
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postVideo2(String videoPath, String subject, String message)  
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		//click(Conversations.btn_UploadVideo, "Click on Plus(+) Icon");
		
		fileUpload(videoPath);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		click(Conversations.selectSpeficChildFromGroup,"click any group");//change done by chinmoy for the project
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		pause(5000);
		isWebElementVisible(PostXpath);
		return true;
	}
	public boolean editPostVideo(String subject , String message)  {
		
		By EditPostXpath=By.xpath("//span[text()='"+subject+"']/following::a[@class='post-edit'][1]");
		click(EditPostXpath,"Click on post edit Icon ");
		//click(Conversations.btn_Photos,"Click on Photos");
		String subjectEdit= subject+getRandomString2();
		
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subjectEdit, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.input_PostMessage, message+getRandomString2(), "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subjectEdit+"']");	
		isWebElementVisible(PostXpath);
		
		return true;
	}
	
	public boolean postFile2(String filePath,String subject, String message)  {
		click(Conversations.shareMessage,"Click on Post");
		click(Conversations.btn_Files,"Click on Files");
		fileUpload(filePath);
		//inputText(Conversations.btn_UploadFile, filePath, "Enter File Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		return true;
	}
	
	public boolean postFile(String filePath,String subject, String message)  {
		click(Conversations.shareMessage,"Click on Post");
		click(Conversations.btn_Files,"Click on Files");
		
		inputText(Conversations.btn_UploadFile, filePath, "Enter File Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.selectChildGroup,"click On sub child");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		return true;
	}
	public boolean postMessage(String subject, String message,String groupName)  {
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
			
		return true;
	
	}
	public boolean postMessageOnlyParents(String subject, String message,String groupName)  {
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");*/
		ifAlertDismiss();
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
			
		return true;
	
	}
	public boolean postMessageOnlyParentsInapproiatecontent(String subject, String message,String groupName,String error_message_provide) 
	{
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		pause(1000);
		click(Conversations.selectJustME,"select just me option");
		pause(1000);
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li";
		System.out.println(isGroupOpen);
		if(!isWebElementPresent(By.xpath(isGroupOpen), 4)) {
			//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
			String group="//a[@class='group_listings' and text()='"+groupName+"']";
			click(By.xpath(group),"Click on Group");
			}
			pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");*/
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		isWebElementVisible(Conversations.lbl_error_message_inapproiate_content, "inapproiate content found");
		String error_message=getText(Conversations.lbl_error_message_inapproiate_content);
		compareTwoValues(error_message,error_message_provide);
		getWebDriver().navigate().refresh();
		pause(8000);
		return true;
	
	}
	public boolean postPhotoOnlyParentsInapproiatecontent(String photoPath, String subject, String message, String groupName,String error_message_provide)  
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		
		click(Conversations.btn_Photos,"Click on Add Photo");
		fileUpload(photoPath);
		//inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(55000);
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(1000);
		click(Conversations.selectJustME,"select just me option");
		pause(1000);
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 4)) {
			//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
			String group="//a[@class='group_listings' and text()='"+groupName+"']";
			click(By.xpath(group),"Click on Group");
			}
			pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");*/
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		isWebElementVisible(Conversations.lbl_error_message_inapproiate_content, "inapproiate content found");
		String error_message=getText(Conversations.lbl_error_message_inapproiate_content);
		compareTwoValues(error_message,error_message_provide);
		getWebDriver().navigate().refresh();
		pause(8000);
		return true;
	}
	public boolean postVideoOnlyParentsInapproiatecontent(String videoPath, String subject, String message, String groupName,String error_message_provide) 
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		/*inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		
		click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
	*/	fileUpload(videoPath);
		pause(45000);
		//pause0(3000);
		isWebElementVisible(Conversations.btn_RemoveVideo,"Verify Remove Video button");
		
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(1000);
		click(Conversations.selectJustME,"select just me option");
		pause(1000);
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 4)) {
			//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
			String group="//a[@class='group_listings' and text()='"+groupName+"']";
			click(By.xpath(group),"Click on Group");
			}
			pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");*/
		pause(2000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		isWebElementVisible(Conversations.lbl_error_message_inapproiate_content, "inapproiate content found");
		String error_message=getText(Conversations.lbl_error_message_inapproiate_content);
		compareTwoValues(error_message,error_message_provide);
		getWebDriver().navigate().refresh();
		pause(8000);
		return true;
	}
	
	public boolean postFileOnlyParentsInapproiatecontent(String filePath, String subject, String message, String groupName,String error_message_provide) 
	{
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Files,"Click on File");
		pause(2000);
		fileUpload(filePath);
		//inputText2(Conversations.btn_UploadFile, filePath, "Enter File Path");
		pause(10000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		
		isWebElementVisible(Conversations.selectDropDown);
		click(Conversations.selectDropDown,"Click on Option");
		pause(1000);
		click(Conversations.selectJustME,"select just me option");
		pause(1000);
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 4)) {
			//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
			String group="//a[@class='group_listings' and text()='"+groupName+"']";
			click(By.xpath(group),"Click on Group");
			}
			pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");*/
		pause(2000);
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		isWebElementVisible(Conversations.lbl_error_message_inapproiate_content, "inapproiate content found");
		String error_message=getText(Conversations.lbl_error_message_inapproiate_content);
		compareTwoValues(error_message,error_message_provide);
		getWebDriver().navigate().refresh();
		pause(8000);
		return true;
	}
	public boolean postScheduleMessageOnlyParents(String subject, String message,String groupName)  {
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");*/
		postSchedule();
		
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
			
		return true;
	
	}
	public boolean postScheduleMessage(String OptinalSubject, String TypeMessage,String groupName)  {
		isWebElementVisible(Conversations.shareMessage);
		click(Conversations.shareMessage,"Click on Option");
		pause(1000);
		inputText(Conversations.postSubject, OptinalSubject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, TypeMessage, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		
		postSchedule();
		
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+OptinalSubject+"']");
		
		isWebElementVisible(PostXpath);
			
		return true;
	
	}
	
	public boolean postSchedule()  {
		click(Conversations.chk_SchedulePost,"Click on SchedulePost checkbox");
		//inputText(Conversations.input_ScheduledPost_Date, TypeMessage, "Enter Message");
		String date=getElementValue(Conversations.input_SchedulePost_ReleaseTime);		
		String newDate=getFutureTime(3)+" "+date.split(" ")[1];
		inputText(Conversations.input_SchedulePost_ReleaseTime, newDate, "Enter Time");
		return true;
	}
	public boolean postSchedule_4min()  {
		click(Conversations.chk_SchedulePost,"Click on SchedulePost checkbox");
		//inputText(Conversations.input_ScheduledPost_Date, TypeMessage, "Enter Message");
		String date=getElementValue(Conversations.input_SchedulePost_ReleaseTime);		
		String newDate=getFutureTime(4)+" "+date.split(" ")[1];
		inputText(Conversations.input_SchedulePost_ReleaseTime, newDate, "Enter Time");
		return true;
	}
	public boolean remove_postScheduleoption()
	{
		click(Conversations.chk_SchedulePost,"Click on SchedulePost checkbox tgo remove");
		return true;
	}
	public boolean postSchedulePhoto(String photoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Photos,"Click on Photos");
		
		inputText2(Conversations.btn_UploadPhoto, photoPath, "Enter photo Path");
		pause(5000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		//parent::span/following-sibling::ul/li/span[contains(text(),'Parents')]
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		 postSchedule();
		 ifAlertDismiss();
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleVideo(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(15000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		postSchedule();
		pause(2000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleVideo2(String videoPath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Video,"Click on Video");
		pause(1000);
		//inputText2(Conversations.btn_UploadVideo, videoPath, "Enter Video Path");
		//click(Conversations.btn_UploadVideo,"Click on Video upload + icon");
		fileUpload(videoPath);
		pause(15000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		ifAlertDismiss();
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		postSchedule();
		ifAlertDismiss();
		pause(2000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(240000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleFile(String filePath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Files,"Click on File");
		pause(2000);
		inputText2(Conversations.btn_UploadFile, filePath, "Enter File Path");
		pause(10000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		postSchedule();
		
		ifAlertDismiss();
		
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleFile2(String filePath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Files,"Click on File");
		pause(2000);
		fileUpload(filePath);
		//inputText2(Conversations.btn_UploadFile, filePath, "Enter File Path");
		pause(10000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Parents')])[1]";
		//String groupParents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415776']";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/parent::span/following-sibling::div/ul/li/span[contains(text(),'Students')])[1]";
		//String groupStudents="//li[@id='group_list_26483242_no_filter']//div[@id='temp_post_options_26483242']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_39415778_39415779']";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		postSchedule();
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);

		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postScheduleFileOnlyParents(String filePath, String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Files,"Click on File");
		pause(2000);
		inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		pause(10000);
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage, message, "Enter Message");
		getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 4)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		/*String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		*/
		postSchedule();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postSchedulePhone(String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Phone,"Click on Phone");				
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		//getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage_phone_alert, message, "Enter Message");
		//getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		postSchedule();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		alertAccept();
		pause(180000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean postAddTextMessage(String subject, String message, String groupName)  {
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_Add_Text_Message,"Click on Phone");				
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		//getWebDriver().switchTo().frame(0);
		inputText(Conversations.typeMessage_message_alert, message, "Enter Message");
		//getWebDriver().switchTo().defaultContent();
		//pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		//postSchedule();
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		//alertAccept();
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		
		return true;
	}
	public boolean editEdbackerCampaign()  {
		click(HomePage.tab_TopNavigation_Give,"Click on Give Icon");
		
		String parent= getWebDriver().getWindowHandle();
		if (!isWebElementPresent(Give.btn_EdBackerCampaign_DonateEdit)) {
			scrollTo(Give.btn_EdBackerCampaign_DonateEdit);			
		}
		click(Give.btn_EdBackerCampaign_DonateEdit,"Click on Edit Icon");
		pause(3000);
		Set<String> handles = getWebDriver().getWindowHandles();
		 
		handles.remove(parent);
        for (String child : handles) {
       
         getWebDriver().switchTo().window(child);
         isWebElementVisible(Give.txt_Campaign_Edit);
 		
         getWebDriver().close();
         }
        getWebDriver().switchTo().window(parent);
        
		return true;
	}
	
	public boolean changePassword(String OldPassword,String newPassword)  {
		click(UserProfiles.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_Settings,"Click on settings");
		click(UserProfiles.lnk_User_MyProfile,"Click on My Profile");
		click(UserProfiles.lnk_User_ChangeMyPassword,"Click on Change My Password ");
		inputText(UserProfiles.input_User_OldPassword, OldPassword,"Enter OldPassword");
		inputText(UserProfiles.input_User_NewPassword, newPassword,"Enter New Password");
		inputText(UserProfiles.input_User_ConfirmNewPassword, newPassword,"Enter Confirm new Password");
		click(UserProfiles.btn_User_ChangePassword, "Click on Change Password button");
		pause(2500);
		waitForVisible(UserProfiles.btn_User_ChangePasswordSuccessMessage);
		
		//click(HomePage.tab_TopNavigation_Conversations, "Click on Conversations ");
		//waitForVisible(Conversations.shareMessage);
		
		return true;
	}

	public boolean inviteGroup() {
		
		click(HomePage.lnk_JoinGroup,"Click on Join Group");
		isWebElementVisible(Common.btn_Cancel, "Verify Cancel button");
		isWebElementVisible(HomePage.btn_Group_Join, "Verify Join button");
		isWebElementVisible(HomePage.input_GroupName, "Verify Group Name textbox");
		click(Common.btn_Cancel,"Click on Cancel button");		
		return true;
		
	}
	public boolean searchIteminphotovideosection(String text)
	{
		ifAlertDismiss();
		click(Conversations.lnk_PhotosVideos,"click on photo/video link");
		pause(5000);
		inputText(Conversations.txt_search_item, text, "Serach '"+text+"'post in photos/videos filter");
		pause(2000);
		click(Conversations.btn_search_icon,"click on serach icon");
		pause(60000);
		
		By PostXpath=By.xpath("//a[@title='"+text+"']");
		
		isWebElementVisible(PostXpath);
		return true;
	}
	public boolean searchItemAllsection(String text)
	{
		inputText(Conversations.txt_search_item, text, "Serach '"+text+"'post in photos/videos filter");
		pause(2000);
		click(Conversations.btn_search_icon,"click on serach icon");
		pause(60000);
		
		By PostXpath=By.xpath("//a[@title='"+text+"']");
		
		isWebElementVisible(PostXpath);
		return true;
	}
	
public boolean inviteInGroup(String GroupCode, String ChildFirstName, String ChildLastName) {
		
		click(HomePage.lnk_JoinGroup,"Click on Join Group");
		isWebElementVisible(Common.btn_Cancel, "Verify Cancel button");
		isWebElementVisible(HomePage.btn_Group_Join, "Verify Join button");
		isWebElementVisible(HomePage.input_GroupName, "Verify Group Name textbox");
		inputText(HomePage.input_GroupName, GroupCode, "Enter the Group Code");
		click(HomePage.btn_Group_Join,"Click on join button");
		pause(5000);
		click(HomePage.btn_Continu_join,"Click on continue button");
		pause(5000);
		if(getWebDriver().findElement(By.id("invitee_new_group_name_0")).isDisplayed())
		{
			inputText(HomePage.grp_child_fname,ChildFirstName,"Enter child first name");
			inputText(HomePage.grp_child_lname,ChildLastName,"Enter child last name");
		}
		else
			click(HomePage.grp_checkbox,"Click on check box");
		click(HomePage.btn_accept_join,"click on acept to join the group");

		return true;
		
	}
	public boolean inviteNewUser(String userName,String message) {
		
		click(HomePage.img_1stGroupInvite,"Click on 1st Group Invite Message box Icon");
		//isWebElementVisible(HomePage.txt_SendInvite, "Verify Send Invite text");
		//click(HomePage.lnk_InviteParent_EMail,"Click on Invite Parent using EMail");
		isWebElementVisible(HomePage.input_Invite_SearchContacts,"Verify Invite Search Contacts");
		isWebElementVisible(HomePage.btn_Invite_ClearAll,"Verify Clear All button");
		isWebElementVisible(Common.btn_Cancel,"Verify Cancel button");
		inputText(HomePage.input_Invite_SearchContacts, userName,"Enter userName");
		pause(2000);
		click(HomePage.txt_Invite_1stUser,"Click on User name");
		inputText(HomePage.input_Invite_Message, message,"Enter Message");
		click(HomePage.btn_Invite_Send,"Click on Send Invite button");		
		isWebElementVisible(HomePage.txt_Invite_SuccessMsg,"Verify Invite Success Message");
		
		//click(Common.btn_Cancel,"Click on Cancel button");		
		return true;
		
	}
	
public boolean transfrownership(String Invite){
		
		click(HomePage.img_mynetwork,"Click on my network");
		pause(2000);
		/*click(HomePage.lnk_group,"click on a group");
		pause(1000);
		click(HomePage.lnk_invite_parent,"click on parent link ");
		pause(1000);
		click(HomePage.lnk_invitee_mail,"clcik on invite via email");
		*/
		getWebDriver().navigate().refresh();
		pause(5000);
		getWebDriver().navigate().refresh();
		pause(5000);
		click(HomePage.btn_Hover_ownertrn,"click on hover button");
		pause(5000);
		
		click(HomePage.lnk_ownertrn,"click on the ownership transfr link");
		
		inputText(HomePage.ownershiptransfrmail, Invite, "Enter Invitee");
		pause(5000);
		try {
			re = new Robot();
			re.keyPress(KeyEvent.VK_DOWN);
			re.keyRelease(KeyEvent.VK_DOWN);
			re.keyPress(KeyEvent.VK_ENTER);
			re.keyRelease(KeyEvent.VK_ENTER);
		}
		catch(AWTException e)
		{
			e.printStackTrace();
		}
		pause(5000);
		
		//alertAccept();
		click(HomePage.btn_sendownership,"Click On Transfer ownership button");
		
		alertAccept();
//		pause(50000);
//		alertAccept();
			
//		click(HomePage.inviteBox,"click on Invitee");
//		//click(HomePage.InviteeEmail,"Click In Email Box");
//		inputText(HomePage.InviteeEmail, Invite, "Enter Invitee");
//		//getWebDriver().switchTo().defaultContent();
//		click(HomePage.sendInvitee,"Click On Send Invite");
		pause(10000);
		return true;
	}

public boolean transfrownershipnew(String Invite){
	
	click(HomePage.img_mynetwork,"Click on my network");
	pause(2000);
	/*click(HomePage.lnk_group,"click on a group");
	pause(1000);
	click(HomePage.lnk_invite_parent,"click on parent link ");
	pause(1000);
	click(HomePage.lnk_invitee_mail,"clcik on invite via email");
	*/
	getWebDriver().navigate().refresh();
	pause(5000);
	getWebDriver().navigate().refresh();
	pause(5000);
	click(HomePage.btn_Hover_ownertrn,"click on hover button");
	pause(5000);
	
	click(HomePage.lnk_ownertrn,"click on the ownership transfr link");
	
	inputText(HomePage.ownershiptransfrmail, Invite, "Enter Invitee");
	pause(5000);
	try {
		re = new Robot();
		re.keyPress(KeyEvent.VK_DOWN);
		re.keyRelease(KeyEvent.VK_DOWN);
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
	}
	catch(AWTException e)
	{
		e.printStackTrace();
	}
	pause(5000);
	
	//alertAccept();
	click(HomePage.btn_sendownership,"Click On Transfer ownership button");
	
	alertAccept();
	pause(5000);
	if(isAlertPresent())
    {
    alertAccept();
    }
	else
	{
		By btn_close=By.xpath("(//button[@class='close close_btn']//span[@class='cls-txt hidden-xs'])[1]");
		click(btn_close,"close the ownership option");
	}
//	click(HomePage.inviteBox,"click on Invitee");
//	//click(HomePage.InviteeEmail,"Click In Email Box");
//	inputText(HomePage.InviteeEmail, Invite, "Enter Invitee");
//	//getWebDriver().switchTo().defaultContent();
//	click(HomePage.sendInvitee,"Click On Send Invite");
	pause(10000);
	return true;
}
public boolean isvisibiltyafterownership(String groupname,String subject,String subject1,String subject2) {
	By mynetwork = By.xpath("//div[@id='topnav-livingtree-text']");

	pause(5000);
	//By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
	//By postTitle1= By.xpath("//a[@class='post_title' and text()='"+subject1+"']");
	//isWebElementVisible(postTitle, "title is visible");
	//scrolltoElement_new(locator, message);
	if(subject1 != null)
	{
	By postTitle1= By.xpath("//span[text()='"+subject1+"']");
	scrollTo(postTitle1);
	isWebElementVisible(postTitle1, "title 1 is visible");
	}
	if(subject2 !=null )
	{
		By postTitle2= By.xpath("//span[text()='"+subject2+"']");
		scrollTo(postTitle2);
		isWebElementVisible(postTitle2, "title 2 is visible");
	}
	By postTitle= By.xpath("//span[text()='"+subject+"']");
	scrollTo(postTitle);
	isWebElementVisible(postTitle, "title is visible");
	
	click(mynetwork, "click on my network");
	By afterownergroup= By.xpath("//li[@id='Groups']/ul[1]");
	click(afterownergroup,"click on specific group");
	return true;
}
public boolean isvisibiltyafterownershipinFamily(String family,String subject,String subject1,String subject2) {
	By afterownergroup= By.xpath("(//a[text()='"+family+"'])[2]");
	click(afterownergroup,"click on spefic group");
	pause(5000);
	//By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
	//By postTitle1= By.xpath("//a[@class='post_title' and text()='"+subject1+"']");
	//isWebElementVisible(postTitle, "title is visible");
	//scrolltoElement_new(locator, message);
	if(subject1 != null)
	{
	By postTitle1= By.xpath("//a[text()='"+subject1+"']");
	scrollTo(postTitle1);
	isWebElementVisible(postTitle1, "title 1 is visible");
	}
	if(subject2 !=null )
	{
		By postTitle2= By.xpath("(//span[@class='post_title' and text()='"+subject2+"'])[1]");
		scrollTo(postTitle2);
		isWebElementVisible(postTitle2, "title 2 is visible");
	}
	By postTitle= By.xpath("(//span[@class='post_title' and text()='"+subject+"'])[1]");
	scrollTo(postTitle);
	isWebElementVisible(postTitle, "title is visible");
	return true;
}	
	public boolean sentMessage(String recipient1,String recipient2,String subject,String message,String filePath)  {
		
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
		inputText(Inbox.input_Compose_UserTo, recipient1,"Enter recipient1");
		pause(6000);
		click(Inbox.txt_Compose_SelectUser,"Click on User");
		inputText2(Inbox.input_Compose_UserTo, recipient2,"Enter recipient2");
		pause(6000);
		click(Inbox.txt_Compose_SelectUser,"Click on User");
		inputText(Inbox.input_Compose_Subject, subject,"Enter subject");
		switchToFrame(Inbox.frame_Compose_Message);
		
		inputText(Inbox.input_Compose_Message, message,"Enter Message");
		switchTodefaultContent();
		inputText(Inbox.input_Compose_File, USERDIR+filePath,"upload file");
		pause(3000);
		
		ifAlertDismiss();
		
		click(Inbox.btn_Send,"Click on Send button");
		return true;
	}
	public boolean forwardMessagetouser(String subject)
	{
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		pause(5000);
		click(By.xpath("//a[contains(text(),'"+subject+"')]"),"Click on Subject of inbox message");
		pause(8000);
		isWebElementVisible(Inbox.lbl_forward,"Forward element is visiable");
		click(Inbox.lbl_forward,"click on forward button");
		scrollTo(Inbox.input_forward_reception);
		scrollToup();
		isWebElementVisible(Inbox.input_forward_reception,"forward reception is visiable");
		click(Inbox.input_forward_reception,"click on forward recption");
		pause(5000);
		return true;
	}
	public boolean replyMessagetoAlluser(String subject,String reply_name_1,String reply_name_2)
	{
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		pause(5000);
		click(By.xpath("//a[contains(text(),'"+subject+"')]"),"Click on Subject of inbox message");
		pause(8000);
		isWebElementVisible(Inbox.lbl_reply_all_reception,"Reply all element is visiable");
		click(Inbox.lbl_reply_all_reception,"click on reply all button");
		scrollTo(Inbox.input_reply_reception);
		scrollToup();
		isWebElementVisible(Inbox.input_reply_reception,"Reply All reception is visiable");
		String toreplyreceptionfirst=("//div[@class='tolist tolist_userPicker_reply']//span[contains(text(),'"+reply_name_1+"')]");
		String toreplyreceptionsecond=("//div[@class='tolist tolist_userPicker_reply']//span[contains(text(),'"+reply_name_2+"')]");
		isWebElementVisible( By.xpath(toreplyreceptionfirst),"reception name first is visiable");
		isWebElementVisible( By.xpath(toreplyreceptionsecond),"reception name Second is visiable");
		pause(5000);
		return true;
	}
	public boolean messageRecptionNotShown(String recipient1)
	{
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
		inputText(Inbox.input_Compose_UserTo, recipient1,"Enter recipient1");
		pause(6000);
		boolean eleSelected= getWebDriver().findElements(By.xpath("//div[@class='typeahead_primary']")).size() >0;
		System.out.println(eleSelected);
		if(!eleSelected)
		{
			
			logStepPass("recipient is not visible");
			return true;
		}
		
		else
		{
			logStepFail("recipient is not visible");
			Assert.fail();
			return false;			
		}
	}
	
	public boolean checkForblockschool(String schoolName,String bantitle)
	{
		String bantitle1=getText(Conversations.txt_school_ban_info);
		isWebElementVisible(Conversations.txt_school_ban_info, "scholl ban information is showing");
		compareTwoValues(bantitle1,bantitle);
		String schoolname=getText(Conversations.txt_ban_school_name);
		isWebElementVisible(Conversations.txt_ban_school_name,"school information");
		compareTwoValuesIgnoreCase(schoolName,schoolname);
		return true;
	}
	public boolean replyMessagetouser(String subject,String reply_name)
	{
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		pause(5000);
		click(By.xpath("//a[contains(text(),'"+subject+"')]"),"Click on Subject of inbox message");
		pause(8000);
		isWebElementVisible(Inbox.lbl_reply,"Reply element is visiable");
		click(Inbox.lbl_reply,"click on reply button");
		scrollTo(Inbox.input_reply_reception);
		scrollToup();
		isWebElementVisible(Inbox.input_reply_reception,"forward reception is visiable");
		String toreplyreception=("//div[@class='tolist tolist_userPicker_reply']//span[contains(text(),'"+reply_name+"')]");
		System.out.println(toreplyreception);
		isWebElementVisible( By.xpath(toreplyreception),"reception name  is visiable");
		pause(5000);
		return true;
	}
	
public boolean sentMessagetoPrinciple(String recipient1,String subject,String message,String filePath) 
{
		
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		isWebElementVisible(Inbox.btn_ComposeAMessage,"compose button is visiable");
		click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
		inputText(Inbox.input_Compose_UserTo, recipient1,"Enter recipient1");
		pause(10000);
		click(Inbox.txt_Compose_SelectUser,"Click on User");
		inputText(Inbox.input_Compose_Subject, subject,"Enter subject");
		switchToFrame(Inbox.frame_Compose_Message);
		
		inputText(Inbox.input_Compose_Message, message,"Enter Message");
		switchTodefaultContent();
		inputText(Inbox.input_Compose_File, USERDIR+filePath,"upload file");
		pause(3000);
		ifAlertDismiss();
		click(Inbox.btn_Send,"Click on Send button");
		isWebElementVisible(Inbox.txt_Compose_SuccessMsg,"Verify 'Message has been sent.' message");
		isWebElementVisible(Inbox.btn_ComposeAMessage,"compose button is visiable");
		return true;
	}
	public boolean verifySentMessage(String recipient1,String recipient2,String subject,String message)  {
	
		click(Inbox.lbl_Sent,"Click on Sent tab");
		pause(5000);
		click(By.xpath("//a[contains(text(),'"+subject+"')]"),"Click on Subject of sent message");
		isWebElementVisible(By.xpath("//p[contains(text(),'"+message+"')]"),"Verify Message Body");
		//isWebElementVisible(By.xpath("//div[@class='messages-owner']/a[text()='"+recipient1+"']"),"Verify recipient name "+recipient1);
		//isWebElementVisible(By.xpath("//div[@class='messages-owner']/a[text()='"+recipient2+"']"),"Verify recipient name "+recipient2);
		click(By.xpath("//a[text()='"+subject+"']"),"Click on Subject of sent message");
		return true;
	}
	public boolean verifySentMessageOfteacher(String recipient1,String subject,String message)  {
		
		click(Inbox.lbl_Sent,"Click on Sent Messages tab");
		pause(2000);
		ifAlertDismiss();
		click(By.xpath("//a[contains(text(),'"+subject+"')]"),"Click on Subject of sent message");
		isWebElementVisible(By.xpath("//p[contains(text(),'"+message+"')]"),"Verify Message Body");
		isWebElementVisible(By.xpath("//div[@class='messages-owner']/a[text()='"+recipient1+"']"),"Verify recipient name "+recipient1);
		//isWebElementVisible(By.xpath("//div[@class='messages-owner']/a[text()='"+recipient2+"']"),"Verify recipient name "+recipient2);
		click(By.xpath("//a[text()='"+subject+"']"),"Click on Subject of sent message");
		return true;
	}
	public boolean deleteAllSentMessages() {		
		click(Inbox.lbl_Sent,"Click on Sent tab");
		click(Inbox.btn_ToggleAll, "Click on ToggleAll button");
		click(Inbox.btn_Delete, "Click on Delete button");
		
		return true;
	}
	public boolean deleteASentMessage(String subject) {		
		click(Inbox.lbl_Sent,"Click on Sent tab");
		click(By.xpath("//a[text()='"+subject+"']/preceding::div[@class='messages-select']/input"), "Click on checkbox of "+subject +" Subject");
		click(Inbox.btn_Delete, "Click on Delete button");		
		return true;
	}
	public boolean deleteAllReceiveMessages() {		
		click(Inbox.lbl_Inbox,"Click on Inbox tab");
		click(Inbox.btn_ToggleAll, "Click on ToggleAll button");
		click(Inbox.btn_Delete, "Click on Delete button");
		
		return true;
	}
	public boolean deleteAReceiveMessage(String subject) {		
		click(Inbox.lbl_Inbox,"Click on Sent Messages tab");
		click(By.xpath("//a[text()='"+subject+"']/preceding::div[@class='messages-select']/input"), "Click on checkbox of "+subject +" Subject");
		click(Inbox.btn_Delete, "Click on Delete button");		
		return true;
	}
	
	public int getAllSentMessagesCountBeforeSent() {		
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		click(Inbox.lbl_Sent,"Click on Sent button");		
		List<WebElement> sentMessagesList= getWebDriver().findElements(Inbox.list_AllSentMessages);		
		return sentMessagesList.size();
	}
	public int getAllSentMessagesCountAfterSent() {		
		click(Inbox.lbl_Sent,"Click on Sent button");
		List<WebElement> sentMessagesList= getWebDriver().findElements(Inbox.list_AllSentMessages);
		return sentMessagesList.size();
	}
	public boolean receiveMessage(String senderName,String recipient1,String subject,String message)  {
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		click(Inbox.lbl_Inbox,"Click on Inbox tab");
		click(By.xpath("//a[text()='"+subject+"']"),"Click on Subject of sent message");
		isWebElementVisible(By.xpath("//p[contains(text(),'"+message+"')]"),"Verify Message Body");
		isWebElementVisible(By.xpath("//div[@class='messages-owner']/a[text()='"+senderName+"']"),"Verify sender Name "+senderName);
		isWebElementVisible(By.xpath("//div[starts-with(text(),'To:')]/a[text()='"+recipient1+"']"),"Verify recipient name "+recipient1);
		click(Inbox.lbl_Inbox,"Click on Inbox tab");
		return true;
	}
 public boolean deleteAllPosts() {		
		/*while(isWebElementPresent(Conversations.btn_Post_Delete,4)) {
		click(Conversations.btn_Post_Delete, "Click on Close(X) on 1st Post");
		click(Conversations.btn_Post_Delete_OK, "Click on OK Button");
		}*/
		return true;
	}
	public boolean delete1stPost(String subject) {
		//searchPost(subject);
		click(Conversations.btn_Post_Delete, "Click on Close(X) on 1st Post");	
		click(Conversations.btn_Post_Delete_OK, "Click on OK Button");		
		return true;
	}
	
	public boolean updateProfilePhoto(String photoPath) {
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_Settings,"Click on settings");
		pause(2000);
		click(HomePage.lnk_User_MyProfile,"Click on MyProfile");	
		click(MyProfile.lbl_EditProfilePicture,"Click on Edit Profile Picture");	
		isWebElementVisible(MyProfile.btn_ClearPicture, "Verify Clear Picture button");
		isWebElementVisible(MyProfile.btn_ChoosePictureText, "Verify Choose Picture button Text");
		inputText2(MyProfile.btn_ChoosePicture,photoPath, "Select profile picture");	
		pause(7000);
		isWebElementVisible(MyProfile.btn_Cancel, "Verify Cancel button");
		isWebElementVisible(MyProfile.btn_CropSavePhoto, "Verify Crop Save Photo button");
		isWebElementVisible(MyProfile.btn_AutofitSavePhoto, "Verify Auto fit Save Photo button");
		pause(8000);
		click(MyProfile.btn_AutofitSavePhoto,"Click on Auto fit Save Photo button");	
		attachScreen();
		return true;
	}
	public boolean VerifyNoGroup(String NoGroup,String cssAttributeValue ,String cssValue) {
			
			click(HomePage.tab_TopNavigation_Conversations,"Click on Conversations button");		
			isWebElementVisible(Conversations.txt_NoGroup,"Verify None text when No group avaialble");
			compareTwoValues(getText(Conversations.txt_NoGroup), NoGroup);
			String ActualcssValue=getCSSValue(Conversations.txt_NoGroup, cssAttributeValue,"get No Group text font sytle");
			compareTwoValues(ActualcssValue, cssValue);
			return true;
			
		}
	
	public boolean VerifyGroupMoreLess() {
		
		List<WebElement> allGroups= getWebDriver().findElements(Conversations.list_Group_All);
		int groupsCount=allGroups.size();
		if(groupsCount>15) {
			isWebElementVisible(Conversations.lnk_Group_More,"Verify More/Less options");
			click(Conversations.lnk_Group_More,"Click on More/Less Link");		
			isWebElementVisible(Conversations.lnk_Group_Less,"Verify Less options");
			
		}else {
			logStepPass("Group Size is less than 15. Found acutal groups count "+groupsCount);
		}
		
		return true;
		
	}
	
	public boolean VerifyShare() {
		
		List<WebElement> allGroups= getWebDriver().findElements(Conversations.list_Group_All);
		int groupsCount=allGroups.size();
		if(groupsCount>15) {
			isWebElementVisible(Conversations.lnk_Group_More,"Verify More/Less options");
			click(Conversations.lnk_Group_More,"Click on More/Less Link");		
			isWebElementVisible(Conversations.lnk_Group_Less,"Verify Less options");
			
		}else {
			logStepPass("Group Size is less than 15. Found acutal groups count "+groupsCount);
		}
		
		return true;
		
	}
	public boolean sharePost(String postTitle) {
		By postShare= By.xpath("(//span[@class='post_title' and text()='"+postTitle+"']/following::span[text()='Share'])[1]");
		isWebElementVisible(postShare,"Verify Share button");
		click(postShare,"Click on Share button");
		pause(8000);
		
		return true;
	}
	public boolean pinPost(String postTitle) {
		By postPin= By.xpath("(//span[@class='post_title' and text()='"+postTitle+"']/following::span[text()='Save'])[1]");
		isWebElementVisible(postPin,"Verify Save button");
		click(postPin,"Click on Save button");	
		
		return true;
	}
	public boolean likePost2(String postTitle) {
		By postLike= By.xpath("(//span[@class='post_title' and text()='"+postTitle+"']/following::span[text()='Like'])[1]");
		isWebElementVisible(postLike,"Verify Like button");
		click(postLike,"Click on Like button");	
		return true;
	}
	public boolean commentPost2(String postTitle,String comment) {
		By postComment= By.xpath("(//span[@class='post_title' and text()='"+postTitle+"']/following::textarea[@id='textarea_comment'])[1]");
		isWebElementVisible(postComment,"Verify comment textbox");
		//click(postPin,"Click on Pin button");	
		inputTextWithEnter(postComment, comment, "Enter Comment");
		
		return true;
	}
	public boolean editPost(String postTitle) {
		By editPostIcon= By.xpath("(//span[@class='post_title' and text()='"+postTitle+"']/following::a[@class='post-edit']/i)[1]");
		isWebElementVisible(editPostIcon,"Verify Edit Icon");
		click(editPostIcon,"Click on Edit Icon");	
		return true;
	}
		
	public boolean sharePost(String postTitle, String groupName) {
		
		sharePost(postTitle);
		click(Conversations.picker_GroupSelect,"Click Select group dropdown");		
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		click(Conversations.btn_Post,"Click Post");		
		return true;
		
	}
public boolean sharePost_group(String postTitle, String groupName) {
		
		sharePost(postTitle);
		click(Conversations.picker_GroupSelect,"Click Select group dropdown");		
		//String group="//a[@class='group_listings' and text()='"+groupName+"']";
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 2)) {
			String group="//a[@class='group_listings' and text()='"+groupName+"']";
			click(By.xpath(group),"Click on Group");
			}
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		System.out.println(groupParents);
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		System.out.println(groupParents);
		pause(2000);
		pause(2000);
		ifAlertDismiss();
		//click(Conversations.btn_Submit,"Click Post Submit button");
		click(Conversations.btn_Post,"Click Post");		
		return true;
		
	}
	public boolean editMessagePost(String subject, String newSubject)  {
		editPost(subject);
		pause(1000);
		inputText(Conversations.postSubject, newSubject, "Enter Subject");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Post,"Click Post button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+newSubject+"']");
		
		isWebElementVisible(PostXpath);
			
		return true;
	
	}
	public boolean verifyPostStransaltion(String  TranslatedPostTitle,String  TranslatedPostDescription,String SeeOriginal,String  subject)  {
		System.out.println(TranslatedPostTitle);
		String actualTranslatedPostTitle=getText(Conversations.txt_Translated_First_Post_Title);
		compareTwoValues(actualTranslatedPostTitle, TranslatedPostTitle);
		String actualTranslatedPostDescription=getText(Conversations.txt_Translated_First_Post_Description);
		compareTwoValues(actualTranslatedPostDescription, TranslatedPostDescription);
		String actualSeeOriginal=getText(Conversations.lnk_SeeOriginal);
		compareTwoValues(actualSeeOriginal, SeeOriginal);
		
		isWebElementPresent(Conversations.lnk_SeeOriginal, "Verify See Original in User language ");
		click(Conversations.lnk_SeeOriginal,"Click on See Original link in User language");
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		isWebElementPresent(postTitle, "Verify See Original post title");
		
		return true;
	}
	
	public boolean verifyPostStransaltion2(String  TranslatedPostTitle,String  TranslatedPostDescription,String SeeOriginal,String  subject)  {
		String actualTranslatedPostTitle=getText(Conversations.txt_Translated_First_Post_Title);
		compareTwoValuesIgnoreCase(actualTranslatedPostTitle, TranslatedPostTitle);
		String actualTranslatedPostDescription=getText(Conversations.txt_Translated_First_Post_Description);
		compareTwoValues(actualTranslatedPostDescription, TranslatedPostDescription);
		String actualSeeOriginal=getText(Conversations.lnk_SeeOriginal);
		compareTwoValues(actualSeeOriginal, SeeOriginal);
		
		isWebElementPresent(Conversations.lnk_SeeOriginal, "Verify See Original in User language ");
		click(Conversations.lnk_SeeOriginal,"Click on See Original link in User language");
		By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
		isWebElementPresent(postTitle, "Verify See Original post title");
		
		return true;
	}
	
	public boolean openProvisionSchool()  {
		click(HomePage.lnk_ProvisionSchool,"Click on Provision School");
		//pause(2000);
		String title=getWebDriver().getTitle();
		boolean flag=isWebElementPresent(HomePage.picker_ImportCSV,2);
		while(!flag){
			getWebDriver().navigate().refresh();
			 title=getWebDriver().getTitle();
			//click(HomePage.lnk_ProvisionSchool,"Click on Provision School");
			pause(1000);
			flag=isWebElementPresent(HomePage.picker_ImportCSV,2);
		}
		//isWebElementPresent(, "Verify See Original post title");
		
		return true;
	}
	public boolean provisionManually(String CSVPath)  {
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_Support,"Click on Support");
		openProvisionSchool();
		inputText2(HomePage.picker_ImportCSV, CSVPath, "Upload CSV File");
		
		click(HomePage.rbn_CompleteProvisioning,"Click on Complete Provisioning");
		click(HomePage.btn_ProvisioningUpload,"Click on Upload button");
		alertAccept();
		pause(2000);
		isWebElementPresent(HomePage.txt_FileUploadSuccessMsg, "Verify File Upload Success Message");
		click(HomePage.btn_ProvisioningCheckStatus, "Click on Check Status button");
		pause(5000);
		isWebElementPresent(HomePage.txt_Provision_Progress, "Verify Provision Progress");
		isWebElementPresent(HomePage.txt_Provision_Status, "Verify Provision Status");
		click(HomePage.lnk_Provision_Proceed, "Click on Proceed button");
		pause(5000);
		click(HomePage.lnk_Provision_Process_Activate, "Click on Proceed Activate button");
		isWebElementPresent(HomePage.txt_SubmittedProvisionNotificationMsg, "Verify Submitted Provision Message");
		pause(5000);
		click(HomePage.btn_ProvisioningCheckStatus, "Click on Check Status button");
		pause(5000);
		isWebElementPresent(HomePage.txt_Provision_Progress, "Verify ProcessMessage");
		click(HomePage.btn_ProvisioningCheckStatus, "Click on Check Status button");
		isWebElementPresent(HomePage.txt_Provision_Progress, "Verify Process "+getText(HomePage.txt_Provision_Progress));
		pause(3000);
		click(HomePage.btn_ProvisioningCheckStatus, "Click on Check Status button");
		isWebElementPresent(HomePage.txt_Provision_Progress, "Verify Process "+getText(HomePage.txt_Provision_Progress));
		
		return true;
	}
	
	public String provisionBaseCSVFile(String baseCSVFile) {
		String value=null;
		try
        {
		File inputFile = new File(USERDIR+baseCSVFile+".csv");

		// Read existing file 
		CSVReader reader1 = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader1.readAll();
		// get CSV row column  and replace with by using row and column
		value= csvBody.get(1)[0];
		reader1.close();
		value=value.substring(5);
        }catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
		return value;
	}
	public String createNewProvisionCSVFile(String oldNumber,int newNumber, String baseCSVFile) {	
		 String newProvisionCSVFile=null;
		try
        {
		File file = new File(USERDIR+baseCSVFile+".csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "", oldtext = "";
        while((line = reader.readLine()) != null)
            {
        	
            oldtext += line + "\r\n";
        }
        reader.close();
        // replace a word in a file
        //String newtext = oldtext.replaceAll("drink", "Love");
       
        //To replace a line in a file
        String newNum=String.valueOf(newNumber);
        String newtext = oldtext.replaceAll(oldNumber,newNum );
       
        FileWriter writer = new FileWriter(USERDIR+baseCSVFile+".csv");
        writer.write(newtext);
        writer.close();
        newProvisionCSVFile=USERDIR+baseCSVFile+newNum+".csv";
        FileWriter writer2 = new FileWriter(newProvisionCSVFile);
        writer2.write(newtext);
        writer2.close();
    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
		return newProvisionCSVFile;
	}
	public boolean updateWelcomeProfile(String newPassword,String confirmNewPassword,String DOB,String timeZone, String language)  {
		pause(2000);
		isWebElementPresent(ProvisionWelcomePage.txt_UserName, "Verify Username field");
		inputText(ProvisionWelcomePage.input_NewPassword, newPassword, "Enter New password");
		inputText(ProvisionWelcomePage.input_ConfirmNewPassword, confirmNewPassword, "Enter confirm New password");
		inputText(ProvisionWelcomePage.input_DOB, DOB, "Enter Birthday");
		click(ProvisionWelcomePage.txt_UserName,"close Birthday Popup");		
		selectByValue(ProvisionWelcomePage.picker_Language, language,"Select Language");
		selectByValue(ProvisionWelcomePage.picker_TimeZone, timeZone,"Select TimeZone");
		click(ProvisionWelcomePage.btn_Continue,"Click on Continue");
		if(isWebElementPresent(ProvisionWelcomePage.txt_ResponseRequiredError, 5)) {
		click(ProvisionWelcomePage.chk_TermsOfService,"Click on Continue");		
		click(ProvisionWelcomePage.btn_Continue,"Click on Continue");
		}
		return true;
	}
	public boolean Self_service_add_grade(String filePath,String School_name)
	{
		click(HomePage.lnk_UserOptions,"Click on Options");
		pause(2000);
		click(HomePage.lnk_Manage_Data,"Click on Manage data");
		pause(8000);
		click(Manage_Data.select_down_school,"select drop down achool");
		Select drpschl = new Select(getWebDriver().findElement(By.id("school_select")));
		drpschl.selectByVisibleText(School_name);
		pause(6000);
		isAddadmindisplayandClick();
		pause(6000);
		scrollTo(Manage_Data.lbl_upload_student_data);
		click(Manage_Data.lbl_upload_student_data,"open the pop up for upload student data");
		//inputText2(Conversations.btn_UploadFile, filePath, "Enter file Path");
		fileUpload(filePath);
		click(Manage_Data.btn_update_network,"update to network");
		pause(5000);
		
		return true;
	}
	public boolean isAddadmindisplayandClick()
	{
		//scrollTo(Manage_Data.btn_add_admin);
		click(Manage_Data.btn_add_admin,"click on add button");
		pause(4000);
		click(Manage_Data.lbl_close_add_admin,"close add adminpanel");
		return true;
	}
	public boolean verifyEditProfile(String expectedDOB,String expectedLanguage)  {
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_MyProfile,"Click on My Profile");
		
		click(MyProfile.lbl_EditProfilePreferences,"Click on Edit Profile & Preferences");
		String actualDOB=getElementValue(EditProfile.input_DOB);
		String actualLanguage=getElementValue(EditProfile.input_Language);
		compareTwoValues(actualDOB, expectedDOB);
		compareTwoValues(actualLanguage, expectedLanguage);
		return true;
	}
	public boolean editEmailid(String email)
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_Settings,"Click on settings");
		click(HomePage.lnk_User_MyProfile,"Click on My Profile");
		click(MyProfile.lbl_EditProfilePreferences,"Click on Edit Profile & Preferences");
		inputText(EditProfile.input_Email,email, "Enter new email id for the user");
		click(EditProfile.btn_Save,"Click on save button");
		pause(10000);
		return true;
		
	}
	public boolean verifyemailaddresserormessage(String newmailaddressinvalid,String excepted_error_message,String newmailaddressvalid)
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_MyProfile,"Click on My Profile");
		click(MyProfile.lbl_EditProfilePreferences,"Click on Edit Profile & Preferences");
		inputText(EditProfile.input_Email,newmailaddressinvalid, "Enter new email address");
		click(EditProfile.btn_Save,"Click on save button");
		pause(10000);
		String actual_error_message_in_phone_number=getText(EditProfile.lbl_error_message_mail);
		pause(1000);
		compareTwoValues(actual_error_message_in_phone_number, excepted_error_message);
		pause(2000);
		clear_locator(EditProfile.input_Email);
		pause(2000);
		inputText(EditProfile.input_Email,newmailaddressvalid, "Enter new mail address");
		pause(1000);
		click(EditProfile.btn_Save,"Click on save button");
		pause(10000);
		return true;	
	}
	public boolean verifyphonenumbererrormessage(String newphonenoinvalid,String excepted_error_message,String newphonenovalid)
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		click(HomePage.lnk_User_Settings,"Click on settings");
		click(HomePage.lnk_User_MyProfile,"Click on My Profile");
		click(MyProfile.lbl_EditProfilePreferences,"Click on Edit Profile & Preferences");
		inputText(UserProfiles.input_User_chngephoneno,newphonenoinvalid, "Enter new phone number");
		click(EditProfile.btn_Save,"Click on save button");
		pause(10000);
		String actual_error_message_in_phone_number=getText(EditProfile.lbl_error_message);
		pause(1000);
		compareTwoValues(actual_error_message_in_phone_number, excepted_error_message);
		pause(2000);
		clear_locator(UserProfiles.input_User_chngephoneno);
		pause(2000);
		inputText(UserProfiles.input_User_chngephoneno,newphonenovalid, "Enter new phone number");
		pause(1000);
		ifAlertDismiss();
		click(EditProfile.btn_Save,"Click on save button");
		pause(10000);
		return true;
		
	}
	public void navigateToAddlayer(String SchoolName,String UserName,String new_user_value_n,String button_name_value_other,String button_name_value_teacher,String filepath,String new_user_value_y,String env )
	{
		pause(2000);
		click(HomePage.tab_TopNavigation_MyNetwork,"navigate to my network page");
		pause(10000);
		String school_name="//li[@id='Schools']/ul/li[2]";
		click(By.xpath(school_name),"Click on school");
		pause(8000);
		ifAlertDismiss();
		isWebElementgettextvalidate(AddToLayer.invite_other_element,button_name_value_other);
		isWebElementgettextvalidate(AddToLayer.Invite_Classroom_Teacher,button_name_value_teacher);
		pause(6000);
		click(AddToLayer.invite_other_element,"click on the other to invite in the Layer");
		pause(2000);
		inputText(AddToLayer.search_box, UserName, "Add user to layer");
		pause(10000);
		if(env.equalsIgnoreCase("maple"))
		{
		//public static By user_to_add=By.xpath("//a[contains(text(),'Alisha Johnson')]");
		String user_to_add="//a[contains(text(),'"+UserName+"')]";
		click(By.xpath(user_to_add),"Click on user to add in layer");
		isWebElementgettextvalidate(AddToLayer.table_value1,new_user_value_n);
		}
		click(AddToLayer.upload_list, "open file upload to upload csv file");
		fileUpload(filepath);
		pause(50000);
		isWebElementgettextvalidate(AddToLayer.table_value2,new_user_value_y);
		click(AddToLayer.chk_send_mail,"click on the check box send mail");
		click(AddToLayer.chek_wellcome_sms,"click on check  wellcome sms");
		isWebElementPresent(AddToLayer.btn_add_layer_cancel,"cancel button display");
		isWebElementPresent(AddToLayer.btn_add_layer_add_contact,"add contact button display");
		click(AddToLayer.btn_add_layer_add_contact,"click on add contact button to add in layer");
		//isWebElementVisible_need(AddToLayer.txt_add_user_SuccessMsg,"Users were added successfully");
		
		
	}
	public void navigateToAddlayer_new(String SchoolName,String UserName,String new_user_value_n,String button_name_value_other,String button_name_value_teacher,String filepath,String new_user_value_y )
	{
		String test="TEST";
		pause(2000);
		click(HomePage.tab_TopNavigation_MyNetwork,"navigate to my network page");
		pause(10000);
		String school_name="//li[@id='Schools']/ul/li[2]";
		click(By.xpath(school_name),"Click on school");
		pause(8000);
		click(HomePage.btn_Create_a_New_Layer,"click on create a new layer button");
		inputText(HomePage.txt_new_layer_name, test,"gave the layer a new name: test");
		click (HomePage.btn_save_new_layer,"click to save the new layer");
		ifAlertDismiss();
		pause(8000);
		getWebDriver().navigate().refresh();
		pause(10000);
		/*try {
			scrollDowntoEnd("scroll to end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String invite_other_element="//div[8]//div[1]//div[2]//a[3]";
		String Invite_Classroom_Teacher="//div[8]//div[1]//div[2]//a[2]";
		isWebElementgettextvalidate(By.xpath(invite_other_element),button_name_value_other);
		isWebElementgettextvalidate(By.xpath(Invite_Classroom_Teacher),button_name_value_teacher);
		pause(6000);
		click(By.xpath(invite_other_element),"click on the other to invite in the Layer");
		pause(2000);
		//inputText(AddToLayer.search_box, UserName, "Add user to layer");
		//pause(10000);
		//public static By user_to_add=By.xpath("//a[contains(text(),'Alisha Johnson')]");
		//String user_to_add="//a[contains(text(),'"+UserName+"')]";
		//click(By.xpath(user_to_add),"Click on user to add in layer");
		//isWebElementgettextvalidate(AddToLayer.table_value1,new_user_value_n);
		click(AddToLayer.upload_list, "open file upload to upload csv file");
		fileUpload(filepath);
		pause(50000);
		isWebElementgettextvalidate(AddToLayer.table_value2,new_user_value_y);
		click(AddToLayer.chk_send_mail,"click on the check box send mail");
		click(AddToLayer.chek_wellcome_sms,"click on check  wellcome sms");
		isWebElementPresent(AddToLayer.btn_add_layer_cancel,"cancel button display");
		isWebElementPresent(AddToLayer.btn_add_layer_add_contact,"add contact button display");
		click(AddToLayer.btn_add_layer_add_contact,"click on add contact button to add in layer");
		//isWebElementVisible_need(AddToLayer.txt_add_user_SuccessMsg,"Users were added successfully");
		
		
	}
	
	public void addTeacherinGroup(String SchoolName,String TeacherName)
	{
		pause(2000);
		click(HomePage.tab_TopNavigation_MyNetwork,"navigate to my network page");
		pause(10000);
		String school_name="//a[text()='"+SchoolName+"']";
		click(By.xpath(school_name),"Click on school");
		pause(8000);
		//isWebElementgettextvalidate(AddToLayer.invite_other_element,button_name_value_other);
		//isWebElementgettextvalidate(AddToLayer.Invite_Classroom_Teacher,button_name_value_teacher);
		pause(6000);
		click(AddToLayer.Invite_Classroom_Teacher,"click on the Teacher to invite in the Layer");
		pause(2000);
		inputText(HomePage.InviteeEmail, TeacherName, "Enter teacher Invitee");
		click(HomePage.sendInvitee,"Click On Send Invite");
	}
	public void checkleftbarschool(String schoolname)
	{
		String school_name="//div[@class='div-left']//a[text()='"+schoolname+"']";
		//isWebElementDisplayed(By.xpath(school_name),"user added to that school")
		isWebElementVisible(By.xpath(school_name),"user added to that school");
		//isWebElementgettextvalidate(By.xpath(school_name), schoolname);
	}
	
	public void save_default_message(String subject,String message)
	{
		click(Send_Emergency_Alert.lbl_emergency_alert_create_message,"click on the defalt message to create a new message");
		pause(5000);
		clear_locator(Send_Emergency_Alert.txt_default_subject);
		inputText2(Send_Emergency_Alert.txt_default_subject, subject, "Enter subject for default emergencey alert");
		clear_locator(Send_Emergency_Alert.txt_default_text_area);
		inputText2(Send_Emergency_Alert.txt_default_text_area, message, "Enter message for default emergencey alert");
		click(Send_Emergency_Alert.btn_default_save,"save the default message");
		pause(10000);
		
		
	}
	public void postEmergenceyAlert(String subject,String message)
	{
		click(Send_Emergency_Alert.lbl_emergency_alert_send_alert,"click on the alret to create a alert");
		pause(8000);
		String subject_from_post_screen=getElementValue(Send_Emergency_Alert.txt_share_screen_subject);
		//System.out.println(subject_from_post_screen);
		compareTwoValuesIgnoreCase(subject_from_post_screen, subject);
		String text_from_post_screen =getElementValue(Send_Emergency_Alert.txt_share_screen_text_area);
		compareTwoValuesIgnoreCase(text_from_post_screen, message);
		pause(2000);
		ifAlertDismiss();
		click(Send_Emergency_Alert.btn_share_screen_post,"post the emergencey alert");
		alertAccept();
		alertAccept();
		pause(10000);
		
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		isWebElementVisible(PostXpath);
		
	}
	public boolean clickOnImginLoginpage(String url) 
	{
		
		click(LoginPage.img_edliobanner,"click on the imge in login page to load website");
		pause(5000);
		String text=getWebDriver().getCurrentUrl();
		compareTwoValuesIgnoreCase(text, url);
		return true;
	}
	public boolean check_secdule_post(boolean admin)
	{
		pause(9000);
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		System.out.println(admin);
		try {
			scrolltoElement_new(Conversations.btn_Post, "scroll down to schedule post");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(admin)
		{
			
			verifyWebElementPresent(Conversations.lbl_SchedulePost,"element is visible");
			System.out.println("enter");
		}
		else
		{
			verifyWebElementPresent(Conversations.lbl_SchedulePost, "secdule post option not available");
		}
		
		getWebDriver().navigate().refresh();
		pause(8000);
		return true;
	}
	public boolean updateprofile_new(String Timezone,String email,String newphoneno )
	{
	click(HomePage.lnk_UserOptions,"Click on Options");
	pause(2000);
	click(HomePage.lnk_User_MyProfile,"Click on MyProfile");
	pause(2000);
	//click(HomePage.lnk_EditProfile,"click on Edit Profile & Preferences");
	click(MyProfile.lbl_EditProfilePreferences,"click on Edit Profile & Preferences");
	pause(2000);
	/*click(EditProfile.picker_Timezone,"click on TimeZone");*/
	Select drpcountry=new Select(getWebDriver().findElement(By.id("timezone_select_list")));
	pause(2000);
	drpcountry.selectByValue(Timezone);
	pause(2000);
	inputText(UserProfiles.input_User_chngemail,email, "Enter new email id");
	pause(2000);
	// edit phone number( By Deepak)
	
	inputText(UserProfiles.input_User_chngephoneno,newphoneno, "Enter new phone number");
	pause(5000);
	
	//click on save button 
	
	click(EditProfile.btn_Save,"Click on save button");
	pause(10000);
		
	return true;
		
	}
	public boolean updateNotificationPreferences()
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		//pause(2000);
		click(HomePage.lnk_User_MyProfile,"Click on MyProfile");
		click(MyProfile.lbl_EditProfilePreferences,"click on lbl edit profile preferences");
		try {
			scrolltoElement_new(EditProfile.rbn_EmailNotification_Yes, "navigate to email notification");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement emailradiobutton_yes=getWebElement(EditProfile.rbn_EmailNotification_Yes);
		if(emailradiobutton_yes.isSelected())
		{
			logStep("Email Notification already selected");
		}
		else
		{
			emailradiobutton_yes.click();
			logStep("Email Notification is selected");
			
		}
		/*WebElement mobileradiobutton_yes=getWebElement(EditProfile.rbn_MobilePushAlert_Yes);
		if(mobileradiobutton_yes.isSelected())
		{
			logStep("Mobile Push Alerts already selected");
		}
		else
		{
			emailradiobutton_yes.click();
			logStep("Mobile Push Alerts is selected");
		}*/
		
		return true;
	}
	public boolean postPhonealertJustMe(String subject, String message,String time)
	{
		pause(5000);
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_phn_alert,"Click on Phone Alert");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		//getWebDriver().switchTo().frame(0);
		inputText(Conversations.txt_autodial_text_area, message, "Enter Message");
		//getWebDriver().switchTo().defaultContent();
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		click(Conversations.txt_JustMe,"Click on just me");
		pause(5000);
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		
		
		
		String al_text=alertText();
		alertAccept();
		if(al_text.contains(time))
		{
			pause(10000);
			By PostXpath=By.xpath("//span[text()='"+subject+"']");
			isWebElementVisible(PostXpath);
			return true;
		
		}
		else
		{
			return false;
		}
	}
	
	public boolean postPhonealertgroup(String subject, String message,String time,String groupName)
	{
		pause(5000);
		click(Conversations.shareMessage,"Click on Create a post or share a photo, video or file textbox");
		click(Conversations.btn_phn_alert,"Click on Phone Alert");
		inputText(Conversations.postSubject, subject, "Enter Subject");
		pause(3000);
		//getWebDriver().switchTo().frame(0);
		inputText(Conversations.txt_autodial_text_area, message, "Enter text area");
		//getWebDriver().switchTo().defaultContent();
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 2)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupStuff="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Staff')])[1]";
		click(By.xpath(groupStuff),"Click on stuff");
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		String groupStudents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Students')])[1]";
		click(By.xpath(groupStudents),"Click on Students");
		pause(2000);
		ifAlertDismiss();
		pause(5000);
		click(Conversations.btn_Submit,"Click Post Submit button");
		
		
		
		String al_text=alertText();
		alertAccept();
		if(al_text.contains(time))
		{
			pause(10000);
			By PostXpath=By.xpath("//span[text()='"+subject+"']");
			isWebElementVisible(PostXpath);
			return true;
		
		}
		else
		{
			return false;
		}
}
public boolean welcomemailtoindividually(String SchoolName,String Lbllayer,String ClassName,String ParentName)
{
	click(HomePage.tab_TopNavigation_MyNetwork,"navigate to my network page");
	pause(10000);
	String school_name="//a[text()='"+SchoolName+"']";
	click(By.xpath(school_name),"Click on school");
	pause(8000);
	String lbl_layer_name="(//span[text()='"+Lbllayer+"']/parent::div//following::span[@class='build-big-arrow'])[2]";
	click(By.xpath(lbl_layer_name),"Click on layer");
	pause(2000);
	//click(mynetwrok.lbl_class_select,"navigate to class");
	String class_name="//a[text()='"+ClassName+"']";
	click(By.xpath(class_name),"Click on class");
	pause(10000);
	click(mynetwrok.lbl_parent_select,"select a lable of parent");
	pause(5000);
	String parent_name="//a[text()='"+ParentName+"']";
	click(By.xpath(parent_name),"Click on parent");
	pause(5000);
	try {
		scrolltoElement_new(mynetwrok.sendwellcomemailtosingleuser, "navigate to wellcome mail sent");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	isWebElementVisible(mynetwrok.sendwellcomemailtosingleuser, "welcome mail send visible to admin");
	click(mynetwrok.sendwellcomemailtosingleuser, "click to send welcome mail to that user");
	String al_text=alertText();
	alertAccept();
	if(al_text.contains("welcome mail"))
	{
		pause(10000);
		//By PostXpath=By.xpath("//span[text()='"+subject+"']");
		//isWebElementVisible(PostXpath);
		return true;
	
	}
	else
	{
		return false;
	}
}
public boolean validatewithparentgroup(String subject)
{
	click(Conversations.left_nav_parent_group,"click on parent group");
	By PostXpath=By.xpath("//span[text()='"+subject+"']");
	
	isWebElementVisible(PostXpath);
	return true;
	
}
public boolean serachfamily(String family)
{
	click(HomePage.tab_TopNavigation_MyNetwork,"navigate to my network page");
	pause(10000);
	String family_name="//a[text()='"+family+"']";
	click(By.xpath(family_name),"Click on family");
	pause(8000);
	return true;	
}
public boolean deletegorup(String groupname)
{
	String group_name_delete="//div[contains(text(),'"+groupname+"')]/parent::div//span[@class='build-delete']";
	click(By.xpath(group_name_delete),"Click on delete");
	alertAccept();
	pause(5000);
	return true;
}
public boolean delete_parent_from_group(String groupname)
{
	click(HomePage.img_mynetwork,"Click on my network");
	pause(2000);
	String familyname="//a[text()='"+groupname+"']";
	click(By.xpath(familyname),"Click on group");
	pause(2000);
	click(mynetwrok.lnk_parent_connection,"click on connection under parent section");
	pause(2000);
	click(mynetwrok.lnk_icon_on_first_parent,"click on icon on first parent");
	pause(1000);
	click(mynetwrok.lnk_delete_parent,"click to delete the first parent");
	pause(1000);
	if(isAlertPresent())
    {
    alertAccept();
    }
	pause(5000);
	return true;
}
public boolean delete_principal_from_distric(String groupname)
{
	click(HomePage.img_mynetwork,"Click on my network");
	pause(2000);
	String familyname="//a[text()='"+groupname+"']";
	click(By.xpath(familyname),"Click on group");
	pause(2000);
	/*click(mynetwrok.lnk_parent_connection,"click on connection under parent section");
	pause(2000);*/
	click(mynetwrok.lnk_icon_on_first_admisitrator,"click on icon on first admin of the group");
	pause(1000);
	click(mynetwrok.lnk_delete_administrator,"click to delete the first admistrator that added");
	pause(1000);
	if(isAlertPresent())
    {
    alertAccept();
    }
	pause(5000);
	return true;
}
public boolean delete_student_from_group(String groupname)
{
	click(HomePage.img_mynetwork,"Click on my network");
	pause(2000);
	String familyname="//a[text()='"+groupname+"']";
	click(By.xpath(familyname),"Click on group");
	pause(2000);
	click(mynetwrok.lnk_student_connection,"click on connectoin under student section");
	pause(2000);
	click(mynetwrok.lnk_icon_on_first_parent,"click on icon on student parent");
	pause(1000);
	click(mynetwrok.lnk_delete_parent,"click to delete the student parent");
	pause(1000);
	if(isAlertPresent())
    {
    alertAccept();
    }
	pause(5000);
	return true;
}
public boolean create_mirror_group(String family_name,String group_owner_mail_id,String groupowner_first_name,String groupowner_last_name,String groupowner_phone)
{
	click(HomePage.img_mynetwork,"Click on my network");
	pause(2000);
	String familyname="//a[text()='"+family_name+"']";
//	click(By.xpath(familyname),"Click on family");
	pause(5000);
	click(mynetwrok.btn_create_mirror_group,"click to create a mirror group");
	inputText(mynetwrok.txt_input_group_user_mail_id, group_owner_mail_id, "enter group owner mail id");
	pause(2000);
	click(mynetwrok.btn_create_mirror_group_to_netwrok,"click to create a mirror group");
	boolean eleSelected= getWebDriver().findElement(By.id("first_name")).isDisplayed();
	//int status=getWebDriver().findElements(By.id("first_name")).size();
	//System.out.println(eleSelected);
	if(eleSelected)
	{
		inputText(mynetwrok.txt_first_name, groupowner_first_name, "input the firstname");
		pause(1000);
		inputText(mynetwrok.txt_last_name, groupowner_last_name, "input last name");
		pause(1000);
		inputText(mynetwrok.txt_phone, groupowner_phone, "input phone");
		pause(1000);
		click(mynetwrok.chk_Send_Welcome_Email,"click the check box of Welcome email");
		pause(1000);
		click(mynetwrok.chk_Send_Welcome_SMS,"click the check box of welcome sms");
		pause(1000);
		click(mynetwrok.btn_create_mirror_group_to_netwrok,"click to create a mirror group");
		pause(10000);
	}
	{
		pause(10000);
	}
	return true;
	
}
public boolean isPostDisplay(String subject)
{
	By postTitle= By.xpath("//span[@class='post_title' and text()='"+subject+"']");
	boolean eleSelected= getWebDriver().findElements(By.xpath("//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Share']")).size() >0;
	if(!eleSelected)
	{
		return true;
	}
	
	else
	{
		return false;
	}
	
}
public boolean isPostContentDisplay(String Content_name)
{
	By postcontent= By.xpath("//span[contains(text(),'"+Content_name+"')]");
	boolean eleSelected= getWebDriver().findElements(By.xpath("//span[contains(text(),'"+Content_name+"')]")).size() >0;
	if(eleSelected)
	{
		click(postcontent,"download the content");
		pause(60000);
		return true;
	}
	
	else
	{
		return false;
	}
	
}
public boolean searchUserinDirectory(String user)  {
	
	click(HomePage.tab_TopNavigation_Directory,"Click on Directory");
	//click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
	inputText(Directory.input_search_directory, user,"Enter recipient1");
	pause(6000);
	boolean eleSelected= getWebDriver().findElements(By.xpath("//div[@class='typeahead_primary']")).size() >0;
	if(!eleSelected)
	{
		return true;
	}
	
	else
	{
		return false;
	}
}
public boolean searchClassinDirectory(String groupName)
{
	click(HomePage.tab_TopNavigation_Directory,"Click on Directory");
	boolean eleSelected= getWebDriver().findElements(By.xpath("//h4[contains(text(),'"+groupName+"')]")).size() >0;
	if(!eleSelected)
	{
		return true;
	}
	
	else
	{
		return false;
	}
}


//Monica 

public boolean verifyPost(String message, String LogStep)
{
	By photoPostTitle = By.xpath("//p[contains(text(),message)]");
	
	try{
		 isWebElementPresent(photoPostTitle,10);
		 logStepPass(LogStep);
		}
		catch(NoSuchElementException e){
		 //Element is not present
			logStepFail(LogStep + "  --Fail");
		}
	return true;
	
}

public boolean verifyLikes(String LogStep)
{
	By postLikes = By.xpath("//ul[@class='nav nav-pills comment-like-container-count pull-right']/li[2]/div/span");
	
	if(Integer.parseInt(getWebDriver().findElement(postLikes).getText())>=1)
	{
		logStepPass(LogStep);
	}
	else
	{
		logStepFail(LogStep + "  --Fail");
	}
	
	return true;
}

public boolean verifyNotificationForLikes(String name, String LogStep)
{
	By notifications = By.xpath("//div[@id='topnav-dashboard-icon']");
	
	WebElement ele = getWebDriver().findElement(notifications);
	Actions action = new Actions(getWebDriver());
	action.moveToElement(ele).perform();
	
	By likesNotif = By.xpath("//ul[@class='lt-inbox-menu']/li[2]");
	
	if(getWebDriver().findElement(likesNotif).getText().contains(name)) {
		logStepPass(LogStep);
	}
	else
	{
		logStepFail(LogStep + "  --Fail");
	}
	
	return true;
}

public boolean verifyClasses(String LogStep)
{
	By classesTitle = By.xpath("//span[contains(text(),'CLASSES')]");
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
		 jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(classesTitle));
		 if(isWebElementPresent(classesTitle,10))
		 {
		 logStepPass(LogStep);
		 }
		 else{
		 //Element is not present
			logStepFail(LogStep + "  --Fail");
		}
	
	
	return true;
}

public boolean verifyGroup(String LogStep)
{
	By groupTitle = By.xpath("//span[contains(text(),'GROUPS')]");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
	 jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(groupTitle));
	 if(isWebElementPresent(groupTitle,10))
	 {
	 logStepPass(LogStep);
	 }
	 else{
	 //Element is not present
		logStepFail(LogStep + "  --Fail");
	}
	
	
	return true;
}

public boolean verifySchools(String LogStep)
{
	By schoolsTitle = By.xpath("//span[contains(text(),'SCHOOLS')]");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
	 jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(schoolsTitle));
	 if(isWebElementPresent(schoolsTitle,10))
	 {
	 logStepPass(LogStep);
	 }
	 else{
	 //Element is not present
		logStepFail(LogStep + "  --Fail");
	}
	
	
	
	return true;
}

public boolean verifyDistricts(String LogStep)
{
	By districtsTitle = By.xpath("//span[contains(text(),'DISTRICTS')]");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
	 jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(districtsTitle));
	 if(isWebElementPresent(districtsTitle,10))
	 {
	 logStepPass(LogStep);
	 }
	 else{
	 //Element is not present
		logStepFail(LogStep + "  --Fail");
	}
	
	
	return true;
}

public boolean postTypeFilterCheck()
{
	By allPosts = By.xpath("//label[contains(text(),'All')]");
	By savedPosts = By.xpath("//label[contains(text(),'Saved')]");
	By eventPosts = By.xpath("//label[contains(text(),'Events')]");
	By filePosts = By.xpath("//label[contains(text(),'Files')]");
	By photoVideoPosts = By.xpath("//label[contains(text(),'Photos / Videos')]");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
	jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(allPosts));
	jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(savedPosts));
	jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(eventPosts));
	jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(filePosts));
	jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(photoVideoPosts));
	 
	 if(isWebElementPresent(allPosts,10))
	 {
	 logStepPass("All filter Present");
	 }
	 else{
	 //Element is not present
		logStepFail("All filter Present" + "  --Fail");
	}
	 
	 if(isWebElementPresent(savedPosts,10))
	 {
	 logStepPass("Saved filter Present");
	 }
	 else{
	 //Element is not present
		logStepFail("Saved filter Present" + "  --Fail");
	}
	 
	 if(isWebElementPresent(eventPosts,10))
	 {
	 logStepPass("Events filter Present");
	 }
	 else{
	 //Element is not present
		logStepFail("Events filter Present" + "  --Fail");
	}
	 
	 if(isWebElementPresent(filePosts,10))
	 {
	 logStepPass("Files filter Present");
	 }
	 else{
	 //Element is not present
		logStepFail("Files filter Present" + "  --Fail");
	}
	 
	 if(isWebElementPresent(photoVideoPosts,10))
	 {
	 logStepPass("Photo/Video filter Present");
	 }
	 else{
	 //Element is not present
		logStepFail("Photo/Video filter Present" + "  --Fail");
	}
	return true;
}


	public boolean navigateToInboxFromPost(String logStep)
	{
		
		pause(5000);
		if(isWebElementPresent(HomePage.posterName,10))
		{
		click(HomePage.posterName,"Click on poster's name");
		logStepPass(logStep);
		}
		else
			logStepFail(logStep + "  --Fail");
		
		return true;
		
	}
	
	public boolean verifyInbox(String logStep) {
		
		if(isWebElementPresent(HomePage.inboxHeader,10))
		{
			click(HomePage.inboxHeader, "Verify Inbox header");
			logStepPass(logStep);
		}
		else
			logStepFail(logStep + "  --Fail");
		return true;
		
	}
	
	public boolean verifySearchInbox(String logStep) {
		
		click(HomePage.inboxIcon,"Click on Inbox icon");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.searchTabInbox));
		
		if(isWebElementPresent(HomePage.searchTabInbox,10))
		{
			logStepPass(logStep+ "  -- exists");
		}
		else
		{
			logStepFail(logStep +" -- does not exist");
		}
		
		return true;
	}
	
	public boolean searchByUsername(String logStep)
	{
		inputText(HomePage.searchTabInbox, "gill01 john01", logStep);
		click(HomePage.searchButton, "Search for user");
		
		//List of searched messages
		List<WebElement> messagesList = new ArrayList<WebElement>();
		
		messagesList = getWebDriver().findElements(HomePage.searchedMessages);
		int count = 0;
		for(WebElement i: messagesList)
		{
			if(i.getText().contains("gill01 john01"))
			{
				count++;
			}
		}
		if(count == messagesList.size())
		{
			logStepPass(logStep+ " -- messages are filtered");
		}
		
		
		return true;
	}
	
	public boolean searchByTitle(String logStep)
	{
		inputText(HomePage.searchTabInbox, "Test LM913", logStep);
		click(HomePage.searchButton, "Search for user");
		
		//List of searched messages
		List<WebElement> messagesList = new ArrayList<WebElement>();
		
		messagesList = getWebDriver().findElements(HomePage.searchTitle);
		int count = 0;
		for(WebElement i: messagesList)
		{
			if(i.getText().contains("Test LM913"))
			{
				count++;
			}
		}
		if(count == messagesList.size())
		{
			logStepPass(logStep+ " -- messages are filtered by title");
		}
		return true;
	}
	
	public boolean searchReceivedMessage(String title,String logStep)
	{
		if(getWebDriver().findElement(HomePage.searchTitle).getText() == title)
		{
			logStepPass(logStep+ " -- Message received");
		}
		return true;
	}
	
	public boolean resharePost(String subject, String logStep)
	{
		
		By sharePost = By.xpath("//span[contains(text(),subject)]/following::li[@class='reshare_photos']");
		click(getWebDriver().findElement(sharePost), "Click on share");
		pause(3000);
		click(HomePage.wantToShare, "Click on 'I want to share this with' button");
		click(HomePage.grpSelect, "Select group");
		click(HomePage.grpStudents, "Able to share with Classes");
		
		pause(3000);
		
		ifAlertDismiss();
		
		click(HomePage.submitPost, "Click on submit");
		
		
		return true;		
	}

	public boolean resharePostParent(String subject, String logStep)
	{
		
		By sharePost = By.xpath("//span[contains(text(),subject)]/following::li[@class='reshare_photos']");
		click(getWebDriver().findElement(sharePost), "Click on share");
		pause(3000);
		click(HomePage.wantToShare, "Click on 'I want to share this with' button");
		click(HomePage.grpSelect, "Select group");
		
		if(!isWebElementVisible(HomePage.grpStudents))
		{
			logStepPass(logStep+ " -- Classes/groups not visible for parents");
		}
		pause(3000);
		
		click(HomePage.grpSelectForParents, "Select group to re-share");
		
		ifAlertDismiss();
		
		click(HomePage.submitPost, "Click on submit");
		
		
		return true;		
	}
	
	public boolean typeParentSeeStudentInbox(String parent, String logStep)
	{
		click(Inbox.inboxNav, "Navigate to Inbox page");
		pause(3000);
		click(Inbox.composeMessage, "Click on compose a Message");
		inputText(Inbox.toField, parent, "Enter parent name");
		pause(3000);
		List<WebElement> names = new ArrayList<WebElement>();
		
		names = getWebDriver().findElements(By.xpath("//ul[@class='typeahead dropdown-menu']/li"));
		
		for(WebElement i:names)
		{
			logStepPass(logStep + "---" +i.getText().substring(i.getText().indexOf('(')+1, i.getText().length()-1));
		}
		
		return true;
		
	}
	
	public boolean pinnedToSaved(String logStep)
	{
		if(isWebElementVisible(HomePage.filterSaved))
		{
			logStepPass(logStep+ "  -- exists");
		}
		else
		{
			logStepFail(logStep +" -- does not exist");
		}
		
		return true;
		
	}
	
	public boolean pinToSave(String subject, String logStep)
	{
		
		By postSave = By.xpath("//span[contains(text(),subject)]/following::li/a/i[@class='post-pin ']");
		logStepPass(logStep + "---Save button present");
		click(getWebDriver().findElement(postSave)," Click on Save post");
		
		return true;
	}
	
public boolean sentMessageCopyImage(String recipient1,String recipient2,String subject,String message)  {
		
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
		inputText(Inbox.input_Compose_UserTo, recipient1,"Enter recipient1");
		pause(6000);
		click(Inbox.txt_Compose_SelectUser,"Click on User");
		inputText2(Inbox.input_Compose_UserTo, recipient2,"Enter recipient2");
		pause(6000);
		click(Inbox.txt_Compose_SelectUser,"Click on User");
		inputText(Inbox.input_Compose_Subject, subject,"Enter subject");
		switchToFrame(Inbox.frame_Compose_Message);
		
		inputText(Inbox.input_Compose_Message, message,"Enter Message");
		switchTodefaultContent();
		
		//Continue
//		Actions builder = new Actions(getWebDriver());
//		builder.keyDown( Keys.CONTROL ).sendKeys( "a" ).keyUp( Keys.CONTROL ).build().perform();
//		builder.keyDown( Keys.CONTROL ).sendKeys( "c" ).keyUp( Keys.CONTROL ).build().perform();
		
		pause(3000);
		
		ifAlertDismiss();
		
		click(Inbox.btn_Send,"Click on Send button");
		isWebElementVisible(Inbox.txt_Compose_SuccessMsg,"Verify 'Message has been sent.' message");//temp for maple work out
		return true;
	}

	public boolean homepageRewording(String logStep)
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.postFilter));
	
		if(isWebElementPresent(HomePage.postFilter))
		{
			logStepPass("Group Filter reworded to 'POST TYPE FILTER' ");
		}
		else
		{
			logStepFail("Group Filter reworded to 'POST TYPE FILTER' ");
		}
		
		pause(3000);
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.createPostField));
	
		if(isWebElementPresent(HomePage.createPostField))
		{
			logStepPass("Create post field re-worded to "+getWebDriver().findElement(HomePage.createPostField).getAttribute("placeholder"));
		}
		else
		{
			logStepFail("Create post field re-worded to "+getWebDriver().findElement(HomePage.createPostField).getAttribute("placeholder"));
		}
		
		pause(3000);
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.groupFilterMessage));
	
		if(isWebElementPresent(HomePage.groupFilterMessage))
		{
			logStepPass("Group filter message says -- "+getWebDriver().findElement(HomePage.groupFilterMessage).getText());
		}
		
		pause(3000);
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.clearFilterMessage));
	
		if(isWebElementPresent(HomePage.clearFilterMessage))
		{
			logStepPass("Clear filter message says -- "+getWebDriver().findElement(HomePage.clearFilterMessage).getText());
		}
		
		return true;
	}
	
	public boolean inboxLeftNav()
	{
		click(HomePage.inboxNav, "Navigate to Inbox");
		
		List<WebElement> inboxLeftNavList = new ArrayList<WebElement>();
		inboxLeftNavList = getWebDriver().findElements(HomePage.inboxLeftNavList);
		int count = 1;
		logStepPass("Inbox Left Navigation List Arrangment: ");
		for(WebElement ele: inboxLeftNavList)
		{	
			JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", ele);
			logStepPass(count +": "+ele.getText());
			count++;
		}
		return true;
	}
	
	public boolean resharePostToProfile(String subject)
	{
		By titleToPoster = By.xpath("//span[contains(text(),subject)]/parent::div/a/div/img");
		click(titleToPoster,"Click on teacher's photo");
		
		if(isWebElementPresent(HomePage.leftNavProfile))
		{
			logStepPass("Landed on teacher's Profile page");
		}
		
		if(isWebElementPresent(HomePage.posterProfileName))
		{
			logStepPass("Poster's name is "+getWebDriver().findElement(HomePage.posterProfileName).getText());
		}
		
		return true;
	}
	
	public boolean grpAddOthers(String searchkey)
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
		click(HomePage.myNtw,"Navigate to My Network page");
		click(HomePage.navToGrp, "Navigate to group");
		if(isWebElementPresent(HomePage.grpCode))
		{
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.grpCode));
			logStepPass("Group code button present");
		}
		else
		{
			logStepFail("Group code button not present");
		}
		
		pause(3000);
		if(isWebElementPresent(HomePage.invitePar))
		{
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.invitePar));
			logStepPass("Invite Parent button present");
		}
		else
		{
			logStepFail("Invite Parent button not present");
		}
		
		pause(3000);
		if(isWebElementPresent(HomePage.addOthers))
		{
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.addOthers));
			logStepPass("Add others button present");
		}
		else
		{
			logStepFail("Add others button not present");
		}
		pause(3000);
		click(HomePage.addOthers,"Click on Add Others");
		pause(3000);
		inputText(HomePage.searchCon, searchkey, "Enter contatcs to search");
		pause(2000);
		click(HomePage.searchRes,"Click on search result");
		click(HomePage.addCon, "Click on Add contacts");
		
		
		return true;
	}

	public boolean profileToSettings()
	{
		pause(2000);
		click(HomePage.lnk_UserOptions,"Click on Options");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();  
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", getWebDriver().findElement(HomePage.opSettings));
		if(isWebElementPresent(HomePage.opSettings))
		{
			logStepPass("My Profile option changed to " +getWebDriver().findElement(HomePage.opSettings).getText());
		}
		else
		{
			logStepFail("My Profile option changed to " +getWebDriver().findElement(HomePage.opSettings).getText());
		}
		return true;
	}
	
	public boolean delNotif()
	{
		WebElement ele = getWebDriver().findElement(By.xpath("//div[@id='topnav-dashboard-icon']"));
		Actions action = new Actions(getWebDriver());
		action.moveToElement(ele).perform();
		click(HomePage.delNot, "Click on clear notifications");
		return true;
	}
	
	public boolean sentMessageToCCBCC(String recipient1,String ToCC, String ToBCC,String subject,String message)  {
		
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
		inputText(Inbox.input_Compose_UserTo, recipient1,"Enter recipient1");
		pause(6000);
		click(Inbox.txt_Compose_SelectUser,"Click on User");
		
		click(HomePage.ccAdd,"Click on CC Button");
		inputText(HomePage.ccInput,ToCC,"Enter CC recipient");
		pause(6000);
		WebElement textbox = getWebDriver().findElement(HomePage.ccInput);
		textbox.sendKeys(Keys.ENTER);
		pause(2000);
		click(HomePage.bccAdd,"Click on BCC Button");

		inputText(HomePage.bccInput,ToBCC,"Enter BCC recipient");
		pause(6000);
		WebElement textbox1 = getWebDriver().findElement(HomePage.bccInput);
		textbox1.sendKeys(Keys.ENTER);
		inputText(Inbox.input_Compose_Subject, subject,"Enter subject");
		switchToFrame(Inbox.frame_Compose_Message);
		
		inputText(Inbox.input_Compose_Message, message,"Enter Message");
		getWebDriver().switchTo().defaultContent();
		pause(3000);
		ifAlertDismiss();
		pause(3000);

		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].scrollIntoView();", getWebDriver().findElement(Inbox.btn_Send));
		click(Inbox.btn_Send,"Click on Send button");
		
		return true;
	}
	
	public boolean  reshareAsParents(String  subject,String comment,String groupName,String message)
	{
		By postsharebefore=By.xpath("(//span[@class='post_title' and text()='"+subject+"']/following::span[text()='Share'])[1]");//span[@class='post_title' and text()='rkxzecmrhdSubject test automation']/following::span[text()='Share']
		isWebElementPresent(postsharebefore, "Verify post reshare");
		click(postsharebefore,"click on share link");
		pause(8000);
		By typeMessage = By.xpath("//body");
//		getWebDriver().switchTo().frame(0);
		logStepPass("No option available to enter message while re-sharing post");
		pause(3000);
		isWebElementVisible(Conversations.selectDropDown);
		
		click(Conversations.selectDropDown,"Click on Option");
		
		String isGroupOpen="//a[@class='group_listings' and text()='"+groupName+"']/parent::span/parent::li[@class='group_lists child_list selected']";
		if(!isWebElementPresent(By.xpath(isGroupOpen), 2)) {
		//a[@class='group_listings' and text()='First class785']/parent::span/parent::li[@class='group_lists child_list_photo selected']
		String group="//a[@class='group_listings' and text()='"+groupName+"']";
		click(By.xpath(group),"Click on Group");
		}
		pause(1000);
		String groupParents="(//a[@class='group_listings' and text()='"+groupName+"']/following::span[contains(text(),'Parents')])[1]";
		click(By.xpath(groupParents),"Click on Parents");
		pause(2000);
		ifAlertDismiss();
		click(Conversations.btn_Submit,"Click Post Submit button");
		pause(5000);
		By PostXpath=By.xpath("//span[text()='"+subject+"']");
		
		isWebElementVisible(PostXpath);
		return true;
	}
	
	public boolean createGroup(String groupName)
	{
		if(isWebElementVisible(HomePage.addGrpBtn,"Verify if Add group button is visible"))
		{
			logStepPass("Add group button present");
		}
		
		pause(3000);
		click(HomePage.addGrpBtn,"Click on Add Group button");
		pause(3000);
		click(HomePage.groupSelect, "Select group");
		pause(3000);
		click(HomePage.radioClass,"Select class");
		pause(3000);
		click(HomePage.grpSubmit, "Click on Next");
		pause(3000);
		inputText(HomePage.grpName, groupName, "Enter Group Name");
		click(HomePage.selectSchool, "Select school");
		click(HomePage.grpDone, "Click on Done");
		pause(5000);
		click(HomePage.grpSkip, "Click on Skip");
		
		if(isWebElementVisible(By.xpath("//h3[contains(text(),groupName)]"), "Verify is group is created"))
		{
			logStepPass("Group created");
		}
		
		pause(10000);
		click(HomePage.delClass, "Click on edit Class");
		pause(3000);
		click(HomePage.delButton, "Click on Delete Class");
		pause(2000);
		getWebDriver().switchTo().alert().accept();
		pause(3000);
		return true;
	}
	
	public boolean showFilter()
	{
	isWebElementVisible(HomePage.postfilter,"verify post filter tab present");
	if(isWebElementVisible(HomePage.postfilter,"show post filter"));
	{
	logStepPass("show post type filtert");
	}


	return true;
	}
	
	public boolean checkRec()
	{
		
		click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
		pause(5000);
		By findSub = By.xpath("//div[@class='messages-subject']");
		click(findSub,"Click on the sent message");
		
		List <WebElement> recs = getWebDriver().findElements(By.xpath("//div/div[@class='message-read']"));
		int count = 0;
		
		for(WebElement i: recs) {
			
			logStep("Recipients: "+i.getText());
			count++;
		}
		return true;
		
	}


//akshata

public boolean multipleRecipient(String subject,String message)
{
	JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
	click(HomePage.tab_TopNavigation_Inbox,"Click on Inbox");
	click(Inbox.btn_ComposeAMessage,"Click on Compose Message button");
	click(HomePage.multipleRecipientBtn,"Click on Select Multiple receipient");
	//isWebElementVisible(HomePage.classBtn,"verify Classes tab present");
	click(HomePage.groupName,"Click on group Name");
	pause(3000);
	List<WebElement> s = getWebDriver().findElements(HomePage.selectAll);
	for(WebElement i:s)
		click(i,"selecting");

	click(HomePage.donebtn,"Click on done button");

	click(HomePage.ccAdd,"Click on CC Button");

	click(HomePage.multipleRecipientCcBtn,"Click on Select Multiple receipient");
	isWebElementVisible(HomePage.classBtn,"verify Classes tab present");
	click(HomePage.groupName,"Click on group Name");
	List<WebElement> s1 = getWebDriver().findElements(HomePage.selectAll);
	for(WebElement i:s1)
		click(i,"selecting");
	click(HomePage.donebtn,"Click on done button");
	pause(2000);

	js.executeScript("scroll(250, 0)");
	click(HomePage.bccAdd,"Click on BCC Button");
	click(HomePage.multipleRecipientBccBtn,"Click on Select Multiple receipient");
	isWebElementVisible(HomePage.classBtn,"verify Classes tab present");
	click(HomePage.groupName,"Click on group Name");
	List<WebElement> s2 = getWebDriver().findElements(HomePage.selectAll);
	for(WebElement i:s2)
		click(i,"selecting");
	js.executeScript("scroll(0, 250)");
	click(HomePage.donebtn,"Click on done button");
	
	inputText(Inbox.input_Compose_Subject, subject,"Enter subject");
	switchToFrame(Inbox.frame_Compose_Message);
	
	inputText(Inbox.input_Compose_Message, message,"Enter Message");
	getWebDriver().switchTo().defaultContent();
	pause(3000);
	ifAlertDismiss();
	pause(3000);


	js.executeScript("arguments[0].scrollIntoView();", getWebDriver().findElement(Inbox.btn_Send));
	click(Inbox.btn_Send,"Click on Send button");
	//inputText(Inbox.input_Compose_UserTo, recipient1,"Enter recipient1");
	//click(Inbox.txt_Compose_SelectUser,"Click on User");
	
	return true;
	
	}
public boolean MynetworkSearch (String Name){
	
	click(HomePage.img_mynetwork,"Click on my network");
	pause(2000);
	isWebElementVisible(HomePage.mynetworkSearch,"Verify the Search box present");
	inputText(HomePage.MYNETWORK_SEARCH_INPUT_BOX,"parent","Enter search name");
	click(HomePage.mynetworkSearchTearmName,"select name");
	isWebElementVisible(HomePage.ProfilePage,"Verify the Profile page");
//	click(HomePage.SendWelcomeEmailButton,"click on send welcome email");
//	driver.switchTo().alert().accept();
	return true;
}
}


