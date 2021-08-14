package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @Author Chandu
 * @Date 15-Nov-2018
 */

public class BrowserFactory extends InitMethod
{
	@SuppressWarnings("deprecation")
	static WebDriver createDriver(String browser, String url) throws Exception
	{
		WebDriver driver;

		DesiredCapabilities capabilities;
		switch(browser.toLowerCase())
		{
		case "chrome":
			Map<String,Object> prefs=new HashMap<String,Object>() ;
			prefs.put("profile.default_content_setting_values.notifications", 2);
			System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
			capabilities = new DesiredCapabilities();
			capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
			/*driver = new ChromeDriver(capabilities);*/
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("ignore-certificate-errors");
			options.addArguments("--disable-popup-blocking");//new add option to disable wootric rating pop up at 11/03/2021
			options.merge(capabilities);
	       /*	options.addArguments("user-data-dir=C:/Users/Chandu/AppData/Local/Google/Chrome/User Data");
	       	options.addArguments("disable-infobars"); // disabling infobars
	       	options.addArguments("--disable-extensions"); // disabling extensions
	        options.addArguments("--disable-gpu"); // applicable to windows os only
	        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
	        options.addArguments("--no-sandbox"); // Bypass OS security model
	        options.addArguments("--start-maximized");*/
			driver = new ChromeDriver(options);
			/*driver=new ChromeDriver();*/
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.get(url);
			break;
			
		case "chrome_headless":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");  
			chromeOptions.addArguments("--disable-gpu");  
			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.get(url);
			break;

		case  "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//	driver.get(url);
			break;

		case  "ie":
			System.setProperty("webdriver.ie.driver", "src/main/resources/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			//driver.get(url);
			break;	

		case  "edge":
			System.setProperty("webdriver.edge.driver", "src/main/resources/Drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(610, TimeUnit.SECONDS);
		//	driver.get(url);
			break;

		/*case  "unit":
			driver = new HtmlUnitDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//	driver.get(url);
			break;
*/
		case  "opera":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
			capabilities = DesiredCapabilities.opera();
			ChromeOptions optionsOpera = new ChromeOptions();
			optionsOpera.setBinary("C:/Program Files/Opera/launcher.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, optionsOpera);
			driver = new ChromeDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.get(url);
			break;

		default:
			throw new Exception("Please Provide a Valid Browser");
		}
		return driver;		
	}
}
