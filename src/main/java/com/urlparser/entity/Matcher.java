package com.urlparser.entity;

import java.util.ArrayList;
import java.util.List;

public class Matcher {

  public static boolean matches(Iterable origin, Iterable expected) {
    List<Object> originListed = new ArrayList<>();
    List<Object> expectedListed = new ArrayList<>();
    for (Object origins : origin) {
      originListed.add(origins);
    }
    for (Object expects : expected) {
      expectedListed.add(expects);
    }
    return originListed.equals(expectedListed);
  }
}
