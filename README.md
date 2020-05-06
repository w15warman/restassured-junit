[![LinkedIn](https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555)](https://www.linkedin.com/in/artem-gonchar)

# Pet store API automation in action

### Table of Contents
* [About the Project](#about-the-project)
    * [Project structure](#project-structure)
* [Running the tests](#running-the-tests)
* [Generating Allure report](#generating-allure-report)
* [Built With](#built-with)

### About The Project
This simple project was created to demonstrate how to use the most popular java library for API testing.

### Project structure
The Standart Maven Directory Structure is using on this project.
The src directory is the root directory of source code and test code.
```bash
├── src

```

## Running the tests

To run tests from the terminal, go to the project root directory in the command line and enter the next command:
```
mvn clean test -P {environmentName}
```
## Generating Allure report

For generate report, use the next command in terminal after test execution:

```
mvn allure:serve
```

## Built With

* [JAVA 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html) - The Java Runtime Environment.
* [Maven](https://maven.apache.org/) - Build tool
* [REST Assured](http://rest-assured.io/) - Java DSL for easy testing of REST services
* [JUnit 5](https://junit.org/junit5/) - The unit testing framework for the Java programming language. 
* [Allure 2](http://allure.qatools.ru/) - Test report and framework for writing self-documented tests.