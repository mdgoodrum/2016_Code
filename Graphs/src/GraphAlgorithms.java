
import java.util.*;

/**
 * Your implementations of various graph algorithms.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Cannot search using null graph "
                    + "or starting point.");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList =
                graph.getAdjacencyList();
        if (!adjacencyList.containsKey(start)) {
            throw new IllegalArgumentException("Start not present "
                    + "in graph " + graph);
        }
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(start);
        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(start);
        List<Vertex<T>> bfs = new LinkedList<>();
        bfs.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> n = queue.remove();
            List<VertexDistancePair<T>> list = adjacencyList.get(n);
            for (int x = 0; x < list.size(); x++) {
                Vertex<T> vert = list.get(x).getVertex();
                if (!visited.contains(vert)) {
                    visited.add(vert);
                    bfs.add(vert);
                    queue.add(vert);
                }
            }
        }
        return bfs;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Cannot search using null "
                    + "graph or starting point.");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList =
                graph.getAdjacencyList();
        if (!adjacencyList.containsKey(start)) {
            throw new IllegalArgumentException(
                    "Start not present in graph " + graph);
        }
        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(start);
        List<Vertex<T>> dfs = new LinkedList<>();
        dfs.add(start);
        List<VertexDistancePair<T>> list = adjacencyList.get(start);
        for (int x = 0; x < list.size(); x++) {
            Vertex<T> vert = list.get(x).getVertex();
            if (!visited.contains(vert)) {
                depthFirstSearch(vert, graph, visited, dfs);
            }
        }
        return dfs;
    }

    /**
     * recursive helper method for dfs
     * @param vert vertex currently accessed recursively
     * @param graph graph we are iterating
     * @param visited visited vertexes
     * @param dfs final list to be returned
     * @param <T> generic type
     */
    public static <T> void depthFirstSearch(
            Vertex<T> vert,
            Graph<T> graph,
            Set<Vertex<T>> visited, List<Vertex<T>> dfs) {
        visited.add(vert);
        dfs.add(vert);
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList =
                graph.getAdjacencyList();
        List<VertexDistancePair<T>> list = adjacencyList.get(vert);
        for (int x = 0; x < list.size(); x++) {
            Vertex<T> verty = list.get(x).getVertex();
            if (!visited.contains(verty)) {
                depthFirstSearch(verty, graph, visited, dfs);
            }
        }
    }

    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        return null;
    }

    /**
     * Run Kruskal's algorithm on the given graph and return the minimum
     * spanning tree in the form of a set of Edges. If the graph is
     * disconnected, and therefore there is no valid MST, return null.
     *
     * You may assume that there will only be one valid MST that can be formed.
     * In addition, only an undirected graph will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, {@code java.util.Map} and any class from java.util
     * that implements the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        Set<Edge<T>> edges = graph.getEdgeList();

        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyList =
                graph.getAdjacencyList();


    }
}