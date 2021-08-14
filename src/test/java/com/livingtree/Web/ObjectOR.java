package com.livingtree.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controllers.BaseMethod;

public class ObjectOR extends BaseMethod {
		static class LoginPage {
		public static By img_edliobanner=By.xpath("//a[contains(@class,'navbar-brand  center')]/img");
		public static By lnk_SignUp = By.xpath("//a[text()='Sign Up']");
		// public static By lnk_Login =By.xpath("//a[text()='Login']");
		public static By lnk_Login = By.id("loginbox");

		public static By lnk_Support = By.xpath("//a[text()='Support']");
		public static By lnk_Contact = By.xpath("//a[text()='Contact']");
		public static By lnk_News = By.xpath("//a[text()='News']");
		public static By input_UserName = By.id("txtEmail");
		public static By input_Password = By.id("txtPassword");
		public static By btn_SignIn = By.name("login");
		// public static final By frmHomePage
		// =By.xpath("//div[@id='b-menu-2']//a[text()='LOG OUT']");
		public static By chk_RememberMe = By.id("cbPersistent");
		//public static By btn_ForgotPassword = By.xpath("//a[@class='forgot_link btn btn-default']");
		public static By btn_LoginWithClever = By
				.xpath("//a[@title='Click here to use your Clever credentials to login to LivingTree.']/img");
		public static By btn_SignUp = By.xpath("//a[contains(text(),'SIGN UP')]");
		public static By btn_singup_engaged=By.xpath("//a[@id='lp-pom-button-206']//strong[contains(text(),'Sign Up')]");
		// div[@class='intercom-launcher-close-icon']
		public static By forgtpaswrd_Link = By.xpath("//*[@class='forgot_link btn btn-default']");
		public static By recovry_Mail_Address = By.xpath("//*[@id='txtEmail']");
		public static By request_Btn = By.xpath("//*[@type='submit']");
		
		//public static By chk_RememberMe = By.id("cbPersistent");
		
		

	}
	static class Support{
		public static By txt_search_Groups__by_name=By.xpath("(//input[@name='name'])[2]");
		public static By btn_search=By.xpath("(//input[@value='Search'])[2]");
		public static By btn_search_email=By.xpath("(//input[@value='Search'])[1]");
		public static By img_pencil_edit=By.xpath("//a[contains(@class,'manage_school_show')]//img");
		public static By txt_search_Members_by_email=By.xpath("(//input[@name='name'])[1]");
		public static By lbl_user_guid=By.xpath("//table/tbody/tr[2]/td[1]");
		public static By chk_test_mode=By.id("manage_school_test_mode");
		public static By bth_save_manage_school=By.id("manage_school_save");
	}	

	static class HomePage {

		public static By img_LivingTreeLogonew
		 =By.xpath("//a[@class='navbar-brand ']/img");
		public static By img_EdlioEngagednew=By.xpath("//a[@class='navbar-brand no-padding-left-right']");
		//ownership transfer
		public static By btn_Hover_ownertrn= By.xpath("//span[@id='hoverButton']");
		public static By lnk_ownertrn= By.xpath("//div[@id='hoverMenuItems']/div[contains(text(),'Transfer Ownership')]");
		public static By ownershiptransfrmail = By.xpath("//input[@id='user_dropdown_gt']");
		public static By btn_sendownership = By.xpath("//button[@id='btn_transfer']");
		
		public static By tab_LeftNavigation_ALL = By.id("mylivingtree-aggregation");
		public static By tab_LeftNavigation_Me = By.id("Me");
		public static By tab_LeftNavigation_Family = By.id("Family");
		public static By tab_LeftNavigation_Groups = By.id("Groups");
		public static By tab_LeftNavigation_Organizations = By.id("Organizations");

		/*public static By tab_TopNavigation_Conversations = By.xpath("//a[text()='Conversations']");
		public static By tab_TopNavigation_Calendar = By.xpath("//a[text()='Calendar']");
		public static By tab_TopNavigation_Give = By.xpath("//a[text()='Give']");
		public static By tab_TopNavigation_Inbox = By.xpath("//a[text()='Inbox']");
		public static By tab_TopNavigation_MyNetwork = By.xpath("//a[text()='My Network']");
		public static By tab_TopNavigation_Directory = By.xpath("//a[text()='Directory']");
		*/
		public static By tab_TopNavigation_Conversations = By.id("topnav-dashboard-text");
		public static By tab_TopNavigation_Calendar = By.id("topnav-calendar-text");
		public static By tab_TopNavigation_Give = By.id("topnav-fundraising-text");
		public static By give_icon_img=By.xpath("//div[@class='livingtree-give-div']");
		public static By tab_TopNavigation_Inbox = By.id("topnav-messages-text");
		public static By tab_TopNavigation_MyNetwork = By.id("topnav-livingtree-text");
		public static By tab_TopNavigation_Directory = By.id("topnav-contacts-text");
		public static By btn_Create_a_New_Layer =By.xpath("//a[@class='elgg-button elgg-button-dropdown create-group_tip btn btn-sm btn_gray_cmn']");
		public static By txt_new_layer_name=By.id("new_member_type");
		public static By btn_save_new_layer=By.id("add_membertype_submit");
		public static By lnk_EditProfile = By.xpath("//a[text()='Edit Profile & Preferences ']");
		public static By lnk_EditGroups = By.xpath("//a[text()='Edit Groups & Connections']");
		public static By lnk_JoinGroup = By.xpath("//a[text()='Have a Group Code? Join Now']");
		public static By txt_UserName = By.id("//ul[@class='elgg-menu elgg-child-menu ']/li[1]/div/a");
		
		
		public static By img_UserProfile = By.xpath("//img[@title='Profile']");
		
		public static By lnk_UserOptions = By.xpath("//a[text()='options']");
		public static By lnk_User_MyProfile = By.xpath("//a[text()='My Profile']");
		public static By lnk_User_Support = By.xpath("//a[text()='Support']");
		public static By lnk_User_Help = By.xpath("//a[text()='Help']");
		public static By lnk_User_Logout = By.xpath("//li[@class='elgg-menu-item-logout']/a[text()='Logout']");
		public static By lnk_User_Settings = By.xpath("//li[@class='elgg-menu-item-settings']/a[text()='Settings']");
		public static By lnk_Manage_Data= By.xpath("//a[text()='Manage Data']");
		public static By lnk_Auto_Provision_Status= By.xpath("//a[contains(text(),'Auto Provision Status')]");
		public static By btn_close_autoprovision=By.xpath("//button[contains(text(),'Close')]");

