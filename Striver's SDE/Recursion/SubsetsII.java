import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public static List<List<Integer>> SubsetsII(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        track(0, nums, list, ans);
        return ans;
    }
    private static void track(int index, int[] nums, List<Integer> list, List<List<Integer>> ans){
        ans.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1])
                continue;
            list.add(nums[i]);
            track(i + 1, nums, list, ans);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args){
        int[] nums = {1,2,3};
        List<List<Integer>> result = SubsetsII(nums);
        // Printing the result list
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