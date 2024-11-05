import java.util.LinkedList;
import java.util.Stack;

public class ImplementQueueUsingStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public ImplementQueueUsingStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    public static void main(String[] args){
        ImplementQueueUsingStack obj = new ImplementQueueUsingStack();
        obj.push(1);
        boolean param_2 = obj.empty();
        int param_3 = obj.peek();
        int param_4 = obj.pop();
        boolean param_5 = obj.empty();
        System.out.println(param_2+" "+param_3+" "+param_4+" "+param_5);
    }
}