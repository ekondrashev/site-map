package com.flat.odessa;

import com.flat.odessa.callback.PrintOut;
import com.flat.odessa.container.Accumulator;
import com.flat.odessa.container.LinkAccumulator;
import com.flat.odessa.extractors.LinkAbsoluteExtractor;
import com.flat.odessa.extractors.TagExtractor;
import com.flat.odessa.extractors.UniqueLinkExtractor;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    String url = args[0];

    BlockingQueue<Collection<String>> queueInExtractor = new ArrayBlockingQueue(100);
    BlockingQueue<Collection<String>> queueOutExtractor = new ArrayBlockingQueue(100);

    queueInExtractor.put(Arrays.asList(url));

    LinkAbsoluteExtractor extractor = new LinkAbsoluteExtractor(
        new UniqueLinkExtractor(new TagExtractor()));
    Runnable extractorManager = new ExtractorManager(queueInExtractor, queueOutExtractor,
        extractor);

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.submit(extractorManager);

    Accumulator<Collection<String>> accumulator = new LinkAccumulator(new PrintOut());

    while (true) {
      Collection<String> taken = queueOutExtractor.take();
      if (taken.size() == 1 && taken.iterator().next().equals("STOP")) {
        break;
      }
      Collection<String> accumulated = accumulator.accumulate(taken);
      queueInExtractor.put(accumulated);
    }
    executorService.shutdownNow();
  }
}
