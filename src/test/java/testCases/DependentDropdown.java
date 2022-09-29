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
		depPOm = new DependentdropdownPOM(driver);
		Thread.sleep(1000);
	}

	@AfterMethod
	public void end() {
		driver.quit();
	}

	@Test
	public void verifySubjectList()throws StreamReadException, DatabindException, IOException, InterruptedException {
			
		Assert.assertEquals(depPOm.actualsubList(), depPOm.expectedsubList());
	}
	@Test
	public void verifyTopicList()throws InterruptedException, StreamReadException, DatabindException, IOException {
		
		depPOm.selectSub().selectByValue("Back-end");
		Thread.sleep(5000);
		//expected function sending subject 
		Assert.assertEquals(depPOm.actualTopList(), depPOm.expectedTopList("Back-end"));

	}
	@Test
	public void verifyChapterList() throws InterruptedException, StreamReadException, DatabindException, IOException {
		//these two functions select the required subject and topic
		depPOm.selectSub().selectByValue("Front-end");
		depPOm.selectTopic().selectByValue("HTML");
		Thread.sleep(5000);
		//expected function is sending the subject and topic to get chapter list
		Assert.assertEquals(depPOm.actualChapterList(), depPOm.expectedChapterList("Front-end","HTML"));
		
	}
}
