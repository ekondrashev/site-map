package com.urlparser.entity;

import static com.urlparser.matcher.Matcher.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.urlparser.model.Links;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinksFromUrlTest {

  private static Logger log = LogManager.getLogger(LinksFromUrl.class);

  List<URL> expected;

  @Before
  public void setUp() {

    List<String> array = Arrays.asList("https://github.com#start-of-contentT",
        "https://github.com/",
        "https://github.com/features",
        "https://github.com/business",
        "https://github.com/explore",
        "https://github.com/marketplace",
        "https://github.com/pricing",
        "https://github.com/dashboard",
        "https://github.com/login",
        "https://github.com/join?source=header-home",
        "https://github.com/open-source",
        "https://github.com/business",
        "https://help.github.com/terms",
        "https://help.github.com/privacy",
        "https://github.com/join?source=button-home",
        "https://github.com/join?plan=business&setup_organization=true&source=business-page",
        "https://github.com/features/code-review",
        "https://github.com/features/project-management",
        "https://github.com/features/integrations",
        "https://github.com/business",
        "https://github.com/pricing",
        "https://github.com/features/integrations",
        "https://github.com/marketplace",
        "https://github.com/open-source/stories/ariya",
        "https://github.com/open-source/stories/freakboy3742",
        "https://github.com/business/customers/mailchimp",
        "https://github.com/open-source/stories/kris-nova",
        "https://github.com/open-source/stories/yyx990803",
        "https://github.com/business/customers/mapbox",
        "https://github.com/open-source/stories/jessfraz",
        "https://github.com/open-source",
        "https://github.com/personal",
        "https://github.com/business",
        "https://help.github.com/terms",
        "https://help.github.com/privacy",
        "https://github.com/features#code-review",
        "https://github.com/features#project-management",
        "https://github.com/features#community-management",
        "https://github.com/features#documentation",
        "https://github.com/features#code-hosting",
        "https://atom.io",
        "http://electron.atom.io/",
        "https://desktop.github.com/",
        "https://developer.github.com",
        "https://github.com/personal",
        "https://github.com/open-source",
        "https://github.com/business",
        "https://education.github.com/",
        "https://github.com/business/customers",
        "https://partner.github.com/",
        "https://community.github.com/",
        "https://github.com/about",
        "https://github.com/blog",
        "https://github.com/about/careers",
        "https://github.com/about/press",
        "https://shop.github.com",
        "https://github.com/contact",
        "https://github.community",
        "https://help.github.com",
        "https://status.github.com/",
        "https://github.com/site/terms",
        "https://github.com/site/privacy",
        "https://github.com/security",
        "https://services.github.com/",
        "https://github.com",
        "https://github.com");

    expected = new ArrayList<>();

    for (String string : array) {
      try {
        expected.add(new URL(string));
      } catch (MalformedURLException e) {
        log.error("Error during URL creation", e);
      }
    }
  }

  @Test
  public void linksTest() {
    Links<URL> origin = new LinksFromUrl(new StringLinksFromUrl("https://github.com"));
    assertThat(origin, is(expected));
  }

}
