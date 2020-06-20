package Projects.WordNet.src;
/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 6 WordNet
 * Revision History:
 * 5/18 @ 9:55AM - completed initial implementation of class (no testing)
 * 5/18 - added comments
 * 
 * Class Description:
 * Finding the noun that is most unlike the others
 * based on the wordnet
 */


import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.util.Scanner;

public class Outcast {
    private WordNet wordnet;
    /**
     * Initializes newly created {@code Outcast} object so
     * it represents the passed in WordNet object
     * @param wordnet the source wordnet
     */
    public Outcast(WordNet wordnet)
    {
        this.wordnet = wordnet;
    }

    /**
     * Returns the noun that is furthest from all
     * other nouns in the passed in array 
     * @param nouns array of nouns
     * @return furthest noun from all other nouns
     */
    public String outcast(String[] nouns)
    {
        int max = -1, currLen = -1;
        String retVal = "";
        // Parse through nouns
        for (String v : nouns)
        {
            currLen = 0;
            // Parse through nouns
            for (String w : nouns)
            {
                // Skips over so it doesnt calculate distance to itself
                if (!v.equals(w))
                {
                    currLen += wordnet.distance(w, v);
                }
            }

            // Defining the outcast
            if (currLen > max)
            {
                max = currLen;
                retVal = v;
            }
        }
        return retVal;
    }

    // for unit testing of this class
    public static void main(String[] args) throws Exception
    {
        // Set this to an array of outcast files to feed them all through
        // your Outcast object, OR set it to null so you can enter nouns
        // directly in the Console window
        // String[] outcastFiles = { "testInput/outcast3.txt", "testInput/outcast4.txt" };
        String[] outcastFiles = null;

        String synsetsFile = "testInput/synsets.txt";
        String hypernymsFile = "testInput/hypernyms.txt";

        WordNet wordnet = new WordNet(synsetsFile, hypernymsFile);
        Outcast outcast = new Outcast(wordnet);

        // For testing outcasts, either read the noun list from files whose
        // filenames you put in Run Configurations OR read the noun list directly
        // from the interactive console

        if (outcastFiles == null)
        {
            // Get the outcast test list interactively from the user
            Scanner console = new Scanner(System.in);
            while (true)
            {
                System.out.print("Enter a space-separated list of nouns: ");
                String[] nouns = console.nextLine().split(" ");
                StdOut.println("Outcast is: " + outcast.outcast(nouns));
            }
        }
        else
        {
            // Get the outcast test list from array
            for (int t = 0; t < outcastFiles.length; t++)
            {
                // NOTE: Although Eclipse crosses out readStrings, it's ok to use.
                String[] nouns = In.readStrings(outcastFiles[t]);
                StdOut.println(outcastFiles[t] + ": " + Arrays.toString(nouns) + " --> "
                        + outcast.outcast(nouns));
            }
        }
    }

}
