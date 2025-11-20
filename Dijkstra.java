import java.util.*;

public class Dijkstra {

    static class Graph {
        int vertices;
        LinkedList<Edge> adjList[];

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int src, int dest, int weight) {
            adjList[src].add(new Edge(dest, weight));
            adjList[dest].add(new Edge(src, weight));
        }
    }

    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void dijkstra(Graph graph, int startVertex) {
        int vertices = graph.vertices;
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, startVertex});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDistance = current[0];
            int currentVertex = current[1];

            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for (Edge edge : graph.adjList[currentVertex]) {
                if (!visited[edge.dest]) {
                    int newDist = currentDistance + edge.weight;
                    if (newDist < distances[edge.dest]) {
                        distances[edge.dest] = newDist;
                        pq.add(new int[]{newDist, edge.dest});
                    }
                }
            }
        }

        for (int i = 0; i < vertices; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + " is not reachable from vertex " + startVertex);
            } else {
                System.out.println("Distance from vertex " + startVertex + " to vertex " + i + " is " + distances[i]);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 4);

        System.out.println("Dijkstra's Algorithm starting from vertex 0:");
        dijkstra(graph, 0);
    }
}
