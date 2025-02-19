# Cucumber BDD

### What is Cucumber ?

Cucumber is an open-source testing tool used for **Behavior-Driven Development (BDD)**. It allows teams to describe application behavior in plain text that both technical and non-technical stakeholders can understand. The primary goal of Cucumber is to bridge the gap between business and technical teams by using a common language, **Gherkin**, which defines test scenarios in a readable and structured format.

Need Of Using Cucumber :
Cucumber brings developers, testers, and non-technical stakeholders onto the same page. Using Gherkin language ensures that all team members understand the requirements and tests.

---

### **What are the primary keywords in Cucumber?**

Cucumber uses the **Gherkin language**, which includes specific **keywords** to write feature files. These keywords help define the structure and logic of scenarios and features in plain text. Here's a list of the primary keywords in Cucumber:

---

### **Primary Keywords in Cucumber**

1. **Feature**
    - Represents a high-level description of the functionality being tested.
    - Acts as the title for the feature file.
    - **Example:**

        ```gherkin
        Feature: Login functionality
          As a user
          I want to log in to the system
          So that I can access my dashboard
        ```

2. **Scenario**
    - Describes a specific example or use case of the feature being tested.
    - Consists of a series of steps written in **Given-When-Then** format.
    - **Example:**

        ```gherkin
        Scenario: Successful login
          Given the user is on the login page
          When the user enters valid credentials
          Then the user is redirected to the home page
        ```

3. **Scenario Outline**
    - Used for parameterized or data-driven testing, allowing the same scenario to be tested with multiple sets of data.
    - Paired with **Examples** to define the test data.
    - **Example:**

        ```gherkin
        Scenario Outline: Login with multiple credentials
          Given the user is on the login page
          When the user enters "<username>" and "<password>"
          Then the user should see "<result>"
        
        Examples:
          | username      | password     | result        |
          | valid_user    | valid_pass  | home page     |
          | invalid_user  | valid_pass  | error message |
        
        ```

4. **Given**
    - Sets up the initial context or preconditions for the scenario.
    - **Example:**

        ```gherkin
        Given the user is on the login page
        ```

5. **When**
    - Describes the action or event performed by the user.
    - **Example:**

        ```gherkin
        When the user enters valid credentials
        ```

6. **Then**
    - Defines the expected outcome or result of the action.
    - **Example:**

        ```gherkin
        Then the user is redirected to the home page
        ```

7. **And**
    - Used to add additional steps for **Given**, **When**, or **Then**.
    - **Example:**

        ```gherkin
        Given the user is on the login page
        And the user has a valid account
        ```

8. **But**
    - Adds a negative or contrasting step to a scenario.
    - **Example:**

        ```gherkin
        Then the user should see an error message
        But the system should not lock the account
        ```

9. **Background**
    - Defines common preconditions or steps that are shared by all scenarios in a feature.
    - Runs before each scenario in the feature file.
    - **Example:**

        ```gherkin
        Background:
          Given the user is on the login page
        
        ```

10. **Examples**
    - Provides the test data for a **Scenario Outline**.
    - Written as a table of input values and expected results.
    - **Example:**

        ```gherkin
        Examples:
          | username      | password     |
          | valid_user    | valid_pass  |
          | invalid_user  | valid_pass  |
        
        ```


---

### **Secondary Keywords**

1. **Rule** (Optional)
    - Used to group related scenarios and provide additional structure to feature files.
    - **Example:**

        ```gherkin
        Rule: Login with valid credentials
          Scenario: Successful login
          ...
        
        ```

2. **Doc Strings**
    - Used to handle multi-line text data.
    - **Example:**

        ```gherkin
        When the user submits the following data:
          """
          {
            "username": "test_user",
            "password": "test_pass"
          }
          """
        
        ```

3. **Tags**
    - Used to group scenarios for selective execution.
    - **Example:**

        ```gherkin
        @smoke
        Scenario: Successful login
          ...
        
        ```


---

These keywords allow non-technical stakeholders to understand and contribute to the requirements while enabling developers and testers to implement automated tests.

---

### **What is a Feature in Cucumber?**

