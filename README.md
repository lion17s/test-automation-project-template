# test-automation-project-template

## Description
Test-automation project template with ready-to-start developing auto scripts functionality to test WEB UI, Rest-API, Mobile(iOS, Android). NOT another "wrapper-test-automation-framework", just a "minimal facade" for popular open-source instruments: TestNG, Selenium, REST-assured, Allure. Extend/modify it in a way required to match your test-automation needs

## Purpose
To start test-automation on the project immediately without spending time on developing basic functionality

## Quick start
1. Create a folder with your test-automation project name
2. Clone https://github.com/lion17s/test-automation-project-template.git
3. Change value of `rootProject.name` in `settings.gradle` file to yours
4. Remove files: `README.md`, `LICENSE` etc.
5. Run example test by executing command from the root of the project folder:
    * Linux/MacOS: `./gradlew clean test -Denv=desktop.chrome -DincludeGroups=ui.test.example`
    * Windows: `gradlew.bat clean test -Denv=desktop.chrome -DincludeGroups=ui.test.example`
6. Generate and open [Allure](https://docs.qameta.io/allure/) report:
    * Linux/MacOS: `./gradlew allureReport & allureServe`
    * Windows: `gradlew.bat allureReport & allureServe`
7. Enjoy the benefits of open-source!

## Overview

* ### environment.conf file

[`environment.conf`](https://github.com/lion17s/test-automation-project-template/blob/main/src/test/resources/environment.conf) [HOCON](https://github.com/lightbend/config) file is a control center for the project. Put needed properties and get them in the code with functionality included in [`Environment.java`](https://github.com/lion17s/test-automation-project-template/blob/main/src/main/java/com/ta/core/env/Environment.java) class
```
env {
  default { // required environment name for storing default values accross custom environments
    driverWait = 5 // predefined key used to control element presence implemented in BaseUIElement.java class
    attachScreenshot = true // predefined key used to control attaching screenshot on test failure. Works for desktop browsers, iOS, Android
    alwaysAttachScreenshot = false // predefined key used to control attaching screenshot after test finishes. Works for desktop browsers, iOS, Android
    attachVideo = true // predefined key used to control attaching video on test failure. Works for iOS, Android
    alwaysAttachVideo = false // predefined key used to control attaching video after test finishes. Works for iOS, Android
  }
  iphone.12.pro.max.safari { // custom environment name for storing related properties
    driver = appium // predeined key used to set driver to be initilized. Available values: appium, chrome, firefox
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
    chrome.arguments = [--headless] // <driver_name>.arguments - predefined key for storing driver cli arguments. Current value is array as per chromedriver requirements
  }
  cats.api {
    base.uri = "https://cat-fact.herokuapp.com" // custom property
  }

}
```



   