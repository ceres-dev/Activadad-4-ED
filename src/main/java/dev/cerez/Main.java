package dev.cerez;

public class Main {

    static void main(String[] args) {
        Graph<Long, Expediente> graph = new HashGraph<>();
        Expediente test1 = new Expediente(1, "ex-1", "test-1", Expediente.Estado.EN_PROCESO);
        Expediente test2 = new Expediente(2, "ex-2", "test-2", Expediente.Estado.EN_PROCESO);
        Expediente test3 = new Expediente(3, "ex-3", "test-3", Expediente.Estado.EN_PROCESO);
        Expediente test4 = new Expediente(4, "ex-4", "test-4", Expediente.Estado.EN_PROCESO);

        graph.addVertex(test1.id(), test1);
        graph.addVertex(test2.id(), test2);
        graph.addVertex(test3.id(), test3);
        graph.addEdge(1L, 2L);
        graph.addEdge(1L, 3L);
        graph.addEdge(2L, 1L);
        graph.addEdge(3L, 1L);


        System.out.println(graph.neighbors(1L));
        System.out.println(graph.neighbors(2L));
        System.out.println(graph.neighbors(3L));
        graph.removeEdge(1L, 3L);
        System.out.println(graph.neighbors(1L));

        for (Expediente expediente : graph.values()){
            System.out.println(expediente);
        }

        System.out.println("================");
        Tree<Long, Expediente> tree = new BinarySearchTree<>();

        tree.put(test1.id(), test1);
        tree.put(test2.id(), test2);
        tree.put(test3.id(), test3);


        System.out.println(tree.containsKey(test4.id()));
        System.out.println(tree.containsKey(test2.id()));

        tree.remove(test2.id());

        System.out.println(tree.containsKey(test2.id()));


        for (Expediente expediente : tree.values()){
            System.out.println(expediente);
        }


    }
}
