package testCases;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import POM.Configuration;

public class Datepicker {
	WebDriver driver;

	@Test
	public void selectDate() throws InterruptedException {
		driver=Configuration.browser();
		driver.get("https://www.globalsqa.com/demo-site/datepicker/");
		driver.findElement(By.id("Simple Date Picker")).click();
		driver.switchTo().frame( driver.findElement( By.xpath( "//body/div[@id='wrapper']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[1]/iframe[1]" ) ) );
		driver.findElement(By.id("datepicker")).click();
	}
}
