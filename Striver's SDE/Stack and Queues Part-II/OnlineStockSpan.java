import java.util.Stack;

public class OnlineStockSpan {
    static Stack<int[]> s;
    public OnlineStockSpan(){
        s = new Stack<>();
    }
    public static int next(int price){
        int span = 1;
        while(!s.isEmpty() && s.peek()[0] <=price){
            span+=s.pop()[1];
        }
        s.push(new int[] {price,span});
        return span;
    }

    public static void main(String[] args){
        OnlineStockSpan obj = new OnlineStockSpan();
        int[] input = {100,80,60,70,60,75,85};
        int[] output = new int[input.length];
        for(int i = 0; i<input.length; i++){
            output[i] = obj.next(input[i]);
        }
        System.out.print("[");
        for(int i = 0; i< output.length-1; i++){
            System.out.print(output[i]+", ");
        }
        System.out.print(output[output.length-1]+"]");
    }
}