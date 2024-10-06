import java.util.HashMap;

public class LargestSubarrayWithSumZero {

    public static int LargestSubarrayWithSumZero(int[] nums){
        int sum = 0;
        int length = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            sum += nums[i];
            if(sum==0){
                length = i+1;
            }
            if(map.containsKey(sum)){
                int len = i-map.get(sum);
                if(length<len){
                    length = len;
                }
            }else{
                map.put(sum,i);
            }
        }
        return length;
    }
    public static void main(String[] args){
        int[] nums = {15,-2,2,-8,1,7,10,23};
        System.out.println(LargestSubarrayWithSumZero(nums));

    }
}
