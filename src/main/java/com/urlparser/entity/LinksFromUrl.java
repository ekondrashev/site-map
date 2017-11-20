package com.urlparser.entity;

import com.urlparser.model.Links;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LinksFromUrl implements Links {

  private String url;

  public LinksFromUrl(String url) {
    this.url = url;
  }

  private static Logger log = LogManager.getLogger(LinksFromUrl.class);

  private static List<URL> links(String url) {
    Document doc = null;
    List<URL> listedLinks = new ArrayList<>();
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      log.error("Error during connect to website", e);
    }
    Elements links = doc.select("a[href]");
    for (Element link : links) {
      try {
        listedLinks.add(new URL(link.attr("abs:href")));
      } catch (MalformedURLException e) {
        log.error("Error during URL creation", e);
      }
    }
    return listedLinks;
  }

  @Override
  public Iterator<URL> iterator() {
    return links(url).iterator();
  }
}
