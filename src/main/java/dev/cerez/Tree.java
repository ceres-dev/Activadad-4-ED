package dev.cerez;

import java.util.Collection;
import java.util.Set;

public interface Tree<K extends Comparable<K>, V> {

    boolean put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();

    boolean isEmpty();

    void clear();

    Set<K> keys();

    Collection<V> values();

    K firstKey();

    K lastKey();
}