In Cucumber, a **Feature** represents a high-level functionality or a system behavior that you want to test. It serves as a container for multiple **Scenarios**, each describing specific test cases for that feature.

A **Feature** file is written in the **Gherkin** language and contains both a **Feature** description and one or more **Scenarios** or **Scenario Outlines**.

### **Example of a Feature**

**Feature Title:** Login Functionality

**Description:** Ensures that users can log in using valid credentials and cannot log in using invalid ones.

**Business Value:** Provides secure access to the application for authorized users.

```gherkin
Feature: Login Functionality
  As a registered user
  I want to log in to the system
  So that I can access my account and use the services.

  Scenario: Successful login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user is redirected to the home page

  Scenario: Unsuccessful login with invalid credentials
    Given the user is on the login page
    When the user enters invalid credentials
    Then the user sees an error message
```

### **Key Points About Features**

1. **Located in the `features` Directory**: Feature files are saved in the `src/test/resources/features` folder by convention, with a `.feature` extension.
2. **Written in Plain English**: They use simple, descriptive language so they can serve as a live document for the project.
3. **Linked to Step Definitions**: Each step in a scenario corresponds to a method in the step definition file, where automation code is implemented.

---

### **What Are Step Definitions in Cucumber?**

**Step Definitions** in Cucumber are Java methods that provide the underlying code implementation for the steps defined in a Gherkin **Feature** file. These methods bridge the gap between human-readable test steps written in Gherkin and the actual automation code executed during testing.

---

### **Purpose of Step Definitions**

1. **Automation Implementation**: They contain the Selenium, REST Assured, or other automation framework code to perform actions or validations.
2. **Link Gherkin to Code**: Each Gherkin step (e.g., `Given`, `When`, `Then`) is matched with a corresponding Step Definition method using annotations like `@Given`, `@When`, and `@Then`.
3. **Reusability**: Step Definitions are reusable across multiple scenarios in the same or different Feature files.

---

### **Structure of a Step Definition**

A Step Definition has the following components:

1. **Annotation**: Defines the type of step (`@Given`, `@When`, `@Then`) and a regular expression to match the step text.
2. **Method Body**: Contains the code to execute for the corresponding step.

---

### **Example**

### **Feature File (Gherkin)**

```gherkin
Feature: Login Functionality
  Scenario: Successful login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user is redirected to the home page

```

### **Step Definition (Java)**

```java
package stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {

    WebDriver driver;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");
    }

    @Then("the user is redirected to the home page")
    public void the_user_is_redirected_to_the_home_page() {
        boolean isLogoDisplayed = driver.findElement(By.className("app_logo")).isDisplayed();
        if (!isLogoDisplayed) {
            throw new AssertionError("User is not on the home page");
        }
        driver.quit();
    }
}

```

---

### **Key Points About Step Definitions**

1. **Annotations Link Steps**:
    - `@Given`, `@When`, `@Then` match the steps in the Feature file.
    - The regular expression in the annotation determines which Gherkin step the method corresponds to.
2. **Reusability**: Step Definitions can be reused across multiple scenarios if the steps are identical.
3. **Independent of Order**: Steps in the Feature file do not need to follow the order of methods in the Step Definition file.

---

### **How Step Definitions Work**

1. **Cucumber Reads the Feature File**: Parses the Gherkin steps and searches for corresponding Step Definitions.
2. **Regular Expression Matching**: Matches the step text with the regex provided in the Step Definition annotation.
3. **Executes the Code**: Executes the method body when the step is run during test execution.

---

### Files Needed in a Cucumber Framework

A typical **Cucumber framework** is structured to support Behavior-Driven Development (BDD) and includes various types of files and configurations. Below is a list of essential files needed:

---

### **1. Feature Files**

- **Purpose**: Contain test scenarios written in **Gherkin** syntax.
- **Location**: Typically located in the `src/test/resources/features` directory.
- **Example**:

    ```gherkin
    Feature: Login Functionality
      Scenario: Successful login
        Given the user is on the login page
        When the user enters valid credentials
        Then the user is redirected to the home page
    
    ```


---

### **2. Step Definition Files**

