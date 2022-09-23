package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPom {

	public WebDriver driver = Configuration.browser();

	By username = By.id("email");
	By password = By.id("passwd");
	By login = By.name("SubmitLogin");

	public void setUserName(String strUsername) {

		driver.findElement(username).sendKeys(strUsername);
	}

	public void setPassword(String pwd) {

		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickLogin() {
		driver.findElement(login).click();
	}
	
	public void LoginToWeb(String strUsername,String strPassword ) {
		this.setUserName(strUsername);
		this.setPassword(strPassword);
		this.clickLogin();
		
	}

}
