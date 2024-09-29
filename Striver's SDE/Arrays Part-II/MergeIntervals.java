import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.max;

public class MergeIntervals {
    public static int[][] MergeIntervals(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<int[]> prev = new ArrayList<>();
        prev.add(intervals[0]);
        for(int i = 1;i<intervals.length;i++){
            int val = prev.get(prev.size() - 1)[prev.get(prev.size() - 1).length - 1];
            if(intervals[i][0]<val){
                prev.get(prev.size() - 1)[prev.get(prev.size() - 1).length - 1] = max(intervals[i][1], val);
            }else{
                prev.add(intervals[i]);
            }
        }
        return prev.toArray(new int[prev.size()-1][]);
    }
    public static void main(String[] args){
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        intervals = MergeIntervals(intervals);
        for (int[] arr : intervals) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();  // Newline after each inner array
        }
    }
}