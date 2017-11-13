package com.flat.odessa.entity;

public class LinkTest {

  public static void main(String[] args) {
    Links links = new Links("https://github.com");

    for (String link : links) {
      System.out.println(link);
    }
  }
}