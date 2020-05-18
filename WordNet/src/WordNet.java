/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 6 WordNet
 * Revision History:
 * 5/18 @ 9:55AM - completed initial implementation of class (no testing)
 * 5/18 @ 10:06AM - attempt to fix constructor
 * 5/18 @ 10:12 AM - added a cycle detector
 * 5/18 @ 10:29 AM - added a multi-root detector
 * 5/18 @ 11:21 AM - Fixed exception handling
 * 5/18 - Added comments
 * 
 * Class Description:
 * Builds the hierarchy of hypernyms
 */

import java.io.FileReader;
import java.io.BufferedReader;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class WordNet {
    private Digraph G;
    private HashMap<Integer, String> synsetMap; //Stores ID and term
    private HashMap<String, ArrayList<Integer>> nounsMap;

    /**
     * Constructor for {@code WordNet} object
     * @param synsets file location of synsets
     * @param hypernyms file location of hypernyms
     */
    public WordNet(String synsets, String hypernyms) 
    {
        this.G = null;
        try {
            // File reader
            BufferedReader br = new BufferedReader(new FileReader(synsets));
            synsetMap = new HashMap<Integer, String>();
            nounsMap = new HashMap<String, ArrayList<Integer>>();

            String currLine = br.readLine(); // Reads the line in the file
            while (currLine != null)
            {
                String[] currParts = currLine.split(","); // Gets different parts of line
                int currID = Integer.parseInt(currParts[0]); // ID (integer) is the first value  
                synsetMap.put(currID, currParts[1]);

                // Separates individual synonyms and adds them to synsetMap
                for (String noun : currParts[1].split(" ")) // Iterate through synonyms
                {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    if (nounsMap.containsKey(noun))
                    {
                        list = nounsMap.get(noun);
                    }
                    list.add(currID);
                    nounsMap.put(noun, list);
                }
                currLine = br.readLine();
            }
            br.close();

            Digraph G = new Digraph(synsetMap.size());
            this.G = G;
            br = new BufferedReader(new FileReader(hypernyms));
            
            currLine = br.readLine(); // Reads line in file
            while (currLine != null)
            {
                String[] currParts = currLine.split(",");
                int currID = Integer.parseInt(currParts[0]);
                // Adds synset's hypernyms IDs to Digraph
                for (int i = 1; i < currParts.length; i++)
                {
                    G.addEdge(currID, Integer.parseInt(currParts[i]));
                }

                currLine = br.readLine();
            }
            br.close();
        } catch (Exception e) {
        }

        if (this.G != null)
        {
            // Cycle detection
            CycleDetector cd = new CycleDetector(G);
            if (cd.hasCycle()) throw new IllegalArgumentException();

            // Multi-root detection
            int numRoots = 0;
            for (int i = 0; i < G.V(); i++)
            {
                if (!G.adj(i).iterator().hasNext())
                {
                    numRoots++;
                }
                if (numRoots > 1) throw new IllegalArgumentException();
            }
        }
    }

    // My code from the CycleDetector lab
    private class CycleDetector
    {
        private boolean[] marked;     // marked[v] = whether v has been visited
        private int[] edgeTo;         // edgeTo[v] = previous vertex on path to v
        private boolean[] onStack;    // onStack[v] = whether the vertex is on the stack
        private List<Integer> cycle;  // directed cycle (or null if no such cycle)
    
        /**
         * @param graph input Digraph
         */
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
        
        /**
         * Finds cycles that inputted vertex is on
         * @param graph input Digraph
         * @param vertex input 
         */
        private void dfs(Digraph graph, int vertex) {
            marked[vertex] = onStack[vertex] = true; 
            for (int currVertex : graph.adj(vertex)) // Iterates through all adjacent vertices
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
        
        //Returns whether the directed graph contains a cycle.
        public boolean hasCycle() {
            return cycle != null;
        }
    }

    /**
     * Returns all nouns
     * @return keyset of nouns
     */
    public Iterable<String> nouns()
    {
        return nounsMap.keySet();
    }

    /**
     * Checks if word is a noun 
     * @param word input value to be checked
     * @return if inputted value is anoun
     */
    public boolean isNoun(String word)
    {
        return nounsMap.containsKey(word);
    }

    /**
     * Finds the total distance between both inputted strings
     * @param nounA input noun
     * @param nounB input noun
     * @return total distance between inputted strings 
     */
    public int distance(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        SAP sapObject = new SAP(G);
        ArrayList<Integer> listA = nounsMap.get(nounA);
        ArrayList<Integer> listB = nounsMap.get(nounB);
        return sapObject.length(listA, listB);
    }

    /**
     * The shortest common ancestor of inputted strings
     * @param nounA input noun
     * @param nounB input noun
     * @return shortest common ancestor
     */
    public String sap(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        SAP sapObject = new SAP(G);
        ArrayList<Integer> listA = nounsMap.get(nounA);
        ArrayList<Integer> listB = nounsMap.get(nounB);
        return synsetMap.get(sapObject.ancestor(listA, listB));
    }

    private void testNouns(String nounA, String nounB)
    {
        System.out.print("'" + nounA + "' and '" + nounB + "': ");
        System.out.print("sap: '" + sap(nounA, nounB));
        System.out.println("', distance=" + distance(nounA, nounB));
    }

    // for unit testing of this class
    public static void main(String[] args)
    {
        String synsetsFile = "../testInput/synsets3.txt";
        String hypernymsFile = "../testInput/hypernyms3InvalidCycle.txt";

        try {
            WordNet wordnet = new WordNet(synsetsFile, hypernymsFile);
            wordnet.testNouns("municipality", "region");
            wordnet.testNouns("individual", "edible_fruit");
            wordnet.testNouns("Black_Plague", "black_marlin");
            wordnet.testNouns("American_water_spaniel", "histology");
            wordnet.testNouns("Brown_Swiss", "barrel_roll");

            wordnet.testNouns("chocolate", "brownie");
            wordnet.testNouns("cookie", "brownie");
            wordnet.testNouns("martini", "beer");
        } catch (Exception e) {
            throw e;
        }
    }
}
