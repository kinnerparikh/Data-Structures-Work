package Unit_6_Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import edu.princeton.cs.algs4.Digraph;

public class TopologicalOrder {
    private boolean[] marked;                // marked[v] = whether v has been visited
    private List<Integer> dfsOrderVertices;  // The topological ordering
    
    public TopologicalOrder(Digraph graph) {
        marked = new boolean[graph.V()];
        dfsOrderVertices = new ArrayList<Integer>();
        for (int i = 0; i < marked.length; i++)
        {
            dfs(graph, i);
        }
    }
    
    private void dfs(Digraph graph, int v) {
        if (!marked[v])
        {
            marked[v] = true;
            for (int i : graph.adj(v))
            {
                dfs(graph, i);
            }
            dfsOrderVertices.add(v);
        }
            
    }
    
    /** Returns a valid topological ordering for the given directed graph. */
    public Iterable<Integer> topologicalOrder() {
        Collections.reverse(dfsOrderVertices);
        return dfsOrderVertices;
    }
}