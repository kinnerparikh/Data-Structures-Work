
// ===============================================================================================
//
//                                         DO NOT MODIFY
//
// ===============================================================================================

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * An ordered sequence of cells within a maze that forms a path.
 */
public final class Path implements Iterable<Cell>
{
    private Deque<Cell> path = new ArrayDeque<>();

    /**
     * A special value that indicates there is no path.
     *
     * Use this constant to indicate that there is no solution for a maze.
     */
    public static final Path NO_PATH = new Path();

    /**
     * Adds a new cell to the front of the path.
     *
     * @param cell the cell to add
     */
    public void addFirst(Cell cell)
    {
        path.addFirst(cell);
    }

    /**
     * Adds a new cell to the end of the path.
     *
     * @param cell the cell to add
     */
    public void addLast(Cell cell)
    {
        path.addLast(cell);
    }

    @Override
    public Iterator<Cell> iterator()
    {
        return path.iterator();
    }
}
