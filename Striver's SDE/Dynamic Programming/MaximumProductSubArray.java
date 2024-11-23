public class MaximumProductSubArray {
    public static int MaximumProductSubArray(int[] arr){
        int product = 1;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<arr.length; i++){
            product*=arr[i];
            max = Math.max(max,product);
            if(product==0){
                product=1;
            }
        }
        product=1;
        for(int i = arr.length-1; i>=0; i--){
            product*=arr[i];
            max = Math.max(max,product);
            if(product==0){
                product=1;
            }
        }
        return max;
    }
    public static void main(String[] args){
        int[] arr = {2,3,-2,4};
        System.out.println(MaximumProductSubArray(arr));

        int[] arr1 = {-2,0,-1};
        System.out.println(MaximumProductSubArray(arr1));
    }
}