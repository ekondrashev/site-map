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


public class LinksFromUrl implements Links{

  private String url;
  private Document doc;
  List<URL> listedLinks = new ArrayList<>();

  public LinksFromUrl(String url) {
    this.url = url;
  }

  private Logger log = LogManager.getLogger(getClass());

  public void listed() {
    Elements links = doc.select("a[href]");
    for (Element link : links) {
      try {
        listedLinks.add(new URL(link.attr("abs:href")));
      } catch (MalformedURLException e) {
        log.error("Error during URL creation", e);
      }
    }
  }

  @Override
  public Iterator<URL> iterator() {
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    listed();
    return listedLinks.iterator();
  }
}
