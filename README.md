# Yume No Shi (Docker/Grid version)


This Java project runs automated browser tests, with the capability of performing cross-browser testing. It runs tests
in parallel on Selenium Grid running in Docker out of the box, as well as running tests serially on your chosen local browser (Chrome/Firefox
supported for now!)

Tools used:
- Java
- Maven
- Docker
- Selenium
- TestNG

## How to run the tests

Firstly, if you have a unix shell, create the `application.properties` file by running
```
make init
```

The newly created `application.properties` file will be blank and should be populated as follows:

- `implicitWait=` (default time the framework waits for an element in milliseconds, I use '0' and handle waiting using
  explicit waits)

- `baseUrl=` (for the purposes of this project, the value 'https://the-internet.herokuapp.com/' is used)

- `seleniumHubUrl=` (this is populated with 'http://localhost:4444/wd/hub' to access the hub)

- `defaultLocalBrowser=chrome` (the browser you'd like to user for local test execution, firefox/chrome currently
  supported)

If you do not have access to a unix shell, create an `application.properties` file in the resources directory and populate
it as directed above.


Tests can be ran locally from the command line as follows:
```
make test-local
```

Tests can be ran in the Selenium Grid from the command line as follows (once the grid has been spun up):
```
make test-grid
``` 

If your CLI is unable to use Make, just copy the `mvn` commands from the Makefile.

Tests can also be ran from the IDE, and will default to running locally in the browser specified in the
`application.properties` file.

## Test Configuration

To access values stored in the `application.properties` file, the TestConfig class is used like so:

```
testConfig.getBaseUrl();
```

## Selenium Grid

A Selenium Grid can be spun up by executing the following command:
```
docker-compose -f docker-compose.yml up
```

The RemoteWebDriver can use this by providing the URL `http://localhost:4444/wd/hub` when creating it.

## Page Objects

Page Objects are stored in `src/main/java/page_objects` and represent a single page or component in the application.
Locators should be defined in the page object class, rather than tests themselves, as should methods which interact with
elements on the page. Each method should aim to perform a single action and should return an instance of the page which
the action leaves the user on.

## Cross-browser testing

Presently, the browser which tests will run on, and whether they are executed in the grid or locally, are passed as
parameters defined in the `*testng.xml` files.  The `BaseTest` class handles how the driver is created by accepting the
"browser" and "remote" parameters.  If the test methods are ran individually through the IDE, default parameters used
instead and the values found in the `application.properties` file are used.
The xml files contain entries for tests like so:
```
    <test name="Firefox on Grid" preserve-order="true">
        <parameter name="browser" value="firefox"/>
        <parameter name="remote" value="true"/>
        <packages>
            <package name=".*"/>
        </packages>
    </test>
```
This tells TestNG to run all the test methods (annotated with `@Test`) in every test class.
If a new browser/device is to be added, the corresponding DesiredCapabilities should be added to the `RemoteBrowsers`
enum, as per the existing browsers. An entry should be added to the `grid-testng.xml` file if this browser is intended
to be included in every full test run. To run one test remotely for a single browser, the XML file can be modified to
contain just the entry for the browser you desire, then reverted back once the tests have ran.

## Allure Reporting tool

Allure is used to provide test reports. Once tests have been ran, results will be generated in the allure-results
directory. To generate the html report and automatically open it in a web browser, run the following command:
```
allure serve allure-results
```