- **Purpose**: Provide the implementation (code) for the steps defined in Feature files.
- **Location**: Typically located in the `src/test/java/stepdefs` package.
- **Example**:

    ```java
    package stepdefs;
    
    import io.cucumber.java.en.*;
    
    public class LoginSteps {
    
        @Given("the user is on the login page")
        public void userIsOnLoginPage() {
            System.out.println("Navigating to the login page");
        }
    
        @When("the user enters valid credentials")
        public void userEntersValidCredentials() {
            System.out.println("Entering username and password");
        }
    
        @Then("the user is redirected to the home page")
        public void userRedirectedToHomePage() {
            System.out.println("Validating home page redirection");
        }
    }
    
    ```


---

### **3. Test Runner File**

- **Purpose**: Integrates the framework, specifying paths for Feature files and Step Definitions. It configures Cucumber execution options.
- **Location**: Typically located in the `src/test/java` directory.
- **Example**:

    ```java
    package TestRunner;
    
    import io.cucumber.junit.Cucumber;
    import io.cucumber.junit.CucumberOptions;
    import org.junit.runner.RunWith;
    
    @RunWith(Cucumber.class)
    @CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
    )
    public class TestRunner {
    }
    
    ```


---

### **4. Configuration Files**

- **Purpose**: Store reusable configurations for the framework.
- **Examples**:
    - **`config.properties`**: Contains environment-specific configurations like URLs, credentials, etc.
        - Example:

            ```
            baseURL=https://www.saucedemo.com
            username=standard_user
            password=secret_sauce
            
            ```

    - **`log4j.properties`** (optional): Configures logging.
        - Example:

            ```
            log4j.rootLogger=DEBUG, FILE
            log4j.appender.FILE=org.apache.log4j.FileAppender
            log4j.appender.FILE.File=logs/application.log
            
            ```


---

### **5. Build Tool Configuration File**

- **Purpose**: Manages dependencies and build configurations.
- **Examples**:
    - **`pom.xml`** (for Maven):
        - Example:

            ```xml
            <dependencies>
                <dependency>
                    <groupId>io.cucumber</groupId>
                    <artifactId>cucumber-java</artifactId>
                    <version>7.11.0</version>
                </dependency>
                <dependency>
                    <groupId>org.seleniumhq.selenium</groupId>
                    <artifactId>selenium-java</artifactId>
                    <version>4.11.0</version>
                </dependency>
                <dependency>
                    <groupId>io.cucumber</groupId>
                    <artifactId>cucumber-testng</artifactId>
                    <version>7.11.0</version>
                </dependency>
            </dependencies>
            
            ```


---

### **6. Hooks File**

- **Purpose**: Define actions to execute before or after each scenario (e.g., setup, teardown).
- **Location**: Typically in the `src/test/java/hooks` package.
- **Example**:

    ```java
    package hooks;
    
    import io.cucumber.java.After;
    import io.cucumber.java.Before;
    
    public class Hooks {
        @Before
        public void setup() {
            System.out.println("Setup before the scenario");
        }
    
        @After
        public void teardown() {
            System.out.println("Teardown after the scenario");
        }
    }
    
    ```


---

### **7. Utility Files**

- **Purpose**: Contain reusable functions like WebDriver initialization, screenshot capturing, or file reading.
- **Examples**:
    - **`DriverFactory.java`**:

        ```java
        package utils;
        
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        
        public class DriverFactory {
            private static WebDriver driver;
        
            public static WebDriver getDriver() {
                if (driver == null) {
                    driver = new ChromeDriver();
                }
                return driver;
            }
        }
        
        ```


---

### **8. Reports Directory**

- **Purpose**: Stores the reports generated by Cucumber after test execution.
- **Location**: `target/` or `reports/` directory.

---

### **9. Resource Files**

- **Purpose**: Store additional resources like test data or environment variables.
- **Examples**:
    - `data.json`
    - `testdata.xlsx`

---

### **Directory Structure Example**

