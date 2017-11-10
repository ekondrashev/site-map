package com.flat.odessa.entity;

import com.flat.odessa.extractors.LinkAbsoluteExtractor;
import com.flat.odessa.extractors.TagExtractor;
import com.flat.odessa.extractors.UniqueLinkExtractor;

public class LinkTest {

  public static void main(String[] args) {
    Link link = new Link("https://github.com",
        new LinkAbsoluteExtractor(new UniqueLinkExtractor(new TagExtractor())));
    while (link.hasNext()) {
      System.out.println(link.next());
    }
  }
}