package com.flat.odessa;

import com.flat.odessa.extractors.Extractor;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ExtractorManager implements Runnable {

  private BlockingQueue<Collection<String>> queueIn;
  private BlockingQueue<Collection<String>> queueOut;
  private Extractor<String> extractor;

  public ExtractorManager(BlockingQueue<Collection<String>> queueIn,
      BlockingQueue<Collection<String>> queueOut,
      Extractor<String> extractor) {
    this.queueIn = queueIn;
    this.queueOut = queueOut;
    this.extractor = extractor;
  }

  private void proccess() throws InterruptedException {
    Collection<String> poll = queueIn.poll(1, TimeUnit.SECONDS);
    if (poll == null) {
      queueOut.put(Arrays.asList("STOP"));
      throw new InterruptedException();
    }
    for (String url : poll) {
      Collection<String> links = extractor.getLinks(url);
      queueOut.put(links);
    }
  }

  @Override
  public void run() {
    try {
      while (true) {
        proccess();
      }
    } catch (Exception ex) {
      System.err.println("extractor stoped");
    }
  }
}