		public static By btn_DayPrevious = By.id("day2_cal_prev");
		public static By btn_DayNext = By.id("day2_cal_next");
		public static By txt_Event_Date1 = By.xpath("(//div[@class='event_date'])[1]");
		public static By txt_Event_Date2 = By.xpath("(//div[@class='event_date'])[2]");
		public static By txt_Today = By.xpath("//div[@class='event_day']");
		public static By img_AppStore = By.id("itunes_app_link_div");
		public static By img_GooglePlay = By.id("google_app_link_div");
		public static By img_Facebook = By.xpath("//div[@class='social-facebook']");
		public static By img_Twitter = By.xpath("//div[@class='social-twitter']");
		public static By img_Linkedin = By.xpath("//div[@class='social-linkedin']");
		public static By img_Pinterest = By.xpath("//div[@class='social-pinterest']");
		public static By img_mynetwork= By.xpath("//div[@id='topnav-livingtree-icon']");
		public static By lnk_group_maple_auto= By.xpath("//a[contains(text(),'Functional Academics')]");
		public static By lnk_group_maple= By.xpath("//a[contains(text(),'PK Homeroom')]");
		public static By lnk_invite_parent=By.xpath("//span[contains(text(),'Invite Parent')]");
		public static By lnk_invite_parent_via_group_code= By.xpath("//a[contains(text(),'Invite Parent(s) using group code')]");
		public static By btn_generate_group_code=By.xpath("//button[contains(text(),'Generate Code')]");
		public static By lnk_Add_Students=By.xpath("//a[contains(text(),'Add Students')]");
		public static By lnk_Add_Parents=By.xpath("//a[contains(text(),'Add Parents')]");
		public static By lnk_invitee_mail=By.xpath("//a[contains(text(),'Invite via e-mail')]");
		public static By inviteBox = By.xpath(
				"//*[@id=\"Family\"]//*[contains(text(),\"Blazers\")]/../following-sibling::*[contains(@class,\"invite\")]//img");
		public static By addstudentBox = By.xpath(
				"//input[contains(@placeholder,'Click here to search...')]");
		public static By InviteeEmail = By.xpath("//textarea[@class='email_input_field']");//div[@class='add-children-title']
		public static By add_student_title = By.xpath("//div[@class='add-children-title']");
		//div[@class='add-children-done']/a[contains(text(),'Add')]
		public static By btn_add_student_done_from_add_student = By.xpath("//div[@class='add-children-done']/a[contains(text(),'Add')]");
		public static By btn_accept_invitation=By.xpath("//input[@id='Accept']");
		public static By sendInvitee = By.xpath("//*[@class='doneit btn btn-primary pull-right']");
		public static By lnk_LeftJoinGroup = By.xpath("//a[text()='Click here to join']");
		public static By input_GroupName = By.id("ci_group_invitecode_text");
		public static By btn_Group_Join = By.xpath("//button[text()='Join']");
		public static By btn_Continu_join = By.xpath("//button[contains(text(),'CONTINUE')]");
		public static By btn_accept_join=By.xpath("//input[@id='Accept']");
		public static By btn_Group_Skip = By.xpath("//button[text()='Join']//following-sibling::button[text()='Skip']");
		public static By img_1stGroupInvite = By.xpath("(//img[@class='membertype-big-invite left_nav_agg_title'])[1]");
		public static By txt_SendInvite = By.xpath("//span[text()='Send Invite']");
		public static By lnk_InviteParent_GroupCode = By.xpath("//a[text()='Invite Parent(s) using group code']");
		public static By lnk_InviteParent_EMail = By.xpath("//a[text()='Invite Parent(s) via e-mail']");
		public static By lnk_InviteStudent_GroupCode = By.xpath("//a[text()='Invite Student(s) using group code']");
		public static By lnk_InviteStudent_EMail = By.xpath("//a[text()='Invite Student(s) via e-mail']");
		public static By img_Invite_Close = By.xpath("//a[@class='invite-close']");
		public static By input_Invite_SearchContacts = By.id("search_contacts");
		public static By btn_Invite_ClearAll = By.xpath("//a[text()='Clear All' and @title='Clear All']");
		public static By input_Invite_ImportFile = By.id("invite_import_file");
		public static By input_Invite_Email = By.xpath("//textarea[@class='email_input_field']");
		public static By input_Invite_Message = By.id("invite-message");
		public static By btn_Invite_Send  = By.xpath("//button[text()='Send Invite']");
		public static By txt_Invite_1stUser  = By.xpath("//ul[@id='contacts_search_list']/li/a[1]");
		public static By txt_Invite_SuccessMsg  = By.xpath("//p[text()='Invitation(s) sent...']");
		public static By lnk_livingtree= By.xpath("//a[@class='navbar-brand']//img");
		//provision
		public static By lnk_ProvisionSchool = By.xpath("//a[text()='Provision School']");
		public static By picker_ImportCSV = By.id("importcsv");
		public static By rbn_CompleteProvisioning = By.id("action_fullprovision");
		public static By btn_ProvisioningUpload = By.id("upload");
		//acept alert
		public static By btn_ProvisioningCheckStatus = By.id("check_status");
		public static By txt_FileUploadSuccessMsg = By.xpath("//p[text()='File Imported Successfully.Please check status for further progress']");
		public static By txt_Provision_Status = By.id("provision_status");
		public static By txt_Provision_Progress = By.id("provision_progress");
		
		public static By lnk_Provision_Proceed = By.id("proceed");
		public static By lnk_Provision_Delete = By.id("delete");
		public static By lnk_Provision_Process_Activate = By.id("process_activate");
		public static By txt_SubmittedProvisionNotificationMsg = By.xpath("//p[text()='Submitted to background job for processing']");
		
		
		//Monica
				
		public static By grp_child_fname = By.id("invitee_new_group_name_0"); 
		public static By grp_child_lname = By.id("invitee_new_lastname_0");
		public static By grp_checkbox = By.xpath("//input[@class='childname_checkbox']");
		public static By close_survery_button =By.xpath("By.xpath(\"//div[@aria-label='Close survey']\")");
		
		public static By posterName = By.xpath("//span[@class='post_breadcrumb']/a");
		public static By inboxHeader = By.xpath("//h2[@class='elgg-heading-main']");
		
		//LM913
		public static By inboxIcon = By.id("topnav-messages-icon");
		public static By searchTabInbox = By.id("search-conv");
		public static By searchButton = By.id("search");
		public static By searchedMessages = By.xpath("//div[@class='messages-owner']");
		public static By searchTitle = By.xpath("//div[@class='messages-subject']");
		
		//LQ5173
		public static By wantToShare = By.xpath("//div[@title='I want to share this with']");
		public static By grpSelect = By.xpath("//ul[@class='elgg-tag-groups-list']/li[2]");
		public static By grpStudents = By.xpath("(//ul[@id='nav']/child::li[2]//span)[4]");
		public static By submitPost = By.id("tidypics-upload-button");
		public static By grpSelectForParents = By.xpath("//ul[@class='elgg-tag-groups-list']/li[2]");
		
		//LQ5217
		public static By filterSaved = By.xpath("//label[@for='filter_favorite']");
		
		//LQ5220
		public static By postFilter = By.xpath("//div[contains(text(),'POST TYPE FILTER')]");
		public static By createPostField = By.xpath("//input[@class='input-textbox search-box-input-box']");
		public static By groupFilterMessage = By.xpath("//ul[@id='navigation']/li[2]/div/p");
		public static By clearFilterMessage = By.xpath("//ul[@id='navigation']/li[2]/div/a");
		
		//LQ5218
		public static By inboxNav = By.xpath("//ul/li[@class='elgg-menu-item-messages']/a");
		public static By inboxLeftNavList = By.xpath("//ul[@class='elgg-menu elgg-menu-page elgg-menu-page-default ddddd ']/li");
		
		//LQ5230
		public static By leftNavProfile = By.xpath("//li[@class='elgg-menu-item-profile elgg-state-selected']/a");
		public static By posterProfileName = By.xpath("//h4/span[@id='fc_user_name']");
		
		//LQ5248
		public static By myNtw = By.xpath("//div[@id='topnav-livingtree-icon']");
		public static By navToGrp = By.xpath("//ul/li[@id='Groups']/ul/li[1]");
		public static By grpCode = By.xpath("//div[@class='membertype-header-right-block']/a[@class='elgg-button mlt_tip btn btn-sm btn_gray_cmn']");
		public static By invitePar = By.xpath("//div[@class='membertype-header-right-block']/a[@class='mlt-invite elgg-button btn btn-sm btn_gray_cmn elgg-button-others left-nav-invite']");
		public static By addOthers = By.xpath("//div[@class='membertype-header-right-block']/a[5]");
		public static By searchCon = By.xpath("//input[@id='search_contacts_auv']");
		public static By searchRes = By.xpath("//ul[@id='contacts_search_list_auv']/li[1]");
		public static By addCon = By.id("add-users-to-layer-livingtree-popup-submit");
		
		//LQ5243
		public static By opSettings = By.xpath("//a[contains(text(),'Settings')]");
		
		//LQ5246
		public static By delNot = By.xpath("//a[contains(text(),'Clear All')]");
		
		//LQ5242
		public static By ccAdd = By.xpath("//span[@class='ccbutton']");
		public static By bccAdd = By.xpath("//span[contains(text(),'Bcc')]");
		public static By ccInput = By.xpath("//input[@id='composeUserPicker2']");
		public static By bccInput = By.xpath("//input[@id='composeUserPicker1']");
		public static By listEle = By.xpath("//ul[@class='typeahead dropdown-menu']/li[1]");
		
		//LQ5257
		public static By addGrpBtn = By.xpath("//a[contains(text(),'+')]");
		public static By groupSelect = By.xpath("//div[@id='role-group']");
		public static By radioClass = By.xpath("//input[@id='group-class']");
		public static By grpSubmit = By.xpath("//button[@id='group-submit-first']");
		public static By grpName = By.xpath("//input[@id='profileName']");
		public static By selectSchool = By.xpath("//div/div[@class='checkbox'][1]/label/input");
		public static By grpDone = By.xpath("//button[contains(text(),'Done')]");
		public static By grpSkip = By.xpath("//button[contains(text(),'Skip')]");
		public static By delClass = By.xpath("//span[@class='glyphicon glyphicon-pencil']");
		public static By delButton = By.xpath("//a[contains(text(),'Delete Class')]");
		public static By postfilter = By.xpath("//div[contains(text(),'POST TYPE FILTER')]");
		
