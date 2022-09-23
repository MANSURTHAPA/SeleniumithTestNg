package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Configuration {

	public static WebDriver driver;
	public static String Url;

	public static WebDriver browser() {
		WebDriverManager.chromedriver().setup();
		return driver = new ChromeDriver();

	}

	public static String WebUrl() {
		Url = "http://automationpractice.com/index.php";
		return Url;
	}
}
