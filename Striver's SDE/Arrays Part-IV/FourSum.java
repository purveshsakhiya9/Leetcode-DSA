import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static List<List<Integer>> FourSum(int[] nums , int target){
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i< nums.length-3;i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i+1; j < nums.length - 2; j++) {
                if (j>i+1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args){
        int[] num = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> ans = FourSum(num,target);
        System.out.print("[");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            if (i != ans.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }
}