package com.urlparser.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Matcher extends TypeSafeMatcher<Iterable<URL>> {

  private List<URL> expected = new ArrayList<>();

  public Matcher(Iterable<URL> expected) {
    for (URL expects : expected) {
      this.expected.add(expects);
    }
  }

  @Override
  public boolean matchesSafely(Iterable<URL> item) {
    List<URL> origin = new ArrayList<>();
    for (URL origins : item) {
      origin.add(origins);
    }
    return origin.equals(expected);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Iterator should contain ").appendValue(expected);
  }

  public static Matcher contains(Iterable<URL> expected) {
    return new Matcher(expected);
  }
}
