// ===============================================================================================
//
//                                         DO NOT MODIFY
//
// ===============================================================================================

/**
 * Represents a cell in the maze. A cell is simply a pair of (x, y) positions.
 */
public final class Cell
{
    private final int x;
    private final int y;

    /**
     * Creates a cell with the given (x, y) positions.
     *
     * @param x the x position
     * @param y the y position
     */
    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x position.
     *
     * @return the x position
     */
    public int getX()
    {
        return x;
    }

    /**
     * Returns the y position.
     *
     * @return the y position
     */
    public int getY()
    {
        return y;
    }

    /**
     * Draws the cell. A cell is drawn as a filled circle using the current pen color.
     */
    public void draw()
    {
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.375);
    }

    @Override
    public String toString()
    {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
