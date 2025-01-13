package com.yourcompany.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Login {

    WebDriver driver;

    @Given("User is on login page")
    public void user_is_on_login_page() {
        // Set up WebDriver and navigate to the login page
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        // Locate and enter username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        // Locate and enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");
    }

    @When("User clicks on Login Button")
    public void user_clicks_on_login_button() {
        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("User is navigated to the Home Page")
    public void user_is_navigated_to_the_home_page() {
        // Verify the presence of an element unique to the home page
        Assert.assertTrue(
                driver.findElements(By.xpath("//div[@class='app_logo']")).size() > 0,
                "User is not navigated to the Home Page"
        );
    }

    @And("Close the browser")
    public void close_the_browser() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
