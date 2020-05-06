package com.si.web.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.si.BaseTest;
import com.si.helpers.actions.PetStoreActions;
import com.si.web.model.Pet;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("@pet")
class PetApiClientImplTest extends BaseTest {

  private final PetStoreActions ACTION = new PetStoreActions();
  private Pet pet;

  @BeforeEach
  public void initEach() {
    pet = Pet.builder().build();
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations with pets in the pet store")
  @Story("Add image")
  @Description("Add image to existed pet")
  @DisplayName("ADD_IMG_001 - Add pet's image")
  void addPetImage() {

    response = ACTION.addImage();
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations with pets in the pet store")
  @Story("Add pet")
  @Description("Add a new pet to the store")
  @DisplayName("ADD_PET_001 - Add new pet")
  void addPet() {

    response = ACTION.addNewPet(pet);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @Feature("Operations with pets in the pet store")
  @Story("Update pet")
  @Description("Update an existing pet")
  @DisplayName("UPD_PET_001 - Update pet info: PUT")
  void updatePet() {

    response = ACTION.addNewPet(pet);
    String nameBeforeUpdate = response.getBody().path("name");
    response = ACTION.updatePetInfo(pet.setName("Barsik"));
    String nameAfterUpdate = response.getBody().path("name");

    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertNotEquals(nameBeforeUpdate, nameAfterUpdate, "Names are equal");
  }

  @ParameterizedTest(name = "run #{index} : All pets in status [{arguments}]")
  @ValueSource(strings = {"available", "pending", "sold"})
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations with pets in the pet store")
  @Story("Find pet")
  @Description("Find all pets by status")
  @DisplayName("FIND_001 - Finds Pets by status")
  void findPetByStatus(String strings) {

    response = ACTION.findByStatus(strings);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @Feature("Operations with pets in the pet store")
  @Story("Find pet")
  @Description("Add new pet and then find it by ID")
  @DisplayName("FIND_002 - Find pet by ID")
  void findPetById() {

    ACTION.addNewPet(pet);
    response = ACTION.findById(pet);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    assertEquals(pet.getName(), response.getBody().path("name"));
  }

  @Test
  @Severity(SeverityLevel.TRIVIAL)
  @Feature("Operations with pets in the pet store")
  @Story("Update pet")
  @Description("Add new pet and then update a pet in the store with form data")
  @DisplayName("UPD_PET_002 - Update pet info: POST")
  void updatePetInfo() {

    ACTION.addNewPet(pet);
    response = ACTION.updatePetInStore(pet, "Murzik", "sold");
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    response = ACTION.findById(pet);
    assertEquals("Murzik", response.getBody().path("name"));
    assertEquals("sold", response.getBody().path("status"));
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Feature("Operations with pets in the pet store")
  @Story("Delete pet")
  @Description("Add new pet and then delete added pet from the store")
  @DisplayName("DEL_PET_001 - Delete the pet")
  void deletePet() {

    ACTION.addNewPet(pet);
    response = ACTION.findById(pet);
    assertEquals(pet.getName(), response.getBody().path("name"));
    response = ACTION.deletePetFromStore(pet);
    assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    response = ACTION.findById(pet);
    assertEquals("Pet not found", response.getBody().path("message"));
  }
}