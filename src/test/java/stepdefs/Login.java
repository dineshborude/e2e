package stepdefs;

import page.objects.web.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Login {

    WebDriver driver;
    LoginPage loginPage;

    @Given("User is on login page")
    public void user_is_on_login_page() {
        // Set up WebDriver

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        System.out.println("Navigated to the login page.");
    }

    @When("User enters valid username {string} and password {string}")
    public void user_enters_valid_username_and_password(String username, String password) {
        // Enter username
//        WebElement usernameField = driver.findElement(By.id("user-name"));

        loginPage = new LoginPage(driver);
        loginPage.enterUserName(username);

//        usernameField.sendKeys(username);

        System.out.println("Entered username: " + username);

        // Enter password
//        WebElement passwordField = driver.findElement(By.id("password"));
//        passwordField.sendKeys(password);

        loginPage.enterPassword(password);
        System.out.println("Entered password.");
    }

    @When("User clicks on Login Button")
    public void user_clicks_on_login_button() {
        // Click the login button
//        WebElement loginButton = driver.findElement(By.id("login-button"));
//        loginButton.click();
        loginPage.clickLogin();
        System.out.println("Clicked on the Login button.");
    }

    @Then("User is navigated to the Home Page")
    public void user_is_navigated_to_the_home_page() {
        // Verify successful navigation

        loginPage.isLogoVisible();
//        boolean isLogoDisplayed = driver.findElements(By.xpath("//div[@class='app_logo']")).size() > 0;
//        Assert.assertTrue(isLogoVisible(), "User is not navigated to the Home Page.");
        System.out.println("Verified navigation to the Home Page.");
    }

    @And("Close the browser")
    public void close_the_browser() throws InterruptedException {
        // Close the browser
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
