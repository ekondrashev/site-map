package com.flat.odessa.extractors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TagExtractor {

  Logger logger = Logger.getGlobal();

  public List<Element> getTags(String url, String tagName) {
    Document document = null;
    try {
      document = Jsoup.connect(url).get();
    } catch (IOException ex) {
      logger.log(Level.INFO, ex.getMessage());
    }
    return document == null
        ? Collections.emptyList()
        : document
            .select(tagName)
            .stream()
            .collect(Collectors.toList());
  }
}
