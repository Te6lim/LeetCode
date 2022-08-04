import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] array = new int[nums.length];
        HashMap<Double, Integer> map = new HashMap<>();

        double doubleValue;

        for (int i = 0; i < nums.length; ++i) {
            array[i] = nums[i];
            doubleValue = array[i];
            if (!map.containsKey(doubleValue)) {
                map.put(doubleValue, i);
            } else {
                if (!map.containsKey(doubleValue + 0.5)) map.put(doubleValue + 0.5, i);
            }
        }

        Arrays.sort(array);

        boolean found;
        int i = 0;
        int other;

        while (i < array.length && i + 1 < array.length) {
            other = target - array[i];
            found = Arrays.binarySearch(array, i + 1, array.length, other) >= 0;
            double d = array[i];
            if (found) {
                if (array[i] != other) {
                    return new int[]{map.get(d), map.get((double)other)};
                } else {
                    return new int[]{map.get(d), map.get(d + 0.5)};
                }
            }
            ++i;
        }
        return  null;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 6142, 8192, 10239};
        int target = 18431;

        int[] ans = TwoSum.twoSum(nums, target);
        for (int i : ans) System.out.println(i);
    }
}
