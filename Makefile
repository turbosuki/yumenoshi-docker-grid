build:
	mvn clean verify -DskipTests=true

test-local:
	mvn clean test -q -e -Dsurefire.suiteXmlFiles=local-testng.xml

test-grid:
	mvn clean test -Dsurefire.suiteXmlFiles=grid-testng.xml

init:
	printf '%s\n' 'implicitWait=' 'baseUrl=' 'seleniumHubUrl=' 'defaultLocalBrowser='> src/main/resources/application.properties