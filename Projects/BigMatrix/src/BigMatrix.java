package Projects.BigMatrix.src;
/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 4 BigMaze
 * Revision History:
 * 2/11 - Added Main Method (12:43 PM)
 * 2/11 - VS Code Run Capabilities (4:33 PM)
 * 2/13 - Completed setValue and all getEmpty methods (12:50 AM)
 * 2/13 - Completed all methods, toString method, and tester class (1:47 AM)
 * 2/13 - Attempt at fixing setValue -> unsuccessful (2:04 AM)
 * 2/13 - Fixed setValue -> dummy copy pasta mistake (10:11 AM)
 * 2/13 - Added multiply matrix (11:59 AM)
 * 2/13 - Individual method commenting (1:20 PM)
 * 2/18 - More Commenting (8:05PM)
 * 
 * Class Description: 
 * This class creates an object that represents a matrix
 * that only uses memory for cells that have a non-zero 
 * value through the use of HashMaps, making the representation
 * far more efficient in run time and memory. 
*/

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class BigMatrix 
{
	private HashMap<Integer, HashMap<Integer, Integer>> rowsHMap;
	private HashMap<Integer, HashMap<Integer, Integer>> colsHMap;
	public BigMatrix()
	{
		rowsHMap = new HashMap<Integer, HashMap<Integer, Integer>>();
		colsHMap = new HashMap<Integer, HashMap<Integer, Integer>>();
	}
	
	/**
	 * This method is used to create a passed in value at the specified
	 * row and column in the BigMatrix
	 * @param row desired row in which value is to be placed
	 * @param col desired column in which value is to be placed
	 * @param value the value that will be placed at desired row and column
	 */
	public void setValue(int row, int col, int value)
	{
		if (value == 0 && getValue(row, col) == 0) return;

		if(!rowsHMap.containsKey(row)) {
			HashMap<Integer, Integer> currRowHMap = new HashMap<Integer, Integer>();
			currRowHMap.put(col, value);
			rowsHMap.put(row, currRowHMap);
		} 
		else {
			if (value == 0) {
				rowsHMap.get(row).remove(col);	
				if (rowsHMap.get(row).isEmpty()) rowsHMap.remove(row);
			} 
			else rowsHMap.get(row).put(col, value);
		}

		if(!colsHMap.containsKey(col)) 
		{
			HashMap<Integer, Integer> currColHMap = new HashMap<Integer, Integer>();
			currColHMap.put(row, value);
			colsHMap.put(col, currColHMap);
		} 
		else 
		{
			if (value == 0) 
			{
				colsHMap.get(col).remove(row);
				if (colsHMap.get(col).isEmpty()) colsHMap.remove(col); 
			} 
			else colsHMap.get(col).put(row, value);
		}
	}
	
	/**
	 * Gets the value at any index
	 * @param row The row where the desired values exists
	 * @param col The column where the desired value exists
	 * @return The value at the index, or 0 if a value does not exist
	 */
	public int getValue(int row, int col)
	{
		// Confirms that a value exists at the coordinates
		if (rowsHMap.containsKey(row) && rowsHMap.get(row).containsKey(col)) 
		{
			return rowsHMap.get(row).get(col);
		}
		// Case only if value doesn't exist at given coordinates
		return 0;
	}
	
	/**
	 * This method gets the key of every row that is not empty
	 * @return An ArrayList of keys of every row that is not empty
	 */
	public List<Integer> getNonEmptyRows()
	{
		// The ArrayList constructor is overloaded so that it can 
		// build an ArrayList that is initalized with the elements
		// of a collection, like the set passed here
		return new ArrayList<Integer>(rowsHMap.keySet());
	}

	/**
	 * This method gets the key of very column that is not empty
	 * @return An ArrayList of keys of every column that is not empty
	 */
	public List<Integer> getNonEmptyCols()
	{
		// The ArrayList constructor is overloaded so that it can 
		// build an ArrayList that is initalized with the elements
		// of a collection, like the set passed here
		return new ArrayList<Integer>(colsHMap.keySet());
	}
	
	/**
	 * This method gets the key of every row in a specified column that is not empty
	 * @param col The specified columnn to find which rows are not empty
	 * @return An ArrayList of keys of every row in the specified column that is not empty
	 */
	public List<Integer> getNonEmptyRowsInColumn(int col)
	{
		return new ArrayList<Integer>(colsHMap.get(col).keySet());
	}
	
	/**
	 * This method get the key of every column in a speficied row that is not empty
	 * @param row The specified row to find which columns are not empty
	 * @return An ArrayList of keys of every column in the specified row that is not empty
	 */
	public List<Integer> getNonEmptyColsInRow(int row)
	{
		return new ArrayList<Integer>(rowsHMap.get(row).keySet());
	}

	/**
	 * This method calculates the sum of all values in a specified row
	 * @param row The specified row
	 * @return An int of the sum of all values in the passed in row
	 */
	public int getRowSum(int row)
	{
		int sum = 0;
		// Confirms that it is a valid row
		if (rowsHMap.containsKey(row))
		{
			// Iterate through the values in the desired row
			for (Integer currValue : rowsHMap.get(row).values())
			{
				sum += currValue;
			}
		}
		return sum;
	}
	
	/**
	 * This methods calculates the sum of all values in a specified column
	 * @param col The specified column
	 * @return An int of the sum of all values in the passed column
	 */
	public int getColSum(int col)
	{
		int sum = 0;
		// Confirms that it is a valid column
		if (colsHMap.containsKey(col))
		{
			// Iterate through the values in the desired column
			for (Integer currValue : colsHMap.get(col).values())
			{
				sum += currValue;
			}
		}
		return sum;
	}
	
	/**
	 * This method calculates the sum of all values
	 * @return An int of the sum of all values
	 */
	public int getTotalSum()
	{
		int sum = 0;
		
		// Iterate through non-empty rows
		for (Integer currRow : getNonEmptyRows())
		{
			// Adding the sum of the current row to sum
			sum += getRowSum(currRow);
		}
		return sum;
	}
	
	/**
	 * This method returns a matrix that has been multiplied by a constant 
	 * @param constant The constant that the matrix will be multiplied by
	 * @return A BigMatrix where all values have been multiplied by the constant
	 */
	public BigMatrix multiplyByConstant(int constant)
	{
		BigMatrix tempBigMatrix = new BigMatrix();
		HashMap<Integer, Integer> currRowHMap;

		// Iterate through rows
		for (Integer row : getNonEmptyRows())
		{
			currRowHMap = rowsHMap.get(row);
			// Iterate through columns in current row
			for (Integer col : getNonEmptyColsInRow(row))
			{
				// Set the multiplied value to the temporary BigMatrix
				tempBigMatrix.setValue(row, col, currRowHMap.get(col) * constant);
			}
		}
		return tempBigMatrix;
	}
	
	/**
	 * Adds two matrices and returns the added matrix
	 * @param other The other matrix that will be added to the current matrix
	 * @return The BigMatrix of added values
	 */
	public BigMatrix addMatrix(BigMatrix other)
	{
		// BigMatrix tempBigMatrix = multiplyByConstant(1); <-- “I choose a lazy person to do a hard job. Because a lazy person will find an easy way to do it.” - Bill Gates
		BigMatrix tempBigMatrix = new BigMatrix();
		tempBigMatrix.colsHMap = this.colsHMap; tempBigMatrix.rowsHMap = this.rowsHMap;
		
		// Iterate through other's rowsHMap
		for (Integer row : other.getNonEmptyRows())
		{
			// Iterate through the row's columns
			for (Integer col : other.getNonEmptyColsInRow(row))
			{
				// Set the added value to the temporary BigMatrix
				tempBigMatrix.setValue(row, col, other.getValue(row, col) + tempBigMatrix.getValue(row, col));
			}
		}
		return tempBigMatrix;
	}
	
	// Created to assist with multiplyMatrix
	/**
	 * Gets the length of every column
	 * @return An ArrayList containing integers, representing the lengths of every column
	 */
	public List<Integer> colLengths()
	{
		List<Integer> retList = new ArrayList<Integer>();

		// Iterates through rowsHMap
		for (Integer col : getNonEmptyCols())
		{
			// Adds the size of current row to retList
			retList.add(colsHMap.get(col).size());
		}
		return retList;
	}

	// Created to assist with multiplyMatrix
	/**
	 * Gets the length of every row
	 * @return An ArrayList containing integers, representing the lengths of every row
	 */
	public List<Integer> rowLengths()
	{
		List<Integer> retList = new ArrayList<Integer>();

		// Iterates through rowsHMap
		for (Integer row : getNonEmptyRows())
		{
			// Adds the size of current row to retList
			retList.add(rowsHMap.get(row).size());
		}
		return retList;
	}

	/**
	 * Multiplies two matrices and returns the multiplied matrix
	 * @param other The other matrix that will be multiplied to the current matrix
	 * @return A BigMatrix containing the values from the multiplication
	 */
	public BigMatrix multiplyMatrix(BigMatrix other)
	{
		BigMatrix tempBigMatrix = new BigMatrix();

		// Getting the lengths of all rows and columns
		List<Integer> currRowLengths = rowLengths(), otherRowLengths = other.rowLengths(), 
					  currColLengths = colLengths(), otherColLengths = other.colLengths();
		
		// Making sure all lengths are the same
		boolean lengthsAllSame = new HashSet<Integer>(currRowLengths).size()  <= 1 && 
								 new HashSet<Integer>(currColLengths).size()  <= 1 &&
								 new HashSet<Integer>(otherColLengths).size() <= 1 &&
								 new HashSet<Integer>(otherRowLengths).size() <= 1;
		
		// Checking if dimensions are legal to multiply
		boolean equalDimensions = currRowLengths.equals(otherColLengths) && 
								  currColLengths.equals(otherRowLengths);

		if (equalDimensions && lengthsAllSame)
		{
			int currentSum;
			
			// Iterate through rowsHMap
			for (Integer thisRow : getNonEmptyRows())
			{
				// Iterate through other's columns
				for (Integer otherCol : other.getNonEmptyCols())
				{
					currentSum = 0;
					// Iterate through columns at thisRow
					for (Integer thisColInRow : getNonEmptyColsInRow(thisRow))
					{
						currentSum += getValue(thisRow, thisColInRow) * other.getValue(thisColInRow, otherCol);
					}
					tempBigMatrix.setValue(thisRow, otherCol, currentSum);
				}
			}
		}
		return tempBigMatrix;
	}
	
	// Used for testing only
	@Override
	public String toString()
	{
		String retVal = "";
		// Loop through rows
		for (Integer row : getNonEmptyRows())
		{
			retVal += "[";
			for (Integer col : getNonEmptyCols())
			{
				retVal += col + "-" + rowsHMap.get(row).get(col) + ", ";
			}
			retVal += "]\n";
		}
		return retVal;
	}
}