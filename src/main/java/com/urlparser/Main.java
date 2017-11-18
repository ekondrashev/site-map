package com.urlparser;

import com.urlparser.entity.LinksFromUrl;
import com.urlparser.model.Links;

import java.net.URL;

public class Main {

  public static void main(String[] args) {

    String url = args[0];

    Links links = new LinksFromUrl(url);
    for (URL line : links) {
      System.out.println(line);
    }
  }
}
