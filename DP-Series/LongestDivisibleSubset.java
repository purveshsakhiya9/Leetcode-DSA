import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LongestDivisibleSubset {
    public static ArrayList<Integer> longestDivisibleSubsequence(int[] arr){
        int n = arr.length;
        Arrays.sort(arr);
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int[] hash=new int[n];
        for(int i = 0; i<n;i++){
            hash[i] = i;
            for(int prev = 0;prev<i;prev++){
                if(arr[i]%arr[prev]==0 && 1+dp[prev]>dp[i]){
                    dp[i] = 1+dp[prev];
                    hash[i] = prev;
                }
            }
        }

        int ans = dp[0];
        int lastIndex = 0;
        for(int i = 1; i<n;i++){
            if(dp[i]>ans) {
                ans = dp[i];
                lastIndex = i;
            }
        }
        ArrayList<Integer>  print = new ArrayList<>() ;
        int a = ans-1;
        print.add(arr[lastIndex]);
        while(hash[lastIndex]!=lastIndex){
            lastIndex = hash[lastIndex];
            print.add(arr[lastIndex]);
        }
        return print;
    }
    public static void main(String[] args){
        int[] arr = {6,3,3,3};
        ArrayList<Integer> ans =longestDivisibleSubsequence(arr);
        System.out.println(ans);
    }
}