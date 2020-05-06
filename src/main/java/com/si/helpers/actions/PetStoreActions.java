package com.si.helpers.actions;

import com.si.web.api.PetApiClient;
import com.si.web.api.StoreApiClient;
import com.si.web.api.UserApiClient;
import com.si.web.model.Order;
import com.si.web.model.Pet;
import com.si.web.model.User;
import com.si.web.services.PetApiClientImpl;
import com.si.web.services.StoreApiClientImpl;
import com.si.web.services.UserApiClientImpl;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.io.File;
import java.util.ArrayList;

public class PetStoreActions {

  private Response response;
  private PetApiClient petApiClient = new PetApiClientImpl();
  private UserApiClient userApiClient = new UserApiClientImpl();
  private StoreApiClient storeApiClient = new StoreApiClientImpl();

  @Step("Uploads an image")
  public Response addImage() {
    int petId = 123;
    String metadata = "Pet name: Barsik";
    File petImage = new File("src/test/resources/documents/cat.jpg");

    return response = petApiClient
        .addPetImage(petId, metadata, petImage);
  }

  @Step("Add a new pet to the store")
  public Response addNewPet(Pet pet) {

    return response = petApiClient
        .addPet(pet);
  }

  @Step("Update an existing pet")
  public Response updatePetInfo(Pet pet) {

    return response = petApiClient
        .updatePet(pet);
  }

  @Step("Finds Pets by status")
  public Response findByStatus(String status) {

    return response = petApiClient
        .findPetByStatus(status);
  }

  @Step("Find pet by ID")
  public Response findById(Pet pet) {

    return response = petApiClient
        .findPetById(pet.getId());
  }

  @Step("Updates a pet in the store with form data")
  public Response updatePetInStore(Pet pet, String name, String status) {

    return response = petApiClient
        .updatePetInfo(pet.getId(), name, status);
  }

  @Step("Deletes a pet")
  public Response deletePetFromStore(Pet pet) {
    String api_key = "special-key";

    return response = petApiClient
        .deletePet(pet.getId(), api_key);
  }

  @Step("Creates list of users with given input array")
  public Response createWithArray(User user) {
    ArrayList<User> users = new ArrayList<>();
    users.add(user);

    return response = userApiClient
        .createUserWithArray(users);
  }

  @Step("Creates list of users with given input array")
  public Response createWithList(User user) {
    User[] users = {user};

    return response = userApiClient
        .createUserWithList(users);
  }

  @Step("Get user by user name")
  public Response findUserByUserName(User user) {

    return response = userApiClient
        .getUserByUserName(user.getUsername());
  }

  @Step("Update user by user name")
  public Response updateUser(User user) {
    User newUser = User.builder().build();
    return response = userApiClient
        .updateUserByUserName(user.getUsername(), newUser);
  }

  @Step("Get user by user name")
  public Response deleteUser(User user) {

    return response = userApiClient
        .deleteUserByUserName(user.getUsername());
  }

  @Step("Logs user into the system")
  public Response login(User user) {

    return response = userApiClient
        .userLogin(user.getUsername(), user.getPassword());
  }

  @Step("Logs out current logged in user session")
  public Response logout() {

    return response = userApiClient
        .userLogout();
  }

  @Step("Logs out current logged in user session")
  public Response createSingleUser(User user) {

    return response = userApiClient
        .createUser(user);
  }

  @Step("Place an order for a pet")
  public Response createOrder(Order order) {

    return response = storeApiClient
        .placeOrder(order);
  }

  @Step("Find purchase order by ID")
  public Response findOrderById(Order order) {

    return response = storeApiClient
        .findOrder(order.getId());
  }

  @Step("Delete purchase order by ID")
  public Response deleteOrderById(Order order) {

    return response = storeApiClient
        .deleteOrder(order.getId());
  }

  @Step("Delete purchase order by ID")
  public Response getInventoryByStatus() {

    return response = storeApiClient
        .getInventory();
  }


}
