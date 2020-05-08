package com.si;

import framework.config.RestAssuredSettings;
import framework.utils.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class BaseTest {

  protected Response response;
  protected RestAssuredSettings runSettings = new RestAssuredSettings();

  @BeforeAll
  void setUp() {
    runSettings.setEnvProperties();
  }
}
