package dev.cerez;

import java.util.*;

public class HashGraph<K, V> implements Graph<K, V> {

    private final Map<K, Vertex<K, V>> vertices = new HashMap<>();
    private int edgeCount;

    @Override
    public boolean addVertex(K key, V value) {
        if (vertices.containsKey(key)) {
            return false;
        }

        vertices.put(key, new Vertex<>(value));
        return true;
    }

    @Override
    public boolean removeVertex(K key) {
        Vertex<K, V> removed = vertices.remove(key);

        if (removed == null) {
            return false;
        }

        edgeCount -= removed.neighbors.size();

        for (Vertex<K, V> vertex : vertices.values()) {
            if (vertex.neighbors.remove(key)) {
                edgeCount--;
            }
        }

        return true;
    }

    @Override
    public boolean containsVertex(K key) {
        return vertices.containsKey(key);
    }

    @Override
    public V getValue(K key) {
        Vertex<K, V> vertex = vertices.get(key);
        return vertex == null ? null : vertex.value;
    }

    @Override
    public V setValue(K key, V value) {
        Vertex<K, V> vertex = vertices.get(key);

        if (vertex == null) {
            return null;
        }

        V old = vertex.value;
        vertex.value = value;
        return old;
    }

    @Override
    public Set<K> vertices() {
        return Collections.unmodifiableSet(vertices.keySet());
    }

    @Override
    public int vertexCount() {
        return vertices.size();
    }

    @Override
    public boolean addEdge(K from, K to) {
        Vertex<K, V> source = vertices.get(from);
        Vertex<K, V> target = vertices.get(to);

        if (source == null || target == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        boolean added = source.neighbors.add(to);

        if (added) {
            edgeCount++;
        }

        return added;
    }

    @Override
    public boolean removeEdge(K from, K to) {
        Vertex<K, V> source = vertices.get(from);

        if (source == null) {
            return false;
        }

        boolean removed = source.neighbors.remove(to);

        if (removed) {
            edgeCount--;
        }

        return removed;
    }

    @Override
    public boolean containsEdge(K from, K to) {
        Vertex<K, V> source = vertices.get(from);

        return source != null && source.neighbors.contains(to);
    }

    @Override
    public Set<K> neighbors(K key) {
        Vertex<K, V> vertex = vertices.get(key);

        if (vertex == null) {
            return Collections.emptySet();
        }

        return Collections.unmodifiableSet(vertex.neighbors);
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>(vertices.size());

        for (Vertex<K, V> vertex : vertices.values()) {
            values.add(vertex.value);
        }

        return Collections.unmodifiableList(values);
    }

    private static final class Vertex<K, V> {

        private V value;
        private final Set<K> neighbors = new HashSet<>();

        private Vertex(V value) {
            this.value = value;
        }
    }
}