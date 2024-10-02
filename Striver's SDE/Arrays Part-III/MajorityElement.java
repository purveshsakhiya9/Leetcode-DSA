public class MajorityElement {

    // Function to find the majority element in an array
    // A majority element is one that appears more than n/2 times in the array
    // We are using the Boyer-Moore Voting Algorithm to solve this problem in O(n) time
    public static int MajorityElement(int[] nums) {
        // Initialize the first element as the candidate for majority element
        int candidate = nums[0];
        int count = 1;  // Count keeps track of how many times the candidate appears

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // If the count is zero, we pick a new candidate
            if (count == 0) {
                candidate = nums[i];  // Assign a new candidate
            }

            // If the current element is equal to the candidate, increment the count
            if (candidate == nums[i]) {
                count++;
            } else {
                // Otherwise, decrement the count
                count--;
            }
        }

        // After the loop, the candidate will be the majority element
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 2, 1, 1, 1};
        System.out.println(MajorityElement(nums));  // Output will be 1
    }
}
