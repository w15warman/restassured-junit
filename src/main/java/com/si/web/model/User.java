package com.si.web.model;

import com.github.javafaker.Faker;
import java.util.Locale;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder(builderClassName = "Builder")
public class User {

  private int id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phone;
  private int userStatus;

  public static User.Builder builder() {
    Faker faker = new Faker(new Locale("en", "EN"));

    return new Builder()
        .id(faker.idNumber().hashCode())
        .username(faker.name().username())
        .firstName(faker.name().firstName())
        .lastName(faker.name().lastName())
        .email(faker.internet().emailAddress().toLowerCase())
        .phone(faker.phoneNumber().phoneNumber())
        .userStatus(faker.number().numberBetween(1, 5));
  }

}
