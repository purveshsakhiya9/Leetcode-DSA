import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopKthFrequentElements {
    public static int[] TopKthFrequentElements(int[] nums, int k) {
        List<Integer> bucket[] = new List[nums.length + 1];
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int p: nums){
            hm.put(p, hm.getOrDefault(p,0)+1);
        }
        for(int p: hm.keySet()){
            int frequency = hm.get(p);
            if(bucket[frequency] == null){
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(p);
        }
        int [] result = new int[k];
        int counter =0;
        for(int i = bucket.length-1; i>=0 && counter < k; i--){
            if(bucket[i] != null){
                for(Integer integer: bucket[i]){
                    result[counter++] = integer;
                }
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] result = TopKthFrequentElements(nums,k);
        System.out.print("[");
        for(int i=0;i< result.length-1;i++){
            System.out.print(result[i]+", ");
        }
        System.out.print(result[result.length-1]+"]");

    }
}