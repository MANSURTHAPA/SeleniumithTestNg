package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import POM.LoginPom;
import POM.Configuration;

public class Login {
	LoginPom pom;
	public WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver =Configuration.browser();
		driver.get(Configuration.WebUrl());
		pom = new LoginPom(driver);
		Thread.sleep(1000);
		
	}
	@AfterMethod
	public void end() {
		driver.quit();
	}
	

	@Test(priority = 1, description = "This is test for successfull Login")
	public void testSuccessfullLogin() {
		
		pom.clickSignIn();
		pom.LoginToWeb(pom.email,pom.pasword);
		Assert.assertTrue(pom.getUsername().contains("Test User Vsoft"));

	}
	
	@Test(priority=2,description="This is test for invalid email")
	public void invalidEmail() {
	
		pom.clickSignIn();
		pom.LoginToWeb(LoginPom.email+"jsadh", pom.pasword);
		String expected="Authentication failed.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]")).getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test(priority=3,description="This is test for invalid password")
	public void invalidPassword() {
		
		pom.clickSignIn();
		pom.LoginToWeb(pom.email, pom.pasword+"asd");
		String expected="Authentication failed.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]")).getText();
		Assert.assertEquals(actual, expected);
	}
	@Test(priority=4,description="This is test for empty email")
	public void emptyEmail() {
		
		pom.clickSignIn();
		pom.LoginToWeb(" ",pom.pasword);
		String expected="An email address required.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'An email address required.')]")).getText();
		Assert.assertEquals(actual, expected);
	}
	@Test(priority=5,description="This is test for empty password")
	public void emptyPassword() {
		
		pom.clickSignIn();
		pom.LoginToWeb(pom.email, " ");
		String expected="Password is required.";
		String actual=driver.findElement(By.xpath("//li[contains(text(),'Password is required.')]")).getText();
		Assert.assertEquals(actual, expected);
	}


}
