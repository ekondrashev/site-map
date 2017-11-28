package com.urlparser.entity;

import com.urlparser.model.Links;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LinksFromUrl implements Links<URL> {

  private static Logger log = LogManager.getLogger(LinksFromUrl.class);
  private Links<String> origin;

  public LinksFromUrl(Links<String> origin) {
    this.origin = origin;
  }

  private static List<URL> links(Links<String> origin) {
    List<URL> listedLinks = new ArrayList<>();
    for (String link : origin) {
      try {
        listedLinks.add(new URL(link));
      } catch (MalformedURLException e) {
        log.error("Error during URL creation", e);
      }
    }
    return listedLinks;
  }

  @Override
  public Iterator<URL> iterator() {
    return links(origin).iterator();
  }
}
