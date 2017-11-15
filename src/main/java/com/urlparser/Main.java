package com.urlparser;

import com.urlparser.entity.Links;

public class Main {

  public static void main(String[] args) {

    String url = args[0];

    Links links = new Links(url);

    links.listed();
  }
}
