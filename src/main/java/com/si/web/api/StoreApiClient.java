package com.si.web.api;

import com.si.web.model.Order;
import io.restassured.response.Response;

/**
 * Access to Petstore orders
 *
 * @author Artem Gonchar
 */
public interface StoreApiClient {

  /**
   * @param order Order placed for purchasing the pet.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response placeOrder(Order order);

  /**
   * @param orderId Order placed for purchasing the pet.
   * @return {@link Response } The response Object that can be used for data gathering
   * @apiNote For valid response try integer IDs with value >= 1 and <= 10. Other values will
   * generated exceptions
   */
  Response findOrder(int orderId);

  /**
   * @param orderId ID of the order that needs to be deleted.
   * @return {@link Response } The response Object that can be used for data gathering
   * @apiNote For valid response try integer IDs with value >= 1 and <= 10. Other values will
   * generated exceptions
   */
  Response deleteOrder(int orderId);

  /**
   * @return {@link Response } The response Object that can be used for data gathering
   * @apiNote Returns pet inventories by status
   */
  Response getInventory();
}
