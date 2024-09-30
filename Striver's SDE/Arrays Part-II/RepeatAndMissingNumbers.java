public class RepeatAndMissingNumbers {
    public static int[] RepeatAndMissingNumbers(int[] nums){
        int sum = 0;
        int actualsum = 0;
        int square = 0;
        int actualsqaure = 0;
        int sumdiff,squarediff;
        for(int i = 1; i<=nums.length;i++){
            sum+=nums[i-1];
            actualsum += (i);
            square += (nums[i-1]*nums[i-1]);
            actualsqaure+= ((i)*(i));
        }
        sumdiff = (actualsum-sum);
        squarediff = (actualsqaure-square)/sumdiff;
        return new int[]{(squarediff-sumdiff)/2,(squarediff+sumdiff)/2};
    }

    public static void main(String[] args){
        int[] nums = {1,2,2,3,4};
        int[] res = RepeatAndMissingNumbers(nums);
        System.out.println("["+res[0]+", "+res[1]+"]");
    }
}