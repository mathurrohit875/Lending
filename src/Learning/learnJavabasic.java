package Learning;

import java.util.HashMap;
import java.util.Map;

public class learnJavabasic {

  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    int target = 9;


    int[] result = twoSum(nums, target);

    // Print the output
    System.out.println("[" + result[0] + "," + result[1] + "]");
    System.out.println("Values: [" + nums[result[0]] + ", " + nums[result[1]] + "]");
  }

  public static int[] twoSum(int[] nums, int target) {
    // Create a HashMap to store the difference (target - nums[i]) and the index i
    Map<Integer, Integer> map = new HashMap<>();
    int sum;
    // Iterate over the array
    for (int i = 0; i < nums.length - 1; i++) {
      // Calculate the difference needed to reach the target
      sum = nums[i] + nums[i + 1];
      if (sum == target) {
        System.out.println("" + nums[i]);
        System.out.println("" + nums[i + 1]);
        return new int[]{i, i + 1};
      }
    }


    // If no solution is found, return an empty array (problem constraints guarantee a solution)
    return new int[]{};
  }

}
