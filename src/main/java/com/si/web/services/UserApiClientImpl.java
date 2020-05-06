package com.si.web.services;

import static io.restassured.RestAssured.given;

import com.si.web.api.UserApiClient;
import com.si.web.model.User;
import framework.utils.SpecBuilders;
import io.restassured.response.Response;
import java.util.ArrayList;

public class UserApiClientImpl implements UserApiClient {

  private SpecBuilders specBuilders = new SpecBuilders();

  @Override
  public Response createUserWithArray(ArrayList<User> users) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .body(users)
        .when()
        .post("/user/createWithArray")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response createUserWithList(User[] users) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .body(users)
        .when()
        .post("/user/createWithList")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response getUserByUserName(String userName) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .pathParam("username", userName)
        .when()
        .get("/user/{username}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response updateUserByUserName(String userName, User user) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .pathParam("username", userName)
        .body(user)
        .when()
        .put("/user/{username}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response deleteUserByUserName(String userName) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .pathParam("username", userName)
        .when()
        .delete("/user/{username}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response userLogin(String username, String password) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .queryParam("username", username)
        .queryParam("password", password)
        .when()
        .get("/user/login")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response userLogout() {
    return given()
        .request().spec(specBuilders.requestSpec())
        .when()
        .get("/user/logout")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response createUser(User user) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .body(user)
        .when()
        .post("/user")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }
}
