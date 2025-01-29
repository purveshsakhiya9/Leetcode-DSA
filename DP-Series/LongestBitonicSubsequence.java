import java.util.Arrays;

public class LongestBitonicSubsequence {
    public static int longestBitonicSubsequence(int[] arr){
        int[] dp1 = new int[arr.length];
        int[] dp2 = new int[arr.length];
        Arrays.fill(dp1,1);
        Arrays.fill(dp2,1);
        for(int i = 0;i<arr.length;i++){
            for(int prev = 0;prev<i;prev++){
                if(arr[i]>arr[prev] && dp1[prev]+1>dp1[i]){
                    dp1[i] = dp1[prev]+1;
                }
            }
        }
        for(int i = arr.length-1;i>=0;i--){
            for(int after = arr.length-1;after>i;after--){
                if(arr[i]>arr[after] && dp2[after]+1>dp2[i]){
                    dp2[i] = dp2[after]+1;
                }
            }
        }
        int max = 0;
        for(int i = 0; i<arr.length;i++){
            max = Math.max(max,dp1[i]+dp2[i]-1);
        }
        return max;
    }
    public static void main(String[] args){
        int[] arr = {1,2,1,3,4};
        System.out.println(longestBitonicSubsequence(arr));
    }
}