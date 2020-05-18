/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 6 WordNet
 * Revision History:
 * 5/18 @ 9:55AM - completed initial implementation of class (no testing)
 * 5/18 @ 10:22AM - commented class
 * Class Description:
 * Finds the shortest ancestrial path between two nodes in the Digraph
 */


public class SAP {
    Digraph G;
 
    /**
     * @param G input digraph
     */
    public SAP(Digraph G)
    {
        if (G == null) throw new NullPointerException();
        this.G = G;
    }

    /**
     * @param v source vertex
     * @param w source vertex
     * @return total distance from source vertices to shortest common ancestor 
     */
    public int length(int v, int w)
    {
        check(v, w);
        BreadthFirstDirectedPaths dpV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths dpW = new BreadthFirstDirectedPaths(G, w);

        int ancestor = ancestor(v, w);
        // Checks if there is no ancestor
        if (ancestor < 0) return -1;
        return dpV.distTo(ancestor) + dpW.distTo(ancestor);
    }

    /**
     * @param v source vertex
     * @param w source vertex
     * @return vertex of shortest common ancestor of both inputted vertices
     */
    public int ancestor(int v, int w)
    {
        check(v, w);
        BreadthFirstDirectedPaths dpV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths dpW = new BreadthFirstDirectedPaths(G, w);

        int ancestor = -1, distance = -1;
        int currDist;
        for (int i = 0; i < G.V(); i++)
        {
            // Continue only if the current vertex is a common ancestor
            if (dpV.hasPathTo(i) && dpW.hasPathTo(i))
            {
                currDist = dpV.distTo(i) + dpW.distTo(i);
                // changes distance first time
                if (distance < 0)
                {
                    distance = currDist;
                }
                if (currDist <= distance)
                {
                    distance = currDist;
                    ancestor = i;
                }
            }
        }

        return ancestor;
    }
    
    /**
     * @param v list of source vertices
     * @param w list of source vertices
     * @return the shortest distance between one of the vertices in each list of source vertices 
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        check(v, w);
        BreadthFirstDirectedPaths dpV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths dpW = new BreadthFirstDirectedPaths(G, w);

        // finds common ancestor of vertices
        int ancestor = ancestor(v, w);
        if (ancestor < 0) return -1;
        return dpV.distTo(ancestor) + dpW.distTo(ancestor);
    }

    /**
     * @param v list of source vertices
     * @param w list of source vertices
     * @return the shortest common ancestor between one of the vertices in each list of source vertices
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        check(v, w);
        BreadthFirstDirectedPaths dpV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths dpW = new BreadthFirstDirectedPaths(G, w);

        int ancestor = -1, distance = -1;
        int currDist;
        // Loop thorugh all vertices
        for (int i = 0; i < G.V(); i++)
        {
            // Continue only if the current vertex is a common ancestor
            if (dpV.hasPathTo(i) && dpW.hasPathTo(i))
            {
                currDist = dpV.distTo(i) + dpW.distTo(i);
                // Changes distnace only first time
                if (distance < 0)
                {
                    distance = currDist;
                }
                if (currDist <= distance)
                {
                    distance = currDist;
                    ancestor = i;
                }
            }
        }

        return ancestor;
    }

    /**
     * Throws an exception if vertices are illegal
     * 
     * @param v source vertex
     * @param w source vertex
     */
    private void check(int v, int w)
    {
        if (v < 0 || w < 0 ||
            v > G.V() - 1  || 
            w > G.V() - 1) throw new IllegalArgumentException();
    }

    /**
     * Thorws an exception if any of the vertices are illegal
     * 
     * @param v source vertex
     * @param w source vertex
     */
    private void check(Iterable<Integer> v, Iterable<Integer> w)
    {
        for (int i : v)
        {
            for (int j : w)
            {
                check(i , j);
            }
        }
    }

    public static void main(String[] args)
    {
        String digraphFile = "testInput/synsets3.txt";

        In in = new In(digraphFile);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty())
        {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
