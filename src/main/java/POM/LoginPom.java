package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPom {

	public WebDriver driver;
	public static String email="mansur@test.com";
	public static String pasword="PKR@PKR";
	
	@FindBy(id="email")
	WebElement username;
	@FindBy(id="passwd")
	WebElement password;
	@FindBy(name="SubmitLogin")
	WebElement login;
	@FindBy(xpath="//span[contains(text(),'Test User Vsoft')]")
	WebElement userTxt;
	@FindBy(linkText="Sign in")
	WebElement signIn;


	public LoginPom(WebDriver driver) {
		this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	public void clickSignIn() {
		signIn.click();
	}

	public void setUserName(String strUsername) {

		username.sendKeys(strUsername);
	}

	public void setPassword(String pwd) {

		password.sendKeys(pwd);
	}

	public void clickLogin() {
		login.click();
	}

	public String getUsername() {
		return userTxt.getText();
	}

	public void LoginToWeb(String strUsername, String strPassword) {
		this.setUserName(strUsername);
		this.setPassword(strPassword);
		this.clickLogin();

	}

}
