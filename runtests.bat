java -Dbrowser=%1 -jar .\target\demo-0.0.1-jar-with-dependencies.jar classpath:features --glue steps --plugin json:cucumber.json --tags @%1
