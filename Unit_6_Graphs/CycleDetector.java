package Unit_6_Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import edu.princeton.cs.algs4.Digraph;

public class CycleDetector {
    private boolean[] marked;     // marked[v] = whether v has been visited
    private int[] edgeTo;         // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;    // onStack[v] = whether the vertex is on the stack
    private List<Integer> cycle;  // directed cycle (or null if no such cycle)

    public CycleDetector(Digraph graph) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        onStack = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++)
        {
            if (!marked[i] && cycle == null)
            {
                dfs(graph, i);
            }
        }
    }
    
    private void dfs(Digraph graph, int vertex) {
        marked[vertex] = onStack[vertex] = true;
        for (int currVertex : graph.adj(vertex))
        {
            if (cycle != null) return;
            else if (!marked[currVertex]) 
            {
                edgeTo[currVertex] = vertex;
                dfs(graph, currVertex);
            }
            else if (onStack[currVertex])
            {
                cycle = new ArrayList<Integer>();
                for (int j = vertex; j != currVertex; j = edgeTo[j])
                {
                    cycle.add(j);
                }
                cycle.add(currVertex);
                cycle.add(vertex);
            }
        }
        onStack[vertex] = false;
    }
    
    /** Returns whether the directed graph contains a cycle. */
    public boolean hasCycle() {
        return cycle != null;
    }
    
    /** Returns the vertices of a directed cycle in the graph and null if there is no cycle */
    public Iterable<Integer> cycle() {
        if (cycle == null) return null;
        Collections.reverse(cycle);
        return cycle;
    }
}