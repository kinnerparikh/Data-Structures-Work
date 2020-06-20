package Projects.TravellingSalesman.src;

/**
 * Partner 1 Name: Vedantha Venkatapathy
 * Partner 2 Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 1 Traveling Salesman
 * Revision History:
 * Monday - Kinner Wrote the Node Class
 * Tuesday - Vedantha wrote size(), length(), constructors(), and toString()
 * Wednesday - Kinner started insertNearest() and insertSmallest()
 * Thursday - Vedantha finished insertNearest() and insertSmallest()
 * Friday - Kinner wrote and tested the main method.
 * 
 * Project Description:
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import edu.princeton.cs.algs4.StdDraw;


public class Tour {

	// Node class
    private class Node{
    	// Stores data of type Point
        private Point data;
        // Stores next Node
        private Node next;

        // One parameter constructor
        public Node(Point p){
            this.data = p;
            this.next = null;
        }

        // Two parameter constructor
        public Node(Point p, Node next){
            this.data = p;
            this.next = next;
        }
    }

    // Initializes LinkedList
    private Node head;
    private int size;
    
    // Creates empty tour
    public Tour()                                    
    {
        head = null;
        size = 0;
    }
    
    /**
     *  creates the 4-point tour a->b->c->d->a (for debugging)
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Tour(Point a, Point b, Point c, Point d)  
    {
        Node Na = new Node(a);
        Node Nb = new Node(b);
        Node Nc = new Node(c);
        Node Nd = new Node(d);

        Nc.next = Nd;
        size++;
        Nb.next = Nc;
        size++;
        Na.next = Nb;
        size++;
        Nd.next = Na;
        size++;

        head = Na;
    }
    
    /**
     *  returns the number of points in this tour
     * @return
     */
    public int size() 
    {
    	// Return size
    	return size;
    }
    
    /**
     *  returns the length of this tour
     * @return 
     */
    public double length()
    {
    	double distance = 0;
    	Node temp = head;
    	// Loop through linked list
    	for (int i = 0; i < size(); i++)
    	{
    		// Increment distances between cities
    		distance += temp.data.distanceTo(temp.next.data);
    		temp = temp.next;
    	}
    	// Return distance
    	return distance;
	}
    
    /**
     *  returns a string representation of this tour
     */
    public String toString()
    {
    	String retStr = "";
    	Node temp = head;
    	// Loop through linked list
    	for (int i = 0; i < size(); i++) 
    	{
    		// Appends string to end of return string
    		retStr += temp.data.toString();
    		// If the next value is not null
    		if (temp.next != null) {
    			// Add a new line
    			retStr += "\n";
    		}
    		temp = temp.next;
    
    	}
    	
    	// Return the string
    	return retStr;
    }
    
    /** 
     * draws this tour to standard drawing
     */
    public void draw()
    {
    	Node temp = head;
    	
    	// Stores points
    	Point point1;
    	Point point2;
    	
    	// Loops through linked list
    	for (int i = 0; i < size(); i++)
    	{
    		// Gets new points
    		point1 = temp.data;
    		point2 = temp.next.data;
    		
    		// Draw points
    		point1.drawTo(point2);
    		
    		temp = temp.next;
    	}
    }
    
    /**
     * inserts p into the tour using the nearest neighbor heuristic
     * @param p
     */
    public void insertNearest(Point p)
    {
    	// Creates temporary variable that points to head
    	Node temp = head;
    	// Stores the minimum distance node
    	Node minNode = null;
    	// Stores the minimum distance
    	double minDistance = Double.MAX_VALUE;
    	
    	// Loops through linked list
    	for (int i = 0; i < size(); i++)
    	{
    		// Get the distance from p to the current node point
    		double distance = p.distanceTo(temp.data);
    		// If distance is less than the minimum distance
    		if (distance < minDistance) {
    			// Set minDistance to newest minimum distance
    			minDistance = distance;
    			// Set minNode to newest minimum distance node
    			minNode = temp;
    		}
    		
    		temp = temp.next;
    	}

    	// if minNode is null
    	if (minNode == null) 
    	{
    		// Automatically adds new element to list
    		head = new Node(p);
    		// Loop the list
    		head.next = head;
    	}
    	else 
    	{
    		// Inputs new node into list with loop
    		minNode.next = new Node(p, minNode.next);
    	}
    	
    	// Increment size
    	size++;
    }
    
    /** 
     * inserts p into the tour using the smallest increase heuristic
     * @param p
     */
    public void insertSmallest(Point p)                   
    {
    	// Stores minimum difference
    	double minDelta = Double.MAX_VALUE;
    	// Temp variable that points to head
    	Node temp = head;
    	// Stores minimum node
    	Node minNode = null;
    	
    	// Loops through list
    	for (int i = 0; i < size(); i++) {
    		// Gets the distance from temp to p
    		double tempToNewDist = temp.data.distanceTo(p);
    		// Gets the distance from p to temp.next
    		double newToNextDist = p.distanceTo(temp.next.data);
    		// Gets the distance from temp to temp.next
    		double tempToNextDist = temp.data.distanceTo(temp.next.data);
    		
    		// Calculates difference
    		double delta = tempToNewDist + newToNextDist - tempToNextDist;
    		
    		// If the difference is less than the minimum difference
    		if (delta < minDelta) {
    			// Set minDelta to new difference
    			minDelta = delta;
    			// Set minDelta to temp
    			minNode = temp;
    		}
    		
    		temp = temp.next;
    	}
    	
    	// if minNode is null
    	if (minNode == null) 
    	{
    		// Automatically adds new element to list
    		head = new Node(p);
    		// Loop the list
    		head.next = head;
    	}
    	else 
    	{
    		// Inputs new node into list with loop
    		minNode.next = new Node(p, minNode.next);
    	}
    	
    	// increment size
    	size++;
    }
    
    /**
    * Tests this class by directly calling all constructors and methods
    * use your knowledge of Buffered Reader and Standard Draw to read in
    * from your chosen file and draw output.
    * 
    * Alternatively: use your knowledge of junit tests to write your own test cases for each method
    */
   
    public static void main(String[] args)
    {
    	try {
    		// Open the file
            File f = new File("./input/tsp1000.txt");
            // Create new FileReader object 
            FileReader fr = new FileReader(f);
            // Create new BufferedReader object
            BufferedReader br = new BufferedReader(fr);   
            
            // Read next line
            String boundaries = br.readLine();
            // Get the boundaries
            String[] boundariesAsArray = boundaries.split(" ");
            
            // Set canvas scale
            StdDraw.setXscale(0, Integer.parseInt(boundariesAsArray[0]));
            StdDraw.setYscale(0, Integer.parseInt(boundariesAsArray[1]));
            
            // Read next line
            String input = br.readLine();
            // Create an empty tour
            Tour tour = new Tour();
            // Loop while input is not null
            while(input != null) {
            	// If input.length() is not 0
            	if(input.length() != 0)
            	{
            		// Get rid of excess white space
	            	input = input.trim();
	            	
	            	// Split the input
	                String[] coordinatesStr = input.split(" ");
	                
	                // Convert the coordinates to doubles
	                double[] coordinates = new double[2];
	                // Loop through coordinatesStr
	                for (int i = 0, j = 0; i < coordinatesStr.length; i++){
	                	// If coordinates string length is greater than 1
	                	if(coordinatesStr[i].length() > 1)
	                	{
	                		// Set coordinate to coordinate[j]
                            coordinates[j] = Double.parseDouble(coordinatesStr[i]);
                            // Increment j
                            j++;
                        }
	                }
	                
	                // Smallest Heuristic insertion
	                tour.insertSmallest(new Point(coordinates[0], coordinates[1]));
	                // Nearest Heuristic insertion
	                //tour.insertNearest(new Point(coordinates[0], coordinates[1]));
            	}
            	// Read next line
                input = br.readLine();
            }
            // Print out toString
            System.out.println(tour.toString());
            // Print out lengths
            System.out.println("\nTour length = " + tour.length());
            // Print out size
            System.out.println("Number of points = " + tour.size());
            // Draw to canvas
            tour.draw();
            br.close();
    	}
    	// Catch if exception
    	catch(Exception e) {
    		// Print out exception
    		System.out.println("Exception: " + e);
    	}
    }
}
