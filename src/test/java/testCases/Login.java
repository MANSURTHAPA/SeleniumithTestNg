package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import POM.LoginPom;
import POM.Configuration;

public class Login {
	LoginPom pom;
	Configuration conf=new Configuration();
	public WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = Configuration.browser();
		driver.get(Configuration.WebUrl());
		Thread.sleep(1000);
		
	}
	@AfterMethod
	public void end() {
		driver.quit();
	}
	

	@Test(priority = 1, description = "This is test for successfull Login")
	public void testSuccessfullLogin() {
		pom = new LoginPom(driver);
		pom.clickSignIn();
		pom.LoginToWeb(conf.email,conf.password);
		Assert.assertTrue(pom.getUsername().contains("Vsoft"));

	}
	
	@Test(priority=2,description="This is test for invalid email")
	public void invalidEmail() {
		pom = new LoginPom(driver);
		pom.clickSignIn();
		pom.LoginToWeb(conf.email+"jsadh", conf.password);
		String expected="Authentication failed.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]")).getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test(priority=3,description="This is test for invalid password")
	public void invalidPassword() {
		pom = new LoginPom(driver);
		pom.clickSignIn();
		pom.LoginToWeb(conf.email, conf.password+"asd");
		String expected="Authentication failed.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]")).getText();
		Assert.assertEquals(actual, expected);
	}
	@Test(priority=4,description="This is test for empty email")
	public void emptyEmail() {
		pom = new LoginPom(driver);
		pom.clickSignIn();
		pom.LoginToWeb(" ",conf.password);
		String expected="An email address required.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'An email address required.')]")).getText();
		Assert.assertEquals(actual, expected);
	}
	@Test(priority=5,description="This is test for empty password")
	public void emptyPassword() {
		pom = new LoginPom(driver);
		pom.clickSignIn();
		pom.LoginToWeb(conf.email, " ");
		String expected="Password is required.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'Password is required.')]")).getText();
		Assert.assertEquals(actual, expected);
	}


}
