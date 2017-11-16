package com.urlparser;

import com.urlparser.entity.LinksFromUrl;
import com.urlparser.model.Links;

import java.net.URL;

public class Main {

  public static void main(String[] args) {

    String urlInput = args[0];

    Links links = new LinksFromUrl(urlInput);
    for (URL url : links) {
      System.out.println(url);
    }
  }
}
