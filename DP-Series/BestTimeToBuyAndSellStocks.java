public class BestTimeToBuyAndSellStocks {
    public static int bestTimeToBuyAndSellStocks(int[] arr){
        int minimum = arr[0];
        int profit = 0;
        for(int i = 1;i< arr.length;i++){
            profit = Math.max(profit,arr[i]-minimum);
            minimum = Math.min(minimum,arr[i]);
        }
        return profit;
    }
    public static void main(String[] args){
        int[] arr = {1,2,3,4};
        int[] arr1 = {2,2,2,2};
        // Using Recursion
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Recursion: ");
        System.out.println(bestTimeToBuyAndSellStocks(arr));
        System.out.println(bestTimeToBuyAndSellStocks(arr1));
    }
}