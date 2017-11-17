package com.flat.odessa;

import com.flat.odessa.entity.Links;
import com.flat.odessa.entity.SinglePageCrawler;

import java.net.URL;

public class Main {

  public static void main(String[] args) {
    String url = args[0];

    Links links = new SinglePageCrawler(url);
    for (URL link : links) {
      System.out.println(link);
    }
  }
}
