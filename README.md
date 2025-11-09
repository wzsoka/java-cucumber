# Cucumber project

<p>This small test automation project is written in java-selenium, cucumber and JUnit 5, 
and aims to test the user authentication process of a publicly available web app.
The project is made for demo purposes.</p>


## Settings:
- install maven dependencies
- project structure:
  - SDK: Java17, language level: 8
- modules: 
  - source: src/main/java
  - test source: src/test/java
  - test resources: src/test/features
- use Cucumber and Gherkin plugins in your IDE
- choose windows-1250 for file encoding in order to handle hungarian characters


## Project structure:
- gherkin test scenario: src/test/features/
- step definitions: src/test/java/stepdefs
- page objects: src/main/java/pages
  - abbreviations for web elements:
       - IF: input field
       - CB: checkbox
       - RB: radio button
- test runner classes: src/test/java/utils
- global test data: in config.properties file
  - test data are read by src/main/java/dataProvider/ConfigFileReader

## Running tests
- run configurations: give the selected browser type to the driver property 
> mvn test -Dbrowser=chrome
- if you would like to run only a part of the tests, you can mark the desired tests with a tag, and specify the tag name in CucumberOptions of the TestRunner class
> @CucumberOptions(tags = "@smoke", ~"@err")


## Test reports
- html report can be opened from target/reports/html-report/

