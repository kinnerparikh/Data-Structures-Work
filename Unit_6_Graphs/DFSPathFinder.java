package Unit_6_Graphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;
import java.util.Collections;

public class DFSPathFinder {
    private boolean[] marked;  // marked[v] = whether there is a path from the source to vertex 'v'
    private int[] edgeTo;      // edgeTo[v] = the last edge on the path from the source to vertex 'v'
    private final int source;  // source vertex
    
    public DFSPathFinder(Digraph G, int s) {
        this.source = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        valid(s);
        dfs(s, G);
    }
    
    private void valid(int input)
    {
        if (input < 0 || input >= marked.length)
        {
            throw new IllegalArgumentException();
        }
    }
    
    private void dfs(int input, Digraph G)
    {
        marked[input] = true;
        for (int i : G.adj(input))
        {
            if (!marked[i])
            {
                edgeTo[i] = input;
                dfs(i, G);
            }
        }
    }
    
    
    /** Returns whether there exists a directed path from the source vertex and vertex {@code v}. */
    public boolean hasPathTo(int v) {
        valid(v);
        return marked[v];
    }
    
    /** Returns a path between the source vertex {@code s} and vertex {@code v}, or {@code null} if no such path. */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != source; i = edgeTo[i])
        {
            path.push(i);
        }
        path.push(source);
        Collections.reverse(path);
        return path;
    }
}