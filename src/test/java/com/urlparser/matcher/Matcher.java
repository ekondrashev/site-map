package com.urlparser.matcher;

import com.urlparser.model.Links;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Matcher extends BaseMatcher {

  private List<URL> expected;

  public Matcher(List<URL> expected) {
    this.expected = expected;
  }

  @Override
  public boolean matches(Object item) {
    List<Object> originListed = new ArrayList<>();
    List<Object> expectedListed = new ArrayList<>();
    for (Object origins : (Links)item) {
      originListed.add(origins);
    }
    for (Object expects : expected) {
      expectedListed.add(expects);
    }
    return originListed.equals(expectedListed);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Iterator should contain ").appendValue(expected);
  }

  @Override
  public void describeMismatch(final Object item, final
  Description description) {
    List<Object> originListed = new ArrayList<>();
    for (Object origins : (Links)item) {
      originListed.add(origins);
    }
    description.appendText("was").appendValue(originListed);
  }

  public static Matcher contains(List<URL> expected) {
    return new Matcher(expected);
  }

}
