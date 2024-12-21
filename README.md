# Software Verification and Testing Project: korpa.ba

## Project Overview

This project is dedicated to verifying and testing the functionality of the website `korpa.ba`. The goal is to ensure the website functions as expected across different scenarios using automated testing. JUnit, a widely-used testing framework in Java, will be employed to perform unit and integration tests on various features of the website.

## Table of Contents

1. [Project Setup](#project-setup)
2. [Test Cases](#test-cases)
3. [Prerequisites](#prerequisites)
4. [How to Run Tests](#how-to-run-tests)
5. [Test Reports](#test-reports)
6. [Contributing](#contributing)
7. [License](#license)

## Project Setup

### Prerequisites
Before running the tests, ensure the following software is installed:

- **Java 11 or higher** - The project is developed in Java.
- **JUnit 5** - The testing framework used in this project.
- **Maven** - The build tool to manage dependencies and project structure.
- **WebDriver (Selenium)** - Used to interact with and automate actions on the website.

### Dependencies

The project uses Maven for dependency management. Here is the necessary setup in your `pom.xml` file:

```xml
<dependencies>
    <!-- JUnit for unit testing -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Selenium WebDriver for web automation -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.0.0</version>
    </dependency>

    <!-- Maven Surefire Plugin to run tests -->
    <dependency>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0-M5</version>
    </dependency>
</dependencies>
