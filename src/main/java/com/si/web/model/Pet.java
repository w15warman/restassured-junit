package com.si.web.model;

import com.github.javafaker.Faker;
import java.util.Locale;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder(builderClassName = "Builder")
public class Pet {

  private int id;
  private Category category;
  private String name;
  private String[] photoUrls;
  private Tags[] tags;
  private String status;

  private enum Status {available, pending, sold}

  @Data
  @Accessors(chain = true)
  public static class Category {

    private int id;
    private String name;
  }

  @Data
  @Accessors(chain = true)
  public static class Tags {

    private int id;
    private String name;
  }

  public static Pet.Builder builder() {
    Faker faker = new Faker(new Locale("en", "EN"));

    Category category = new Category()
        .setId(faker.number().numberBetween(1, 10))
        .setName(faker.ancient().titan());

    String[] photoUrls = {faker.internet().avatar()};

    Tags[] tags = new Tags[]{new Tags()
        .setId(faker.number().randomDigit())
        .setName("@animal")
    };

    return new Builder()
        .id(faker.idNumber().hashCode())
        .category(category)
        .name(faker.funnyName().name())
        .photoUrls(photoUrls)
        .tags(tags)
        .status(faker.options().option(Status.class).toString());
  }
}