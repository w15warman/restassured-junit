package com.si.web.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.si.BaseTest;
import com.si.helpers.actions.PetStoreActions;
import com.si.web.model.Order;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StoreApiClientImplTest extends BaseTest {

  private final PetStoreActions ACTION = new PetStoreActions();
  private Order order;

  @BeforeEach
  void initEach() {
    order = Order.builder().build();
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations with orders in the pet store")
  @Story("Place order")
  @Description("Place an order for a pet")
  @DisplayName("ORDER_001 - Place an order")
  void placeOrder() {
    response = ACTION.createOrder(order);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations with orders in the pet store")
  @Story("Find order")
  @Description("Find purchase order by ID")
  @DisplayName("ORDER_002 - Find order by ID")
  void findOrder() {
    ACTION.createOrder(order);
    response = ACTION.findOrderById(order);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertEquals(order.getId(), (int) response.getBody().path("id"));
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations with orders in the pet store")
  @Story("Delete order")
  @Description("Delete purchase order by ID")
  @DisplayName("ORDER_003 - Delete order by ID")
  void deleteOrder() {
    ACTION.createOrder(order);
    response = ACTION.deleteOrderById(order);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertEquals(order.getId(), Integer.parseInt(response.getBody().path("message")));
    response = ACTION.findOrderById(order);
    assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations with orders in the pet store")
  @Story("Get inventories")
  @Description("Get map of status codes to quantities")
  @DisplayName("ORDER_004 - Get inventories by status")
  void getInventory() {
    response = ACTION.getInventoryByStatus();
  }
}