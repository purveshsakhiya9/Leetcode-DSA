import java.util.HashMap;

public class MinimumVariance {
    public static int MinimumVariance(int[] arr){
        int minVariance = Integer.MAX_VALUE;
        HashMap<Integer,Integer> subsequence = new HashMap<>();
        for(int i = 0; i< arr.length; i++){
            if(subsequence.containsKey(arr[i])){
                int length = i+1-subsequence.get(arr[i]);
                minVariance = Math.min(minVariance,length-2);
            }
            subsequence.put(arr[i],i);
        }
        return minVariance;
    }
    public static void main(String[] args){
        int[] arr = {4,5,7,4,6,4};
        System.out.println(MinimumVariance(arr));
    }
}