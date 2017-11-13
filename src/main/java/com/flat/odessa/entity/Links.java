package com.flat.odessa.entity;

import com.flat.odessa.extractors.Extractor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Links implements Iterable<String> {

  private String url;
  private Extractor<String> extractor;
  Iterator<String> linkIterator;
  Set<Node> nodes = new HashSet<>();

  public Links(String url, Extractor<String> extractor) {
    this.url = url;
    this.extractor = extractor;
  }

  private void initIterator() {
    linkIterator = extractor.getLinks(url).iterator();
  }

  @Override
  public Iterator<String> iterator() {
    return new LinkIterator();
  }

  class LinkIterator implements Iterator<String> {

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

  static class Node {

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
