package com.urlparser;

import com.urlparser.entity.LinksFromUrl;
import com.urlparser.entity.StringLinks;
import com.urlparser.model.Links;

import java.net.URL;

public class Main {

  public static void main(String[] args) {

    String url = args[0];

    Links<URL> links = new LinksFromUrl(new StringLinks(url));
    for (URL line : links) {
      System.out.println(line);
    }
  }
}
