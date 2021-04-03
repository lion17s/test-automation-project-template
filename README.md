# test-automation-project-template

![CodeQL](https://github.com/lion17s/test-automation-project-template/blob/main/.github/workflows/codeql-analysis.yml/badge.svg)
![iOS Safari Test](https://github.com/lion17s/test-automation-project-template/blob/main/.github/workflows/ios-safari-test.yml/badge.svg)
![Linux API Test](https://github.com/lion17s/test-automation-project-template/blob/main/.github/workflows/linux-api-test.yml/badge.svg)
![Linux Chrome Test](https://github.com/lion17s/test-automation-project-template/blob/main/.github/workflows/linux-chrome-test.yml/badge.svg)
![Windows Chrome Test](https://github.com/lion17s/test-automation-project-template/blob/main/.github/workflows/windows-chrome-test.yml/badge.svg)


## Description
Test-automation project template with ready-to-start developing auto scripts functionality to test WEB UI, Rest-API, Mobile(iOS, Android). NOT another "wrapper-test-automation-framework", just a "minimal facade" for popular open-source instruments: TestNG, Selenium, REST-assured, Allure. Extend/modify it in a way required to match your test-automation needs

## Purpose
To start test-automation immediately without spending time on developing of basic functionality

## Quick start
1. Create a folder with your test-automation project name
2. Clone https://github.com/lion17s/test-automation-project-template.git
3. Change value of `rootProject.name` in `settings.gradle` file to yours
4. Remove files: `README.md`, `LICENSE` etc.
5. Run example test by executing command from the root of the project folder:
    * Linux/MacOS: `./gradlew clean test -Denv=desktop.chrome -DincludeGroups=ui.test.example`
    * Windows: `./gradlew.bat clean test -Denv="desktop.chrome" -DincludeGroups="ui.test.example"`
6. Generate and open [Allure](https://docs.qameta.io/allure/) report:
    * Linux/MacOS: `./gradlew allureReport & allureServe`
    * Windows: `gradlew.bat allureReport & allureServe`
7. Enjoy the benefits of open-source!

## Overview

* ### environment.conf file

[`environment.conf`](https://github.com/lion17s/test-automation-project-template/blob/main/src/test/resources/environment.conf) - [HOCON](https://github.com/lightbend/config) file is a control center for the project. Put needed properties and get them in the code with functionality included in [`Environment.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/main/java/com/ta/core/env/Environment.java) class
```
env {
  default { // required environment name for storing default values accross custom environments
    driverWait = 5 // used to control wait time until element presence. Default value is 3
    retryOnFail = 1 // used to control tests rerun count in case of fail result. Default value is 0
    attachScreenshot = true // used to control attaching screenshot on test failure. Works for desktop browsers, iOS, Android. Default value is false
    alwaysAttachScreenshot = false // used to control attaching screenshot after test finishes. Works for desktop browsers, iOS, Android. Default value is false
    attachVideo = true // used to control attaching video on test failure. Works for iOS, Android. Default value is false
    alwaysAttachVideo = false // used to control attaching video after test finishes. Works for iOS, Android. Default value is false
  }
  iphone.12.pro.max.safari { // custom environment name for storing related properties
    driver = appium // used to set driver to be initilized. Available values: appium, chrome, firefox, remote. No default value, please set explicitly with using of DrverFactory.setDriver(driverName, capabilities)
    appium.hub = "http://localhost:4723/wd/hub" // <driver_name>.<capability_name> - put driver's available capabilities. Capabilities are listed in driver's docs
    appium.automationName = XCUITest
    appium.platformName = iOS
    appium.platformVersion = "14.4"
    appium.deviceName = iPhone 12 Pro Max
    appium.browserName = Safari
    appium.isHeadless = true
  }
  desktop.chrome {
    driver = chrome
    chrome.arguments = [--headless] // <driver_name>.arguments - used for storing driver cli arguments. Current value is an array as per chromedriver requirements
  }
  cats.api {
    base.uri = "https://cat-fact.herokuapp.com" // custom property
  }

}
```

* ### UI testing
1. Use `DriverFactory.setDriver(driverName, capabilities)` to set driver.
2. To get driver instance simply use `DriverFactory.getDriver()`. 
3. To get a particular driver just cast your driver instance: `(IOSDriver) DriverFactory.getDriver()` or `(AndroidDriver) DriverFactory.getDriver()` or `(RemoteWebDriver) DriverFactory.getDriver()` or `(AppiumDriver) DriverFactory.getDriver()`.
4. Use `DriverFactory.quitDriver()` for driver instance to quit.
5. Use `DriverFactory.getDriverWait(timeOutInSeconds)` to get explicit wait with ability to define wait condition. Explicit driver wait will override `driverWait` setting defined in `environment.conf` or it's default value.
6. Page Object Pattern is suggested to use. Refer to [`BaseUITest.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/test/java/testng/BaseUITest.java) class on driver control examples, [`GoogleSearchPage.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/main/java/example/ui/pages/GoogleSearchPage.java) on page object example and [`ExampleUITests.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/test/java/testng/example/ExampleUITests.java) for test example.

* ### API testing
1. Use `setResponse()` method and define request as a parameter. Use `getResponse()` to get response.
2. "Client" Object Pattern(same idea as for Page Object Pattern) suggested to use. Please refer to [`CatFactsAPIClient.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/main/java/example/api/clients/CatFactsAPIClient.java), [`BaseAPITest.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/test/java/testng/BaseAPITest.java) and [`ExampleAPITests.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/test/java/testng/example/ExampleAPITests.java) for examples.
