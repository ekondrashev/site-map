package com.urlparser.entity;

import com.urlparser.model.Links;

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

public class LinksFromUrl implements Links {

  private Document doc;
  List<URL> listedLinks = new ArrayList<>();

  public LinksFromUrl(String url) {
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    listed();
  }

  public void listed() {
    Elements links = doc.select("a[href]");
    for (Element link : links) {
      try {
        listedLinks.add(new URL(link.attr("abs:href")));
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Iterator<URL> iterator() {
    return listedLinks.iterator();
  }
}
