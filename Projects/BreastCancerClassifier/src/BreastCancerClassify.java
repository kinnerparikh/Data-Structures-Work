package Projects.BreastCancerClassifier.src;

/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 0 Breast Cancer Classifier
 * Revision History:
 *    09/09/19 -
 *    09/10/19 -
 *    09/11/19 - 
 * 
 * BreastCancerClassify contains the core implementation of the 
 * kNearestNeighbors algorithm to classify cell clumps as malignant
 * or benign. 
 * 
 * Work on the functions in the following order:
 *  0) author
 * 	1) calculateDistance - once you finish this, you should see a
 * 	   graph of distances appear!
 * 	2) getAllDistances
 * 	3) findKClosestEntries
 * 	4) classify
 *  5) kNearestNeighbors (use your helpers correctly!)
 *  6) getAccuracy
 */

public class BreastCancerClassify {
	
	// Constants
	static int K = 5;
	static int BENIGN = 2;
	static int MALIGNANT = 4;
	
	/**
	 * calculateDistance computes the distance between the two data
	 * parameters. The distance is found by taking the difference in each 
	 * "coordinate", squaring it, adding all of those, and then taking the 
	 * square root of the result. 
	 * 
	 * Remember to exclude the patient ID and the tumor classification
	 * 
	 * For example: 
	 * [12345, 6, 4, 4, MALIGNANT]
	 * [22344, 2, 8, 3, BENIGN]
	 * 
	 * distance = sqrt((6-2)^2 + (4-8)^2 + (4-3)^2)
	 */
	public static double calculateDistance(int[] first, int[] second)
	{
		// Value for squared numbers
		double base = 0;
		
		// Iterates through the array
		for(int i = 1; i < first.length-1; i++) {
			// Add the squared number to base variable
			base += Math.pow((first[i]-second[i]), 2);
		}
		
		// Return statement
		return Math.sqrt(base);
	}
	
	/**
	 * getAllDistances creates an array of doubles with the distances
	 * to each training instance. The double[] returned should have the 
	 * same number of instances as trainData. 
	 */
	public static double[] getAllDistances(int[][] trainData, int[] testInstance)
	{
		// Initialize allDistances to the column size of trainData[][]
		double[] allDistances = new double[trainData[0].length];
		
		// Iterate through the columns of the trainData array
		for(int col = 0; col < trainData[0].length; col++) {
			// Iterate through the rows of trainData[][]
			for(int row = 0; row < trainData.length; row++) {
				// Calculate distance and store returned value to AllDistances[]
				allDistances[col] = calculateDistance(testInstance, trainData[row]);
			}
		}
		
		// Return statement
		return allDistances;
	}
	
	/**
	 * findKClosestEntries finds and returns the indexes of the 
	 * K closest distances in allDistances. Return an array of size K, 
	 * that is filled with the indexes of the closest distances (not
	 * the distances themselves). 
	 * 
	 * Be careful! This function can be tricky.
	 */
	public static int[] findKClosestEntries(double[] allDistances0)
	{
		// New arrays
		double[] allDistances = allDistances0;
		int[] kClosestIndexes = new int[K];	
		
		// Vars
		double lowestVal = Double.MAX_VALUE;
		int lowestIndex = -1;
		
		// Iterates through kClosestIndexes[]
		for (int i = 0; i < kClosestIndexes.length; i++) {
			// Iterates through allDistances[]
			for (int j = 0; j < allDistances.length; j++) {
				// If the distance value is lower than lowestVal
				if (allDistances[j] < lowestVal) {
					// set lowestVal to new lowest distance
					lowestVal = allDistances[j];
					// Set lowest index to current index
					lowestIndex = j;
				}
			}
			
			// add lowestIndex to kClosesetIndexes
		    kClosestIndexes[i] = lowestIndex;
		    // Set lowest value in allDistancesp[] to high value
		    allDistances[lowestIndex] = Double.MAX_VALUE;
		    // reset lowestVal
			lowestVal = Double.MAX_VALUE;
			// reset lowestIndex
			lowestIndex = -1;
		}
		
		// Return statement
		return kClosestIndexes;
	}
	
