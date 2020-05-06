package com.si.web.services;

import static io.restassured.RestAssured.given;

import com.si.web.api.PetApiClient;
import com.si.web.model.Pet;
import framework.utils.SpecBuilders;
import io.restassured.response.Response;
import java.io.File;

public class PetApiClientImpl implements PetApiClient {

  private SpecBuilders specBuilders = new SpecBuilders();

  @Override
  public Response addPetImage(int petId, String metaData, File file) {

    return given()
        .request().with()
        .pathParam("petId", petId)
        .formParam("additionalMetadata", metaData)
        .multiPart(specBuilders.multipartRequestSpec(file))
        .and().log().everything()
        .when()
        .post("/pet/{petId}/uploadImage")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response addPet(Pet pet) {

    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .body(pet)
        .when()
        .post("/pet")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response updatePet(Pet pet) {

    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .body(pet)
        .when()
        .put("/pet")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response findPetByStatus(String status) {

    return given()
        .request()
        .spec(specBuilders.requestSpec())
        .with()
        .queryParam("status", status)
        .when()
        .get("/pet/findByStatus")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response findPetById(int petId) {

    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .pathParam("petId", petId)
        .when()
        .get("/pet/{petId}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response updatePetInfo(int petId, String name, String status) {

    return given()
        .request().spec(specBuilders.formRequestSpec())
        .with()
        .pathParam("petId", petId)
        .formParam("name", name)
        .formParam("status", status)
        .when()
        .post("/pet/{petId}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }

  @Override
  public Response deletePet(int petId, String api_key) {
    return given()
        .request().spec(specBuilders.requestSpec())
        .with()
        .header("api_key", api_key)
        .pathParam("petId", petId)
        .when()
        .delete("/pet/{petId}")
        .then()
        .spec(specBuilders.responseSpec())
        .and().extract()
        .response();
  }
}
