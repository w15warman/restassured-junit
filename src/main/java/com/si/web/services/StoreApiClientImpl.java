package com.si.web.services;

import static io.restassured.RestAssured.given;

import com.si.web.api.StoreApiClient;
import com.si.web.model.Order;
import framework.utils.SpecBuilders;
import io.restassured.response.Response;

public class StoreApiClientImpl implements StoreApiClient {

  private SpecBuilders specBuilders = new SpecBuilders();

  @Override
  public Response placeOrder(Order order) {
    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .body(order)
        .when()
        .post("/store/order")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response findOrder(int orderId) {
    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .pathParam("orderId", orderId)
        .when()
        .get("/store/order/{orderId}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response deleteOrder(int orderId) {
    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .pathParam("orderId", orderId)
        .when()
        .delete("/store/order/{orderId}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response getInventory() {
    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .auth().oauth2("")
        .when()
        .get("/store/inventory")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }
}
