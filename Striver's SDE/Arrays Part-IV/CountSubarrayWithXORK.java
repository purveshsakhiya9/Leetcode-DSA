import java.util.HashMap;

public class CountSubarrayWithXORK {
    public static int CountSubarrayWithXORK(int[] nums,int target){
        int count = 0;
        int xor = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i = 0; i<nums.length; i++){
            xor ^= nums[i];
            if (map.containsKey(xor^target)){
                count+=map.get(xor^target);
            }
            map.put(xor,map.getOrDefault(xor,0)+1);
        }
        return count;
    }
    public static void main(String[] args){
        int[] nums = {5, 6, 7, 8, 9};
        int target = 5;
        int[] nums1 = {4, 2, 2, 6, 4};
        int target1 = 6;
        System.out.println(CountSubarrayWithXORK(nums,target));
        System.out.println(CountSubarrayWithXORK(nums1,target1));
    }
}