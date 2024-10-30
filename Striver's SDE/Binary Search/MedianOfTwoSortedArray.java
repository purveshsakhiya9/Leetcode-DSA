public class MedianOfTwoSortedArray {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            // If partitionX is 0, it means there are no elements on the left side of nums1
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            // If partitionX is equal to the length of nums1, there are no elements on the right side of nums1
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            // If partitionY is 0, it means there are no elements on the left side of nums2
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            // If partitionY is equal to the length of nums2, there are no elements on the right side of nums2
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partition
            if (maxX <= minY && maxY <= minX) {
                // If the total number of elements is even
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else { // If the total number of elements is odd
                    return (double)Math.max(maxX, maxY);
                }
            } else if (maxX > minY) { // We are too far on the right side in nums1
                high = partitionX - 1;
            } else { // We are too far on the left side in nums1
                low = partitionX + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));  // Output: 2.0

        int[] nums1b = {1, 2};
        int[] nums2b = {3, 4};
        System.out.println(findMedianSortedArrays(nums1b, nums2b));  // Output: 2.5

        int[] nums1c = {1, 3, 5, 7, 9};
        int[] nums2c = {2, 4, 6, 8, 10};
        System.out.println(findMedianSortedArrays(nums1c, nums2c)); // Output: 5.5
    }
}
