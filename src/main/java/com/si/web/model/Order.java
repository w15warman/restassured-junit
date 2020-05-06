package com.si.web.model;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder(builderClassName = "Builder")
public class Order {

  private int id;
  private int petId;
  private int quantity;
  private String shipDate;
  private String status;

  private enum Status {placed, approved, delivered}

  private boolean complete;

  public static Order.Builder builder() {
    Faker faker = new Faker(new Locale("en", "EN"));

    return new Builder()
        .id(faker.number().numberBetween(1, 10))
        .petId(faker.idNumber().hashCode())
        .quantity(1)
        .shipDate(faker.date().future(7, TimeUnit.DAYS).toInstant()
            .atZone(ZoneId.of("Europe/Kiev"))
            .toLocalDate()
            .toString())
        .status(faker.options().option(Status.class).toString())
        .complete(true);
  }
}