		//LQ5300
				
				
		public static By multipleRecipientBtn = By.xpath("//div[@class='tolist tolist_composeUserPicker']/button");
		public static By multipleRecipientCcBtn = By.xpath("//div[@class='tolistcc tolist_composeUserPicker2']/button");
		public static By multipleRecipientBccBtn = By.xpath("//div[@class='tolistbcc tolist_composeUserPicker1']/button");
		public static By classBtn = By.xpath("//h4[contains(text(),'classes')]");
		public static By groupBtn = By.xpath("//a[contains(text(),'gpschool')]");
		public static By schoolBtn = By.xpath("//h4[contains(text(),'schools')]");
		public static By districtBtn = By.xpath("//h4[contains(text(),'districts')]");
		public static By groupName = By.xpath("//div[@class='row classes']/div[2]/a");
		//public static By parentField = By.xpath("//body/div[3]/div[7]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/div[1]/div[1]/div[6]/div[1]/div[2]/div[2]/div[1]/div[1]/h2[1]");
		//public static By checkBox = By.xpath("//body/div[3]/div[7]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/div[1]/div[1]/div[6]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");
		public static By toName = By.xpath("//div[@class='tolist tolist_composeUserPicker']/button");
		public static By ccName = By.xpath("//div[@class='tolistcc tolist_composeUserPicker2']/button");
		public static By bccName = By.xpath("//div[@class='tolistbcc tolist_composeUserPicker1']/button");
		public static By selectAll = By.xpath("//div/input[@class='select_all_contact']");
		public static By select= By.xpath("//div[1]/input[@type='checkbox']");
		public static By resetbtn= By.xpath("//button[contains(text(),'Reset')]");
		public static By donebtn= By.xpath("//button[@id='selection-save']");
		
		
		//5307
		
		public static By mynetworkSearch= By.xpath("//input[@id='srch-term']");
		public static By mynetworkSearchDropDown= By.xpath("//body/ul[2]]");
		public static By MYNETWORK_SEARCH_INPUT_BOX= By.xpath("//input[@id='srch-term']");
		public static By mynetworkSearchTearmName= By.xpath("//body/ul[2]");
		public static By ProfilePage= By.xpath("//div[@id='page-content-wrapper']");
		public static By SendWelcomeEmailButton= By.xpath("//a[@id='send_welcome_email_singl_euser']");
		public static By mynetworkSearchTeam1= By.xpath("//input[@id='srch-term']");
		public static By mynetworkSearchTearmName1= By.xpath("//body/ul[2]/li[1]/a[1]");
		public static By ProfilePage1= By.xpath("//div[@id='page-content-wrapper']");
		public static By SendWelcomeEmailButton1= By.xpath("//a[@id='send_welcome_email_singl_euser']");
		
	}
	
	
	static class AddToLayer
	{

		public static By invite_other_element=By.xpath("(//div[contains(@class,'elgg-module-member-types')]//a[@class='mlt-invite elgg-button btn btn-sm btn_gray_cmn mlt_tip btn btn-sm btn_gray_cmn' and text()=' Add Others'])[2]");
		public static By Invite_Classroom_Teacher=By.xpath("//div[contains(@class,'build-allmembergroups')]//div[4]//div[1]//div[2]//a[2]");
		public static By search_box=By.xpath("//input[@id='search_contacts_auv']");
		public static By table_value1=By.xpath("//table[@id='layers_table']/tbody/tr[1]/td[5]");
		public static By table_value2=By.xpath("//table[@id='layers_table']/tbody/tr[2]/td[5]");
		public static By upload_list=By.xpath("//a[contains(text(),'Upload List')]");
		public static By chk_send_mail=By.id("welcome_email");
		public static By chek_wellcome_sms=By.id("welcome_sms");
		public static By btn_add_layer_cancel=By.xpath("//button[@class='mlt-invite elgg-button btn btn-sm btn_gray_cmn mlt_tip btn btn-sm btn_gray_cmn']");
		public static By btn_add_layer_add_contact=By.id("add-users-to-layer-livingtree-popup-submit");
		public static By txt_add_user_SuccessMsg= By.xpath("//p[text()='Users were added successfully']");
		
	
		
		
		
	}
	
	static class Conversations {
		
		
		public static By shareMessage = By.xpath("//*[@placeholder='Create a post or share a photo, video or file']");
		public static By img_no_comments = By.xpath("(//span[@class='no_comment_icon'])[1]");
		public static By lnk_clear_filter=By.xpath("//a[contains(text(),'Clear Filter - Show All Content')]");
		//Under share
		public static By lnk_All = By.xpath("//label[@for='filter_all']");
		public static By lnk_Saved = By.xpath("//label[@for='filter_favorite']");
		public static By lnk_Files = By.xpath("//label[@for='filter_file']");
		public static By lnk_PhotosVideos = By.xpath("//label[@for='filter_photos']");
		public static By lnk_Events = By.xpath("//label[@for='filter_event']");
		public static By lnk_Users = By.xpath("//label[@for='filter_users']");
		public static By input_FilterUser = By.id("txtFilterUser");
	
		public static By txt_FirstPostUserName = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@title='Teacher785 test785']");
		public static By btn_upload_attendance_file=By.id("attendance_file");
		public static By btn_upload_button=By.xpath("//input[@class='elgg-button btn btn-sm btn_green_cmn']");
		public static By sel_list_school_for_attendance=By.id("list_schools");
		//attendace alert icon under attendace alert section
		public static By lnk_send_attendance_alerts = By.xpath("//a[contains(text(),'Send Attendance Alerts')]");
		public static By btn_next_attendace=By.xpath("//input[@value='Next']");
		public static By lbl_attendance_alert_final=By.xpath("//h2[contains(text(),'Attendance Alerts')]");
		public static By btn_cancel_Attendance_Alerts=By.xpath("//input[@value='Cancel']");
		public static By btn_previous_Attendance_Alerts=By.xpath("//input[@value='Previous']");
		public static By btn_Approve_and_Send_Attendance_Alerts=By.xpath("//input[@value='Approve and Send']");
		public static By lbl_Attendance_Alerts_upload_sucessful=By.xpath("//strong[contains(text(),'Preview - Alerts will be sent for ')]");//Approve and Send
		public static By btn_Continue_final_submission_page=By.xpath("//input[@value='Continue']");
		public static By lbl_first_photo=By.xpath("(//div[@class='img-wrap img-responsive'])[1]/a");
		public static By input_comment_photo_gallery=By.id("slideshow_comment_txt");
		public static By btn_post_comment_photo_gallery=By.id("slideshow_comment_btn");
		public static By lbl_download_option=By.id("slideshow_download_actual");//div[@id='download_section']
		public static By lbl_close_in_photo_galary_screen=By.xpath("//a[contains(text(),'Close X')]");
		//search icon under conversation page
		public static By txt_search_item=By.id("search-conv");
		public static By btn_search_icon= By.xpath("//i[@class='glyphicon glyphicon-search']");
		
		public static By add_group__from_plus_icon=By.xpath("//li[@id='Groups']/span/span/a[contains(text(),'+')]");

