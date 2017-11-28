package com.urlparser.matcher;

import com.urlparser.model.Links;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Matcher extends TypeSafeMatcher<Links> {

  private List<URL> expected;

  public Matcher(List<URL> expected) {
    this.expected = expected;
  }

  @Override
  public boolean matchesSafely(Links item) {
    boolean result = true;
    for (Object origins : item) {
      result &= check(origins, expected);
    }
    for (Object expects : expected) {
      result &= check(expects, item);
    }
    return result;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Iterator should contain ").appendValue(expected);
  }

//  @Override
//  public void describeMismatch(final Object item, final
//  Description description) {
//    List<Object> originListed = new ArrayList<>();
//    for (Object origins : (Links)item) {
//      originListed.add(origins);
//    }
//    description.appendText("was").appendValue(originListed);
//  }

  public static Matcher contains(List<URL> expected) {
    return new Matcher(expected);
  }

  private boolean check (Object origins, Iterable expected) {
    for (Object expects : expected) {
      if (expects.equals(origins)) {
        return true;
      }
    }
    return false;
  }

}
