# Advantage Online Shopping - Selenium Project

This is a simple Selenium Page Object Model project for:

`https://advantageonlineshopping.com/`

It uses Java, Selenium, TestNG and Maven. Each web page has its own Java class,
and each test area has its own test class.

## Project structure

```text
src/main/java
  config       - reads values from config.properties
  utility      - common click, type, wait and screenshot methods
  base         - opens/closes the browser and handles login/logout
  pages        - locators and actions for each page

src/test/java
  tests        - TestNG test classes

src/test/resources
  testng.xml         - normal test suite
  testng-order.xml   - payment/order tests
```

## Before running

1. Install Java 17, Maven and Google Chrome.
2. Open the project in Eclipse using **File > Import > Existing Maven Projects**.
3. Open `src/main/resources/config.properties`.
4. Add a valid Advantage Shopping username and password for login tests.

Selenium Manager downloads the correct browser driver automatically.

## Run the tests

From Eclipse, right-click `testng.xml` and select **Run As > TestNG Suite**.

From a terminal:

```bash
mvn test
```

The standard report is created at `target/surefire-reports/index.html`.
Screenshots for failed tests are saved in the `screenshots` folder.

## Run the complete order flow

The normal suite does not place demo orders. To run the separate end-to-end
order suite, first add valid login details and run:

```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-order.xml
```

The end-to-end test follows this flow:

`Login -> Search -> Product details -> Cart -> Shipping -> Payment -> Order confirmation`

## How to explain the framework

- A page class stores the locators and actions for one screen.
- A test class calls those page actions and checks the result with assertions.
- `Base.java` starts Chrome before every test and closes it afterward.
- `BaseLoginLogout.java` is used only when a test needs a logged-in user.
- `Utility.java` keeps repeated Selenium code in one place.
- `testng.xml` decides which tests are included in a test run.
