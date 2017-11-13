package com.flat.odessa.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Links implements Iterable<String> {

  private String url;
  private Iterator<String> linkIterator;
  Set<Node> nodes = new HashSet<>();

  public Links(String url) {
    this.url = url;
  }

  private void initIterator() {
    linkIterator = getLinks(url).iterator();
  }

  @Override
  public Iterator<String> iterator() {
    return new LinkIterator();
  }

  private Set<String> getLinks(String url) {
    String baseUrl = getBaseUrl(url);
    Collection<String> links = getTags(url, "a").stream()
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
    Optional<String> baseUrlString = getTags(url, "base")
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

  private List<Element> getTags(String url, String tagName) {
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

  private class LinkIterator implements Iterator<String> {

    @Override
    public boolean hasNext() {
      if (linkIterator == null) {
        initIterator();
      }

      return checkIfLinksExist() ? linkIterator.hasNext() : false;
    }

    private boolean checkIfLinksExist() {
      while (true) {
        if (!linkIterator.hasNext()) {
          Optional<Node> first = nodes.stream()
              .filter(item -> !item.isIssued())
              .findFirst();
          if (first.isPresent()) {
            Node node = first.get();
            Links.this.url = node.url;
            initIterator();
            node.setIssued(true);
            if (linkIterator.hasNext()) {
              return true;
            }
          } else {
            return false;
          }
        } else {
          return true;
        }
      }
    }

    @Override
    public String next() {
      if (linkIterator == null) {
        initIterator();
      }
      String link = linkIterator.next();
      nodes.add(new Node(link));
      return link;
    }
  }

  private static class Node {

    private String url;
    private boolean issued;

    public Node(String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
    }

    public boolean isIssued() {
      return issued;
    }

    public void setIssued(boolean issued) {
      this.issued = issued;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node node = (Node) o;
      return Objects.equals(url, node.url);
    }

    @Override
    public int hashCode() {
      return Objects.hash(url);
    }
  }
}
