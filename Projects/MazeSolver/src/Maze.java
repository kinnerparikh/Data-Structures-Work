// ===============================================================================================
//
//                                         DO NOT MODIFY
//
// ===============================================================================================

/**
 * A maze is represented as a square N-by-N matrix of cells. Each cell may have up to four walls
 * around it (above, below, left, and right). The four outer sides of the maze are enclosed by
 * walls.
 *
 * Within a maze, it may or may not be possible to find a path from one cell to another. A path may
 * travel between two adjacent cells if there is no wall between them. Adjacent cells must share a
 * side, diagonally connected cells are not considered adjacent.
 *
 * If the maze is "perfect" then every cell can be reached from every other cell and there are no
 * path loops or isolated walls. Thus, in a perfect maze (1) there are no inaccessible areas and (2)
 * there is always one unique path between any two cells.
 */
public final class Maze
{
    private final int size;

    private Cell start;
    private Cell end;

    private final boolean[][] visited;
    private final boolean[][] left; // True if there is a wall left of cell x, y
    private final boolean[][] right; // True if there is a wall right of cell x, y
    private final boolean[][] up; // True if there is a wall above cell x, y
    private final boolean[][] down; // True if there is a wall below cell x, y

    private boolean frozen; // Whether the maze is frozen, i.e. no more walls can be removed.
    private boolean drawVisited; // Whether to draw visited cells, in the order they are visited.

    private Path visitedPath = new Path();
    private Path solutionPath;

    /**
     * Constructs a maze of {@param size}. Each side of the maze is {@param size} units long.
     *
     * @param size the maze size
     * @throws IllegalArgumentException if the size is less than two
     */
    public Maze(int size)
    {
        if (size < 2)
            throw new IllegalArgumentException("Invalid size: " + size);

        this.size = size;

        visited = new boolean[size][size];
        left = new boolean[size][size];
        right = new boolean[size][size];
        up = new boolean[size][size];
        down = new boolean[size][size];

        for (int x = 0; x < size; ++x)
        {
            for (int y = 0; y < size; ++y)
            {
                left[x][y] = true;
                right[x][y] = true;
                up[x][y] = true;
                down[x][y] = true;
            }
        }
    }

    /**
     * Returns the size of the maze.
     *
     * @return the size of the maze
     */
    public int size()
    {
        return size;
    }

    /**
     * Sets the start cell. The start cell is drawn as a green circle.
     *
     * @param x the x position of the start cell
     * @param y the y position of the start cell
     */
    public void setStart(int x, int y)
    {
        checkBounds(x, y);
        start = new Cell(x, y);
    }

    /**
     * Sets the end cell. The finish cell is drawn as a red circle.
     *
     * @param x the x position of the end cell
     * @param y the y position of the end cell
     */
    public void setEnd(int x, int y)
    {
        checkBounds(x, y);
        end = new Cell(x, y);
    }

    /**
     * Returns the start cell.
     *
     * @return the start cell
     */
    public Cell getStart()
    {
        return start;
    }

    /**
     * Returns the end cell.
     *
     * @return the end cell
     */
    public Cell getEnd()
    {
        return end;
    }

    /**
     * Marks the given cells as visited.
     *
     * @param x the x position of the visited cell
     * @param y the y position of the visited cell
     */
    public void visit(int x, int y)
    {
        checkBounds(x, y);
        visited[x][y] = true;
        visitedPath.addLast(new Cell(x, y));
    }

    /**
     * Returns whether the given cell has been visited.
     *
     * @param x the x position of the cell to check
     * @param y the y position of the cell to check
     * @return whether the given cell has been visited
     */
    public boolean isVisited(int x, int y)
    {
        checkBounds(x, y);
        return visited[x][y];
    }

    /**
     * Removes a wall next to a given cell. For example, to remove the wall above cell (2, 2), use
     * removeWall(2, 2, Direction.UP).
     *
     * @param x the x position of the cell
     * @param y the y position of the cell
     * @param direction the direction of the wall to remove relative to the given cell
     */
    public void removeWall(int x, int y, Direction direction)
    {
        if (frozen)
            throw new IllegalStateException("Maze is frozen, cannot remove any walls");

        checkBounds(x, y);
        if (isOpen(x, y, direction))
            throw new IllegalStateException(String.format(
                    "Wall at (%d, %d) in direction %s has already been removed", x, y, direction));

        switch (direction)
        {
            case LEFT:
                if (x <= 0)
                    throw new IllegalArgumentException(
                            "Cannot remove wall left of leftmost column");
                left[x][y] = false;
                right[x - 1][y] = false;
                break;
            case RIGHT:
                if (x >= size - 1)
                    throw new IllegalArgumentException(
                            "Cannot remove wall right of rightmost column");
                right[x][y] = false;
                left[x + 1][y] = false;
                break;
            case UP:
                if (y >= size - 1)
                    throw new IllegalArgumentException("Cannot remove wall above top row");
                up[x][y] = false;
                down[x][y + 1] = false;
                break;
            case DOWN:
                if (y <= 0)
                    throw new IllegalArgumentException("Cannot remove wall below bottom row");
                down[x][y] = false;
                up[x][y - 1] = false;
                break;
        }
    }

