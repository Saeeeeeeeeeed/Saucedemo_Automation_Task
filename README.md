# SauceDemo_Automation_Task
this project impelemented for testing some scenarios on saucedemo website to validate on cart list, invalid login and product list .
# I Used the following
- java 17 JDK
- maven
- selenium
- testng
- extent report
- surefire to run test
- Intelij IDE
# How to Install the project ?
navigate to main branch, click on clone, copy repo link, you can clone using bash terminal or any git GUI application (I am using github desktop) . 
# How to run test suite ?
you have many ways to run test .

1- using IDE terminal :
- open IDE terminal
- write command "mvn test -P regression"

2- using test class :
- navigate to saucedemoTest package under Test package and click on run arrow

3- using testng xml file :
- navigate to suite package under resources
- open saucedemoTest.xml file
- click on run arrow
# How to find report ?
report used is extent report, you will find this report under test-output/ExtentReport .
- right click on TestExcustionReport.html
- click on open in from droplist
- click on Browser option
- choose any Browser to open report
# How to find screenshot for failure ?
you will find screenshots in directory with name screenshots in resources under test, Also it's added to report .
# Note :
- please be noted if you need to run test in headless mode uncomment the headless option line in chrome option method in TestBase .
- also be noted report is generated for test when it's run per suite .
