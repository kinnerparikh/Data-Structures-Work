package Unit_4_Labs_Hashes_Maps_Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetPractice {
    public static int numUnique(List<Integer> input)
    {
        HashSet<Integer> set = new HashSet<Integer>();

        for(int n : input)
            set.add(n);

        return set.size();
    }

    public static int numInCommon(List<Integer> list1, List<Integer> list2)
    {
        HashSet<Integer> set1 = new HashSet<Integer>(list1);
        HashSet<Integer> set2 = new HashSet<Integer>(list2);
        int count = 0;

        for(int n : set2) {
            if(set1.contains(n))
                count++;
        }

        return count;
    }

    public static int maxLength(Set<String> input)
    {
        int maxLength = 0;

        for(String str : input) {
            if(maxLength < str.length()) {
                maxLength = str.length();
            }
        }

        return maxLength;
    }

    public static boolean hasOdd(Set<Integer> input)
    {
        for(int n : input) {
            if(n % 2 == 1)
                return true;
        }

        return false;
    }

    public static void removeEvenLength(Set<String> input)
    {
        // for once in your life, dont copy me you hoe <3
    }
}