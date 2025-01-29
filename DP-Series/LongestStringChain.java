import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {
    public static int longestStringChain(String[] arr){
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        int[] dp = new int[arr.length];
        Arrays.fill(dp,1);
        int max = 1;
        for(int i = 1;i<arr.length;i++){
            for(int prev = 0;prev<i;prev++){
                if(checkPossible(arr[i],arr[prev]) && 1+dp[prev]>dp[i]){
                    dp[i] = 1+dp[prev];
                }
            }
            if(dp[i]>max){
                max = dp[i];
            }
        }
        return max;
    }
    public static boolean checkPossible(String s1,String s2){
        if(s1.length()!=s2.length()+1) return false;
        int first = 0;
        int second = 0;
        while(first<s1.length()){
            if(second<s2.length() && s1.charAt(first)==s2.charAt(second)){
                first++;
                second++;
            }else{
                first++;
            }
        }
        return first == s1.length() && second == s2.length();
    }
    public static void main(String[] args){
        String[] arr = {"a","bc","ad","adc","bcd"};
        System.out.println(longestStringChain(arr));
    }
}