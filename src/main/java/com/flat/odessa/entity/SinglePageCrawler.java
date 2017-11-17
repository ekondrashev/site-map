package com.flat.odessa.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SinglePageCrawler implements Links {

  private String url;

  public SinglePageCrawler(String url) {
    this.url = url;
  }

  @Override
  public Iterator<URL> iterator() {
    List<URL> urls = new ArrayList<>();
    links(url).stream().forEach(item -> {
      try {
        urls.add(new URL(item));
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    });
    return urls.iterator();
  }

  private Set<String> links(String url) {
    String baseUrl = getBaseUrl(url);
    Collection<String> links = tags(url, "a").stream()
        .map(element -> element.attr("href"))
        .collect(Collectors.toSet());

    return getAbsolutePath(url, baseUrl, links);
  }

  private Set<String> getAbsolutePath(String parentUrl, String baseUrl,
      Collection<String> links) {
    String hostName = getHostName(parentUrl);
    return links
        .stream()
        .map(item -> {
          String transformedLink = item;
          if (!item.startsWith("http")) {
            try {
              transformedLink = new URL(new URL(baseUrl), item).toString();
            } catch (MalformedURLException ex) {
              ex.printStackTrace();
            }
          }
          return transformedLink;
        })
        .filter(item -> item.startsWith(hostName))
        .collect(Collectors.toSet());
  }

  private String getBaseUrl(String url) {
    String baseUrl = url;
    Optional<String> baseUrlString = tags(url, "base")
        .stream()
        .map(elem -> elem.attr("base"))
        .filter(item -> !item.isEmpty() && item.startsWith("http"))
        .findFirst();
    if (baseUrlString.isPresent()) {
      baseUrl = baseUrlString.get();
    }
    return baseUrl;
  }

  private String getHostName(String stringUrl) {
    try {
      URL url = new URL(stringUrl);
      String host = url.getHost();
      String protocol = url.getProtocol();
      return protocol + "://" + host;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private List<Element> tags(String url, String tagName) {
    Document document = null;
    try {
      document = Jsoup.connect(url).get();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return document == null
        ? Collections.emptyList()
        : document
            .select(tagName)
            .stream()
            .collect(Collectors.toList());
  }
}
