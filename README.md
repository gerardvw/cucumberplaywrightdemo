# Demo project to show simple Cucumber scenario's using Playwright and Java
  
Subjects which are covered in this demo project:
1. [x] Basic concept of feature and scenario by wallet feature
2. [x] PageObjects, using Playwright to implement these
3. [x] Running features in parallel by using multiple threads
4. [x] Running features in a thread-safe way
5. [x] Building a complete artifact (jar file) for running tests from commandline with java
6. [x] Running scenario's from commandline with java, parameterized by browser, headless, environment
7. [x] Filtering scenario's, by browser
8. [x] Creating a json file with test output, which can be used for reporting
9. [x] Screenshot is added to report in case of failure in scenario


https://automationexercise.com is used as test website for this
#
Features and scenarios can be started from your IDE and from commandline

Running from commandline:

- Parameters needed to start feature/scenario, by -D option:
   - browser=\<chrome or msedge>
   - headless=\<true or false> (optional, true=default)
   - env=\<dev> (optional, dev=default; currently only environment)

- See buildjar.bat for building a complete jar file, which can be used for running the tests

- See runtests.bat how tests can be started using the complete jar file, including multi-threads and filtering by tags
  E.g. 'runtests chrome'