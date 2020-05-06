package com.si.web.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.si.BaseTest;
import com.si.helpers.actions.PetStoreActions;
import com.si.web.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("@user")
class UserApiClientImplTest extends BaseTest {

  private final PetStoreActions ACTION = new PetStoreActions();
  private User user;

  @BeforeEach
  public void initEach() {
    user = User.builder().build();
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations about user in petstore")
  @Story("Create user")
  @Description("Create new users from ArrayList")
  @DisplayName("CRT_USER_001 - Create new users")
  void createUserWithArray() {
    response = ACTION.createWithArray(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations about user in petstore")
  @Story("Create user")
  @Description("Create new users from list")
  @DisplayName("CRT_USER_002 - Create new users")
  void createUserWithList() {
    response = ACTION.createWithList(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @Feature("Operations about user in petstore")
  @Story("Find user")
  @Description("Get existed user by userName")
  @DisplayName("GET_USER_001 - Get existed user")
  void getUserByUserName() {
    ACTION.createWithList(user);
    response = ACTION.findUserByUserName(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @Feature("Operations about user in petstore")
  @Story("Find user")
  @Description("Update existed user")
  @DisplayName("UPD_USER_001 - Update user")
  void updateUserByUserName() {
    ACTION.createWithList(user);
    response = ACTION.updateUser(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertNotEquals(user.getUsername(), response.getBody().path("username"));
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @Feature("Operations about user in petstore")
  @Story("Delete user")
  @Description("Delete existed user")
  @DisplayName("DEL_USER_001 - Delete user")
  void deleteUserByUserName() {
    ACTION.createWithList(user);
    response = ACTION.deleteUser(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertEquals(user.getUsername(), response.getBody().path("message"));
    response = ACTION.findUserByUserName(user);
    assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations about user in petstore")
  @Story("Login user")
  @Description("Login as registered user")
  @DisplayName("LOGIN_001 - Logs user into the system")
  void userLogin() {
    ACTION.createWithList(user);
    response = ACTION.login(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertThat("Session was not found", response.body().path("message"),
        containsString("logged in user session:"));
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations about user in petstore")
  @Story("Logout user")
  @Description("Logs out current logged in user session")
  @DisplayName("LOGIN_001 - Logs out user from the system")
  void userLogout() {
    ACTION.createWithList(user);
    ACTION.login(user);
    response = ACTION.logout();
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations about user in petstore")
  @Story("Create user")
  @Description("Create single user")
  @DisplayName("CRT_USER_003 - Create new single user")
  void createUser() {
    response = ACTION.createSingleUser(user);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }
}