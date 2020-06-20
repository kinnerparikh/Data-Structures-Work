/**
 * Solves mazes. Please refer to the specification for instructions on how to solve mazes.
 */
public class MazeSolver
{
    public class QueueItem
    {
        private Cell c;
        private Path p;

        public QueueItem(Cell c, Path p)
        {
            this.c = c;
            this.p = new Path();
            for (Cell cell : p)
            {
                // Clone the passed path to the new path
                this.p.addLast(cell);
            }
            // Add the passed cell to the path
            this.p.addLast(c);
        }

        public Cell getCell() { return c; }
        public Path getPath() { return p; }
    }

    /**
     * Provides a solution for a given maze, if possible. A solution is a path from the start cell
     * to the finish cell (inclusive). If there is no solution to the maze then returns the static
     * instance {@link Path#NO_PATH}. If the maze is perfect then there must be only one solution.
     *
     * @param maze the maze to solve
     * @return a solution for the maze or {@link Path#NO_PATH} if there is no solution
     */
    public Path solve(Maze maze)
    {
        int size = maze.size();
        
        Path resultPath = Path.NO_PATH;

        // Queue of QueueItems
        Queue<QueueItem> genQueue = new Queue<QueueItem>();
        // Enqueues starting QueueItem
        genQueue.enqueue(new QueueItem(maze.getStart(), Path.NO_PATH));
        
        // Loop until genQueue is empty
        while(!genQueue.isEmpty())
        {
            // Dequeue QueueItem and get information on cell
            QueueItem qi = genQueue.dequeue();
            Cell currCell = qi.getCell();
            int x = currCell.getX();
            int y = currCell.getY();
            // Mark cell as visited
            maze.visit(x, y);

            // Checking if the cell is equal to the end point
            if (currCell.equals(maze.getEnd()))
            {
                // Setting the final path
                resultPath = qi.getPath();
                // Break out of while loop
                break;
            }

            // LEFT
            if (x > 0)
            {
                // Checking if the cell and the wall to the left is unvisited and open 
                if (!maze.isVisited(x - 1, y) && maze.isOpen(x, y, Direction.LEFT))
                {
                    // Enqueue a new QueueItem with the next cell
                    genQueue.enqueue(new QueueItem(new Cell(x - 1, y), qi.getPath()));
                }
            }
            // DOWN
            if (y > 0)
            {
                // Checking if the cell and the wall below is unvisited and open 
                if (!maze.isVisited(x, y - 1) && maze.isOpen(x, y, Direction.DOWN))
                {
                    // Enqueue a new QueueItem with the next cell
                    genQueue.enqueue(new QueueItem(new Cell(x, y - 1), qi.getPath()));
                }
            }
            // RIGHT
            if (x < size - 1)
            {
                // Checking if the cell and the wall to the right is unvisited and open 
                if (!maze.isVisited(x + 1, y) && maze.isOpen(x, y, Direction.RIGHT))
                {
                    // Enqueue a new QueueItem with the next cell
                    genQueue.enqueue(new QueueItem(new Cell(x + 1, y), qi.getPath()));
                }
            }
            // UP
            if (y < size - 1)
            {
                // Checking if the cell and the wall above is unvisited and open 
                if (!maze.isVisited(x, y + 1) && maze.isOpen(x, y, Direction.UP))
                {
                    // Enqueue a new QueueItem with the next cell
                    genQueue.enqueue(new QueueItem(new Cell(x, y + 1), qi.getPath()));
                }
            }
        }

        return resultPath;
    }

    /**
     * Creates, solves, and draws a sample maze. Try solving mazes with different sizes!
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        // First, generate a new maze.
        int size = 10; // Setting above 200 is not recommended!
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(size);
        maze.freeze();
        
        // Next, solve it!
        MazeSolver solver = new MazeSolver();
        maze.resetVisited();
        Path solutionPath = solver.solve(maze);
        maze.setSolution(solutionPath);

        // This is so we can see which cells were explored and in what order.
        maze.setDrawVisited(true);

        maze.draw();
    }
}
