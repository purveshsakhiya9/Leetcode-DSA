import static java.lang.Math.max;

public class MaxConsecutiveOnes {
    public static int MaxConsecutiveOnes(int[] nums){
        int count = 0;
        int maxcount = Integer.MIN_VALUE;
        for(int i = 0; i< nums.length; i++){
            if(nums[i]==1){
                count++;
            }else{
                if(count>maxcount){
                    maxcount = count;
                }
                count = 0;
            }
        }
        return max(count,maxcount);
    }
    public static void main(String[] args){
        int[] nums = {1,1,0,1,1,1};
        System.out.println(MaxConsecutiveOnes(nums));
    }
}