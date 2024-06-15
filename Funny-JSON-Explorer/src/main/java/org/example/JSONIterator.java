package org.example;

public interface JSONIterator {
    boolean hasNext();
    String nextKey();
    Object nextValue();
}
