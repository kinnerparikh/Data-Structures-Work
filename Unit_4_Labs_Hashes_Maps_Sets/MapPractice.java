package Unit_4_Labs_Hashes_Maps_Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapPractice {
    public static boolean contains3(List<String> list)
    {    
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(String str : list) {
            if(map.containsKey(str)) {
                map.put(str, map.get(str) + 1);

                if(map.get(str) == 3)
                    return true;
            } else {
                map.put(str, 1);
            }
        }
        return false;
    }

    public static Map<Integer, Integer> counts(List<Integer> list, Set<Integer> set)
    {
        if (list.isEmpty() || set.isEmpty()) return new HashMap<Integer, Integer>();
        Map<Integer, Integer> currM = new HashMap<Integer, Integer>();
        for (Integer s : set)
        {
            currM.put(s, 0);
        }
        for (Integer nums : list)
        {
            for (Integer s: set)
            {
                if (s.equals(nums))
                {
                    currM.put(s, currM.get(s) + 1);
                }
            }
        }

        return currM;
    }

    public static boolean isUnique(Map<String, String> map)
    {
        HashSet<String> set = new HashSet<String>();

        for(String key : map.keySet()) {
            String value = map.get(key);

            if(set.contains(value))
                return false;

            set.add(value);
        }

        return true;
    }

    public static Map<String,Integer> reverse(Map<Integer,String> map)
    {
        HashMap<String, Integer> reversed = new HashMap<String, Integer>();

        for(int key : map.keySet()) {
            String value = map.get(key);

            if(!reversed.containsKey(value)) {
                reversed.put(value, key);
            }
        }

        return reversed;
    }

    public static Map<String,Integer> intersect(Map<String,Integer> map1, Map<String,Integer> map2)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(String key : map1.keySet()) {
            if(map2.containsKey(key) && map1.get(key) == map2.get(key))
                map.put(key, map1.get(key));
        }

        return map;
    }

    public static long maxOccurrences(List<Integer> list)
    {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        long modeVal = 0;

        for(int n : list) {
            if(map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }

            if(map.get(n) > modeVal)
                modeVal = map.get(n);
        }

        return modeVal;
    }
}