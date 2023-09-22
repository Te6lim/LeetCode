import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Partition {
    public int partitionDisjoint(int[] nums) {

        int largestLeft = nums[0];
        int largestRight = -1;

        int rightCount = 0;

        int countLeft = 1;
        for (int i = 1; i < nums.length - 1; ++i) {
            if (largestLeft <= nums[i]) {
                if (nums[i] > largestRight) largestRight = nums[i];
                rightCount++;
            } else {
                countLeft += rightCount;
                countLeft++;
                if (largestRight > largestLeft) largestLeft = largestRight;
                rightCount = 0;
            }
        }
        return countLeft;
    }
}
