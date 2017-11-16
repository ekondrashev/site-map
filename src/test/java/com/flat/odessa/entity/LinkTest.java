package com.flat.odessa.entity;

import java.net.URL;

public class LinkTest {

  public static void main(String[] args) {
    Links links = new SinglePageCrawler("https://github.com");
    for (URL link : links) {
      System.out.println(link);
    }
  }
}