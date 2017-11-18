package com.urlparser;

import com.urlparser.entity.LinksFromUrl;
import com.urlparser.model.Links;

public class Main {

  public static void main(String[] args) {

    String url = args[0];

    Links links = new LinksFromUrl(url);
    for (Object line : links) {
      System.out.println(line);
    }
  }
}