	public static int findMax(int[] vals, double[] allDistances) {
		int retVal = 0;
		double maxVal = Double.MIN_VALUE;
		
		for (int i = 0; i < vals.length; i++) {
			if (allDistances[vals[i]] > maxVal) {
				maxVal = allDistances[vals[i]];
				retVal = i;
			}
		}
		
		return retVal;
	}
	
	/**
	 * classify makes a decision as to whether an instance of testing 
	 * data is BENIGN or MALIGNANT. The function makes this decision based
	 * on the K closest train data instances (whose indexes are stored in 
	 * kClosestIndexes). If more than half of the closest instances are 
	 * malignant, classify the growth as malignant. Otherwise classify
	 * as benign.
	 * 
	 * Return one of the global integer constants defined in this function. 
	 */
	public static int classify(int[][] trainData, int[] kClosestIndexes)
	{
		// variables
		int fours = 0;
		int twos = 0;
		
		// Iterates through kClosestIndexes
		for (int i = 0; i < kClosestIndexes.length; i++) {
			// If the last value in the column of array trainData[][] equals 2, add one to twos
			if (trainData[0][trainData[0].length - 1] == 2) twos++;
			// If the last value in the column of array trainData[][] equals 4, add one to fours
			else if (trainData[0][trainData[0].length - 1] == 4) fours++;
		}
		
		// If MALIGNANT is more present than BENIGN
		if (fours > twos) {
			// Return MALIGNANT 
			return MALIGNANT;
		}
		
		// Returns BENIGN constant
		return BENIGN;
	}
	
	/**
	 * kNearestNeighbors classifies all the data instances in testData as 
	 * BENIGN or MALIGNANT using the helper functions you wrote and the kNN 
	 * algorithm.
	 * 
	 * For each instance of your test data, use your helper methods to find the
	 * K closest points, and classify your result based on that!
	 * @param trainData: all training instances
	 * @param testData: all testing instances
	 * @return: int array of classifications (BENIGN or MALIGNANT)
	 */
	public static int[] kNearestNeighbors(int[][] trainData, int[][] testData){
		int[] myResults = new int[testData.length];
		
		// Iterates through testData[][]
		for (int i = 0; i < testData.length; i++) {
			// Sets myResults to the classication of the points
			myResults[i] = classify(trainData, findKClosestEntries(getAllDistances(trainData, testData[i])));
		}
		
		// Return statement
		return myResults;
	}

	/**
	 * printAccuracy returns a String representing the classification accuracy.
	 *
	 * The output String should be rounded to two decimal places followed by the % symbol.
	 * For example, if 4 out of 5 outcomes were correctly predicted, the returned String should be: "80.00%"
	 * For example, if 3 out of 9 outcomes were correctly predicted, the returned String should be: "33.33%"
	 * For example, if 6 out of 9 outcomes were correctly predicted, the returned String should be: "66.67%"
	 * Look up Java's String Formatter to learn how to round a double to two-decimal places.
	 *
	 * This method should work for any data set, given that the classification outcome is always listed
	 * in the last column of the data set.
	 * @param: myResults: The predicted classifcations produced by your KNN model
	 * @param: testData: The original data that contains the true classifications for the test data.
	 */
	public static String getAccuracy(int[] myResults, int[][] testData) {
		
		double numCorr = 0;
		
		// Iterate through testData[][]
		for (int i = 0; i < testData.length; i++) {
			// If the results match up with testData, add to numCorr
			if (myResults[i] == testData[i][testData[0].length - 1])
				numCorr++;
		}
		
		// Calculate accuracy percentage 
		double percent = (numCorr / testData.length) * 100;
		
		// Formats and returns percentage
		return String.format("%.2f", percent) + "%";
	}
	
	
	//DO NOT MODIFY THE MAIN METHOD
	public static void main(String[] args) {

		int[][] trainData = InputHandler.populateData("./datasets/train_data.csv");
		int[][] testData = InputHandler.populateData("./datasets/test_data.csv");
		
		//Display the distances between instances of the train data. 
		//Points in the upper left corner (both benign) or in the bottom
		//right (both malignant) should be darker. 
		Grapher.createGraph(trainData);

		int[] myResults = kNearestNeighbors(trainData, testData);

		System.out.println("Model Accuracy: " + getAccuracy(myResults, testData));
	}

}