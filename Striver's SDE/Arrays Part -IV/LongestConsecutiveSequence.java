import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestConsecutiveSequence {

    public static int LongestConsecutiveSequence(int[] nums){
        int maxcount = 0, count=0;
        HashSet<Integer> map = new HashSet<>();
        for(int i = 0; i<nums.length; i++){
            map.add(nums[i]);
        }
        for(int num:map){
            if(!map.contains(num-1)) {
                while (map.contains(num)) {
                    count++;
                    num++;
                }
                if (count > maxcount) {
                    maxcount = count;
                    count = 0;
                } else {
                    count = 0;
                }
            }
        }
        return maxcount;
    }

    public static void main(String[] args){
        int[] nums = {1,2,3,0,8,5,4};
        System.out.println(LongestConsecutiveSequence(nums));
    }
}