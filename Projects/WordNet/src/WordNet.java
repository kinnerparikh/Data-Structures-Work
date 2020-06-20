package Projects.WordNet.src;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordNet
{
	private Digraph finalDigraph;
	private HashMap<String, ArrayList<Integer>> hashMapWord;
	private HashMap<Integer, String> hashMapID;
	private SAP path;
	
	//constructor
    public WordNet(String synsets, String hypernyms)
    {
    	checkNull(synsets);
    	checkNull(hypernyms);
    	
        // TODO: You may use the code below to open and parse the
        // synsets and hypernyms file. However, you MUST add your
        // own code to actually store the file contents into the
        // data structures you create as fields of the WordNet class.

    	hashMapWord = new HashMap<String, ArrayList<Integer>>();
    	hashMapID = new HashMap<Integer, String>();
    	
        // Parse synsets
        int largestId = -1; // TODO: You might find this value useful
        In inSynsets = new In(synsets);
        
        while (inSynsets.hasNextLine())
        {
            String line = inSynsets.readLine();
            String[] tokens = line.split(",");

            // Synset ID
            int id = Integer.parseInt(tokens[0]);
            if (id > largestId)
            {
                largestId = id;
            }

            // Nouns in synset
            String synset = tokens[1];
            String[] nouns = synset.split(" ");
            for (String noun : nouns)
            {
                ArrayList<Integer> idList = new ArrayList<Integer>();
                if(hashMapWord.containsKey(noun))
                {
                	idList = hashMapWord.get(noun);
                }
                idList.add(id);
                hashMapWord.put(noun, idList);
            }

            // tokens[2] is gloss, but we're not using that
        }
        inSynsets.close();

        // Parse hypernyms
        In inHypernyms = new In(hypernyms);
        while (inHypernyms.hasNextLine())
        {
            String line = inHypernyms.readLine();
            String[] tokens = line.split(",");

            int v = Integer.parseInt(tokens[0]);

            for (int i = 1; i < tokens.length; i++)
            {
                finalDigraph.addEdge(v, Integer.parseInt(tokens[i]));
            }
        }
        
        path = new SAP(finalDigraph);
        
        //check if dag
    	DirectedCycle dc = new DirectedCycle(finalDigraph);
    	if(dc.hasCycle()) 
            throw new IllegalArgumentException();

        int roots = 0;
        for (int i = 0; i < finalDigraph.V(); i++)
        {
            if(!finalDigraph.adj(i).iterator().hasNext())
            {
                roots++;
            }
            if (roots > 1) throw new IllegalArgumentException();
        }
        
        inHypernyms.close();
    }

    public Iterable<String> nouns()
    {
        return hashMapWord.keySet();
    }

    public boolean isNoun(String word)
    {
    	checkNull(word);
        return hashMapWord.containsKey(word);
    }

    public int distance(String nounA, String nounB)
    {
    	SAPandDistanceExceptions(nounA, nounB);
        ArrayList<Integer> vA = hashMapWord.get(nounA);
        ArrayList<Integer> vB = hashMapWord.get(nounB);
        
        return path.length(vA, vB);
     
    }

    public String sap(String nounA, String nounB)
    {
    	SAPandDistanceExceptions(nounA, nounB);
    	ArrayList<Integer> vA = hashMapWord.get(nounA);
        ArrayList<Integer> vB = hashMapWord.get(nounB);
        
        return hashMapID.get(path.ancestor(vA, vB));
    }

    private void testNouns(String nounA, String nounB)
    {
        System.out.print("'" + nounA + "' and '" + nounB + "': ");
        System.out.print("sap: '" + sap(nounA, nounB));
        System.out.println("', distance=" + distance(nounA, nounB));
    }

    private void checkNull(String param)
    {
    	if(param == null)
    			throw new NullPointerException();
    }
    
    private void SAPandDistanceExceptions(String nounA, String nounB)
    {
    	checkNull(nounA);
    	checkNull(nounB);
    	
    	if(!isNoun(nounA) || !isNoun(nounB))
    		throw new IllegalArgumentException();
    }
    
    // for unit testing of this class
    public static void main(String[] args)
    {
        String synsetsFile = "testInput/synsets.txt";
        String hypernymsFile = "testInput/hypernyms.txt";

        WordNet wordnet = new WordNet(synsetsFile, hypernymsFile);
        wordnet.testNouns("municipality", "region");
        wordnet.testNouns("individual", "edible_fruit");
        wordnet.testNouns("Black_Plague", "black_marlin");
        wordnet.testNouns("American_water_spaniel", "histology");
        wordnet.testNouns("Brown_Swiss", "barrel_roll");

        wordnet.testNouns("chocolate", "brownie");
        wordnet.testNouns("cookie", "brownie");
        wordnet.testNouns("martini", "beer");
    }
}
