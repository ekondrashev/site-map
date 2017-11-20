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

  private static String url;

  public LinksFromUrl(String url) {
    this.url = url;
  }

  private Logger log = LogManager.getLogger(getClass());

  private static List<URL> links() throws MalformedURLException {
    Document doc = null;
    List<URL> listedLinks = new ArrayList<>();
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Elements links = doc.select("a[href]");
    for (Element link : links) {
        listedLinks.add(new URL(link.attr("abs:href")));
    }
    return listedLinks;
  }

  @Override
  public Iterator<URL> iterator() {
    try {
      return links().iterator();
    } catch (MalformedURLException e) {
      log.error("Error during URL creation", e);
    }
    return null;
  }
}
