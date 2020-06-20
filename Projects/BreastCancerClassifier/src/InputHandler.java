package Projects.BreastCancerClassifier.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * InputHandler processes all input files and also
 * prints the accuracy of results. 
 */
public class InputHandler {
	static int CLASSIFICATION_INDEX = 10;

	/**
	 * Returns a two dimensional int array corresponding to a 
	 * csv file (defined by filename) of ints. 
	 */
	public static int[][] populateData(String filename)  {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			String currentLine;
			
			while ((currentLine = br.readLine()) != null) {
				lines.add(currentLine);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int [][] data;
		if(!lines.isEmpty())
		{
			int numLines = lines.size();
			String[] first = lines.get(0).split(",");
			data = new int [numLines][first.length];
			for(int i = 0; i < numLines; i++) {
				String[] parts = lines.get(i).split(",");
				for(int j = 0; j < first.length; j++)
				{
					data[i][j] = Integer.parseInt(parts[j]);
				}
			}
		} else {
			data = new int[0][0];
		}
		
		return data;
	}
	
	/**
	 * printAccuracy prints the accuracy of the results stored in myResults
	 * given the actual results from testData. 
	 */
	public static void printAccuracy(int[] myResults, int[][] testData) {
		if(myResults.length != testData.length) 
			throw new java.lang.Error("Please provide exactly one classification for each test instance.");
		
		int totalTestInstances = testData.length;
		double correctClassifications = 0;
		
		for(int i = 0; i < totalTestInstances; i++) {
			if(myResults[i] == testData[i][CLASSIFICATION_INDEX]) 
				correctClassifications++;
		}
		
		System.out.printf("Percent Accuracy: %.2f", (correctClassifications/totalTestInstances*100));
		System.out.println("%");
		
	}
	
}
