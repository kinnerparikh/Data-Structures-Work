package Unit_6_Graphs;

import java.util.Collections;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DijkstraShortestPaths {
    private Double[] distanceTo;
    private int[] previous;
    private IndexMinPQ<Double> pq;
    private int numV;
    private int source;

    public DijkstraShortestPaths(EdgeWeightedDigraph graph, int source) {
        numV = graph.V();
        this.source = source;
        previous = new int[numV];
        pq = new IndexMinPQ<Double>(numV);
        
        
        // initializing distTo
        distanceTo = new Double[numV];
        for (int i = 0; i < numV; i++)
        {
            distanceTo[i] = Double.POSITIVE_INFINITY;
        }
        distanceTo[source] = 0.0;
        
        pq.insert(source, distanceTo[source]);
        while(!pq.isEmpty())
        {
            int temp = pq.delMin();
            for (DirectedEdge de : graph.adj(temp))
            {
                relax(de, temp);
            }
        }
    }
    
    private void relax(DirectedEdge edge, int min)
    {
        int to = edge.to();
        double weight = edge.weight();
        if (distanceTo[min] < distanceTo[to] - weight)
        {
            distanceTo[to] = distanceTo[min] + weight;
            previous[to] = min;
            if (pq.contains(to)) pq.decreaseKey(to, distanceTo[to]);
            else pq.insert(to, distanceTo[to]);
        }
    }
    
    public double getDistanceTo(int v) {
        check(v);
        return distanceTo[v];
    }
    
    public Iterable<Integer> getPathTo(int v) {
        check(v);
        if (distanceTo[v] >= Double.POSITIVE_INFINITY) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != source; i = previous[i])
        {
            path.push(i);
        }
        path.push(0);
        Collections.reverse(path);
        return path;
    }
        
    private void check(int v)
    {
        if (!(0 <= v && v < numV)) throw new IllegalArgumentException();
    }
}