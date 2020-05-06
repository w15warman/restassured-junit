package com.si.web.api;

import com.si.web.model.Pet;
import io.restassured.response.Response;
import java.io.File;

/**
 * Everything about your Pets
 *
 * @author Artem Gonchar
 */
public interface PetApiClient {

  /**
   * @param petId An identifier of pet.
   * @param file A pet's image
   * @param metaData An additional info
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response addPetImage(int petId, String metaData, File file);

  /**
   * @param pet The request body.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response addPet(Pet pet);

  /**
   * @param pet The request body.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response updatePet(Pet pet);

  /**
   * @param status The query params.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response findPetByStatus(String status);

  /**
   * @param petId The path param.
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response findPetById(int petId);

  /**
   * @param petId The path param.
   * @param name A new pet's name
   * @param status A new pet's status
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response updatePetInfo(int petId, String name, String status);

  /**
   * @param petId The path param.
   * @param api_key A header
   * @return {@link Response } The response Object that can be used for data gathering
   */
  Response deletePet(int petId, String api_key);

}
