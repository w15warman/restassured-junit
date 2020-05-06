package framework.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.File;


public class SpecBuilders {

  public RequestSpecification requestSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
//        .addFilter(new ErrorLoggingFilter())
        .addFilter(new AllureRestAssured())
        .build();
  }

  public RequestSpecification formRequestSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.URLENC)
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
        .addFilter(new ErrorLoggingFilter())
        .addFilter(new AllureRestAssured())
        .build();
  }

  public MultiPartSpecification multipartRequestSpec(File content) {
    return new MultiPartSpecBuilder(content)
        .fileName(content.getName())
        .and()
        .mimeType("multipart/form-data")
        .build();
  }

  public ResponseSpecification responseSpec() {
    return new ResponseSpecBuilder()
        .expectContentType(ContentType.JSON)
//        .expectHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
//        .expectHeader("x-xss-protection", "1; mode=block")
        .build();
  }

}
