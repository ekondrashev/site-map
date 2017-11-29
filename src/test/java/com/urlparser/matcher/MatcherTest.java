package com.urlparser.matcher;

import static com.urlparser.matcher.Matcher.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MatcherTest {

  List<String> expected;

  @Before
  public void setUp() {
    expected = Arrays.asList("String1", "String2");
  }

  @Test
  public void matcherTest() {
    List<String> origin = Arrays.asList("String1", "String2");
    assertThat(origin, is(expected));
  }

  @Test
  public void matcherFailTest() {
    List<String> origin = Arrays.asList("String", "String2");
    try {
      assertThat(origin, is(expected));
    } catch (AssertionError error) {
      assertEquals(error.getMessage(), "\nExpected: Iterator should contain <[String1, String2]>\n"
          + "     but: was<[String, String2]>");
    }
  }
}
