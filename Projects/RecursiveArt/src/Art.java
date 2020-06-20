package Projects.RecursiveArt.src;

/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 2 Recursive Art Project Part 3: Art
 *
 * Revision History:
 * 11/21 - Brainstorm Ideas
 * 11/22 - Create a recursive flower
 * 11/23 - Brainstormed better ideas
 * 11/24 - Created psychedelic blobs
 * 11/25 - Fixed bugs
 * 11/26 - Added Comments
 *
 * Class Description:
 * Creates a psychedelic looking blobs (or clouds) using recursion
 */

import java.awt.*;
import java.util.Random;
import edu.princeton.cs.algs4.StdDraw;


public class Art
{
    // Creates an object of type Random with the seed being the current time
    private static Random random = new Random(System.currentTimeMillis());
    // Creates a random number between 0.01 and 0.1
    private static double finalSize = random(0.01, 0.1);

    /**
     * @param n the number of iterations
     */
    public static void draw(int n)
    {
        // Creating random colors for all four sides
        double color0 = random(0, 1), color1 = random(0, 1);
        double color2 = random(0, 1), color3 = random(0, 1);

        // Creating random value within 0 and 10 that controls how much color changes
        double deviate = random(0, 10);

        // The draw method's base case is base on the size of the squares being drawn.
        // This operation gathers the random value initially initialized to finalsize
        // and divides it by five times the n value passed. This way, it creates an
        // interesting piece of art with any n value
        finalSize /= (n * 5);

        // Draws art
        StdDraw.enableDoubleBuffering();
        draw(0.5, 0.5, color0, color1, color2, color3, 0.5, deviate);
        StdDraw.show();
    }

    /**
     * @param x position of square
     * @param y position of square
     * @param color0 color of surrounding square
     * @param color1 color of surrounding square
     * @param color2 color of surrounding square
     * @param color3 color of surrounding square
     * @param size size of square
     * @param dev stores how much to deviate the color by
     */
    private static void draw(double x,  double y, double color0, double color1, double color2, double color3, double size,  double dev)
    {
        // Base case
        if (size <= finalSize) return;

        // Color of new square based on the average of the surrounding four colors and the Gaussian Distribution equation
        double newSquare = average(new double[]{color0, color1, color2, color3}) + (dev * distribution());
        // Creates newSquare but of type float
        float flNewSquare = (float)newSquare;

        // Sets pen color based on HSB (hue, saturation, brightness) values
        StdDraw.setPenColor(
                (new Color(
                        Color.HSBtoRGB(flNewSquare, 1f, 1f))
                )
        );
        // Draws a square
        StdDraw.filledSquare(x, y, size);

        // Creates new colors based on the average of passed colors
        double colorLeft   = average(new double[]{color0, color2});
        double colorRight  = average(new double[]{color1, color3});
        double colorTop    = average(new double[]{color0, color1});
        double colorBottom = average(new double[]{color2, color3});

        // Recursive calls
        draw(x + size, y + size, colorTop , color1    , newSquare  , colorRight , size / 2, dev / 2); // Right top
        draw(x + size, y - size, newSquare, colorRight, colorBottom, color3     , size / 2, dev / 2); // Right bottom
        draw(x - size, y - size, colorLeft, newSquare , color2     , colorBottom, size / 2, dev / 2); // Left bottom
        draw(x - size, y + size, color0   , colorTop  , colorLeft  , newSquare  , size / 2, dev / 2); // Left top
    }

    /**
     *
     * @param values a double array
     * @return the average of the elements in values
     */
    private static double average(double[] values)
    {
        double totalVal = 0;
        // Loops through values, adding each value to totalVal
        for (double val : values)
        {
            totalVal += val;
        }
        // Returning the average
        return totalVal / (values.length * 1.0);
    }

    /**
     * @return a random number based on Gaussian Distribution
     */
    private static double distribution()
    {
        // Get random values between -1 and 1
        double x = random(-1.0, 1.0), y = random(-1.0, 1.0);
        // Square both values
        double num = Math.pow(x, 2) + Math.pow(y, 2);

        // Loop until desired result
        while (num == 0 || num >= 1)
        {
            x = random(-1.0, 1.0);
            y = random(-1.0, 1.0);
            num = Math.pow(x, 2) + Math.pow(y, 2);
        }
        // Perform Gaussian formula
        return x * Math.sqrt(-2 * Math.log(num / 2));
    }

    /**
     * @param lowerBound lower bound of random value
     * @param higherBound higher bound of random value
     * @return a random double in between lowerBound and higherBound
     */
    private static double random(double lowerBound, double higherBound)
    {
        return lowerBound + random.nextDouble() * (higherBound - lowerBound);
    }
}