```
src
├── main
├── test
│   ├── java
│   │   ├── stepdefs
│   │   │   └── LoginSteps.java
│   │   ├── hooks
│   │   │   └── Hooks.java
│   │   ├── TestRunner
│   │   │   └── TestRunner.java
│   │   ├── utils
│   │   │   └── DriverFactory.java
│   ├── resources
│   │   ├── features
│   │   │   └── Login.feature
│   │   ├── config.properties
│   └── target
│       └── cucumber-reports.html
pom.xml

```

This structure ensures maintainability, modularity, and scalability in your Cucumber framework.

---

### What is the use of the Options tag in the Cucumber Framework?

The **`@CucumberOptions`** annotation in the Cucumber framework is used to configure and customize the execution of Cucumber tests. It is applied to the **Test Runner** class and serves as a way to specify various properties that control how feature files are executed, where step definitions are located, and how reports are generated.

---

### **Key Features and Uses of `@CucumberOptions`**

### 1. **`features`**

- **Purpose**: Specifies the path to the directory or file containing Cucumber feature files.
- **Example**:

This ensures that all feature files in the `features` folder are executed.

    ```java
    @CucumberOptions(features = "src/test/resources/features")
    ```


---

### 2. **`glue`**

- **Purpose**: Specifies the package or packages containing step definitions and hooks.
- **Example**:

This links the feature files to their corresponding step definitions.

    ```java
    @CucumberOptions(glue = "stepdefs")
    ```


---

### 3. **`plugin`**

- **Purpose**: Defines the reporting format and where the reports will be saved.
- **Common Plugins**:
    - `"pretty"`: Prints the steps and results in a readable format.
    - `"html:target/cucumber-reports.html"`: Generates an HTML report in the specified location.
    - `"json:target/cucumber-reports.json"`: Generates a JSON report for further analysis.
    - `"junit:target/cucumber-reports.xml"`: Generates a JUnit XML report.
- **Example**:

    ```java
    @CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports.html"})
    ```


---

### 4. **`tags`**

- **Purpose**: Filters which scenarios to execute based on tags in the feature files.
- **Example**:

This runs only the scenarios marked with the `@SmokeTest` tag.

    ```java
    @CucumberOptions(tags = "@SmokeTest")
    ```


---

### 5. **`monochrome`**

- **Purpose**: Makes the console output more readable by removing unnecessary characters or colors.
- **Example**:

    ```java
    @CucumberOptions(monochrome = true)
    ```


---

### 6. **`dryRun`**

- **Purpose**: Checks the mapping between feature file steps and step definitions without executing the test steps. Useful for verifying that all steps are implemented.
- **Example**:

    ```java
    @CucumberOptions(dryRun = true)
    ```


---

### 8. **`name`**

- **Purpose**: Filters scenarios to execute based on their names.
- **Example**:

    ```java
    @CucumberOptions(name = {"Login Functionality", "Signup Flow"})
    ```


---

### **Comprehensive Example**

```java
package TestRunner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",     // Path to feature files
    glue = "stepdefs",                            // Path to step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"}, // Reporting
    tags = "@SmokeTest",                          // Run scenarios with specific tags
    monochrome = true,                            // Cleaner console output
    dryRun = false                                // Validate steps without running tests
)
public class TestRunner {
}

```

---

### **Benefits of Using `@CucumberOptions`**

1. **Customization**: Allows precise control over which tests to run, how they are executed, and how results are reported.
2. **Scalability**: Enables filtering and organizing tests in large-scale projects.
3. **Debugging**: With options like `dryRun`, you can quickly identify unimplemented steps.
4. **Integration**: Facilitates integration with CI/CD pipelines by generating JSON or XML reports.

---

### **10. How Does the Execution Start in Cucumber?**

Cucumber execution begins with the **support level**, following a specific loading order:

1. **`env.rb` file**:
    - This file contains environment configurations or global setup required for the tests, such as browser setup, environment variables, or other initializations.
2. **`hooks.rb` file**:
    - Contains hooks like `Before`, `After`, or `Around` blocks that execute before, after, or around the scenarios. These are used for tasks such as setting up data, cleaning up, or logging.
3. **Feature File Scenario Steps**:
    - Once the support files are loaded, Cucumber starts executing the steps defined in the feature file, mapping them to the respective step definitions in the code.

---

### **11. What is Grouping in the Context of Cucumber?**

