package Unit_6_Graphs;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph {
    private int V;  // Number of vertices.
    private int E;  // Number of edges.
    private List<Integer>[] edges;
    
    // DELETE THIS WHEN YOU COPY
    @SuppressWarnings("unchecked") // <-- !!!!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    /** Initializes a graph with {@code V} vertices. */
    public AdjacencyListGraph(int V) {
        if (V < 0) throw new IllegalArgumentException();
        this.V = V;
        this.E = 0;
        this.edges = (List<Integer>[]) new List[V];
        for (int i = 0; i < V; i++)
        {
            edges[i] = new ArrayList<Integer>();
        }
    }
    
    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        if ((0 > v || v >= V) || (0 > w || w >= V)) throw new IllegalArgumentException();
        if (v != w)
        {
            edges[w].add(v);
        }
        edges[v].add(w);
        E++;
        
    }
    
    /** Returns the vertices adjacent to vertex {@code v}. */
    public Iterable<Integer> adj(int v) {
        if (0 > v || v >= V) throw new IllegalArgumentException();
        //List<Integer> arr = new ArrayList<Integer>();
        return edges[v];
    }
    
    /** Returns the number of edges in this graph. */
    public int E() {
        return this.E;
    }
    
    /** Returns the number of vertices in this graph. */
    public int V() {
        return this.V;
    }
}