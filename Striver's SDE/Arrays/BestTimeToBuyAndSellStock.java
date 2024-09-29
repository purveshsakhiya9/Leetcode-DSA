import static java.lang.Math.max;
import static java.lang.Math.min;

public class BestTimeToBuyAndSellStock {
    public static int BestTimeToBuyAndSellStock(int[] prices){
        int buy = prices[0];
        int maxprofit = 0;
        for(int i=1;i< prices.length;i++){
            buy = min(buy,prices[i]);
            maxprofit = max(maxprofit,prices[i]-buy);
        }
        return maxprofit;
    }
    public static void main(String[] args){
        int[] prices = {7,1,5,3,6,4};
        int res = BestTimeToBuyAndSellStock(prices);
        System.out.println(res);
    }
}