**Grouping** in Cucumber refers to the practice of organizing step definitions into separate files based on their functionality or purpose. Instead of maintaining all step definitions in a single file, they are split into multiple files, such as `login_steps.rb` or `checkout_steps.rb`.

### **Benefits of Grouping:**

1. **Improved Organization**: Steps are grouped logically by feature or functionality.
2. **Better Maintenance**: Easier to update or debug step definitions without affecting unrelated parts.
3. **Scalability**: Makes the framework manageable for large projects with multiple features.

**Example of Grouping:**

- `features/login.feature`:
  Contains scenarios for login functionality.
- `stepdefs/LoginSteps.java`:
  Step definitions for login-related steps.
- `features/checkout.feature`:
  Contains scenarios for checkout functionality.
- `stepdefs/CheckoutSteps.java`:
  Step definitions for checkout-related steps.

---

### **12. How Can You Run Cucumber Tests Parallelly?**

To execute **Cucumber tests in parallel**, you can use tools or plugins like the **Cucumber JVM Parallel Plugin** or **TestNG**. These plugins divide the feature files or scenarios across multiple threads, enabling parallel execution.

### **Using Cucumber JVM Parallel Plugin:**

1. **Setup**: Add the plugin to your `pom.xml` for Maven projects.

    ```xml
    <plugin>
        <groupId>com.github.temyers</groupId>
        <artifactId>cucumber-jvm-parallel-plugin</artifactId>
        <version>5.0.0</version>
        <executions>
            <execution>
                <id>generateRunners</id>
                <phase>generate-test-sources</phase>
                <goals>
                    <goal>generateRunners</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    
    ```

2. **Feature File Directory**:

   Ensure your feature files are in the `src/test/resources` directory.

3. **Generated Runners**:

   The plugin will create separate runners for each feature file in the `target/generated-test-sources` directory.

4. **Execution**:

   Use a parallel test execution framework like TestNG or JUnit to run the generated runners.


---

### **Using TestNG for Parallel Execution:**

1. **Modify `TestNG.xml`**: Configure parallel execution in the TestNG suite file.

    ```xml
    <suite name="Cucumber Suite" parallel="classes" thread-count="4">
        <test name="Test">
            <classes>
                <class name="TestRunner.TestRunner"/>
            </classes>
        </test>
    </suite>
    
    ```

2. **Execution**:

   Run the TestNG suite file to execute tests in parallel.


---

### **Benefits of Parallel Execution:**

1. **Faster Execution**: Reduces the total test execution time by utilizing multiple threads.
2. **Scalability**: Handles larger test suites more efficiently.
3. **Resource Utilization**: Makes better use of available system resources.

By combining plugins like **Cucumber JVM Parallel Plugin** and test execution frameworks like **TestNG** or **Serenity BDD**, you can achieve efficient parallel test execution in Cucumber.

---

## **How Can You Run a Selected Test from a Group of Tests in Cucumber?**

In Cucumber, you can use **tags** to selectively execute specific scenarios or exclude them from execution. This functionality is managed in the **`@CucumberOptions`** section of the TestRunner file.

---

### **How to Use Tags:**

1. **Tagging Scenarios in the Feature File:**
   You can add one or more tags to scenarios in your feature file. Tags start with the `@` symbol and are placed above the scenario definition.

   **Example:**

    ```gherkin
    @smoke
    Feature: Login Feature
    
    @positive
    Scenario: Verify valid login
        Given User is on login page
        When User enters valid username and password
        Then User is navigated to the Home Page
    
    @negative
    Scenario: Verify invalid login
        Given User is on login page
        When User enters invalid username and password
        Then User sees an error message
    
    ```

