/**
 * 
 */
package controllers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

//import com.asprise.
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.ConfigReader;
import utils.EnvironmentSetup;
import utils.ExtentTestManager;
import utils.LogUtil;

/**
 * @Author Chandu
 * @Date 15-Nov-2018
 */
public class BaseMethod extends WebDriverFactory {
	private String RESULT_FOLDER_NAME = USERDIR + "\\ExecutionReports\\ExecutionReports";
	public static String HtmlReports=null;
	/*
	 * @AfterTest public void beforeSuite() throws Exception {
	 * EnvironmentSetup.environmentSetup(); }
	 */

	/* To get the Website Name */
	public String getUrlTitle() throws Exception {
		URL aURL = new URL(WebsiteURL);
		String WebName = aURL.getHost();
		String WebSiteName = WebName.toUpperCase();
		return WebSiteName;
	}

	public void logStep(String logStep) {
		LogUtil.infoLog(this.getClass(), logStep);
		ExtentTestManager.stepInfo(logStep);
	}

	public void logStepFail(String logStep) {

		LogUtil.infoLog(this.getClass(), "Failed :" + logStep);
		ExtentTestManager.stepFail(logStep);
	}

	public void logStepPass(String logStep) {
		LogUtil.infoLog(this.getClass(), "Passed: " + logStep);
		ExtentTestManager.stepPass(logStep);
	}

	/* To Press ENTER Key using Robot */
	public void hitEnter() {
		try {
			re = new Robot();
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* To Press BACKSPACE Key using Robot */
	public void hitBackspace(By locator) throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_BACK_SPACE);
		re.keyRelease(KeyEvent.VK_BACK_SPACE);
	}

	public String getFutureDate1(int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate future = today.plus(value, ChronoUnit.DAYS);
		return dtf.format(future);
	}

	public String getPastDate1(int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate past = today.minusDays(value);
		return dtf.format(past);
	}

