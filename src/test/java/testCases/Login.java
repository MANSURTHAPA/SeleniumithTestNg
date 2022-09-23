package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import POM.LoginPom;
import POM.Configuration;

public class Login {
	LoginPom pom;
	public WebDriver driver;

	@BeforeTest
	public void setup() {
		driver = Configuration.browser();
		driver.get(Configuration.WebUrl());
	}

	@Test(priority = 1, description = "This is test for successfull Login")
	public void testSuccessfullLogin() {
		pom = new LoginPom(driver);
		pom.clickSignIn();
		pom.LoginToWeb("mansur@test.com", "PKR@PKR");
		Assert.assertTrue(pom.getUsername().contains("Vsoft"));

	}

}
