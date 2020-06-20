/**
 * Creates new mazes. Please refer to the spec for instructions on how to generate mazes.
 */

public class MazeGenerator
{
    /**
     * Randomly generates a perfect maze of {@param size}.
     *
     * @param size the size of the maze to generate
     * @return the generated maze
     */
    public Maze generate(int size)
    {
        Maze maze = new Maze(size);

        Stack<Cell> genStack = new Stack<Cell>();
        genStack.push(new Cell(0, 0));

        // loops through genStack
        while (!genStack.isEmpty())
        {
            // Pop top cell and get information on cell
            Cell currCell = genStack.pop();
            int x = currCell.getX();
            int y = currCell.getY();
            // Set cell to visited
            maze.visit(x, y);
            
            // Keeps track of which directions from the current cell are unvisited
            // Array of Directions
            Direction[] unvisitedNeighbors = new Direction[4];
            int unvisitedCount = 0;
            
            // Left-most case
            if (x > 0)
            {
                // Checks if the cell to the left is visited
                if (!maze.isVisited(x - 1, y))
                {
                    unvisitedNeighbors[unvisitedCount] = Direction.LEFT;
                    unvisitedCount++;
                }
            }
            // Bottom-most case
            if (y > 0)
            {
                // Checks if the cell beneath is visited
                if (!maze.isVisited(x, y - 1))
                {
                    unvisitedNeighbors[unvisitedCount] = Direction.DOWN;
                    unvisitedCount++;
                }
            }
            // Right-most case
            if (x < size - 1)
            {
                // Checks if the cell to the right is visited
                if (!maze.isVisited(x + 1, y))
                {
                    unvisitedNeighbors[unvisitedCount] = Direction.RIGHT;
                    unvisitedCount++;
                }
            }
            // Up-most case
            if (y < size - 1)
            {
                // Checks if the cell above is visited
                if (!maze.isVisited(x, y + 1))
                {
                    unvisitedNeighbors[unvisitedCount] = Direction.UP;
                    unvisitedCount++;
                }
            }

            if (unvisitedCount > 0)
            {
                // Finds the random wall to remove
                int randIndex = (int)(Math.random() * unvisitedCount);
                maze.removeWall(x, y, unvisitedNeighbors[randIndex]);
                genStack.push(currCell);

                int nx = x;
                int ny = y;
                
                // Getting the position of the new cell
                switch(unvisitedNeighbors[randIndex])
                {
                    case LEFT : nx--; break;
                    case DOWN : ny--; break;
                    case RIGHT: nx++; break;
                    default   : ny++; break; //UP
                }

                genStack.push(new Cell(nx, ny));
            }
        } // While stack not empty.

        // Finding random non-equal start and stop points
        int startX;
        int startY;
        int endX  ;
        int endY  ;
        do {
            startX = (int)(Math.random() * size);
            startY = (int)(Math.random() * size);
            endX   = (int)(Math.random() * size);
            endY   = (int)(Math.random() * size);
        } while ((startX == endX) && (startY == endY));

        maze.setStart(startX, startY);
        maze.setEnd(endX, endY);

        return maze;
    }

    /**
     * Creates and draws a sample maze. Try generating mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        StdRandom.setSeed(34);
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.draw();
    }
}
