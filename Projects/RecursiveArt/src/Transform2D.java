package Projects.RecursiveArt.src;

/**
 * Name: Kinner Parikh
 * Mrs. Kankelborg
 * Period 1
 * Project 2 Recursive Art Project Part 1: Transform2D
 *
 * Revision History:
 * 11/20 - Completed and tested all methods
 * 11/25 - Added comments
 *
 * Class Description:
 * Several public methods to perform basic transformations on points
 */

public class Transform2D
{
    /**
     * Returns a new array object that is an exact copy of the given array.
     * The given array is not mutated.
     * @param array original array
     * @return a copy of the passed in array
     */
    public static double[] copy(double[] array)
    {
        // Creates the returnable array
        double[] ret = new double[array.length];

        // Assigns all values to ret
        for (int i = 0; i < array.length; i++)
        {
            ret[i] = array[i];
        }

        return ret;
    }

    /**
     * Scales the polygon by the factor alpha.
     * @param x array that holds polygon x-values
     * @param y array that holds polygon y-values
     * @param alpha factor by which points will be scaled
     */
    public static void scale(double[] x, double[] y, double alpha)
    {
        // Loops through all values in x and y
        // Multiplies each value by alpha
        for (int i = 0; (i < x.length) && (i < y.length); i++)
        {
            x[i] *= alpha;
            y[i] *= alpha;
        }
    }


    /**
     * Translates the polygon by (dx, dy).
     * @param x array that holds polygon x-values
     * @param y array that holds polygon y-values
     * @param dx value to be translated by on x
     * @param dy value to be translated by on y
     */
    public static void translate(double[] x, double[] y, double dx, double dy)
    {
        // Loops through all values in x and y
        // Adds each value by given change value
        for (int i = 0; (i < x.length) && (i < y.length); i++)
        {
            x[i] += dx;
            y[i] += dy;
        }
    }


    /**
     * Rotates the polygon theta degrees counterclockwise, about the origin.
     * @param x array that holds polygon x-values
     * @param y array that holds polygon y-values
     * @param theta value how much to rotate points by
     */
    public static void rotate(double[] x, double[] y, double theta)
    {
        // Preset values for sine and cosine of theta
        double cosine = Math.cos(theta);
        double sine = Math.sin(theta);

        // Loops through all values in x and y
        for (int i = 0; (i < x.length) && (i < y.length); i++)
        {
            // Stores x and y values at index i
            double xCurr = x[i], yCurr = y[i];

            // Reassigns x and y values at index i by doing rotation math
            // x: (cos(theta) * x) - (sin(theta) * y)
            // y: (sin(theta) * x) + (cos(theta) * y)
            x[i] = (cosine * xCurr) - (sine * yCurr);
            y[i] = (sine * xCurr) + (cosine * yCurr);
        }
    }
}
