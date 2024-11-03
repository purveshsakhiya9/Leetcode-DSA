import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopKthFrequentElements {
    // Method to find the top K most frequent elements in the array
    public static int[] TopKthFrequentElements(int[] nums, int k) {
        // Create an array of lists (buckets) to store numbers based on frequency.
        // The index represents frequency, so bucket[i] holds numbers that appear i times.
        List<Integer>[] bucket = new List[nums.length + 1];

        // HashMap to count the frequency of each element in the array
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        // Place elements in the bucket according to their frequencies
        for (int num : hm.keySet()) {
            int frequency = hm.get(num);  // Frequency of the current number
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();  // Initialize the list if null
            }
            bucket[frequency].add(num);  // Add the number to the corresponding frequency bucket
        }

        // Array to store the result, i.e., top K frequent elements
        int[] result = new int[k];
        int counter = 0;  // Counter to track number of elements added to the result

        // Traverse the bucket array from the end to find the K most frequent elements
        for (int i = bucket.length - 1; i >= 0 && counter < k; i--) {
            // Check if the bucket at index i (frequency i) is not empty
            if (bucket[i] != null) {
                // Add each element in this bucket to the result array until we have K elements
                for (Integer num : bucket[i]) {
                    result[counter++] = num;  // Add the element to the result and increment the counter
                    if (counter == k) break;  // Stop once we have K elements
                }
            }
        }

        return result;  // Return the top K frequent elements
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};  // Sample input array
        int k = 2;  // Number of top frequent elements to find
        int[] result = TopKthFrequentElements(nums, k);

        // Printing the result array
        System.out.print("[");
        for (int i = 0; i < result.length - 1; i++) {
            System.out.print(result[i] + ", ");
        }
        System.out.print(result[result.length - 1] + "]");
    }
}
