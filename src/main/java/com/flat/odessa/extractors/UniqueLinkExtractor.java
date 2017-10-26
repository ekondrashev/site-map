package com.flat.odessa.extractors;

import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueLinkExtractor implements Extractor<String> {

  private TagExtractor tagExtractor;

  public UniqueLinkExtractor(TagExtractor tagExtractor) {
    this.tagExtractor = tagExtractor;
  }

  @Override
  public Set<String> getLinks(String url) {
    List<Element> elementList = tagExtractor.getTags(url, "a");

    return elementList
        .stream()
        .map(element -> element.attr("href"))
        .collect(Collectors.toSet());
  }

}
