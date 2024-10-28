public class SingleElementSortedArray {
    public static int SingleElementSortedArray(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1)
                mid--;
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }
    public static void main(String[] args){
        int[] nums = {3,3,7,7,10,11,11};
        System.out.println(SingleElementSortedArray(nums));
    }
}