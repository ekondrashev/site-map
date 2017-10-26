package com.flat.odessa.extractors;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LinkAbsoluteExtractor implements Extractor<String> {

  Logger logger = Logger.getGlobal();
  private Extractor<String> linkExtractor;
  private TagExtractor tagExtractor = new TagExtractor();

  public LinkAbsoluteExtractor(Extractor<String> linkExtractor) {
    this.linkExtractor = linkExtractor;
  }

  @Override
  public Set<String> getLinks(String url) {
    String baseUrl = getBaseUrl(url);
    return getAbsolutePath(url, baseUrl, linkExtractor.getLinks(url));
  }

  private Set<String> getAbsolutePath(String parentUrl, String baseUrl, Collection<String> links) {
    String hostName = getHostName(parentUrl);
    return links
        .stream()
        .map(item -> {
          String transformedLink = item;
          if (!item.startsWith("http")) {
            try {
              transformedLink = new URL(new URL(baseUrl), item).toString();
            } catch (MalformedURLException ex) {
              logger.log(Level.INFO, ex.getMessage());
            }
          }
          return transformedLink;
        })
        .filter(item -> item.startsWith(hostName))
        .collect(Collectors.toSet());
  }

  private String getBaseUrl(String url) {
    String baseUrl = url;
    Optional<String> baseUrlString = tagExtractor
        .getTags(url, "base")
        .stream()
        .map(elem -> elem.attr("base"))
        .filter(item -> !item.isEmpty() && item.startsWith("http"))
        .findFirst();
    if (baseUrlString.isPresent()) {
      baseUrl = baseUrlString.get();
    }
    return baseUrl;
  }

  public String getHostName(String stringUrl) {
    try {
      URL url = new URL(stringUrl);
      String host = url.getHost();
      String protocol = url.getProtocol();
      return protocol + "://" + host;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
