# Yume No Shi

This Java project runs automated browser tests, with the capability of performing cross-browser
testing.

Tools used:
- Maven
- Docker
- Selenium
- TestNG
- Allure Reporting
- BrowserStack

## How to run the tests

Firstly, create the `application.properties` file by running
```
make init
```

The newly created `application.properties` file should be populated as follows:

- `implicitWait=10000` (default time the framework waits for an element in milliseconds)

- `baseUrl=http://projecturl` (base url for deployed project, in this case we are using https://the-internet.herokuapp.com/)

- `browserStackUsername=exampleusername` (the BrowserStack username for the project)

- `browserStackKey=12345` (the BrowserStack key for the project)

- `defaultLocalBrowser=chrome` (the browser you'd like to user for local test execution, firefox/chrome currently
  supported)




Tests can be ran locally from the command line as follows:
```
make test-local
```

Tests can be ran in BrowserStack from the command line as follows:
```
make test-browserstack
``` 

Tests can also be ran from the IDE, and will default to running locally in the browser specified in the
`application.properties` file.

## Test Configuration

To access values stored in the `application.properties` file, the TestConfig class is used like so:

```
testConfig.getBaseUrl();
```

## Page Objects

Page Objects are stored in `src/main/java/page_objects` and represent a single page or component in the application.
Locators should be defined in the page object class, rather than tests themselves, as should methods which interact with
elements on the page. Each method should aim to perform a single action and should return an instance of the page which
the action leaves the user on.

```
Code examples including method chaining and examples of readability to come.
```

## Cross-browser/device testing

Presently, the browser which tests will run on, and whether they are executed on BrowserStack or locally, are passed as
parameters defined in the `*testng.xml` files.  The `BaseTest` class handles how the driver is created by accepting the
"browser" and "remote" parameters.  If the test methods are ran individually through the IDE, default parameters used
instead and the values found in the `application.properties` file are used.
The xml files contain entries for tests like so:
```
    <test name="Firefox on BrowserStack" preserve-order="true">
        <parameter name="browser" value="firefox"/>
        <parameter name="remote" value="true"/>
        <packages>
            <package name=".*"/>
        </packages>
    </test>
```
This tells TestNG to run all the test methods (annotated with `@Test`) in every test class.
If a new browser/device is to be added, the corresponding DesiredCapabilities should be added to the `RemoteBrowsers`
enum, as per the existing browsers. The BrowserStack website can auto-generate these easily. An entry should be added to
the `browserstack-testng.xml` file if this browser is intended to be included in every full test run. To run one test
remotely for a single browser, the XML file can be modified to contain just the entry for the browser you desire, then
reverted back once the tests have ran.

## Allure Reporting tool

Allure is used to provide test reports. Once tests have been ran, results will be generated in the allure-results
directory. To generate the html report and automatically open it in a web browser, run the following command:
```
allure serve allure-results
```