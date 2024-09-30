public class InversionOfArray {
    public static int BruteForceApproac(int[] nums){
        int count = 0;
        for(int i = 0; i< nums.length; i++){
            for(int j = i; j< nums.length; j++){
                if(nums[i]>nums[j]){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args){
        int[] nums = {2,5,1,3,4};
        System.out.println(BruteForceApproac(nums));
    }
}
