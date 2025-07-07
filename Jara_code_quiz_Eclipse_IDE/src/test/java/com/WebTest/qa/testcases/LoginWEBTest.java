package com.WebTest.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.WebTest.qa.LoginWEB;
import com.adminpanel.qa.base.TestBase;
import com.adminpanel.qa.util.LoggerLoad;
import com.adminpanel.qa.util.TestUtil;
import com.qa.ExtentReportListener.ExtentReporterNG;
import java.time.Duration; 
import org.openqa.selenium.support.ui.WebDriverWait;

@Listeners(ExtentReporterNG.class)
public class LoginWEBTest extends TestBase{
	

	LoginWEB loginPage;
	TestUtil testUtil;
	String sheetName = "login";
	
	
	public LoginWEBTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginWEB();
	}
	
	
	@DataProvider
	public Object[][] getValidusernameTestDataGoogleSheet() throws GeneralSecurityException, InvalidFormatException{
		Object data[][] = TestUtil.getTestDataFromGS("1sMiXCX6WXoaPHCL1aXKjJghukxbDack6LWBdKYRXXEE", "valid_username!A1:B");
		return data;
	}
	
	@DataProvider
	public Object[][] getInalidusernameTestDataGoogleSheet() throws GeneralSecurityException, InvalidFormatException{
		Object data[][] = TestUtil.getTestDataFromGS("1sMiXCX6WXoaPHCL1aXKjJghukxbDack6LWBdKYRXXEE", "invalid_username!A1:B");
		return data;
	}
	
	
	// Test data stored - https://docs.google.com/spreadsheets/d/1sMiXCX6WXoaPHCL1aXKjJghukxbDack6LWBdKYRXXEE/edit?gid=0#gid=0
	
	
	// valid credentials
	@Test(priority=1, dataProvider="getValidusernameTestDataGoogleSheet")
	public void create_valid_email(String email_input, String password_input) throws IOException{
	
		loginPage.create(email_input, password_input);
		
		WebElement errorMsg = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("html/body/div[1]/div/div[1]/div")
		        ));
		Assert.assertTrue(errorMsg.getText().contains("Hello undefined"));
	}
	
	
	// all invalid credentials and empty filed  
	@Test(priority=2, dataProvider="getInalidusernameTestDataGoogleSheet")
	public void create_invalid_email(String email_input, String password_input) throws IOException{

		loginPage.create(email_input, password_input);
	
		
		WebElement errorMsg = new WebDriverWait(driver, 10)
		        .until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("/html/body/div[1]/div/div[2]/div")
		        ));
		Assert.assertTrue(errorMsg.getText().contains("If you do not have an account, contact an admin"));
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
