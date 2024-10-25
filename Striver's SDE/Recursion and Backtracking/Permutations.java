import java.io.Flushable;
import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static List<List<Integer>> Permutations(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        track(nums,result,ans);
        return result;
    }
    public static void track(int[] nums, List<List<Integer>> result, List<Integer> ans){
        if(ans.size()== nums.length){
            result.add(new ArrayList<>(ans));
        }else{
            for(int i = 0; i< nums.length;i++){
                if(ans.contains(nums[i])) continue;
                ans.add(nums[i]);
                track(nums,result,ans);
                ans.remove(ans.size()-1);
            }
        }
    }
    public static void main(String[] args){
        int[] nums = {1,2,3};
        List<List<Integer>> result = Permutations(nums);
        System.out.print("[");
        for(int i = 0; i<result.size(); i++){
            System.out.print(result.get(i));
            if(result.size()-1!=i){
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}