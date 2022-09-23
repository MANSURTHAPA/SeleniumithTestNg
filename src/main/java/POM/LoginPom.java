package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPom {

	public WebDriver driver;

	By username = By.id("email");
	By password = By.id("passwd");
	By login = By.name("SubmitLogin");
	By userTxt = By.cssSelector("a[href*=automationpractice]");
	By signIn = By.linkText("Sign in");

	public LoginPom(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSignIn() {
		driver.findElement(signIn).click();
	}

	public void setUserName(String strUsername) {

		driver.findElement(username).sendKeys(strUsername);
	}

	public void setPassword(String pwd) {

		driver.findElement(password).sendKeys(pwd);
	}

	public void clickLogin() {
		driver.findElement(login).click();
	}

	public String getUsername() {
		return driver.findElement(userTxt).getText();
	}

	public void LoginToWeb(String strUsername, String strPassword) {
		this.setUserName(strUsername);
		this.setPassword(strPassword);
		this.clickLogin();

	}

}
