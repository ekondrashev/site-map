package com.flat.odessa.callback;

import com.flat.odessa.callback.Callback;

public class PrintOut implements Callback<String> {

  @Override
  public void call(String s) {
    System.out.println(s);
  }
}
