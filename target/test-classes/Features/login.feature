Feature: Automation of Saucedemo App

  Scenario Outline: Verify Login Functionality
    Given User is on login page
    When User enters valid username "<username>" and password "<password>"
    And User clicks on Login Button
    Then User is navigated to the Home Page
    And Close the browser

    Examples:
      | username | password|
      | standard_user | secret_sauce|
      | locked_out_user | secret_sauce|
      | problem_user | secret_sauce|
      | performance_glitch_user | secret_sauce|

