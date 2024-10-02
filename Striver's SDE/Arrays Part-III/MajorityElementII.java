import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public static List<Integer> MajorityElement(int[] nums){
        List<Integer> result  = new ArrayList<>();
        int candidate1 = Integer.MIN_VALUE,candidate2 = Integer.MIN_VALUE,count1 = 0,count2 = 0;
        for(int i = 0; i< nums.length;i++){
            if(count1==0 && candidate2!= nums[i]){
                candidate1 = nums[i];
                count1=1;
            } else if (count2==0 && candidate1!=nums[i]) {
                candidate2 = nums[i];
                count2=1;
            } else if (candidate1==nums[i]) {
                count1++;
            } else if (candidate2==nums[i]) {
                count2++;
            }else{
                count1--;
                count2--;
            }
        }
        count1 = count2 =0;
        // Second pass to count actual occurrences of candidate1 and candidate2
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if(count1> nums.length/3){
            result.add(candidate1);
        }
        if(count2> nums.length/3){
            result.add(candidate2);
        }

        return result;
    }
    public static void main(String[] args){
        int[] nums = {3,2,3};
        System.out.println(MajorityElement(nums));
    }
}