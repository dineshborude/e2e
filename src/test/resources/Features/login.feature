Feature: Automation of Saucedemo App

  Scenario: Verify Login Functionality
    Given User is on login page
    When User enters valid username "standard_user" and password "secret_sauce"
    And User clicks on Login Button
    Then User is navigated to the Home Page
    And Close the browser
