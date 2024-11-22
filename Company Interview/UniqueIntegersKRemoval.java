import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UniqueIntegersKRemoval {
    public static int UniqueIntegersKRemoval(int[] arr, int k){
        Arrays.sort(arr);
        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for(int i = 1; i< arr.length; i++){
            if(arr[i]==arr[i-1]){
                count++;
            }else{
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);
        Collections.sort(freq);
        int i = 0;
        while(k>0){
            if(freq.get(i)<=k){
                k-= freq.get(i);
                i++;
            }else{
                k=0;
            }
        }
        return freq.size()-i;
    }
    public static void main(String[] args){
        int[] arr = {5,5,4};
        int k = 1;
        System.out.println(UniqueIntegersKRemoval(arr,k));

        int[] arr1 = {4,3,1,1,3,3,2};
        int a = 3;
        System.out.println(UniqueIntegersKRemoval(arr1,a));
    }
}