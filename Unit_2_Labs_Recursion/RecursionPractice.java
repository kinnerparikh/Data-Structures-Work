package Unit_2_Labs_Recursion;

public class RecursionPractice {
    /*
    The fibonacci sequence is a famous bit of mathematics, and it happens to have a recursive definition. 
    The first two values in the sequence are 0 and 1 (essentially 2 base cases). Each subsequent value is 
    the sum of the previous two values, so the whole sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
    
    Define a recursive fibonacci(n) method that returns the nth fibonacci number, with n=0 representing the start of the sequence.

    Examples:
    fibonacci(0) → 0
    fibonacci(1) → 1
    fibonacci(2) → 1
    */
    public static int fibonacci(int val)
    {
        if (val == 0) return 0;
        else if (val == 1) return 1;
        return fibonacci(val - 1) + fibonacci(val - 2);
    }

    /*
    Given a non-negative int n, return the sum of its digits recursively (no loops).

    Examples:
    sumDigits(126) → 9
    sumDigits(49) → 13
    sumDigits(12) → 3
    */
    public static int sumDigits(int val)
    {
        if (val == 0) 
        return 0; 
        return (val % 10) + sumDigits(val / 10);
    }

    /*
    Given base and n that are both 1 or more, compute recursively (no loops) the value of base to the n power, so powerN(3, 2) is 9 (3 squared).

    Examples:
    powerN(3, 1) → 3
    powerN(3, 2) → 9
    powerN(3, 3) → 27
    */
    public static int powerN(int num, int power)
    {
        if (power == 1) return num;
        return num * powerN(num, power - 1);
    }

    /*
    Given a string, compute recursively (no loops) the number of "11" substrings in the string. The "11" substrings should not overlap.

    Example
    count11("11abc11") → 2
    count11("abc11x11x11") → 3
    count11("111") → 1
    */
    public static int count11(String input)
    {
        if (input.contains("11"))
        {
            int index = input.indexOf("11");
            return 1 + count11(input.substring(index + 2));
        }
        return 0;
    }

    /*
    Given a string, return true if it is a nesting of zero or more pairs of parenthesis, like "(())" or "((()))".
    Suggestion: check the first and last chars, and then recur on what's inside them.

    Examples:
    nestParen("(())") → true
    nestParen("((()))") → true
    nestParen("(((x))") → false
    */
    public static boolean nestParen(String input)
    {
        if (input.length() == 0)
        {
            return true;
        }
        else if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')')
        {
            return nestParen(input.substring(1, input.length() - 1));
        }
        return false;
        
    }

    /*
    Given a string and a non-empty substring sub, compute recursively if at least n copies of sub appear in the string somewhere, possibly with overlapping. N will be non-negative.

    Examples
    strCopies("catcowcat", "cat", 2) → true
    strCopies("catcowcat", "cow", 2) → false
    strCopies("catcowcat", "cow", 1) → true
    */
    public static boolean strCopies(String input, String substring, int val)
    {
        int index = input.indexOf(substring);
        if (val == 0)
        {
            return true;
        }
        else if (index != -1)
        {
            return strCopies(input.substring(index + 1), substring, val - 1);
        }
        return false;
    }

    /*
    Given a string, return recursively a "cleaned" string where adjacent chars that are the same have been reduced to a single char. So "yyzzza" yields "yza".

    Examples:
    stringClean("yyzzza") → "yza"
    stringClean("abbbcdd") → "abcd"
    stringClean("Hello") → "Helo"
    */
    public static String stringClean(String input)
    {
        if (input.length() <= 1)
        {
            return input;
        }
        
        if(input.charAt(0) == input.charAt(1))
        {
            return stringClean(input.substring(1));
        }
        
        return input.substring(0,1) + stringClean(input.substring(1));
    }

    /*
    Given a string, compute recursively a new string where all the lowercase 'x' chars have been moved to the end of the string.

    Examples:
    endX("xxre") → "rexx"
    endX("xxhixx") → "hixxxx"
    endX("xhixhix") → "hihixxx"
    */
    public static String endX(String input)
    {
        if (input.length() <= 1)
        {
            return input;
        }
        if (input.indexOf('x') == 0) 
        { 
            return endX(input.substring(1)) + "x";
        }
        
        return input.charAt(0) + endX(input.substring(1));
        
    }

    /*
    Given an array of ints, compute recursively the number of times that the value 11 appears in the array. 
    We'll use the convention of considering only the part of the array that begins at the given index. 
    In this way, a recursive call can pass index+1 to move down the array. The initial call will pass in index as 0.

    Examples:
    array11([1, 2, 11], 0) → 1
    array11([11, 11], 0) → 2
    array11([1, 2, 3, 4], 0) → 0
    */
    public static int array11(int[] array, int index)
    {
        if (array.length == index)
        {
            return 0;
        }
        if (array[index] == 11)
        {
            return 1 + array11(array, index + 1);
        }
        return array11(array, index + 1);
    }

    /*
    Given an array of ints, compute recursively if the array contains somewhere a value followed in the array by that value times 10. We'll use the convention of considering only the part of the array that begins at the given index. In this way, a recursive call can pass index+1 to move down the array. The initial call will pass in index as 0.

    Examples
    array220([1, 2, 20], 0) → true
    array220([3, 30], 0) → true
    array220([3], 0) → false
    */
    public static boolean array220(int[] arr, int index)
    {
        if ((arr.length - index) <= 1)
        {
            return false;
        }
        if ((arr[index] * 10) == arr[index + 1])
        {
            return true;
        }
        return array220(arr, index + 1);
    }
}