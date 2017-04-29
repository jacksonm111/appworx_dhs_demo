/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import java.io.IOException;
import java.net.MalformedURLException;
//import java.net.SocketException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.*;
import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 *
 * @author Appwo   rks Admin
 */
public class CalculatorUIT {

    public static String NodeURL = "http://10.0.1.55:5555"; 
    public static String TargetURL = "http://10.0.1.53:8080/Calculator";

    public CalculatorUIT() {
    }

    @BeforeClass
    public static void setUpClass() {
        //  System.out.println("setup class");
        String seleniumServer = System.getProperty("selenium.grid.url");
        if (seleniumServer != null) {
            NodeURL = seleniumServer + ":5555/wd/hub";
            System.out.println("new address of selenium grid...: "+ NodeURL);
        }else{
        System.out.println("Selenium running on : " + NodeURL);
        }
        /**
         * *****      *****
         */
        // System.out.println("setup class");
        String testURL = System.getProperty("test.url");
        if (testURL != null) {
            TargetURL = testURL;
            System.out.println("new address for testing...: " + TargetURL);
        } else {
            System.out.println("Selenium running on : " + TargetURL);
        }

    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tear down class");

    }

  /*  public void testGoogleChromeBrowser() throws MalformedURLException, Exception, IOException {
        System.out.println("Testing chrome");
        DesiredCapabilities browser = DesiredCapabilities.chrome();
        if (browser != null) {
            //for(int i=0;i<3;i++){
            testCodesCrud(browser);
            //  }
        } else {
            Assert.assertTrue("Could not start Google Chrome Browser", false);
        }
    }/*

/*    public void testInternetExplorerBrowser() {
        String testName = "Testing IE browser";
        System.out.println(testName);
        DesiredCapabilities browser = DesiredCapabilities.internetExplorer();
        try {
            if (browser != null) {
                for (int i = 0; i < 3; i++) {
                    testCodesCrud(browser);
                }
            } else {
                Assert.assertTrue("Could not start IE Browser", false);
            }
        } catch (SocketException se) {
            se.printStackTrace();;
            // System.out.println(testName + ":" + se.getMessage());
            Assert.assertTrue("Error Connecting to UI Test Server: " + se.getMessage(), false);
        } catch (java.lang.IllegalStateException ise) {
            //System.out.println(testName + ":" + ise.getMessage());
            ise.printStackTrace();
            Assert.assertTrue(ise.getMessage(), false);
        } catch (Exception e) {
            // System.out.println(testName + ":" + e.getMessage());
            e.printStackTrace();;
            Assert.assertTrue(e.getMessage(), false);
        } finally {
            ;// noop
        }
    }/*

  /*  public void testOperaBrowser() {
        String testName = "Testing opera browser";
        System.out.println(testName);
        DesiredCapabilities browser = DesiredCapabilities.opera();
        try {
            if (browser != null) {
                ////  for(int i=0;i<3;i++){
                testCodesCrud(browser);
                //  }
            } else {
                Assert.assertTrue("Could not start Opera Browser", false);
            }
        } catch (SocketException se) {
            se.printStackTrace();
//            System.out.println(testName + ":" + se.getMessage());
            Assert.assertTrue(se.getMessage(), false);
        } catch (Exception e) {
            e.printStackTrace();;
//            System.out.println(testName + ":" + e.getMessage());
            Assert.assertTrue(e.getMessage(), false);
        } finally {
            ;// noop
        }
    }/*

    /* @Test
     public void testMacOSSafari() throws MalformedURLException, IOException {
     String testName = "Testing Mac OS Safari browser";
     System.out.println(testName);
     DesiredCapabilities browser = DesiredCapabilities.safari();
     try {
     testCodesCrud(browser);
     } catch (SocketException se) {
     System.out.println(testName + ":" + se.getMessage());
     Assert.assertTrue(false);
     } catch (Exception e) {
     System.out.println(testName + ":" + e.getMessage());
     Assert.assertTrue(false);
     } finally {
     ;// noop
     }
     }*/
    @Test
    public void testFirefox() throws MalformedURLException, Exception, IOException {

    	System.out.println("test firefox");
        DesiredCapabilities browser = DesiredCapabilities.firefox();
        if (browser != null) {
            //for(int i=0;i<3;i++){
            testCodesCrud(browser);
            //  }
        } else {
            Assert.assertTrue("Could not start Firefox Browser: ", false);
        }

    }

    public void testCodesCrud(DesiredCapabilities browser) throws Exception {
    	
    	ExtentReports logger = new ExtentReports("target//advancedReport.html", false);
		ExtentTest test1 = logger.startTest("Verify Target String");
		ExtentTest test2 = logger.startTest("Verify Calculation Result");
		
    	WebDriver driver = null;
        System.out.println("Attempt connect to Selenium Node @ " + NodeURL);
	File pathToBinary = new File("/opt/firefox46/firefox/firefox-bin");
	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	FirefoxProfile firefoxProfile = new FirefoxProfile();       
	driver = new FirefoxDriver(ffBinary,firefoxProfile);
        try {
            System.out.println("attempt connect to target: " + TargetURL);
            driver.get(TargetURL);
            //input a addition 12+8 example in the calculator web-application;
            driver.findElement(By.xpath("(//input[@id='buttonRow'])[2]")).click();
            driver.findElement(By.xpath("(//input[@id='buttonRow'])[3]")).click();
            driver.findElement(By.xpath("(//input[@id='buttonRow'])[12]")).click();
            driver.findElement(By.xpath("(//input[@id='buttonRow'])[10]")).click();
            driver.findElement(By.xpath("(//input[@id='buttonRow'])[15]")).click();
            
            
            //search target string value
            String targetStr = "Welcome to the Demo";
            
            
            //verify result
            boolean isFound = (driver.findElement(By.tagName("body")).getText()).contains(targetStr);
            boolean result = driver.findElement(By.name("display")).getAttribute("value").contains("84");
            		
            //verify title  
            if (isFound == true) {
				test1.log(LogStatus.PASS, "Title has been verified");
			}
			if (isFound == false) {
				test1.log(LogStatus.FAIL, "Title has NOT been verified");
			}
			
			
			//verify result     
			if (result == true) {
				test2.log(LogStatus.PASS, "The Calculation result was correct: [12 multiply by 7 gave output of 84]");
			} else {
				test2.log(LogStatus.FAIL, "The Calculation result was not correct [the title is[Welcome to the Demo!]");
				
			}
			logger.endTest(test1);
			logger.endTest(test2);
			logger.flush();
			
            System.out.println("look for= [" + targetStr + "]");
            System.out.println("did we find it? [" + isFound + "]");
            
            System.out.println("calculate 12 multiply by 7");
            System.out.println("Was the result 84 ? [" + result + "]");
            
            Assert.assertTrue(isFound);
            Assert.assertTrue(result);
            //  Assert.assertEquals("hell",driver.getTitle());
            //doTest(driver); 
            // rest of test commands come here
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e.getMessage(), false);
        } finally {
            if (driver != null) {
                 driver.quit();
            }
        }
    }

}
