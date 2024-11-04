import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueue {
    Queue<Integer> que1;
    public ImplementStackUsingQueue() {
        que1 = new LinkedList<>();
    }

    public void push(int x) {
        que1.offer(x);
        int n = que1.size();
        for(int i = 0; i<n-1;i++){
            que1.offer(que1.poll());
        }
    }

    public int pop() {
        return que1.poll();
    }

    public int top() {
        return que1.peek();
    }

    public boolean empty() {
        return que1.isEmpty();
    }
    public static void main(String[] args){
        ImplementStackUsingQueue obj = new ImplementStackUsingQueue();
        obj.push(1);
        int param_2 = obj.top();
        int param_3 = obj.pop();
        boolean param_4 = obj.empty();
        obj.push(2);
        boolean param_5 = obj.empty();
        System.out.println(param_2+" "+param_3+" "+param_4+" "+param_5);
    }
}