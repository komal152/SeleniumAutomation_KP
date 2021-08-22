# SeleniumAutomation_08192021
 Selenium Automation using Maven TestNG 08192021

# PreRequisite:-
Java
Eclipse (Used Version: 2021-06 (4.20.0))

# Other plug-ins used for the Project:-
Maven
TestNG
ChromeDriver
Firefox Driver
------
Dependencies for all above plug-ins has been set in Pom.xml file,
Do not require any separate plug-ins installation.

# Steps:-
1. Pull "SeleniumAutomation" repository in your work space
2. Open "SeleniumAutomation" Project in eclipse
3. Go to "testng.xml" file, do a right click.
4. Run As "TestNG Suite".
5. Once execution completed go to "test-out" folder and expand it.
6. Look for "index.html" file and open it in any web browser.
7. index.html file will display descriptive report of TestCases - Pass and fail.

#Configuration/Additional Automation Suite Information:-
1. Automation script is compatible to execute in either of the browser Chrome and Firefox, which can be maintained in TestNG file as Parameter.
2. Total 5 Test Cases automated which can be executable in Chrome and Firefox.
3. Test Result will contains execution result for 10 test cases.
--
4. TestNG file has been configured to compatible with Parallel execution. Just need to increase thread count="2" from current thread-count="1".
5. Result in Parallel execution of test cases in Chrome and Firefox web browser.
--
6. Failure and Test Cases Execution success screenshot has been appended in TestNG Report along with stored in "screenshot" folder in the project.
7. Appended screenshot in TestNG report which can be viewable in Reporter Output. 
8. Click on reporter output in testng result which display a screenshot for each test cases. 
--
9. TestNG.xml file also contains Test Case description in comment along with TestClass file. I have tried to use comment to describe a steps.