	public String getText(By locator) {
		String s = null;
		WebElement element = getWebElement(locator);
		//System.out.println(element);
		if (element == null) {
			logStepFail("Text : " + s);

		} else {
			s = element.getText();
			//System.out.println(s);
			highLightElement(locator);
			logStepPass("Text : " + s );
		}
		return s;
	}
	public String getElementValue(By locator) {
		String s = null;
		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail("Text : " + s );

		} else {
			s = element.getAttribute("value");
			logStepPass("Text value : " + s );
		}
		return s;
	}
	public String getCSSValue(By locator,String cssValue,String message) {
		String s = null;
		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(message);

		} else {
			s = element.getCssValue(cssValue);
			logStepPass(message);
		}
		return s;
	}

	/* To Press DELETE Key using Robot */
	public void hitDelete(By locator, String message) throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_DELETE);
		re.keyRelease(KeyEvent.VK_DELETE);
	}

	/* To Select all the Text/Web Elements using ROBOT */
	public void selectAll(By locator, String message) throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_A);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_A);
	}

	/* To Copy all the Selected Text/Web Elements using ROBOT */
	public void copyAll() throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_C);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_C);
	}

	/* To Paste all the Selected Text/Web Elements using ROBOT */
	public void pasteAll() throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_V);
	}

	/* To Capture Screenshot(Replaces if already exists) */
	/*
	 * Also, Make sure that the automation in running in the foreground to capture
	 * the Image of the Browser. Else, It'll capture the open Window
	 */
	public void robotScreenCapture(String robotImageName) throws Exception {
		re = new Robot();
		Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage bufferedImage = re.createScreenCapture(area);
		// Save as PNG
		File file = new File(robotImageName);
		if (file.exists()) {
			file.delete();
		}
		ImageIO.write(bufferedImage, "png", file);
	}

	/* To ZoomOut */
	public void robotZoomOut() {
		try {
			re = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}

	/* To ZoomIn */
	public void robotZoomIn() {
		try {
			re = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}

	/* To ScrollUp using ROBOT */
	public void robotScrollUp(By locator, String message) {
		try {
			re = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		re.keyPress(KeyEvent.VK_PAGE_UP);
		re.keyRelease(KeyEvent.VK_PAGE_UP);
	}

	/* To ScrollDown using ROBOT */
	public void robotScrollDown(By locator, String message) throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_DOWN);
		re.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	/* To ScrollUp using JavaScript Executor */
	public void scrollUp(By locator, String message) throws Exception {
		((JavascriptExecutor) getWebDriver()).executeScript("scroll(0, -100);");
	}

	/* To ScrollDown using JavaScript Executor */
	public void scrollDown(By locator, String message) throws Exception {
		((JavascriptExecutor) getWebDriver()).executeScript("scroll(0, 100);");
	}
	/* To ScrollDown to end using JavaScript Executor */
	public void scrollDowntoEnd(String message) throws Exception {
		((JavascriptExecutor) getWebDriver()).executeScript("scroll(0, 1000);");
	}
	/* To Scroll to element using JavaScript Executor */
	public void scrolltoElement(By locator,String message) throws Exception {
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();", getWebElement(locator));
		Thread.sleep(500);
		
	}
	/* To Scroll to element using JavaScript Executor */
	public void scrolltoElement_new(By locator,String message) throws Exception {
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();", getWebElement(locator));
		highLightElement(locator);
		logStep(message);
		Thread.sleep(500);
		
	}

	/* To Move cursor to the Desired Location */
	public void moveCursor(int X_Position, int Y_Position) throws Exception {
		re.mouseMove(X_Position, Y_Position);
	}

	/* To Accept the Alert Dialog Message */
	public void alertAccept() {
		al = getWebDriver().switchTo().alert();
		al.accept();
	}

	/* To Dismiss the Alert Dialog Message */
	public void alertDismiss() throws Exception {
		al = getWebDriver().switchTo().alert();
		al.dismiss();
	}

	/* To Get the Alert Messages */
	public String getAlertText() throws Exception {
		al = getWebDriver().switchTo().alert();
		String text = al.getText();
		Thread.sleep(2000);
		alertAccept();
		return text;
	}
	
	public  boolean isAlertPresent(){
	      try{
	    	  getWebDriver().switchTo().alert();
	          return true;
	      }
	      catch(NoAlertPresentException ex){
	          return false;
	      }
	}

	/* To Upload a File using JAVA AWT ROBOT */
/*	public void fileUpload(String FileToUpload)  {
		Thread.sleep(5000);
		StringSelection filetocopy = new StringSelection(FileToUpload);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filetocopy, null);
		Thread.sleep(1000);
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
	}*/

	/* To Perform a WebAction of Mouse Over */
	public void mousehover(WebElement element) {
		ac = new Actions(getWebDriver());
		ac.moveToElement(element).build().perform();
	}

	/* To Perform Select Option by Visible Text */
	public void selectByVisibleText(WebElement element, String value) {
		se = new Select(element);
		se.selectByVisibleText(value);
	}

	/* To Perform Select Option by Index */
	public void selectByIndex(WebElement element, int value) {
		se = new Select(element);
		se.selectByIndex(value);
	}

	/* To Perform Select Option by Value */
	public void selectByValue(WebElement element, String value) {
		se = new Select(element);
		se.selectByValue(value);
	}

	/* To Perform Select Option by Value */
	public WebElement getWebElement(By locator) {
		return getWebDriver().findElement(locator);
	}

	/* To click a certain Web Element */
	public void click(WebElement element, String message) {
		if (element == null) {
			logStepFail(message + "  --Fail");
		} else {
			// pause(1000);
			System.out.println("in");
			element.click();
			pause(3000);
			logStep(message );
			}
	}

	/* To click a certain Web Element */
	public void click(By locator, String message) {
		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(message + "  --Fail");
		} else {
			highLightElement(locator);
			// pause(1000);
			element.click();
			pause(1000);
			logStepPass(message );
			}
	}

	public void compareTwoValues(String actual, String expected) {
		System.out.println(actual.toLowerCase());
		System.out.println(expected.toLowerCase());
		if (actual.toLowerCase().contains(expected.toLowerCase())) {
			logStepPass("Found actual value " + actual + " and Expected value : " + expected );
		} else {
			logStepFail("Found actual value " + actual + " and Expected value : " + expected );
			Assert.fail();
		}
	}

	public void compareTwoValuesIgnoreCase(String actual, String expected) {
		if (actual.equalsIgnoreCase(expected)) {
			logStep("Found actual value :" + actual + " and Expected value : " + expected );

		} else {
			logStep("Found actual value :" + actual + " and Expected value : " + expected );
			Assert.fail();
		}
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public void inputText(By locator, String data, String logStep) {

		WebElement elm = getWebElement(locator);

		if (elm == null) {
			logStepFail(logStep + "  --Fail");
		} else {
			highlightElement(locator);
			elm.clear();
			elm.sendKeys(data);
			logStepPass(logStep );

		}
	}
	public void changedate()
	{
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");
		Date dt = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);;
		cl.add(Calendar.DAY_OF_YEAR, 2);
		dt=cl.getTime();
		String str = df.format(dt);
		    System.out.println("the date today is " + str);

//		    WebElement img_date=getWebDriver().findElement(By.xpath("//span[@class='icon_img'])"));
//		    img_date.click();
		    WebElement el = getWebDriver().findElement(By.xpath("//input[@id='end_date']"));
		    el.sendKeys(str);
		    WebElement e2=getWebDriver().findElement(By.xpath("//a[contains(text(),'"+str+"')]"));
	}

	public void selectByVisibleText(By locator, String data, String logStep) {

		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(logStep);
		} else {
			highlightElement(locator);
			se = new Select(element);
			se.selectByVisibleText(data);
			logStepPass(logStep );

		}

	}
	public void selectByIndex(By locator, int index, String logStep) {

		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(logStep + "  --Fail");
		} else {
			highlightElement(locator);
			se = new Select(element);
			se.selectByIndex(index);
			logStepPass(logStep );

		}

	}


	public void selectByValue(By locator, String data, String logStep) {

		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(logStep);
		} else {
			highlightElement(locator);
			se = new Select(element);
			se.selectByValue(data);
			logStepPass(logStep );

		}

	}

	/**
	 * @param locator
	 * @return
	 *//*
		 * public boolean isWebElementVisible(By locator) { try{
		 * 
		 * WebElement elm = getWebElement(locator); return elm.isDisplayed(); } catch
		 * (Exception e) { return false; }
		 * 
		 * }
		 */
	/**
	 * @param locator
	 * @return
	 */
	public boolean isWebElementVisible(By locator) {
		try {
			waitForElement(locator);
			WebElement elm = waitForVisible(locator);
			boolean element=elm.isDisplayed();
			return element;
		} catch (Exception e) {
			
			return false;
		}

	}
	
 public boolean isWebElementgettextvalidate(By locator,String provided_text) 
 {
	 try {
		 String text=getWebDriver().findElement(locator).getText();
		 System.out.println(text);
		 System.out.println(provided_text);
		 if(text.equalsIgnoreCase(provided_text))
		 {
			 System.out.println("pass");
			 return true;
		 }
		 else
		 {
			 System.out.println("fail");
			 //Assert.fail();
			 return false;
		 }
	 }
	 catch(Exception e) {
		 e.printStackTrace();
		 System.out.println("catch fail");
		// Assert.fail();
		 return false;
	 }
}	 

 
	public boolean isWebElementVisible(By locator, int timeOut) {
		try {
			LogUtil.infoLog(BaseMethod.class, "Check Element visible: " + locator.toString());
			waitForElement(locator, timeOut);
			WebElement elm = waitForVisible(locator);
			return elm.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}
	public String isvalidategroupname(By locator_groupName,By locator_groupCode ,String logstep,String groupName) {
		String group_code=null;
		try {
		highlightElement(locator_groupName);
		highlightElement(locator_groupCode);
		WebElement element_groupName=getWebElement(locator_groupName);
		String text=element_groupName.getText();
		if(text.contains(groupName))//have to check equalignore case
		{
			WebElement element_groupCode=getWebElement(locator_groupCode);
			group_code=element_groupCode.getText();
			logStepPass(logstep);
			return group_code;
		}
		else
		{
			logStepFail(logstep);
			return group_code;
			
			
		}
		}
		catch(Exception e)
		{
			logStepFail(logstep);
			return group_code;
		}
		
		
	}
	public boolean isWebElementVisible(By locator, String logstep) {
		try {
			//LogUtil.infoLog(BaseMethod.class, "Check Element visible: " + locator.toString());
			waitForElement(locator);
			WebElement elm = waitForVisible(locator);
			highlightElement(locator);
			logStepPass(logstep );
			return elm.isDisplayed();
		} catch (Exception e) {
			logStepFail(logstep);
			return false;
		}

	}
	public boolean isWebElementVisible_need(By locator, String logstep) {
		try {
			//LogUtil.infoLog(BaseMethod.class, "Check Element visible: " + locator.toString());
			waitForElement_need(locator);
			WebElement elm = waitForVisible(locator);
			highlightElement(locator);
			logStepPass(logstep );
			return elm.isDisplayed();
		} catch (Exception e) {
			logStepFail(logstep);
			return false;
		}

	}
	public boolean isWebElementNotPresent1(By locator, String logstep) {
		boolean status = getWebDriver().findElement(locator).isEnabled();
		if (!status) {

			logStepPass(logstep );
			return true;
		} 
		else 
		{
			logStepFail(logstep);
			Assert.fail();
			return false;
		}
	}

	/**
	 * @param a
	 * @param string 
	 * @throws InterruptedException
	 */
	public static void pause(long a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public boolean  isStringInt(String s,String logStep)
    {
        try
        {
            Integer.parseInt(s);
            logStepPass(logStep );
            return true;
        } catch (NumberFormatException ex)
        {
        	ex.printStackTrace();
        	logStepFail(logStep);
        	Assert.fail();
            return false;
        }
    }
	
	
	public void highLightElement(By locator) {
		WebElement element = getWebDriver().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','');", element);
		// border: solid 2px white

	}

	public void highlightElement(By locator) {
		WebElement element = getWebDriver().findElement(locator);
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: yellow; border: 2px solid yellow;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	public void highLightElement1(By locator) {
		WebElement element = getWebDriver().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','');", element);
		// border: solid 2px white

	}

	public boolean waitUntilElement(By type, int timeOut) {
		int time = 0;
		boolean status = false;
		while (time < timeOut) {
			status = getWebDriver().findElements(type).size() > 0;
			if (status) {
				status = true;
				highLightElement(type);
				break;
			}
			time = time + 1;
			pause(1000);
		}
		return status;
	}

	// Get Tag name and locator value of Element
	public static String getElementInfo(By locator) throws Exception {
		return " Locator: " + locator.toString();
	}

	public static String getElementInfo(WebElement element) throws Exception {
		String webElementInfo = "";
		webElementInfo = webElementInfo + "Tag Name: " + element.getTagName() + ", Locator: ["
				+ element.toString().substring(element.toString().indexOf("->") + 2);
		return webElementInfo;

	}

	public WebElement waitForClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @param locator
	 * @return
	 */
	public WebElement waitForPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * @param locator
	 * @return
	 */
	public WebElement waitForVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * @param time
	 * @throws InterruptedException
	 */
	public static void delay(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	/**
	 * @param locator
	 * @return
	 */
	public boolean verifyCurrentDateInput(By locator) {
		boolean flag = false;
		WebElement element = waitForVisible(locator);
		String actual = element.getAttribute("VALUE").trim();
		DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
		String expected = dtFormat.format(date).trim();
		if (actual.trim().contains(expected)) {
			flag = true;

		}
		return flag;
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public boolean uploadFilesUsingSendKeys(By locator, String data) throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\" + data);
		return true;
	}

	/**
	 * @param data
	 * @param page
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static boolean verifyPDFData(String data, int page, String fileName) throws IOException {
		FileInputStream fis = null;
		String dwnFile = null;
		try {

			String dirName = System.getProperty(USERDIR) + "\\src\\test\\resources\\downloadFile\\";
			File dir = new File(dirName);
			File[] path1 = dir.listFiles();

			for (int k = 0; k < path1.length; k++) {
				dwnFile = path1[k].toString();
				if (dwnFile.contains(fileName)) {
					break;
				}

				continue;
			}
			File file = new File(dwnFile);
			fis = new FileInputStream(file.getAbsolutePath());
			PdfReader text = new PdfReader(fis);
			String expected = PdfTextExtractor.getTextFromPage(text, page);

			String[] b = data.split(",");
			fis.close();
			for (int i = 0; i < b.length; i++) {
				if (expected.contains(b[i]))
					continue;
			}
		} catch (Exception e) {
			LogUtil.errorLog(BaseMethod.class, e.getMessage(), e);
		}
		return true;
	}

	/**
	 * @return
	 */
	public boolean delDirectory() {
		File delDestination = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
		if (delDestination.exists()) {
			File[] files = delDestination.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					delDirectory();
				} else {
					files[i].delete();
				}
			}
		}
		return delDestination.delete();
	}

	public void hoverElement(By locator) {
		LogUtil.infoLog(this.getClass(), "Hover Element: " + locator.toString());

		WebElement element = waitForClickable(locator);
		Point p = element.getLocation();
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(element, p.getX(), p.getY()).build().perform();
		pause(1000);

	}

	public void hoverbyElement(By locator) {
		LogUtil.infoLog(this.getClass(), "Hover Element: " + locator.toString());
		WebElement element = waitForClickable(locator);
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(element).build().perform();
		pause(1000);

	}

	public boolean doubleClick(By locator) {
		boolean result = false;
		try {

			LogUtil.infoLog(this.getClass(), "Double click: " + locator.toString());
			WebElement element = getWebDriver().findElement(locator);
			Actions action = new Actions(getWebDriver()).doubleClick(element);
			action.build().perform();
			result = true;

		} catch (StaleElementReferenceException e) {
			LogUtil.infoLog("DoubleClick",
					locator.toString() + " - Element is not attached to the page document " + e.getStackTrace());
			result = false;
		} catch (NoSuchElementException e) {
			LogUtil.infoLog("DoubleClick",
					locator.toString() + " - Element is not attached to the page document " + e.getStackTrace());
			result = false;
		} catch (Exception e) {
			LogUtil.infoLog("DoubleClick",
					locator.toString() + " - Element is not attached to the page document " + e.getStackTrace());
			result = false;
		}
		return result;
	}

	public boolean switchToFrame(String frameName) {

		try {
			getWebDriver().switchTo().frame(frameName);
			return true;
		} catch (Exception e) {
			LogUtil.infoLog("switchToFrame", frameName + " TO FRAME FAILED" + e.getStackTrace());
			return false;
		}
	}

	public boolean switchToFrame(By locator) {

		try {
			getWebDriver().switchTo().frame(getWebDriver().findElement(locator));
			return true;
		} catch (Exception e) {
			LogUtil.infoLog("switchToFrame", " TO FRAME FAILED" + e.getStackTrace());
			return false;
		}
	}

	public boolean switchToFrame(int frameName) {
		try {
			getWebDriver().switchTo().frame(frameName);
			return true;
		} catch (Exception e) {
			LogUtil.infoLog("switchToFrame", frameName + " TO FRAME FAILED" + e.getStackTrace());
			return false;
		}
	}

	public boolean switchTodefaultContent() {
		try {
			getWebDriver().switchTo().defaultContent();

			return true;
		} catch (Exception e) {
			LogUtil.infoLog("switchToFrame", " default TO FRAME FAILED" + e.getStackTrace());
			return false;
		}
	}

	public boolean clickAndHold(By locator1, By locator2) {
		boolean result = false;
		try {
			WebElement element1 = getWebDriver().findElement(locator1);
			WebElement element2 = getWebDriver().findElement(locator2);
			Actions action = new Actions(getWebDriver());
			action.clickAndHold(element1).moveToElement(element2).release().build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean rightClick(By locator1) {
		boolean result = false;
		try {
			WebElement element1 = getWebDriver().findElement(locator1);
			Actions action = new Actions(getWebDriver());
			action.contextClick(element1);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean rightClickJS(By locator1) {
		boolean result = false;
		try {
			WebElement element = getWebDriver().findElement(locator1);
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

			String javaScript = "var evt = document.createEvent('MouseEvents');" + "var RIGHT_CLICK_BUTTON_CODE = 2;"
					+ "evt.initMouseEvent('contextmenu', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, RIGHT_CLICK_BUTTON_CODE, null);"
					+ "arguments[0].dispatchEvent(evt)";

			js.executeScript(javaScript, element);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean rightClick1(By locator1) {
		boolean result = false;
		try {
			WebElement element = getWebDriver().findElement(locator1);
			Actions oAction = new Actions(getWebDriver());
			oAction.moveToElement(element);
			oAction.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean enter99Number(By locator1) {
		boolean result = false;
		try {
			WebElement element = getWebDriver().findElement(locator1);
			Actions oAction = new Actions(getWebDriver());
			oAction.click(element);
			oAction.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.NUMPAD9).sendKeys(Keys.NUMPAD9).build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean enterNumber(By locator, String data) {
		boolean result = false;
		try {
			WebElement element = getWebDriver().findElement(locator);
			Actions oAction = new Actions(getWebDriver());
			oAction.click(element);
			oAction.sendKeys(Keys.BACK_SPACE).sendKeys(data).build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickAndHold2(By locator1, By locator2) {
		boolean result = false;
		try {
			WebElement element1 = getWebDriver().findElement(locator1);
			WebElement element2 = getWebDriver().findElement(locator2);
			Actions action = new Actions(getWebDriver());
			action.clickAndHold(element1).moveToElement(element2).release().build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean dragAndDrop(By locator1, By locator2) {
		boolean result = false;
		try {
			WebElement element1 = getWebDriver().findElement(locator1);
			WebElement element2 = getWebDriver().findElement(locator2);
			Actions action = new Actions(getWebDriver());
			action.dragAndDrop(element1, element2).build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean dragAndDropBy(By locator1, By locator2) {
		boolean result = false;
		try {
			WebElement element1 = getWebDriver().findElement(locator1);
			WebElement element2 = getWebDriver().findElement(locator2);
			Point classname = element2.getLocation();
			int xcordi = classname.getX();
			System.out.println("Element's Position from left side" + xcordi + " pixels.");
			int ycordi = classname.getY();
			System.out.println("Element's Position from top" + ycordi + " pixels.");

			Actions action = new Actions(getWebDriver());
			action.dragAndDropBy(element1, xcordi, ycordi).build().perform();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public int getCoordinates(By locator) throws Exception {
		// Locate element for which you wants to retrieve x y coordinates.
		WebElement Image = getWebDriver().findElement(locator);
		// Used points class to get x and y coordinates of element.
		Point classname = Image.getLocation();
		int xcordi = classname.getX();
		System.out.println("Element's Position from left side" + xcordi + " pixels.");
		int ycordi = classname.getY();
		System.out.println("Element's Position from top" + ycordi + " pixels.");

		return xcordi + ycordi;

	}

	public int getElementSize(By locator) throws Exception {
		// Locate element for which you wants to get height and width.
		WebElement Image = getWebDriver().findElement(locator);

		// Get width of element.
		int ImageWidth = Image.getSize().getWidth();
		System.out.println("Image width Is " + ImageWidth + " pixels");

		// Get height of element.
		int ImageHeight = Image.getSize().getHeight();
		System.out.println("Image height Is " + ImageHeight + " pixels");
		return ImageWidth + ImageHeight;
	}

	public int getElementXSize(By locator) throws Exception {
		// Locate element for which you wants to get height and width.
		WebElement Image = getWebDriver().findElement(locator);

		// Get width of element.
		int ImageWidth = Image.getSize().getWidth();
		System.out.println("Image width Is " + ImageWidth + " pixels");

		return ImageWidth;
	}

	public int getElementYSize(By locator) throws Exception {
		// Locate element for which you wants to get height and width.
		WebElement Image = getWebDriver().findElement(locator);

		// Get height of element.
		int ImageHeight = Image.getSize().getHeight();
		System.out.println("Image height Is " + ImageHeight + " pixels");
		return ImageHeight;
	}

	public String getRandomString(String data) {
		// String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return data + saltStr;

	}

	public String getRandomMailinatorEmail(String data) {
		// String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return data + saltStr+"@mailinator.com";

	}

	public String getRandomString1(String data) {
		// String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr + data;

	}

	public int getRandomNumber(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max) + min;
	}

	public String todayDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(cal.getTime());
		return date;

	}

	public String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		String todatdate = sdf.format(date);
		return todatdate;
	}

	public String getTodayDate(String dateformat) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		String todatdate = sdf.format(date);
		return todatdate;
	}

	public String getYearDate(String dateformat) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.YEAR, 1); // Adds 365 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getTomorrowDate(String dateformat) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 1); // Adds 7 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getweekDate(String dateformat) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 7); // Adds 7 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getFutureDateTime(String dateformat, int days) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, days); // Adds 7 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getTodayDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String getTodayDateOnly() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		return dtf.format(today);
	}

	public String getFutureDate(int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate future = today.plus(value, ChronoUnit.DAYS);
		return dtf.format(future);
	}
	public String getFutureTime(int value) {
		DateFormat dateFormat = new SimpleDateFormat("h:mm");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, value);		
		return dateFormat.format(cal.getTime());
	}

	public String getPastDate(int value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate past = today.minusDays(value);
		return dtf.format(past);
	}

	public String getWeekDate(String dateformat) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 7); // Adds 7 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getTwoWeekDate(String dateformat) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 14); // Adds 4 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getTenDaysDate(String dateformat) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 10); // Adds 4 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		String date = format1.format(c.getTime());
		return date;
	}

	public String getTime(String dateformat) {
		DateFormat dateFormat = new SimpleDateFormat(dateformat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		String currentMonth = new SimpleDateFormat("MMM").format(cal.getTime());
		// System.out.println("currentMonth------------------"+currentMonth);
		return currentMonth;
	}

	public String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		String currentyear = new SimpleDateFormat("YYYY").format(cal.getTime());
		// System.out.println("currentyear"+currentyear);
		return currentyear;
	}

	public String getDate() {
		Calendar cal = Calendar.getInstance();
		String curerntdate = new SimpleDateFormat("dd").format(cal.getTime());
		// System.out.println(curerntdate);
		return curerntdate;
	}

	public String getRandomEmail(String data) {
		// String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return data + saltStr + getRandomNumber(100, 99999) + "@malinator.com";

	}

	public boolean waitForElement(By locator, int maxtime) {
		int time = 0;
		boolean status = false;
		while (time < maxtime) {
			status = getWebDriver().findElements(locator).size() > 0;
			if (status) {
				status = true;
				break;
			}
			time = time + 2;
			pause(2000);
		}
		return status;
	}

	public boolean waitForElement(By locator) {
		int time = 0;
		boolean status = false;
		while (time < 15) {
			status = getWebDriver().findElements(locator).size() > 0;
			if (status) {
				status = true;
				highLightElement(locator);
				break;
			}
			time = time + 1;
			pause(1000);
		}
		return status;
	}
	public boolean waitForElement_need(By locator) {
		int time = 0;
		boolean status = false;
		while (time < 8) {
			status = getWebDriver().findElements(locator).size() > 0;
			if (status) {
				status = true;
				highLightElement(locator);
				break;
			}
			time = time + 1;
			pause(1000);
		}
		return status;
	}

	/*
	 * public static void alertAccept() { driver.switchTo().alert().accept();
	 * LogUtil.infoLog(KeywordUtil.class, "Alert accepted");
	 * 
	 * } public static void alertDismiss() { driver.switchTo().alert().dismiss();
	 * LogUtil.infoLog(KeywordUtil.class, "Alert dismissed"); }
	 */
	public String alertText() {
		String text = getWebDriver().switchTo().alert().getText();
		LogUtil.infoLog(this.getClass(), text);
		return text;
	}

//	public static String readTestFromPDF(String path) {
//		// PDFReader reader = new PDFReader(new File("D:\\enoval_20170620132340.pdf"));
//		PDFReader reader = null;
//		String text = null;
//		try {
//			reader = new PDFReader(new File(path));
//			reader.open(); // open the file.
//			int pages = reader.getNumberOfPages();
//			for (int i = 0; i < pages; i++) {
//				text = reader.extractTextFromPage(i);
//				// System.out.println("Page " + i + ": " + text);
//			}
//			// perform other operations on pages.
//			reader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return text;
//	}

	public static boolean deleteFile(String path) {
		try {

			File file = new File(path);

			if (file.exists()) {
				file.delete();
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return true;
	}

	public void scrollTo(By locator) {

		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();",
				getWebDriver().findElement(locator));
	}
public void scrollToup()
{
	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
}
	public void scrollToBottom() {
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollingByCoordinatesofAPage(int x, int y) {
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(" + x + "," + y + ")", "");
	}

	public void scrollingByCoordinatesofAPage1(int x, int y) {
		((JavascriptExecutor) getWebDriver()).executeScript("scroll(" + x + "," + y + ");");
	}

	/* To click a certain Web Element using DOM/ JavaScript Executor */
	public void JSclick(WebElement element) {
		((JavascriptExecutor) getWebDriver()).executeScript("return arguments[0].click();", element);
	}

	/* To Type at the specified location */
	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	/* To Clear the content in the input location */
	public void clear(WebElement element) {
		element.clear();
	}
	public void clear_locator(By locator)
	{
		WebElement element = getWebElement(locator);
		element.clear();
	}

	/* To Drag and Drop from Source Locator to Destination Locator */
	public void dragandDrop(WebElement Source, WebElement Destination) {
		ac = new Actions(getWebDriver());
		ac.dragAndDrop(Source, Destination);
	}

	/*
	 * To Drag from the given WebElement Location and Drop at the given WebElement
	 * location
	 */
	public void dragandDropTo(WebElement Source, int XOffset, int YOffset) throws Exception {
		ac = new Actions(getWebDriver());
		ac.dragAndDropBy(Source, XOffset, YOffset);
	}

	/* To Open a Page in New Tab */
	public void rightClick(WebElement element) {
		ac = new Actions(getWebDriver());
		ac.contextClick(element);
		ac.build().perform();
	}

	/* To Close Current Tab */
	public void closeCurrentTab() {
		getWebDriver().close();
	}

	/* To Perform Click and Hold Action */
	public void clickAndHold(WebElement element) {
		ac = new Actions(getWebDriver());
		ac.clickAndHold(element);
		ac.build().perform();
	}

	/* To Perform Click and Hold Action */
	public void doubleClick(WebElement element) {
		ac = new Actions(getWebDriver());
		ac.doubleClick(element);
		ac.build().perform();
	}

	/* To Switch To Frame By Index */
	public void switchToFrameByIndex(int index) throws Exception {
		getWebDriver().switchTo().frame(index);
	}

	/* To Switch To Frame By Frame Name */
	public void switchToFrameByFrameName(String frameName) throws Exception {
		getWebDriver().switchTo().frame(frameName);
	}

	/* To Switch To Frame By Web Element */
	public void switchToFrameByWebElement(WebElement element) throws Exception {
		getWebDriver().switchTo().frame(element);
	}

	/* To Switch out of a Frame */
	public void switchOutOfFrame(){
		getWebDriver().switchTo().defaultContent();
	}

	/* To Get Tooltip Text */
	public String getTooltipText(WebElement element) {
		String tooltipText = element.getAttribute("title").trim();
		return tooltipText;
	}
	/* To Get Value Text */
	public String getValueText(WebElement element) {
		String value = element.getAttribute("value");
		return value;
	}

	/* To Close all Tabs/Windows except the First Tab */
	public void closeAllTabsExceptFirst() {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		for (int i = 1; i < tabs.size(); i++) {
			getWebDriver().switchTo().window(tabs.get(i));
			getWebDriver().close();
		}
		getWebDriver().switchTo().window(tabs.get(0));
	}

	/* To Print all the Windows */
	public void printAllTheWindows() {
		ArrayList<String> al = new ArrayList<String>(getWebDriver().getWindowHandles());
		for (String window : al) {
			System.out.println(window);
		}
	}

	public void navigateToUrl(String url) {

		getWebDriver().navigate().to(url);
	}

	public String getCurrentUrl() {
		return getWebDriver().getCurrentUrl();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/*
	 * Common functions for date time and file IO
	 * ===========================================================
	 */
	/**
	 * @return
	 */
	public String getDateTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 
	 */
	public void renameFile() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		String timeStamp = dateFormat.format(date);
		try {
			File oldFile = new File(System.getProperty(USERDIR) + ConfigReader.getValue("testResultExcelPath"));
			String newFilePath = oldFile.getAbsolutePath().replace(oldFile.getName(), "") + "\\ReportHistory\\"
					+ timeStamp + "-TestResult.xls";
			File newFile = new File(newFilePath);

			FileUtils.copyFile(oldFile, newFile);
			LogUtil.infoLog(BaseMethod.class, "History File successfully created... ");

		} catch (IOException e) {
			LogUtil.errorLog(BaseMethod.class, "Exception caught", e);
		}
	}

	/**
	 * 
	 */
	public void checkFileOpen() {
		String fileName = System.getProperty(USERDIR) + "\\ExecutionReports\\ExcelReport\\TestResult.xls";
		File file = new File(fileName);
		File sameFileName = new File(fileName);

		if (file.renameTo(sameFileName)) {

			// LogUtil.infoLog(testCaseID, "**********TestResult.xls is closed**********");
		} else {
			JOptionPane.showMessageDialog(null, "TestResult.xls is opened");
			Thread.currentThread().stop();
		}
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String createZipFile() throws IOException {
		RESULT_FOLDER_NAME = RESULT_FOLDER_NAME.replace("\\", "/");
		String outputFile = RESULT_FOLDER_NAME + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(RESULT_FOLDER_NAME, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	/**
	 * @param directoryPath
	 * @param zos
	 * @throws IOException
	 */
	public void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement : new File(directoryPath).list()) {
			String dirElementPath = directoryPath + "/" + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(RESULT_FOLDER_NAME + "/", ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}
	}

	/**
	 * @param dir
	 */
	public void delDirectory(File dir) {
		File[] currList;
		Stack<File> stack = new Stack<File>();
		stack.push(dir);
		while (!stack.isEmpty()) {
			if (stack.lastElement().isDirectory()) {
				currList = stack.lastElement().listFiles();
				if (currList.length > 0) {
					for (File curr : currList) {
						stack.push(curr);
					}
				} else {
					stack.pop().delete();
				}
			} else {
				stack.pop().delete();
			}
		}
		if (new File(System.getProperty(USERDIR) + "/ExecutionReports/ExecutionReports.zip").exists()) {
			delDirectory(new File(System.getProperty(USERDIR) + "/ExecutionReports/ExecutionReports.zip"));
		}
	}

	/**
	 * @param driver
	 * @param testCaseID
	 * @return
	 * @throws IOException
	 */
	public String takeScreenshot(WebDriver driver, String testCaseID) throws IOException {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = dateFormat.format(date);
		timeStamp = timeStamp + "_";

		String path = USERDIR + ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";
		// Screenshot screenshot = new AShot().shootingStrategy(new
		// ViewportPastingStrategy(100)).takeScreenshot(driver);

		Screenshot screenshot = new AShot().takeScreenshot(driver);
		File src = new File(path);
		LogUtil.infoLog(BaseMethod.class, "Screenshot image path: " + src.getPath());
		ImageIO.write(screenshot.getImage(), "PNG", src);
		return ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";
	}

	public String takeScreenshotWebElement(WebElement element, String testCaseID) throws IOException {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = dateFormat.format(date);
		timeStamp = timeStamp + "_";
		String path = System.getProperty("user.dir") + "\\ExecutionReports\\HtmlReport\\"
				+ ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(getWebDriver(),
				element);
		File src = new File(path);
		LogUtil.infoLog(BaseMethod.class, "Screenshot image path: " + src.getPath());
		ImageIO.write(screenshot.getImage(), "PNG", src);
		return ConfigReader.getValue("screenshotPath") + "\\" + timeStamp + testCaseID + ".jpg";

	}

	public boolean isWebElementPresent(By locator, int timeOut) {
		Boolean flag = false;
		int time = 0;
		while (time < timeOut) {
			List<WebElement> ele = getWebDriver().findElements(locator);

			if (ele.size() > 0) {
				flag = true;
				break;
			}
			pause(1000);
			time = time + 2;

		}

		return flag;

	}
public void printLog(String logstep)
{
	logStep(logstep);
}
	public boolean isWebElementPresent(By locator) {
		Boolean flag = false;
		int time = 0;
		while (time < 20) {
			List<WebElement> ele = getWebDriver().findElements(locator);

			if (ele.size() > 0) {
				flag = true;
				break;
			}
			pause(1000);
			time = time + 2;

		}

		return flag;

	}

	public boolean verifyWebElementPresent(By locator, String logstep) {

		if (isWebElementPresent(locator)) {

			highLightElement(locator);
			logStep(logstep );
			return true;
		} else {
			logStepFail(logstep + "  --Fail");
			Assert.fail();
			return false;
		}

	}
	public boolean isWebElementPresent(By locator,String logstep) {
		boolean status=waitForElement(locator);
		if (status) {
			
			logStepPass(logstep);
		//	LogUtil.infoLog(KeywordUtil.class, logstep);
			return true;
		}else{
			logStepFail(logstep+"  --Fail");
		//LogUtil.infoLog(KeywordUtil.class, logstep+"  --Fail");
		return false;
		}
	}
	public boolean isWebElementDisplayed(WebElement element,String logstep) {
		boolean status =false;
		try {
		 status=element.isDisplayed();
		
		if (status) {
			
			logStepPass(logstep);
		//	LogUtil.infoLog(KeywordUtil.class, logstep);
			
		}
		return true;
		}
		catch(Exception e){
			logStepFail(logstep+"  --Fail");
			Assert.fail();
			return false;
		}
	}
	//To Upload a File using JAVA AWT ROBOT 
	public void fileUpload(String FileToUpload) 
	{
		
		StringSelection filetocopy = new StringSelection(FileToUpload);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filetocopy, null);
		pause(1000);
		try {
			re = new Robot();

			re.keyPress(KeyEvent.VK_CONTROL);
			re.keyPress(KeyEvent.VK_V);
			re.keyRelease(KeyEvent.VK_V);
			re.keyRelease(KeyEvent.VK_CONTROL);
			re.keyPress(KeyEvent.VK_ENTER);
			re.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}



public boolean isWebElementNotPresent2(By locator,String logstep) {
		boolean status=waitForElement(locator,5);
		if (!status) {
			
			logStepPass(logstep);
			return true;
		}else{
			logStepFail(logstep);
			Assert.fail();
		return false;
		}
	}

//Random text1
		public String getRandomString1() {
		    byte[] array = new byte[7]; // length is bounded by 7
		    new Random().nextBytes(array);
		    String generatedString = new String(array, Charset.forName("UTF-8"));
		 
		   return generatedString;
		}
		//Random text2
		public String getRandomString2() {
			  
		    int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		    return generatedString;
		}
		//Random text3
		public String getRandomString3() {
			  
		    int length = 10;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		    return generatedString;
		}
		//Random text4
		public String getRandomString4() {
			String generatedString =  RandomStringUtils.randomAlphabetic(10);
			return generatedString;
		}
		//Random text5
		public String getRandomString5() {
			String generatedString = RandomStringUtils.randomAlphanumeric(10);
			return generatedString;
		}



 public void inputText2(By locator, String data, String logStep) {
	
	WebElement elm = getWebElement(locator);
	
	if (elm == null) {
		logStepFail(logStep+"  --Fail");		
	} else {
		//highlightElement(locator);
		elm.sendKeys(data);
		logStepPass(logStep );

	}
 }
	public void inputTextWithEnter(By locator, String data, String logStep) {
		
		WebElement elm = getWebElement(locator);
		
		if (elm == null) {
			logStepFail(logStep);		
		} else {
			//highlightElement(locator);
			elm.sendKeys(data);
			elm.sendKeys(Keys.ENTER);
			logStepPass(logStep );

		}
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public void attachScreen() {
		try
		{
			String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)getWebDriver()).
	                getScreenshotAs(OutputType.BASE64);
			ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot);
		}catch(Exception e)
		{	e.printStackTrace();
			
		}
	}
	
	
public void verifyImageExists(By locator, String data, String logStep) {
		
		WebElement elm = getWebElement(locator);
		
		if (elm == null) {
			logStepFail(logStep);		
		} else {
			//highlightElement(locator);
			Object result = ((JavascriptExecutor) getWebDriver()).executeScript(
					   "return arguments[0].complete && "+
					   "typeof arguments[0].naturalWidth != \"undefined\" && "+
					   "arguments[0].naturalWidth > 0", elm);

					    boolean loaded = false;
					    if (result instanceof Boolean) {
					      loaded = (Boolean) result;
					      System.out.println(loaded);
					    }
			logStepPass(logStep );

		}
	}
	
public void verifyImageExists(String CSVPath, String OutCSVPath, int editNumber) {
	BufferedReader reader;
	try {
	File file = new File(USERDIR+CSVPath);
    
	
		reader = new BufferedReader(new FileReader(file));
	
    String line = "", oldtext = "";
    while((line = reader.readLine()) != null)
        {
    	
        oldtext += line + "\r\n";
    }
    reader.close();
    // replace a word in a file
    //String newtext = oldtext.replaceAll("drink", "Love");
   
    //To replace a line in a file
    String newtext = oldtext.replaceAll("104", "105");
   
    FileWriter writer = new FileWriter(USERDIR+CSVPath+105+".csv");
    writer.write(newtext);
    writer.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void updateCSV(String fileToUpdate, String replace,
	    int row, int col) throws IOException {

	File inputFile = new File(fileToUpdate);

	// Read existing file 
	CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
	List<String[]> csvBody = reader.readAll();
	// get CSV row column  and replace with by using row and column
	csvBody.get(row)[col] = replace;
	reader.close();

	// Write to CSV file which is open
	CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
	writer.writeAll(csvBody);
	writer.flush();
	writer.close();
	}

public static String getCSVCell(String fileToUpdate, int row, int col) throws IOException {

	File inputFile = new File(fileToUpdate);

	// Read existing file 
	CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
	List<String[]> csvBody = reader.readAll();
	// get CSV row column  and replace with by using row and column
	String value= csvBody.get(row)[col];
	reader.close();
	value=value.substring(5);
	return value;
	
	}

//Monica

public void ifAlertDismiss()
{
	pause(3000);
//	WebDriverWait w = new WebDriverWait(getWebDriver(), 5);
	
//	if(ele.isDisplayed())
//	{
//		ele.click();  
//	}
//	else {
//		System.out.println("Element was not present");
	
	
	
	try{
		 //Since, no exception, so element is present
		WebElement ele = getWebDriver().findElement(By.xpath("//div[@aria-label='Close survey']"));
		 System.out.println("survey present");
		 ele.click(); 
		}
		catch(NoSuchElementException e){
		 //Element is not present
		 System.out.println("Element not present");
		}

}

}
