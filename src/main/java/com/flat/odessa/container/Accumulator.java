package com.flat.odessa.container;

import java.util.Collection;

public interface Accumulator<T> {

  T accumulate(T item);
}
