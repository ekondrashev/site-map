package com.flat.odessa.entity;

import com.flat.odessa.extractors.LinkAbsoluteExtractor;
import com.flat.odessa.extractors.TagExtractor;
import com.flat.odessa.extractors.UniqueLinkExtractor;

public class LinkTest {

  public static void main(String[] args) {
    Links links = new Links("https://github.com",
        new LinkAbsoluteExtractor(new UniqueLinkExtractor(new TagExtractor())));

    for (String link : links) {
      System.out.println(link);
    }
  }
}