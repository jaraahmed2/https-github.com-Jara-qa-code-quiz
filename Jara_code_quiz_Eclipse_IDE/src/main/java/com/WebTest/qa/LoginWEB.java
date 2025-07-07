package com.WebTest.qa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;

import com.adminpanel.qa.base.TestBase;

public class LoginWEB extends TestBase{
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/input[1]")
	WebElement username;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/input[2]")
	WebElement password;
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/button")
	WebElement loginBtn;
	
	
	public LoginWEB(){
		PageFactory.initElements(driver, this);
	}
	
	
	public LoginWEB create(String email_input, String password_input){

		username.sendKeys(email_input);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		password.sendKeys(password_input);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;js.executeScript("arguments[0].click();", loginBtn);
    	return new LoginWEB();
	}
}
