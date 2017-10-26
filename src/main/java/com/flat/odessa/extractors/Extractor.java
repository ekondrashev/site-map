package com.flat.odessa.extractors;

import java.util.Collection;

public interface Extractor<T> {

  Collection<T> getLinks(String url);
}
