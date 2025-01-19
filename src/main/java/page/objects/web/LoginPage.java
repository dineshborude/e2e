package page.objects.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By username = By.id("user-name");
    By password = By.id("password");
    By login_button = By.id("login-button");
    By logo = By.xpath("//div[@class='app_logo']");


    public void enterUserName(String user) {

        driver.findElement(username).sendKeys(user);

    }

    public void  enterPassword(String pass) {

        driver.findElement(password).sendKeys(pass);

    }

    public void clickLogin() {

        driver.findElement(login_button).click();

    }

    public void isLogoVisible() {

        driver.findElement(logo).isDisplayed();

    }

}
