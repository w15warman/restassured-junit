package framework.utils;

import org.apache.commons.lang3.RandomUtils;

public class RandomGenerator {

  private static final String DNI_LETTER_ASSOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";

  public static String randomDni() {
    int number = RandomUtils.nextInt(10000000, 49999999);
    int modulus = number % 23;
    return "" + number + DNI_LETTER_ASSOCIATION.charAt(modulus);
  }

  public static String randomNie() {
    int number = RandomUtils.nextInt(10000000, 14999999);
    String numberString = Integer.toString(number).substring(1);
    int modulus = number % 23;
    return "Y" + numberString + DNI_LETTER_ASSOCIATION.charAt(modulus);
  }

}
