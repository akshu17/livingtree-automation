package controllers;

import utils.JiraUtil;

public class JiraBugCreationDemo {

	public static void main(String[] args) {
		
		
		
		JiraUtil ju=new JiraUtil();
		ju.reporIssue("Tets summary", "description", "D:\\Projects\\LivingTree\\Workspace\\LivingTree_TestAutomation\\ExecutionReports\\FailedScreenshots\\20190220172335_test_LA11924.jpg");
		

	}

}
