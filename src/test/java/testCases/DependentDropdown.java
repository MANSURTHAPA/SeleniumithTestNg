package testCases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POM.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

import POM.DependentdropdownPOM;

public class DependentDropdown {
	public static WebDriver driver;
	DependentdropdownPOM depPOm;

	@BeforeMethod
	public void startUp() throws InterruptedException {
		driver = Configuration.browser();
		driver.get(Configuration.file());
		Thread.sleep(1000);
	}

	@AfterMethod
	public void end() {
		driver.quit();
	}

	@Test
	public void verifyCascadingDropdown()throws StreamReadException, DatabindException, IOException, InterruptedException {
	
		depPOm = new DependentdropdownPOM(driver);		
		Assert.assertEquals(depPOm.actualsubList(), depPOm.expectedsubList());
	}
	@Test
	public void FrontEndVerification()throws InterruptedException, StreamReadException, DatabindException, IOException {
		depPOm = new DependentdropdownPOM(driver);
		depPOm.selectSub().selectByValue("Back-end");
		Thread.sleep(5000);

		Assert.assertEquals(depPOm.actualTopList(), depPOm.expectedTopList());

	}
}
