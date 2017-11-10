package com.flat.odessa.entity;

import com.flat.odessa.extractors.Extractor;
import com.flat.odessa.extractors.LinkAbsoluteExtractor;
import com.flat.odessa.extractors.TagExtractor;
import com.flat.odessa.extractors.UniqueLinkExtractor;

import java.util.Iterator;

public class Link implements Iterator<String> {

  private String url;
  private Extractor<String> extractor;
  Iterator<String> linkIterator;

  public Link(String url, Extractor<String> extractor) {
    this.url = url;
    this.extractor = extractor;
  }

  @Override
  public boolean hasNext() {
    if (linkIterator == null) {
      initIterator();
    }
    return linkIterator.hasNext();
  }

  @Override
  public String next() {
    if (linkIterator == null) {
      initIterator();
    }
    return linkIterator.next();
  }

  private void initIterator() {
    linkIterator = extractor.getLinks(url).iterator();
  }
}
