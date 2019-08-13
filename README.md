# Demo project to show simple cucumber scenario with java code including page objects behind this.
Features and scenarios can be started from your IDE and from commandline

Running from commandline:

- Parameters needed to start feature/scenario, by -D option:
   - browser=<chrome, ie or phantomjs>
   - env=<dev> (optional, dev=default)

- See buildjar.bat for building a complete jar file, which can be used for running the tests

- See runtests.bat how tests can be started using the complete jar file, including multi-threads and filtering by tags