package dev.cerez;

import java.util.Collection;
import java.util.Set;

public interface Graph<K, V> {

    boolean addVertex(K key, V value);

    boolean removeVertex(K key);

    boolean containsVertex(K key);

    V getValue(K key);

    V setValue(K key, V value);

    Set<K> vertices();

    int vertexCount();

    boolean addEdge(K from, K to);

    boolean removeEdge(K from, K to);

    boolean containsEdge(K from, K to);

    Set<K> neighbors(K key);

    int edgeCount();

    void clear();

    boolean isEmpty();

    Collection<V> values();

}
