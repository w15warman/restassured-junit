package com.si.web.api;

import com.si.web.model.User;
import io.restassured.response.Response;
import java.util.ArrayList;

/**
 * Operations about user
 *
 * @author Artem Gonchar
 */
public interface UserApiClient {

  /**
   * @param users A list of user object.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response createUserWithArray(ArrayList<User> users);

  /**
   * @param users A list of user object.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response createUserWithList(User[] users);

  /**
   * @param userName The name that needs to be fetched. Use user1 for testing.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response getUserByUserName(String userName);

  /**
   * @param userName The name that needs to be updated. This can only be done by the logged in
   * user.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response updateUserByUserName(String userName, User user);

  /**
   * @param userName The name that needs to be deleted
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response deleteUserByUserName(String userName);

  /**
   * @param username The user name for login
   * @param password The password for login in clear text
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response userLogin(String username, String password);

  /**
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response userLogout();

  /**
   * @param user Created user object
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response createUser(User user);

}
