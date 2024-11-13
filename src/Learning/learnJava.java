package Learning;

import java.util.HashMap;
import java.util.Map;

public class learnJava {

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

    // Iterate over the array
    for (int i = 0; i < nums.length; i++) {
      // Calculate the difference needed to reach the target
      System.out.println("target: " + target);
      System.out.println("nums[i] " + nums[i]);
      int difference = target - nums[i];
      System.out.println("difference " + difference);
      // Check if the difference exists in the map
      if (map.containsKey(difference)) {
        // If it exists, return the indices of the two numbers
        System.out.println("map difference: " + map.get(difference));
        System.out.println("i value: " + i);
        return new int[]{
              map.get(difference), i
        };
      }
      System.out.println("out of loop ");
      System.out.println("map putting value: " + nums[i]);
      System.out.println("i value out of if loop: " + i);

      // Otherwise, add the current number and its index to the map
      map.put(nums[i], i);


    }


    // If no solution is found, return an empty array (problem constraints guarantee a solution)
    return new int[]{};
  }

}
