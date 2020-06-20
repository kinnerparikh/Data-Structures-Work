package Unit_6_Graphs;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph {
    private int V;  // Number of vertices.
    private int E;  // Number of edges.
    private int[][] edges;
    
    /** Initializes a graph with {@code V} vertices. */
    public AdjacencyMatrixGraph(int V) {
        if (V < 0) throw new IllegalArgumentException();
        this.V = V;
        this.E = 0;
        this.edges = new int[V][V];
    }
    
    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        if ((0 > v || v >= V) || (0 > w || w >= V)) throw new IllegalArgumentException();
        edges[v][w] = 1;
        edges[w][v] = 1;
        E++;
    }
    
    /** Returns the vertices adjacent to vertex {@code v}. */
    public Iterable<Integer> adj(int v) {
        if (0 > v || v >= V) throw new IllegalArgumentException();
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < V; i++)
        {
            if (edges[v][i] == 1) arr.add(i);
        }
        return arr;
    }
    
    /** Returns the number of edges in this graph. */
    public int E() {
        return E;
    }
    
    /** Returns the number of vertices in this graph. */
    public int V() {
        return V;
    }
}