		public static By postSubject = By.xpath("//*[@id='post_subject']");
		public static By alignLeft = By.xpath("//*[@class='cke_button_icon cke_button__justifyleft_icon']");
		public static By typeMessage = By.xpath("//*[contains(text(), 'Type Message')]");
		public static By typeMessage_phone_alert=By.id("autodial_text");//textarea[@id='autodial_text']
		public static By typeMessage_message_alert=By.id("thewire_textarea");//textarea[@id='thewire_textarea']
		public static By placeholderafteraddPollOptions=By.xpath("//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders placeholder']");
		// public static By typeMessage = By.cssSelector("cke_editable");
		public static By selectDropDown = By.xpath("//div[@class='show_text_div_dash']");
		public static By selcetDrpDownGroupn = By
				.xpath("//*[@class='post_options_enclosed']//*[@title='Chris Mozier']/span");
		public static By selectChildGroup = By.xpath("//ul[@id='nav']/child::li[3]");
		public static By selectSpeficChildFromGroup=By.xpath("(//ul[@id='nav']/child::li[3]//span)[4]");
		public static By selectChildGroupauto = By.xpath("//ul[@id='nav']/child::li[2]");
		public static By selectGroupDropdownForelementtaryPrincipal=By.xpath("//a[@title='Aberdeen Elementary School']//span");
		public static By selectJustME=By.xpath("//a[contains(text(),'Just Me')]");
		public static By lbl_error_message_inapproiate_content=By.id("error_message");
		public static By select_ChildGroup2 = By.xpath("//ul[@id='nav']/child::li[4]");
		public static By selectSpeficChildFromGroup2=By.xpath("(//ul[@id='nav']/child::li[4]//span)[4]");
		public static By selectSpeficchildFromGroupAfterSelectingGroup=By.xpath("//ul[@id='nav']/child::li[5]//span[contains(text(),'Parents')]");
		public static By chk_DisableBox=By.xpath("//div[@class='comment_chk_box']//input");
		public static By txt_comntDisbleCheckBox = By.xpath("//div[@class='comment_chk_box']//span[@class='post_option_text']");
//		public static By clickPostButton = By
//				.xpath("//div[@id='post_buttons']/input[@id='campaign-submit-button']");
		public static By clickPostButton = By.xpath("//div[@id='post_buttons']//input[@value='Post']");//change according to Dom chinmoy made it19/11/1984
		public static By clickCreateCampaianButton= By.xpath("//input[@id='campaign-submit-button']");//change for create campain button in create campain test case
		public static By btn_Messages = By.id("btn_share-messages");
		//public static By btn_Photos = By.id("btn_share-photos");
		public static By btn_Photos = By.xpath("//span[text()='Add Photo']");
		public static By btn_UploadPhoto = By.id("tidypics_image");
		public static By btn_add_polls=By.xpath("//span[contains(text(),'Add Poll')]");
		public static By type_poll_ans1=By.xpath("//input[@id='poll_answer_1']");
		public static By type_poll_ans2=By.xpath("//input[@id='poll_answer_2']");
		public static By type_poll_ans3=By.xpath("//input[@id='poll_answer_3']");
		public static By type_poll_ans4=By.xpath("//input[@id='poll_answer_4']");
		public static By type_poll_ans5=By.xpath("//input[@id='poll_answer_5']");
		public static By type_poll_ans6=By.xpath("//input[@id='poll_answer_6']");
		public static By btn_ans_add_answer=By.xpath("//input[@id='add-poll-answer']");
		//public static By btn_add_poll_ans=By.xpath("//span[@id='ajax_poll_loader']/following-sibling::input[@value='Submit']");
		public static By btn_add_poll_ans=By.xpath("//input[@class='elgg-button poll-submit']");
		public static By lbl_error=By.xpath("//div[@id='error_message']/div");
		//public static By btn_Video = By.id("btn_share-video");
		public static By btn_Video = By.xpath("//span[text()='Add Video']");
		public static By btn_Phone_Alert = By.xpath("//span[contains(text(),'Add Phone Alert')]");
//		public static By btn_UploadVideo = By.id("video_file_input_button");
		public static By btn_UploadVideo=By.xpath("//li[@id='btn_share-video']/a/span[contains(text(),'Add Video')]");//change the xpath for the 
			//	+ "video_file_input_button");
			//	+ "video_file_name_s3");
		
		//public static By btn_Files = By.id("btn_share-files");
		public static By btn_Files = By.xpath("//span[text()='Add File']");
		public static By btn_UploadFile = By.id("file");
		public static By btn_Phone = By.id("btn_share-autodial");
		public static By btn_phn_alert=By.xpath("//span[text()='Add Phone Alert']");
		public static By btn_RemovePhoto = By.xpath("//span[text()='Remove Photo']");
		public static By btn_RemoveVideo = By.xpath("//span[text()='Remove Video']");
		public static By btn_RemoveFile = By.xpath("//span[text()='Remove File']");
		public static By btn_Add_Text_Message=By.xpath("//span[contains(text(),'Add Text Message')]");
		public static By txt_JustMe = By.xpath("//a[text()='Just Me']");
		
		
		public static By btn_AddPhoneMessage = By.xpath("//span[text()='Add Text Message']");
		public static By btn_AddPhoneAlert = By.xpath("//span[text()='Add Phone Alert']");
		public static By btn_RemovePhoneMessage = By.xpath("//span[text()='Remove Text Message']");
		public static By btn_RemovePhoneAlert = By.xpath("//span[text()='Remove Phone Alert']");
		
		public static By input_Post_Message = By.id("autodial_text");
		
		public static By input_PostMessage = By.xpath("//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']");
			//	+ "//body/p");
		
		
//	   public static By typeMessage = By.cssSelector("cke_editable");
	    public static By btn_Submit = By.id("post_buttons");
	    //Monica
	   public static By btn_update = By.id("file-upload-button");
	   public static By btn_update2 = By.id("thewire-submit-button");
	    //tidypics-upload-button
	    public static By btn_EditFirstPost = By.xpath("//div[@id='main-div-ajax-call']/ul/li[1]//a[@class='post-edit']");
	   // public static By btn_grouplist = By.xpath("//li[@id='group_list_24635665_no_filter']/span/a");
	    public static By btn_grouplist = By.xpath("//li[@id='group_list_24635666_no_filter']/span/a");
	    public static By lbl_FirstUpdatedPost = By.xpath("//div[@id='main-div-ajax-call']/ul/li[1]");
	    public static By txt_Group_AutoTest = By.xpath("//h3/a[text()='Auto Test']");
	    public static By picker_Group_AutoTest = By.xpath("//span/a[text()='Auto Test']");
		 
	    public static By input_Post_Search = By.id("search-conv");
	    public static By btn_Post_Search = By.id("search");
	    public static By btn_Post_Delete = By.xpath("(//a[@title='Delete this post'])[1]");
	    public static By btn_Post_Delete_OK = By.id("del_alert_ok");
		public static By btn_Post_Delete_Cancel = By.id("del_alert_cancel");
		public static By chk_SchedulePost = By.id("chkSchedulePost");
		public static By lbl_SchedulePost=By.xpath("//span[@class='post_option_text cls_sc_post']");
		public static By input_ScheduledPost_Date = By.id("scheduled_date");
		public static By input_SchedulePost_ReleaseTime = By.id("schedulePostReleaseTime");
		public static By txt_NoGroup = By.xpath("//li[text()=' None ']");
		public static By txt_Post_Mute = By.xpath("//span[text()='Mute']");
		public static By txt_Post_Title = By.xpath("//span[@class='post_title']");
		public static By lnk_Group_More = By.id("Groups-more-link");
		public static By lnk_Group_Less = By.id("Groups-less-link");
		public static By list_Group_All = By.xpath("//ul[@class='elgg-list elgg-list-entity this-selected']/li");
		public static By btn_Post = By.id("thewire-submit-button");
		public static By picker_GroupSelect = By.id("def_show_text");
		//public static By lnk_SeeOriginal = By.xpath("(//span[@class='post-translate-text'])[1]");
		public static By lnk_SeeOriginal = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//span[@class='post-translate-text']");
		public static By txt_school_ban_info=By.xpath("//span[@class='post_title']/span");
		public static By txt_ban_school_name=By.xpath("//span[@class='post_description']/p/b");
		public static By txt_Translated_First_Post_Title = By.xpath("(//ul[@class='elgg-list elgg-list-entity']/li[1]//span[@class='post_title'])[1]");
		public static By txt_Translated_First_Post_Description = By.xpath("(//ul[@class='elgg-list elgg-list-entity']/li[1]//span[@class='post_description']/p)[1]");
		public static By txt_Translated_First_event_post= By.xpath("(//ul[@class='elgg-list elgg-list-entity']/li[1]//a[@class='event_see_translation'])[1]");
		public static By btn_close_event_details_popup=By.id("close_event_details_popup");
		
