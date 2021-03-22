# test-automation-project-template

## Description
Test automation project template with ready-to-start developing auto scripts functionality to test WEB UI, Rest-API, Mobile(iOS, Android). NOT another "wrapper-test-automation-framework", just a "minimal facade" for popular open-source instruments: TestNG, Selenium, REST-assured, Allure. Extend/modify it in a way required to match your test-automation needs

## Purpose
To start test-automation on the project immediately without spending time on developing basic functionality

## Quick start
1. Create a folder with your test-automation project name
2. Initialize a new repository and clone this project
3. Change value of `rootProject.name` in `settings.gradle` file to yours
4. Run example test by executing command from the root of the project folder:
    * Linux/MacOS: `./gradlew clean test -Denv=desktop.chrome -DincludeGroups=ui.test.example`
    * Windows: `gradlew.bat clean test -Denv=desktop.chrome -DincludeGroups=ui.test.example`
5. Generate and open html report:
    * Linux/MacOS: `./gradlew allureReport & allureServe`
    * Windows: `gradlew.bat allureReport & allureServe`