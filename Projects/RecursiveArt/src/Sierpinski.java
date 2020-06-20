package Projects.RecursiveArt.src;

/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 2 Recursive Art Project Part 2: Sierpinski
 *
 * Revision History:
 * 11/20 - Completed and tested all methods
 * 11/25 - Added comments
 *
 * Class Description:
 * Drawing the Sierpinski triangle set using recursion
 */

import edu.princeton.cs.algs4.StdDraw;

public class Sierpinski
{
    /**
     * @param length of triangle
     * @return height of triangle
     */
    private static double height(double length)
    {
        // Calculates height through Pythagorean Theorem
        return Math.sqrt(3) * length / 2;
    }

    /**
     * @param x value of triangle
     * @param y value of triangle
     * @param length of triangle
     */
    private static void filledTriangle(double x, double y, double length)
    {
        // Arrays to store vertexes of triangle
        double[] xVertex = new double[3], yVertex = new double[3];

        // Calculating vertexes
        xVertex[0] = x; yVertex[0] = y; // Bottom (origin)
        xVertex[1] = x + length/2; yVertex[1] = y + height(length); // Top Right
        xVertex[2] = x - length/2; yVertex[2] = yVertex[1]; // Top Left

        // Drawing triangle
        StdDraw.filledPolygon(xVertex, yVertex);
    }

    /**
     * @param n depth of recursion
     */
    public static void sierpinski(int n)
    {
        // Vertexes for outline triangle
        double[] xVertex = {0, 1, 0.5}, yVertex = {0, 0, height(1)};

        StdDraw.polygon(xVertex, yVertex);

        // Method call to draw other triangles
        sierpinski(n, 0.5, 0, 0.5);
    }

    /**
     * @param n the depth of recursion left
     * @param x value of triangle
     * @param y value of triangle
     * @param length of triangle
     */
    private static void sierpinski(int n, double x, double y, double length)
    {
        // Base case
        if (n == 0)
        {
            // Exit method
            return;
        }
        else
        {
            // Draw the triangle
            filledTriangle(x, y, length);
            sierpinski(n - 1, x, y + height(length), length / 2); // Draws triangles to the top
            sierpinski(n - 1, x - length/2, y, length / 2); // Draws triangles to left
            sierpinski(n - 1, x + length/2, y, length / 2); // Draws triangles to right
        }
    }
}