		public static By txt_Original_First_Post_Title = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//span[starts-with(@id,'original_post_title')]");
		public static By txt_Original_First_Post_Description = By.xpath("//ul[@class='elgg-list elgg-list-entity']/li[1]//span[starts-with(@id,'original_post_description_')]/p");
		public static By lbl_orginal_text= By.xpath("(//i[@class='post-translate'])[1]/following-sibling::span");
		public static By btn_close_share=By.xpath("//button[@class='close close_btn'][contains(text(),'×')]");
		public static By txt_autodial_text_area=By.xpath("//textarea[@id='autodial_text']");
		public static By left_nav_parent_group=By.xpath("(//a[contains(text(),'Child 1')])[3]");
		public static By add_group_form_create_group_page=By.xpath("//div[@id='role-group']/h6[contains(text(),'Group')]");
		public static By btn_next=By.id("group-submit-first");
		public static By txt_input_group_name=By.id("profileName");
		public static By chk_organization_in_group_under=By.xpath("//label[contains(text(),'Denbigh Early Childhood Center - PK')]");
		public static By btn_done_group=By.xpath("//button[contains(text(),'Done')]");
		public static By btn_skip_add_people=By.xpath("//button[contains(text(),'Skip')]");
	}

	static class Common {
		public static By img_Intercom = By.xpath("//div[@class='intercom-launcher-close-icon']");
		public static By btn_Done = By.xpath("//button[text()='Done']");
		
		//*[@id="modalCreateGroupInvite"]/div/div/div[2]/div[1]/div[4]/button[2]
		
		public static By img_ClickHereToClose = By.xpath("//a[text()='Click Here to Close']");
		public static By btn_Close = By.xpath("//button[@class='close close_btn']");
		public static By btn_Cancel = By.xpath("//div/button[text()='Cancel']");
		
	}

	static class Calendar {
		public static By add_Event_creatBtn = By.xpath("//*[@class='btn btn-sm btn_green_cmn']");
		public static By add_Enter_Event = By.xpath("//input[@id='createEventTitle']");
		public static By add_Click_DropDown = By.xpath("//div[@class='show_text_div_dash post_drop_down_text_extra']");
		public static By add_AllDaysCheckBox = By.xpath("//*[@id='alldayevent']");
		public static By add_WhereLocation = By.xpath("//*[@id='event_location']");
		public static By add_EnterDecrption = By.xpath("//*[@id='event_description']");
		public static By add_Start_Calender = By.xpath("//*[@id='start_day_icon']");
		public static By add_Reminders=By.id("reminders_required");
		public static By add_RSVP = By.xpath("//*[@id='event_rsvp']");
		public static By add_Require_Sign_Up_for_Tasks=By.id("volunteers_required");
		public static By add_Enter_Event_Description = By.xpath("//textarea[@id='event_description']");
		public static By click_Save_Exit_Btn = By.xpath("//input[@id='event_detailed_save_new']");
		public static By add_Disble_Comment = By.xpath("//div[@id='comments_detailed_switch']/input[@type='checkbox']");
		public static By select_Evnt_ChildGroup = By.xpath(
				"(//tr[@id='eventPostTypesCtrl']//following-sibling::div[@id='post_settings']//child::div[@id='main_tag_photos']//child::li)[2]");
		public static By select_Evnt_ChildGroup_trasferwoenrship=By.xpath("//tr[@id='eventPostTypesCtrl']//a[@class='group_listings'][contains(text(),'schoolofindia')]");
		public static By select_Evnt_ChildGroupsub = By.xpath(
				"(//tr[@id='eventPostTypesCtrl']//following-sibling::div[@id='post_settings']//child::div[@id='main_tag_photos']//child::li[2]/span/a/following::span[contains(text(),'Parents')])[1]");
		public static By select_Evnt_ChildGroupsub_trasnferownership = By.xpath("//li[@id='group_list_25776619_no_filter']//div[@id='temp_post_options_25776619']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_38494492']");
		//public static By select_Evnt_ChildGroup = By.xpath(
				//"//*[@id=\"post_settings\"]/div[3]/div");
		public static By  img_start_date=By.id("start_day_icon");
		public static By img_end_date=By.id("end_day_icon");
		public static By Previous_month=By.xpath("//a[@title='Prev']");
		public static By Past_date=By.xpath("//a[@class='ui-state-default' ][contains(text(),'21')]");
		public static By lbl_week=By.xpath("//span[contains(text(),'Week')]");
		public static By event_owner_edit_option=By.id("event_owner_edit_options");
		
		
	}

	static class Campaign {
		public static By campaignClick = By.xpath("//*[@id='new-campaign']");
		public static By campaignTitle = By.xpath("//*[@id='campaign_title']");
		public static By campaignTargetAmt = By.xpath("//*[@id='target_amount']");
		public static By campaignDescrption = By.xpath("//*[contains(text(),'Describe how you will use the funds')]");
		public static By cmpgn_DropDown = By.xpath("//*[@class='show_text_div_dash']");
		public static By cmpgn_element=By.xpath("//ul[@id='nav']/child::li[2]");
		public static By cmpgn_target=By.xpath("//select[@id='target']");
		public static By cmpgn_target_option=By.xpath("//select[@id='target']/option[@value='22201443']");
		public static By img_date=By.xpath("//span[@class='icon_img']"); 
		//public static By cmpgn_DropDown = By.xpath("//div[@class='show_text_div_dash']");//change made by chinmoy
//		public static By select_Cmpgn_ChildGroup = By
//				.xpath("//ul[@id='nav']/child::li[3]/descendant::span[text()='Just Parents']	");
		public static By select_Cmpgn_ChildGroup = By
				.xpath("//ul[@id='nav']/child::li[2]/descendant::span[contains(text(),'Parents')]");//change made by chinmoy
		public static By select_Cmpgn_ChildGroup_trasferowner = By
				.xpath("//li[@id='group_list_25776619_no_filter']//div[@id='temp_post_options_25776619']//ul[@class='elgg-tag-groups-list']//li//span[@id='opt_text_38494492']");//change made by chinmoy
		public static By disable_Comment = By.xpath("//*[@class='no-comment-check']");
		

	}

	static class SignUpPage {
		public static By txt_Parent = By.id("role-parent");
		public static By txt_Student = By.id("role-student");
		public static By txt_Group = By.id("role-group");
		public static By txt_Organization = By.id("role-organization");
		// Student Profile Information

		public static By input_Student_ProfilePic = By.id("profile_pic");
		public static By input_Student_Email = By.xpath("//input[@placeholder='Email Address*']");
		public static By input_Student_Password = By.id("pwd1");
		public static By input_Student_ConfirmPassword = By.id("pwd2");
		public static By input_Student_FirstName = By.xpath("//input[@placeholder='First Name*']");
		public static By input_Student_LastName = By.xpath("//input[@placeholder='Last Name*']");
		public static By input_Student_DOB = By.name("birth_mdy");

		public static By select_Year = By.xpath("//select[@class='ui-datepicker-year']");
		public static By select_Month = By.xpath("//select[@class='ui-datepicker-month']");

		public static By input_Student_Zipcode = By.xpath("//input[@placeholder='Zipcode']");
		public static By select_Student_TimeZone = By.id("timezone_select_list");
		public static By input_Student_Address1 = By.name("address1");
		public static By input_Student_City = By.name("city");
		public static By select_Student_State = By.id("country_state");
		public static By select_Student_Country = By.id("country");
		public static By select_Student_CountryTimeZone = By.id("timezone_select_list");

		public static By select_Student_Gender = By.name("gender");

		public static By input_Student_Telephone = By.xpath("//input[@placeholder='Telephone']");
		public static By chk_Student_IAgreeToLivingTree = By.xpath("//input[@title='Response Required']");
		public static By chk_Student_IAgreeToLivingTreeUnChecked = By.xpath("//input[@class='required error']");
		public static By chk_Student_IAgreeToLivingTreeChecked = By.xpath("//input[@class='required valid']");

		public static By btn_Student_Next = By.id("student-submit-first");
		public static By btn_Next = By.name("next-step");

		public static By btn_Student_Back = By
				.xpath("//a[@class='btn btn-default back_arrow pull-left' and text()=' Back']");
		public static By lnk_Student_TermsOfService = By.xpath("//label/a[text()='terms of service']");
		public static By lnk_Student_PrivacyPolicy = By.xpath("//a[text()='privacy policy']");
		// Error Validations
		public static By txt_Student_EmailError = By.xpath("//label[text()='Please enter valid Email Address']");
		public static By txt_Student_PasswordError = By.xpath("//label[text()='Please enter the password']");
		public static By txt_Student_ConfirmPasswordError = By.xpath("//label[text()='Please confirm the password']");
		public static By txt_Student_FirstNameError = By.xpath("//label[text()='Please enter first Name']");
		public static By txt_Student_LastNameError = By.xpath("//label[text()='Please enter last Name']");
		public static By txt_Student_DOBError = By.xpath("//label[text()='Please select birthday']");
		public static By txt_Student_IAgreeToLivingTreeError = By.xpath("//input[@class='Response Required']");
		// Loading Profile
		public static By txt_Student_ProfileWait = By.id("signup_loader");
		public static By img_Student_ProfileLoader = By.id("loderImage");
		// Group Popup
		public static By btn_Group_Close = By.xpath("//button[@class='close close_btn']");
		// public static By txt_Have a group code? =By.id("createGroupInvite");
		public static By input_GroupName = By.id("ci_group_invitecode_text");
		public static By btn_Group_Join = By.xpath("//button[text()='Join']");
		//public static By btn_Group_Skip = By.xpath("//button[text()='Join']//following-sibling::button[text()='Skip']");
		public static By btn_Group_Skip = By.xpath("//*[@id=\"modalCreateGroupInvite\"]/div/div/div[2]/div[1]/div[4]/button[2]");
		// Build Your Family
		public static By input_FamilyName = By.id("family-name");
		public static By input_InviteEmail = By.id("invite-email");
		public static By input_InviteMessage = By.id("invite-message");
		public static By btn_Family_Next = By.id("parent-submit-third");

		// next-step
		// Add your child(ren)
		public static By input_Child_FirstName = By.xpath("//input[@placeholder='First Name']");
		public static By input_Child_LastName = By.xpath("//input[@placeholder='Last Name']");

		public static By input_Child_DOB = By.xpath("//div[@class='child']//input[@name='birth_mdy']");
		public static By select_Child_Gender = By.xpath("//select[@name='gender' and @class='form-control']");
		public static By btn_Child_Done = By.id("extended-step-one");
		public static By btn_Child_SaveAddAnother = By
				.xpath("//button[@class='add-another-child add-btn btn btn-primary']");
		// public static By btn_Child_Done =By.xpath("//button[@class='add-another-child
		// add-btn btn btn-primary']");

	}

	static class CreateGroupPage {
		public static By rbn_Group_Class = By.id("group-class");
		public static By rbn_Group_Team = By.id("group-team");
		public static By rbn_Group_BoyScouts = By.id("group-team-boy-scout-troop");
		public static By rbn_Group_GirlScout = By.id("group-team-girl-scout-troop");
		public static By rbn_Group_Youth = By.id("group-team-youth-ministry");
		public static By rbn_Group_OtherChild = By.id("group-team-other-child");
		public static By rbn_Group_NoChild = By.id("group_nochild");
		public static By btn_Group_Back = By.xpath("//a[text()='Back']");
		public static By btn_Group_Next = By.id("group-submit-first");
		public static By btn_Group_Profile_Next = By.id("coach-submit-first");
		public static By input_Group_Profile_Name = By.id("profileName");
		// public static By input_Group_Profile_Name =By.id("profileName");

		public static By tab_TopNavigation_Inbox = By.xpath("//a[text()='Inbox']");
		public static By tab_TopNavigation_MyNetwork = By.xpath("//a[text()='My Network']");
		public static By tab_TopNavigation_Directory = By.xpath("//a[text()='Directory']");
		public static By lnk_EditProfile = By.xpath("//a[text()='Edit Profile & Preferences ']");
		public static By lnk_EditGroups = By.xpath("//a[text()='Edit Groups & Connections']");
		public static By lnk_JoinGroup = By.xpath("//a[text()='Have a Group Code? Join Now']");
		public static By img_UserProfile = By.xpath("//img[@title='Profile']");
		public static By lnk_UserOptions = By.xpath("//a[text()='options']");
		public static By lnk_User_MyProfile = By.xpath("//a[text()='My Profile']");
		public static By lnk_User_Help = By.xpath("//a[text()='Help']");
		public static By lnk_User_Logout = By.xpath("//li[@class='elgg-menu-item-logout']/a[text()='Logout']");

		public static By btn_DayPrevious = By.id("day2_cal_prev");
		public static By btn_DayNext = By.id("day2_cal_next");
		public static By txt_Event_Date1 = By.xpath("(//div[@class='event_date'])[1]");
		public static By txt_Event_Date2 = By.xpath("(//div[@class='event_date'])[2]");
		public static By txt_Today = By.xpath("//div[@class='event_day']");
		public static By img_AppStore = By.id("itunes_app_link_div");
		public static By img_GooglePlay = By.id("google_app_link_div");
		public static By img_Facebook = By.xpath("//div[@class='social-facebook']");
		public static By img_Twitter = By.xpath("//div[@class='social-twitter']");
		public static By img_Linkedin = By.xpath("//div[@class='social-linkedin']");
		public static By img_Pinterest = By.xpath("//div[@class='social-pinterest']");
	}

	static class  GmailOR {
		public static By GooggleLogo=By.xpath("//div[@id='logo'][@title='Google']");
		public static By EmailTextbox=By.xpath("//input[@type='email']"); 
		public static By EmailNext=By.xpath("//span/span[text()='Next']"); 
		public static By InboxRefresh = By.xpath("//*[@title='Inbox']");
		public static By PwdTextbox=By.xpath("//input[@type='password']"); 
		public static By PasswordNext=By.xpath("//div[@id='passwordNext']/span/span[text()='Next']");
		public static By GmailHome=By.xpath("//header[@id='gb']//a[@title='Gmail']/img");
		public static By UserAccount=By.xpath("//a[contains(@aria-label,'fwstestuser.tx@gmail.com')][@role='button']");
		public static By UserAccount_a1=By.xpath("//a[contains(@aria-label,'fwstestuser.a1.tx@gmail.com')][@role='button']");
		public static By SignOut=By.xpath("//a[text()='Sign out']");
		public static By PasswordResetUnreadEmailSubject=By.xpath("//td[@class='xY a4W']/descendant::span[text()='Forgotten Password'][@class='bqe']");
		public static By EmailSender=By.xpath("//h3/span/span[@email='noreply@fabory.com']");
		public static By Email_ResetPasswordLink=By.xpath("//a[contains(@href,'fabory.com')]/u[text()='click here']");
		public static By ShowEmailBody=By.xpath("//div[@class='ajR'][@aria-label='Show trimmed content']/img[@src='//ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif']");
		//Registration Email
		public static By WelcomeUnreadEmailSubject_EN=By.xpath("//td[@class='xY a4W']/descendant::span[contains(text(),'Welcome to Fabory!')][@class='bqe']");
		public static By WelcomeUnreadEmailSubject_NL=By.xpath("//td[@class='xY a4W']/descendant::span[contains(text(),'Welkom bij Fabory!')][@class='bqe']");
		public static By WelcomeEmailBodyLine1_EN=By.xpath("//div/p[contains(text(),'Welcome to Fabory. Thank you for registering with Europe')]");
		public static By WelcomeEmailBodyLine1_NL=By.xpath("//div/p[contains(text(),'Hartelijk dank voor uw registratie bij Fabory.')]");
		public static By WelcomeEmailBodyExisting=By.xpath("//div/p[contains(text(),'Thank you for registering at Fabory.')]");

		//Reset Password page
		public static By PageTitle=By.xpath("//title[contains(text(),'Reset Password')]");
		public static By NewPassword=By.xpath("//input[@name='pwd'][@type='password']");
		public static By ConfirmPassword=By.xpath("//input[@name='checkPwd'][@type='password']");
		public static By UpdateButton=By.xpath("//button[contains(text(),'Update')]");
		public static By ConfirmPwdValidateMessage=By.xpath("//label[text()='Please enter the same value again.']");

		//Admin email
		public static By NewRegistrationAdminEmailSubject=By.xpath("//tr[@class='zA zE'][1]/descendant::span[text()='Fabory | New registration for your company'][2]");
		public static By AdminEmailBodyLine1_en=By.xpath("//div/p[contains(text(),' has registered under your company account. Please review, enable account, and assign appropriate permissions.')]");
		public static By AdminEmailBodyLine1_dutch=By.xpath("//div/p[contains(text(),'heeft zich geregistreerd onder uw bedrijfsaccount. Kunt u deze registratie controleren, activeren en de juiste machtiging toewijzen.')]");
		public static By Email_ClickHereLink=By.xpath("//u[text()='Click here']");
		public static By Email_ClickHereLink_NL=By.xpath("//u[text()='Klik hier']");

		
		//
		public static By lnk_Mute=By.xpath("(//b[contains(text(),'click')])[2]");
		public static By txt_Mute_successMsg=By.xpath("//p[contains(text(),'Notifications for this post have been muted.')]");
		public static By btn_Continue=By.xpath("//a[text()='Continue']");

		

	}
	
	static class  Give {
		public static By btn_EdBackerCampaign_Donate =By.xpath("//div[@id='comment-div-24158677']//a[text()='Donate']");
		public static By btn_EdBackerCampaign_DonateEdit =By.xpath("//div[@id='comment-div-24158677']//span[@title='Edit campaign']");
		
		public static By txt_Campaign_Edit=By.xpath("//h1[text()='Edit Your Campaign']"); 
		public static By EmailNext=By.xpath("//content/span[text()='Next']"); 
		public static By InboxRefresh = By.xpath("//*[@title='Inbox']");
		public static By PwdTextbox=By.xpath("//input[@type='password']"); 
		public static By PasswordNext=By.xpath("//div[@id='passwordNext']/content/span[text()='Next']");
		public static By GmailHome=By.xpath("//*[@id='gb']/div[2]/descendant::div/a[@title='Gmail']");
		public static By UserAccount=By.xpath("//a[contains(@aria-label,'fwstestuser.tx@gmail.com')][@role='button']");
		public static By UserAccount_a1=By.xpath("//a[contains(@aria-label,'fwstestuser.a1.tx@gmail.com')][@role='button']");
		public static By SignOut=By.xpath("//a[text()='Sign out']");
		public static By PasswordResetUnreadEmailSubject=By.xpath("//td[@class='xY a4W']/descendant::span[text()='Forgotten Password'][@class='bqe']");
		public static By EmailSender=By.xpath("//h3/span/span[@email='noreply@fabory.com']");
		public static By Email_ResetPasswordLink=By.xpath("//a[contains(@href,'fabory.com')]/u[text()='click here']");
		public static By ShowEmailBody=By.xpath("//div[@class='ajR'][@aria-label='Show trimmed content']/img[@src='//ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif']");
		//Registration Email
		public static By WelcomeUnreadEmailSubject_EN=By.xpath("//td[@class='xY a4W']/descendant::span[contains(text(),'Welcome to Fabory!')][@class='bqe']");
		public static By WelcomeUnreadEmailSubject_NL=By.xpath("//td[@class='xY a4W']/descendant::span[contains(text(),'Welkom bij Fabory!')][@class='bqe']");
		public static By WelcomeEmailBodyLine1_EN=By.xpath("//div/p[contains(text(),'Welcome to Fabory. Thank you for registering with Europe')]");
		public static By WelcomeEmailBodyLine1_NL=By.xpath("//div/p[contains(text(),'Hartelijk dank voor uw registratie bij Fabory.')]");
		public static By WelcomeEmailBodyExisting=By.xpath("//div/p[contains(text(),'Thank you for registering at Fabory.')]");

		//Reset Password page
		public static By PageTitle=By.xpath("//title[contains(text(),'Reset Password')]");
		public static By NewPassword=By.xpath("//input[@name='pwd'][@type='password']");
		public static By ConfirmPassword=By.xpath("//input[@name='checkPwd'][@type='password']");
		public static By UpdateButton=By.xpath("//button[contains(text(),'Update')]");
		public static By ConfirmPwdValidateMessage=By.xpath("//label[text()='Please enter the same value again.']");

		//Admin email
		public static By NewRegistrationAdminEmailSubject=By.xpath("//tr[@class='zA zE'][1]/descendant::span[text()='Fabory | New registration for your company'][2]");
		public static By AdminEmailBodyLine1_en=By.xpath("//div/p[contains(text(),' has registered under your company account. Please review, enable account, and assign appropriate permissions.')]");
		public static By AdminEmailBodyLine1_dutch=By.xpath("//div/p[contains(text(),'heeft zich geregistreerd onder uw bedrijfsaccount. Kunt u deze registratie controleren, activeren en de juiste machtiging toewijzen.')]");
		public static By Email_ClickHereLink=By.xpath("//u[text()='Click here']");
		public static By Email_ClickHereLink_NL=By.xpath("//u[text()='Klik hier']");


	}
	
	static class  UserProfiles {
		public static By img_UserProfile = By.xpath("//img[@title='Profile']");
		public static By lnk_UserOptions = By.xpath("//a[text()='options']");
		public static By lnk_User_MyProfile = By.xpath("//a[text()='My Profile']");
		public static By lnk_User_Help = By.xpath("//a[text()='Help']");
		public static By lnk_User_Logout = By.xpath("//li[@class='elgg-menu-item-logout']/a[text()='Logout']");	
		public static By lnk_User_ChangeMyPassword = By.xpath("//a[text()='Change My Password']");
		
		public static By input_User_OldPassword = By.id("old_password");
		public static By input_User_NewPassword = By.id("new_password");
		public static By input_User_ConfirmNewPassword = By.id("confirm_new_password");
		public static By btn_User_ChangePassword = By.xpath("//input[@value='Change Password']");
		public static By btn_User_ChangePasswordSuccessMessage = By.xpath("//p[text()='Password change successful']");
		
		public static By input_User_chngemail = By.id("email");
		//by chinmoy
		//public static By input_User_chngephoneno = By.id("phone_view");
		
		public static By input_User_chngephoneno = By.xpath("//input[@id='phone_view']");
		
		
	}
	static class  EditProfile {
		
		public static By input_Firstname = By.id("firstname");
		public static By input_Lastname = By.id("lastname");
		public static By input_Email = By.id("email");
		public static By input_Address = By.name("address");
		public static By input_City = By.name("city");
		public static By input_Zipcode = By.id("zipcode");
		public static By picker_Country= By.id("country_select");
		public static By picker_State = By.id("state_select");
		public static By picker_Timezone = By.id("timezone_select_list");
		public static By input_TelephoneNumber= By.id("phone_view");
		public static By input_Gender = By.name("gender");
		public static By input_DOB = By.name("birth_mdy");
		public static By input_Language = By.id("user_language");
		public static By btn_Save = By.xpath("//input[@class='elgg-button btn']");
		//Notification Preferences Locators
		public static By rbn_DailyDigest_Yes = By.xpath("//input[@name='daily_digest' and @value='yes']");
		public static By rbn_DailyDigest_No = By.xpath("//input[@name='daily_digest' and @value='no']");
		public static By rbn_EmailNotification_Yes= By.xpath("//input[@name='email_notification' and @value='yes']");
		public static By rbn_EmailNotification_No = By.xpath("//input[@name='email_notification' and @value='no']");
		public static By rbn_MobilePushAlert_Yes = By.xpath("//input[@name='mobile_push_alert' and @value='yes']");
		public static By rbn_MobilePushAlert_No = By.xpath("//input[@name='mobile_push_alert' and @value='no']");
		public static By chk_DirectMessages = By.name("direct_messages");
		public static By chk_CommentsOnPost = By.name("comments_on_post");
		public static By chk_CommentsOnPostComment = By.name("comments_on_post_comment");
		public static By chk_EventsRSVPSignups = By.name("events_created_modified");
		public static By chk_EventReminder = By.name("notification_required");
		public static By chk_SignupReceivedEvent = By.name("signup_received");
		public static By chk_RSVPReceivedEvent = By.name("rsvp_received");
		public static By chk_LikesPosts = By.name("likes_posts");
		public static By chk_InviteAccepts = By.name("invite_accepts");
		public static By lbl_Email_Reporting =By.xpath("//div[@id='edit_profile_text']/strong[contains(text(),'Email Reporting (School/District/Org Admins Only)')]");
		public static By chk_weekly_signup_report=By.xpath("//input[@name='weekly_signup_report']");
		public static By chk_weekly_activity_report=By.xpath("//input[@name='weekly_activity_report']");
		public static By chk_district_provision_report=By.xpath("//input[@name='district_provision_report']");
		public static By chk_DISTRICT_ACTIVITY_REPORT_WEEKLY=By.xpath("//input[@name='DISTRICT_ACTIVITY_REPORT_WEEKLY']");
		public static By lbl_error_message=By.xpath("//div[@class='error_message_phone_number']");
		public static By lbl_error_message_mail=By.xpath("//label[@for='email']");
		
	}
	static class ProvisionWelcomePage {
		public static By txt_UserName = By.xpath("//div[@id='modalSystemNotification']/div/p[1]/strong");
		public static By input_NewPassword = By.id("new_password");
		public static By input_ConfirmNewPassword = By.id("confirm_new_password");
		public static By input_DOB = By.name("birth_mdy");
		public static By picker_Language = By.id("user_language");	
		//public static By picker_Language = By.xpath("//option[@value='Asia/Kolkata']");	
		
		public static By picker_TimeZone = By.id("timezone_select_list");
		//public static By picker_TimeZone = By.xpath("//optgroup[@label='India']");
		
		public static By chk_TermsOfService = By.name("termsofservice");
		public static By txt_ResponseRequiredError = By.xpath("//label[text()='Response Required']");
		
		
		public static By lnk_Logout = By.id("wp_logout");
		public static By btn_Continue = By.id("continue");
		public static By btn_User_ChangePassword = By.xpath("//input[@value='Change Password']");
		public static By btn_User_ChangePasswordSuccessMessage = By.xpath("//p[text()='Password change successful']");
		
		
		
		
	}
	static class  Directory {
		public static By txt_FESScore = By.xpath("//div[@class='engage-score']/p");
		public static By txt_UserName = By.id("fc_user_name");
		public static By input_search_directory=By.id("srch-term");
					
	}
	static class  Inbox {
		public static By btn_ComposeAMessage = By.xpath("//a[text()='Compose a Message']");
		public static By chk_Select = By.xpath("//input[@name='message_id[]']");
		public static By btn_Delete= By.name("delete");
		public static By btn_MarkAsRead= By.name("read");
		public static By btn_MarkAsUnread= By.name("unread");
		public static By btn_ToggleAll= By.id("messages-toggle");
		public static By lbl_Inbox = By.xpath("//li/a[starts-with(text(),'Inbox (')]");
		public static By lbl_Drafts = By.xpath("//li/a[starts-with(text(),'Drafts (')]");
		public static By lbl_Invitations = By.xpath("//li/a[starts-with(text(),'Invitations')]");
		public static By lbl_Sent = By.xpath("//li/a[starts-with(text(),'Sent')]");
		public static By input_Compose_UserTo= By.id("composeUserPicker");
		public static By txt_Compose_SelectUser= By.xpath("//div[@class='typeahead_primary']");
		public static By input_Compose_Subject= By.id("composeSubject");
		public static By input_Compose_File= By.id("message_file");
		public static By frame_Compose_Message= By.xpath("//iframe[starts-with(@id,'elgg-input')]");
		public static By lbl_forward=By.xpath("//a[contains(text(),'Forward')]");
		public static By input_forward_reception=By.id("userPicker_forward");
		public static By lbl_reply=By.xpath("//a[contains(text(),'Reply')]");
		public static By input_reply_reception=By.id("userPicker_reply");
		public static By lbl_reply_all_reception=By.xpath("//a[contains(text(),'Reply All')]");
		//public static By input_reply_all_reception=By.id("userPicker_reply");
		
		public static By input_Compose_Message= By.id("tinymce");
		public static By btn_SaveDraft= By.id("btnSaveDraft");
		public static By btn_Send= By.xpath("//div[@class='elgg-foot']/input[1]");
		public static By txt_Compose_SuccessMsg= By.xpath("//p[text()='Message has been sent.']");
		public static By list_AllSentMessages= By.xpath("//ul[@class='elgg-list elgg-list-entity']/li");
		
		//Monica
		public static By inboxNav = By.xpath("//div[@id='topnav-messages-text']");
		public static By composeMessage = By.xpath("//a[contains(text(),'Compose a Message')]");
		public static By toField = By.xpath("//input[@id='composeUserPicker']");
		


 	}
	static class  MyProfile {
		public static By lbl_EditProfilePicture = By.xpath("//a[text()='Edit Profile Picture']");
		public static By lbl_ChangeMyPassword = By.xpath("//a[text()='Change My Password']");
		public static By lbl_EditProfilePreferences = By.xpath("//a[text()='Edit Profile & Preferences']");
		public static By lbl_ManageCreditCard = By.xpath("//a[text()='Manage Credit Card']");
		public static By btn_ChoosePictureText = By.xpath("//span[text()='Choose Picture']");
		
		public static By btn_ClearPicture = By.id("clear_profile_pic");
		public static By btn_ChoosePicture = By.id("avatar_file");
		public static By btn_ChoosePicture2 = By.id("avatar_file_clone");
		public static By btn_Cancel = By.id("avatar_close_btn");
		public static By btn_CropSavePhoto = By.id("avatar_crop_btn");
		public static By btn_AutofitSavePhoto = By.id("avatar_actual_btn");
		
	}
	static class Manage_Data{
		public static By select_down_school=By.id("school_select");
		public static By lbl_upload_student_data= By.xpath("//td[@class='school_file_uploaded_td file-upload-td']/div[@onclick=\"javascript:onclick_input_file('students')\"]");
		public static By btn_update_network=By.xpath("//button[@id='btn_update_network']");
		public static By btn_add_admin=By.id("addrow");
		public static By lbl_close_add_admin=By.xpath("//table[@id='school_admin_table']/tbody/tr//i[@class='ibtnDel glyphicon glyphicon-remove']");
	}
	static class Manage_group_code{
		public static By lbl_gi_group_name=By.id("gi_group_name");
		public static By lbl_gi_group_invitecode=By.id("gi_group_invitecode");
		public static By btn_print=By.xpath("//button[contains(text(),'PRINT')]");
		public static By lbl_print_groupname=By.xpath("//h2[contains(text(),'PK Homeroom')]");
		public static By lbl_print_groupcode=By.xpath("//span[@class='code']");
		public static By btn_close=By.xpath("//div[@id='modalGenerateInviteCode']//span[@class='cls-txt hidden-xs'][contains(text(),'Close')]");
	
	}
	static class Send_Emergency_Alert{
		public static By lbl_emergency_alert_create_message=By.xpath("//div[@class='emergency-default-msg-div']");
		public static By lbl_emergency_alert_send_alert=By.xpath("//div[@class='emergency-alert-div']");
		public static By txt_default_subject=By.id("emergency_default_modal_subject");
		public static By txt_default_text_area=By.id("emergency_default_modal_text");
		public static By btn_default_save=By.id("emergency_default_modal_submit");
		public static By txt_share_screen_subject= By.id("post_subject");
		public static By txt_share_screen_text_area=By.id("thewire_textarea");
		public static By btn_share_screen_post= By.id("thewire-submit-button");
		public static By lbl_remove_text_alret_button=By.xpath("//span[contains(text(),'Remove Text Message')]");
		public static By lbl_remove_phone_alrert_button=By.xpath("//span[contains(text(),'Remove Phone Alert')]");
		
	}
	static class mynetwrok
	{
		public static By lbl_parent_select=By.xpath("(//span[@class='build-big-arrow'])[1]");
		public static By class_choice_12_all=By.xpath("//a[contains(text(),'12_ALL')]");
		public static By sendwellcomemailtosingleuser=By.id("send_welcome_email_singl_euser");
		public static By btn_create_mirror_group=By.xpath("//a[contains(text(),'Create Mirror Group')]");
		public static By btn_create_mirror_group_to_netwrok=By.xpath("//button[@class='elgg-button btn btn-sm btn_green_cmn']");//button[@class='elgg-button btn btn-sm btn_green_cmn']
		public static By txt_input_group_user_mail_id=By.id("mirror_group_user_email");
		public static By txt_first_name=By.id("first_name");
		public static By txt_last_name=By.id("last_name");
		public static By txt_phone=By.id("phone");
		public static By chk_Send_Welcome_Email=By.id("welcome_email_mirror");
		public static By chk_Send_Welcome_SMS =By.id("welcome_sms_mirror");
		public static By lnk_parent_connection=By.xpath("//span[contains(text(),'Parents')]/parent::div//div/a[contains(text(),'Connections')]");
		public static By lnk_student_connection=By.xpath("//span[contains(text(),'Students')]/parent::div//div/a[contains(text(),'Connections')]");
		public static By lnk_icon_on_first_parent=By.xpath("(//span[@class='group_actions'])[2]//span[@class='icon']");
		public static By lnk_icon_on_first_admisitrator=By.xpath("(//span[@class='group_actions'])[1]//span[@class='icon']");
		public static By lnk_delete_parent=By.xpath("(//span[@class='group_actions'])[2]//ul/li/a[contains(text(),'Delete')]");
		public static By lnk_delete_administrator=By.xpath("(//span[@class='group_actions'])[1]//ul/li/a[contains(text(),'Delete')]");
		public static By lnk_invite_admisistrator=By.xpath("//a[contains(text(),' Invite')]");
	}
	
}
