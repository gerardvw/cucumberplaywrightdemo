# Demo project to show simple Cucumber scenario's using Playwright and Java
  
Subjects which are covered in this demo project:
1. [x] Basic concept of feature and scenario by wallet feature
2. [x] Checking UI of webapp, using PageObjects and Playwright
3. [x] Running scenarios in parallel by using multiple threads
4. [ ] Running scenarios in a thread-safe way
5. [x] Building a complete artifact (jar file) for running tests from commandline with java
6. [x] Running scenario's from commandline with java, parameterized by browser, headless, environment
7. [x] Filtering scenario's, by browser and api tag
8. [x] Creating a json file with test output, which can be used for reporting on CI server
9. [x] Creating a html report file with test output
10. [x] Creating a timeline report, which can be used to check if scenarios are executed in parallel
11. [x] Screenshot is added to report in case of failure in scenario
12. [x] Checking API, using Playwright  


https://automationexercise.com is used as test website for this
#
Features and scenarios can be started from your IDE and from commandline

Running from commandline:

- Parameters needed to start feature/scenario, by -D option:
   - browser=\<chrome or msedge>
   - headless=\<true or false> (optional, true=default)
   - env=\<dev> (optional, dev=default; currently only environment)

- See buildjar.bat for building a complete jar file, which can be used for running the tests from commandline

- See runtests.bat how tests can be started from commandline using the complete jar file, including multi-threads and filtering by tags
  E.g. runtests chrome "@chrome or @api"