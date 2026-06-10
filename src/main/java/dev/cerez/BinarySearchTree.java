package dev.cerez;
import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V>
        implements Tree<K, V> {

    private Node<K, V> root;
    private int size;

    @Override
    public boolean put(K key, V value) {

        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return true;
        }

        Node<K, V> current = root;

        while (true) {

            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                current.value = value;
                return false;
            }

            if (cmp < 0) {

                if (current.left == null) {
                    current.left = new Node<>(key, value);
                    size++;
                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right = new Node<>(key, value);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    @Override
    public V get(K key) {

        Node<K, V> node = findNode(key);

        return node == null ? null : node.value;
    }

    @Override
    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    private Node<K, V> findNode(K key) {

        Node<K, V> current = root;

        while (current != null) {

            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                return current;
            }

            current = cmp < 0
                    ? current.left
                    : current.right;
        }

        return null;
    }

    @Override
    public V remove(K key) {

        Node<K, V> parent = null;
        Node<K, V> current = root;

        while (current != null) {

            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                break;
            }

            parent = current;
            current = cmp < 0
                    ? current.left
                    : current.right;
        }

        if (current == null) {
            return null;
        }

        V removedValue = current.value;

        if (current.left != null && current.right != null) {

            Node<K, V> successorParent = current;
            Node<K, V> successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.value = successor.value;

            current = successor;
            parent = successorParent;
        }

        Node<K, V> replacement =
                current.left != null
                        ? current.left
                        : current.right;

        if (parent == null) {
            root = replacement;
        } else if (parent.left == current) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }

        size--;

        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keys() {

        Set<K> result = new LinkedHashSet<>();

        inorderKeys(root, result);

        return result;
    }

    private void inorderKeys(Node<K, V> node,
                             Set<K> result) {

        if (node == null) {
            return;
        }

        inorderKeys(node.left, result);
        result.add(node.key);
        inorderKeys(node.right, result);
    }

    @Override
    public Collection<V> values() {

        List<V> result = new ArrayList<>();

        inorderValues(root, result);

        return result;
    }

    private void inorderValues(Node<K, V> node,
                               List<V> result) {

        if (node == null) {
            return;
        }

        inorderValues(node.left, result);
        result.add(node.value);
        inorderValues(node.right, result);
    }

    @Override
    public K firstKey() {

        if (root == null) {
            return null;
        }

        Node<K, V> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.key;
    }

    @Override
    public K lastKey() {

        if (root == null) {
            return null;
        }

        Node<K, V> current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.key;
    }

    private static final class Node<K, V> {

        private K key;
        private V value;

        private Node<K, V> left;
        private Node<K, V> right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}