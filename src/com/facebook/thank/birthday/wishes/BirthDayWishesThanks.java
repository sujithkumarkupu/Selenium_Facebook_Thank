package com.facebook.thank.birthday.wishes;


import org.apache.log4j.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.BrowserConfigurationOptions;
import com.thoughtworks.selenium.DefaultSelenium;

public class BirthDayWishesThanks {
	
	public SeleniumServer seleniumserver;
	DefaultSelenium selenium;
	
	private static Logger logger = Logger.getLogger(BirthDayWishesThanks.class);
	
	@Before
	public void setUp() throws Exception {
		
		RemoteControlConfiguration Cer = new RemoteControlConfiguration();
		Cer.setTrustAllSSLCertificates(true);
		seleniumserver=new SeleniumServer(Cer);
		seleniumserver.boot();
		seleniumserver.start();
		BrowserConfigurationOptions bco = new BrowserConfigurationOptions();
		selenium = new DefaultSelenium("localhost", 4444, "*firefox","http://");
		selenium.start(bco.setCommandLineFlags("--disable-web-security"));
		selenium.setSpeed("10");	        
	}

	@Test
	public void thank() throws Exception {
		logger.info(" ######################## ------------- Start One Way Script ------------------ ####################### ");
    	selenium.deleteAllVisibleCookies();
    	selenium.setTimeout("60000");
		selenium.open("www.facebook.com");
		selenium.windowMaximize();
		selenium.windowFocus();
		selenium.setTimeout("60000");
		
    	waitForElement("xpath=//div[@id='blueBar']/div/div/a/i");
		
		selenium.type("id=email","sujith010987@gmail.com");
		selenium.type("id=pass","sujithkumarkupunarapu");
		selenium.click("xpath=//label[@id='loginbutton']/input");

		waitForElement("xpath=//div[@id='pagelet_ego_pane_w']/div/div/div/div/div[2]/h5");
		
		selenium.click("xpath=//ul[@id='pageNav']/li/a/span");
		
		Thread.sleep(90000);
		
		for(int i=1;i<50;i++){
			System.out.println(i);
			Thread.sleep(3000);
			
			boolean element_present = checkElementPresent(i, "xpath=//li[", "]/div[2]/div[3]/div/div/span");
			
			boolean birthday_wish_present = false;
			if(selenium.isElementPresent("xpath=//li["+i+"]/div[2]/div[3]/div/div/span")){
				String wishes = selenium.getText("xpath=//li["+i+"]/div[2]/div[3]/div/div/span");
				if(wishes.toLowerCase().contains(("hap").toLowerCase())){
				birthday_wish_present = true;
				}
			}
			
			if(element_present && birthday_wish_present){
				
				boolean like_link_present = checkElementPresent(i, "xpath=//li[", "]/div[2]/div[4]/form/div/div/span/button");
				/*if(like_link_present){
					selenium.click("//li["+i+"]/div[2]/div[4]/form/div/div/span/button");
				}*/
				
				boolean comment_link_present = checkElementPresent(i, "xpath=//li[", "]/div[2]/div[4]/form/div/div/span[2]/label/input");
				/*if(comment_link_present){
					selenium.click("//li["+i+"]/div[2]/div[4]/form/div/div/span[2]/label/input");
				}*/
				
				
				boolean comment_box_present = checkElementPresent(i, "//li[", "]/div[2]/div[4]/form/div[2]/ul/li[4]/div/div/div/div/div[2]/div/div/textarea");
				/*if(comment_box_present){
					selenium.type("//li["+i+"]/div[2]/div[4]/form/div[2]/ul/li[4]/div/div/div/div/div[2]/div/div/textarea","hey thank you :)");
				}*/
				
				boolean comment_it_present = checkElementPresent(i, "//li[", "]/div[2]/div[4]/form/div[2]/ul/li[4]/div/div/label/input");
				/*if(comment_it_present){
					selenium.click("//li["+i+"]/div[2]/div[4]/form/div[2]/ul/li[4]/div/div/label/input");
				}*/
				
				System.out.println("like_link_present...............comment_link_present..........comment_box_present...........comment_it_present");
			}
		}
		
		
		
	}

	private boolean checkElementPresent(int i, String locatorLeft, String locatorRight) {
		boolean element_present = false;
		String loc = locatorLeft+i+locatorRight;
		
		if(selenium.isElementPresent(loc)){
			element_present = true;
		}
		return element_present;
	}

	private void waitForElement(String locator) throws InterruptedException {
		boolean profilePageScreen = false;
		for (int second = 0; second < 240; second++) {
			try {
				if ((selenium.isElementPresent(locator))) {
					profilePageScreen = true;
					break;
				}
			}
			catch (Exception ignore) {
			}
			
			Thread.sleep(500);
		}
		assertTrue(profilePageScreen);
	}
	
	 @After
	 public void tearDown(){
         selenium.close();
         selenium.stop();
    } 
    
}
