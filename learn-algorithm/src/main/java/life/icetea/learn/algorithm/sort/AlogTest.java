package life.icetea.learn.algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AlogTest {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9)));
    }


    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target- nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return null;
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
