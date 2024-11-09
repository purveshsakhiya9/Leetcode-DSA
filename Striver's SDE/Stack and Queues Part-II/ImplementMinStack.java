import java.util.Stack;

public class ImplementMinStack {
    static Stack<Integer> stack;
    static Stack<Integer> minStack;
    public ImplementMinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.empty() || val<=minStack.peek()){
            minStack.push(val);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
            stack.pop();
        }else{
            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
    public static void main(String[] args){
        ImplementMinStack obj = new ImplementMinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        int param_2 = obj.getMin();
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();
        System.out.println(param_2+" "+param_3+" "+param_4);

    }
}