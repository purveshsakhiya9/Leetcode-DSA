import java.util.HashMap;

public class TwoSum {

    public static int[] TwoSum(int[] nums,int target){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i< nums.length; i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
    public static void main(String[] args){
        int[] nums = {3,2,4};
        int target = 7;
        int[] res = TwoSum(nums,target);
        System.out.println("["+res[0]+", "+res[1]+"]");
    }
}