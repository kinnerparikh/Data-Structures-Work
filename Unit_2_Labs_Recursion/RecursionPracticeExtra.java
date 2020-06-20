package Unit_2_Labs_Recursion;

public class RecursionPracticeExtra {
    public static boolean groupSum(int start, int[] nums, int target) {
        if (start >= nums.length) return (target == 0);
        if (groupSum(start + 1, nums, target - nums[start])) return true;
        return groupSum(start + 1, nums, target);
    }

    public static boolean groupSum6(int start, int[] nums, int target) {
        if (start >= nums.length) return (target == 0);
        if (groupSum6(start + 1, nums, target - nums[start])) return true;
        return ((nums[start] != 6) && groupSum6(start + 1, nums, target));
    }

    public static boolean groupNoAdj(int start, int[] nums, int target) {
        if (start >= nums.length) return (target == 0);
        if (groupNoAdj(start + 1, nums, target)) return true;
        return (groupNoAdj(start + 2, nums, target-nums[start]));
    }

    public static boolean groupSum5(int start, int[] nums, int target)
    {
        if (start >= nums.length) return (target == 0);
        
        boolean checkOne;
        if (start == 0) checkOne = true;
        if ((start > 0) && ((nums[start-1] % 5) == 0) && (nums[start] == 1)) checkOne = false;
        else checkOne = true;
        
        if (groupSum5(start + 1, nums, target - nums[start]) && checkOne) return true;
        if ((nums[start] % 5 != 0) && groupSum5(start+1, nums, target)) return true;
        return false;
    }

    public static boolean groupSumClump(int start, int[] nums, int target) {
        if (start >= nums.length) return (target == 0); 
        if ((start < (nums.length - 1)) && (nums[start] == nums[start + 1])) return groupSumClump(start + 2, nums, target);
        if (groupSumClump(start + 1, nums, target)) return true;
        return (groupSumClump(start + 1, nums, target - nums[start]));
    }
}