package dev.cerez;

public class Main {

    public static void main(String[] args) {
        Graph<String, Files> graph = new HashGraph<>();

        graph.addVertex("A", new Files("Alfa"));
        graph.addVertex("B", new Files("Beta"));
        graph.addVertex("C", new Files("Gamma"));
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "A");


        System.out.println(graph.neighbors("A"));
        System.out.println(graph.neighbors("B"));
        System.out.println(graph.neighbors("C"));
        graph.removeEdge("A", "B");
        System.out.println(graph.neighbors("A"));
    }
}
