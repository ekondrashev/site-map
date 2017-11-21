package com.urlparser.entity;

import com.urlparser.model.Links;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringLinksFromUrl implements Links{

  private static Logger log = LogManager.getLogger(StringLinksFromUrl.class);
  private final String url;

  public StringLinksFromUrl(String url) {
    this.url = url;
  }

  private static List<String> links(String url) {
    Document doc = null;
    List<String> listedLinks = new ArrayList<>();
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      log.error("Error during connect to website", e);
    }
    Elements links = doc.select("a[href]");
    for (Element link : links) {
        listedLinks.add(link.attr("abs:href"));
    }
    return listedLinks;

  }

  @Override
  public Iterator iterator() {
    return links(url).iterator();
  }
}