    /**
     * Checks whether there is a wall next to a given cell. For example, to check whether there is a
     * wall above cell (2, 2), use isOpen(2, 2, Direction.UP).
     *
     * @param x the x position of the cell to check
     * @param y the y position of the cell to check
     * @param direction the direction of the wall relative to the given cell
     * @return whether there is a wall at the given cell in the given direction
     */
    public boolean isOpen(int x, int y, Direction direction)
    {
        checkBounds(x, y);
        switch (direction)
        {
            case LEFT:
                return !left[x][y];
            case RIGHT:
                return !right[x][y];
            case UP:
                return !up[x][y];
            case DOWN:
                return !down[x][y];
        }
        // Should not happen.
        throw new IllegalStateException("Invalid direction: " + direction);
    }

    // ===========================================================================================
    //
    // Methods below are only needed for driver and test code.
    //
    // ===========================================================================================

    /**
     * Resets all cells in the maze to unvisited.
     */
    public void resetVisited()
    {
        for (int x = 0; x < size; ++x)
        {
            for (int y = 0; y < size; ++y)
            {
                visited[x][y] = false;
            }
        }
        visitedPath = new Path();
    }

    /**
     * Sets the solution path of the maze. The solution path is drawn as blue circles, except for
     * the start and finish cells.
     *
     * @param solutionPath the solution path of the size
     */
    public void setSolution(Path solutionPath)
    {
        this.solutionPath = solutionPath;
    }

    /**
     * Freezes the maze so no more walls may be removed.
     *
     * @param frozen whether the maze is frozen
     */
    public void freeze()
    {
        this.frozen = true;
    }

    /**
     * Sets whether to draw the visited cells in the order in which they were visited. Visited cells
     * are drawn as gray circles, except for the start and finish cells.
     *
     * @param drawVisited whether to draw the visited cells
     */
    public void setDrawVisited(boolean drawVisited)
    {
        this.drawVisited = drawVisited;
    }

    /**
     * Draws the maze to the screen.
     */
    public void draw()
    {
        StdDraw.setXscale(0, size);
        StdDraw.setYscale(0, size);
        StdDraw.enableDoubleBuffering();

        if (start != null)
        {
            StdDraw.setPenColor(StdDraw.GREEN);
            start.draw();
        }
        if (end != null)
        {
            StdDraw.setPenColor(StdDraw.RED);
            end.draw();
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 0; x < size; ++x)
        {
            for (int y = 0; y < size; ++y)
            {
                if (left[x][y])
                    StdDraw.line(x, y, x, y + 1);
                if (right[x][y])
                    StdDraw.line(x + 1, y, x + 1, y + 1);
                if (up[x][y])
                    StdDraw.line(x, y + 1, x + 1, y + 1);
                if (down[x][y])
                    StdDraw.line(x, y, x + 1, y);
            }
        }
        StdDraw.show();

        StdDraw.setPenColor(StdDraw.GRAY);
        if (drawVisited)
        {
            for (Cell cell : visitedPath)
            {
                if (cell.equals(start) || cell.equals(end))
                    continue;
                cell.draw();
                StdDraw.show();
                StdDraw.pause(30);
            }
        }

        StdDraw.setPenColor(StdDraw.BLUE);
        if (solutionPath != null)
        {
            for (Cell cell : solutionPath)
            {
                if (cell.equals(start) || cell.equals(end))
                    continue;
                cell.draw();
                StdDraw.show();
                StdDraw.pause(10);
            }
        }
    }

    /**
     * Creates and draws a sample maze. You may modify this method for testing.
     *
     * @param args unused
     */
    public static void main(String[] args)
    {
        int size = 4;
        Maze maze = new Maze(size);
        maze.removeWall(0, 0, Direction.RIGHT);
        maze.removeWall(1, 0, Direction.UP);
        maze.removeWall(1, 1, Direction.LEFT);
        maze.removeWall(1, 1, Direction.RIGHT);
        maze.removeWall(2, 1, Direction.RIGHT);
        maze.removeWall(3, 1, Direction.UP);
        maze.removeWall(3, 1, Direction.DOWN);
        maze.removeWall(3, 0, Direction.LEFT);
        maze.removeWall(3, 2, Direction.LEFT);
        maze.removeWall(2, 2, Direction.LEFT);
        maze.removeWall(1, 2, Direction.UP);
        maze.removeWall(1, 3, Direction.LEFT);
        maze.removeWall(1, 3, Direction.RIGHT);
        maze.removeWall(2, 3, Direction.RIGHT);
        maze.removeWall(0, 3, Direction.DOWN);
        maze.setStart(0, 0);
        maze.draw();
    }

    private void checkBounds(int x, int y)
    {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new IllegalArgumentException(
                    String.format("Invalid coordinates (out of bounds): (%s, %s)", x, y));
    }
}
