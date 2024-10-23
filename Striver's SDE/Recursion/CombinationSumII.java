import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public static List<List<Integer>> CombinationSumII(int[] num, int target){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(num);
        track(num,result,new ArrayList<>(),0,target);
        return result;
    }


    public static void track(int[] num, List<List<Integer>> result, List<Integer> curr, int index, int target){
        if(target==0){
            result.add(new ArrayList<>(curr));
            return;
        }
        for(int i = index; i< num.length; i++){
            if(i>index && num[i-1]==num[i]) continue;
            if(target<num[i]) break;
            curr.add(num[i]);
            track(num,result,curr,i+1,target-num[i]);
            curr.remove(curr.size()-1);
        }
    }
    public static void main(String[] args){
        int[] num = {2,5,2,1,2};
        int target = 5;
        List<List<Integer>> result = CombinationSumII(num,target);
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}