2. **Configuring Tags in the TestRunner File:**
   Use the `tags` parameter in the `@CucumberOptions` annotation to specify which tests to include or exclude.

   **Run Tests with a Specific Tag:**

    - To run only the `@smoke` scenarios:

        ```java
        @CucumberOptions(
            features = {"src/test/resources/features"},
            glue = {"stepdefs"},
            tags = "@smoke",
            plugin = {"pretty", "html:target/htmlReport.html"}
        )
        public class TestRunner {}
        
        ```


    **Exclude Tests with a Specific Tag:**
    
    - To run all tests except those with `@negative`:
        
        ```java
        @CucumberOptions(
            features = {"src/test/resources/features"},
            glue = {"stepdefs"},
            tags = "not @negative",
            plugin = {"pretty", "html:target/htmlReport.html"}
        )
        public class TestRunner {}
        
        ```
        
    
    **Combine Multiple Tags:**
    
    - To run tests with either `@smoke` or `@positive`:
        
        ```java
        tags = "@smoke or @positive"
        
        ```
        
    - To run tests with both `@smoke` and `@positive`:
        
        ```java
        tags = "@smoke and @positive"
        ```


---

### **Benefits of Using Tags in Cucumber:**

1. **Selective Execution**: Enables running specific tests, such as smoke, regression, or critical scenarios.
2. **Improved Test Management**: Helps organize and segregate test cases based on functionality, priority, or execution type.
3. **Flexibility**: Allows excluding specific tests temporarily without modifying feature files.

By using tags effectively, you can customize the execution of your test suite, focusing on relevant scenarios while maintaining clear and organized test structures.

---

### Folder Structure For BDD POM :

```css
bdd-pom-project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/          # Page Object Classes
│   │   │   ├── utils/          # Utility Classes (e.g., WebDriver management, configs)
│   │   ├── resources/          # Application-specific resources
│   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── stepdefs/       # Step Definitions
│   │   │   ├── runners/        # Test Runner Classes
│   │   │   ├── hooks/          # Hooks (Before, After annotations)
│   │   │   ├── testdata/       # Test Data or Test Utility
│   │   ├── resources/
│   │   │   ├── features/       # Feature Files
│   │   │   ├── config/         # Test Configurations (e.g., properties, environment settings)
│   │
├── target/                     # Generated reports, compiled classes, etc.
│
├── pom.xml                     # Maven Configuration File
├── README.md                   # Project Documentation
└── .gitignore                  # Git Ignore File
```

### Required Dependencies :

Here are the **required dependencies** with use cases for a **BDD project with Cucumber and POM**:

1. **Cucumber Java**
    - **Use case**: Provides the core functionality for running Cucumber tests in Java. It connects the feature files with step definitions.
2. **Cucumber TestNG**
    - **Use case**: Integrates Cucumber with TestNG, allowing you to run tests and generate reports using TestNG. If you're using TestNG as a test runner, you’ll need this.
3. **Selenium WebDriver**
    - **Use case**: Required for web automation. WebDriver allows Cucumber to interact with browsers, performing actions like clicking buttons, entering text, etc., as per the scenarios.
4. **TestNG**
    - **Use case**: Used as a test runner. TestNG can be configured to run Cucumber feature files and generate detailed reports. It's especially useful when working with parallel test execution or creating customized reports.
5. **Cucumber JUnit** (optional, if using JUnit)
    - **Use case**: If you're using JUnit instead of TestNG, this dependency integrates Cucumber with JUnit, allowing you to run Cucumber tests in a JUnit environment.
6. **JUnit** (optional, if using JUnit)
    - **Use case**: The testing framework if you choose to run Cucumber tests with JUnit instead of TestNG.
7. **Apache POI** (optional)
    - **Use case**: Enables reading and writing data from Excel files. Useful for parameterizing tests, reading input data from external sources like Excel sheets.
8. **Maven Surefire Plugin**
    - **Use case**: Required for executing the tests in a Maven-based project. It ensures tests are picked up and executed during the build lifecycle.
9. **Allure Reports** (optional)
    - **Use case**: For generating visually appealing and detailed test execution reports. It integrates well with TestNG or JUnit to produce advanced reports showing test statuses and execution times.

These dependencies, when combined, provide a complete framework for running BDD tests with Cucumber in a Maven-managed project, ensuring both automated testing and well-documented reports.

---

<aside>
💡

This note was curated by **Dinesh**. If you found it helpful or would like to share suggestions, fixes, or feedback, feel free to reach out to me:

[LinkedIn Profile](https://www.linkedin.com/in/dineshdborude/)

</aside>