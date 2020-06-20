package Unit_6_Graphs;

import edu.princeton.cs.algs4.Graph;

public class ConnectedComponents {
    private boolean[] marked; // marked[v] = whether v has been visited
    private int[] id;         // id[v] = id of connected component containing v
    private int count;        // number of connected components <-- automatic initializes to 0
    // YOU MAY ADD MORE MEMBER VARIABLES IF YOU NEED TO.
    private int numV;

    public ConnectedComponents(Graph graph) {
        // Initializations
        numV = graph.V();
        marked = new boolean[numV];
        id = new int[numV];
        // Loops through all vertices
        for(int i = 0; i < numV; i++)
        {
            if (!marked[i])
            {
                dfs(graph, i);
                count++;
            }
        }
    }
    
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for(int i : graph.adj(v))
        {
            // Ensures circular connected components dont run infinitely
            if (!marked[i]){
                dfs(graph, i);
            }
        }
    }
    
    /** Returns number of connected components. */
    public int count() {
        return this.count;
    }
    
    /** Returns whether the two given vertices {@code v} and {@code w} are in the same connected component. */
    public boolean connected(int v, int w) {
        // This is gross
        if (!(0 <= v && v < numV && 0 <= w && w < numV)) throw new IllegalArgumentException();
        return id[v] == id[w];
    }
}