package com.flat.odessa.container;

import com.flat.odessa.callback.Callback;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LinkAccumulator implements Accumulator<Collection<String>> {

  private final Set<String> accumulator = new HashSet<>();
  private Callback<String> callback;

  public LinkAccumulator(Callback<String> callback) {
    this.callback = callback;
  }

  @Override
  public Collection<String> accumulate(Collection<String> items) {
    return items
        .stream()
        .filter(this::filter)
        .collect(Collectors.toList());
  }

  private boolean filter(String item) {
    boolean added = accumulator.add(item);
    if (added) {
      callback.call(item);
    }
    return added;
  }
}
