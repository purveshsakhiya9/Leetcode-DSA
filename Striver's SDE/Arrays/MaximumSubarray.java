public class MaximumSubarray {
    public static void main(String[] args){
        int[] arr = {5,4,-1,7,8};
        int res = MaximumSubarray(arr);
        System.out.println(res);
    }

    private static int MaximumSubarray(int[] arr) {
        int maxsum = Integer.MIN_VALUE;
        int sum = 0;
        for(int ar:arr){
            sum+=ar;
            if(sum>maxsum){
                maxsum = sum;
            }
            if(sum<0){
                sum = 0;
            }
        }
        return maxsum;
    }
}