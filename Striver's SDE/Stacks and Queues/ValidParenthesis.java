import java.util.Stack;

public class ValidParenthesis {
    public static boolean ValidParenthesis(String s) {
        Stack<Character> st = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                st.push(s.charAt(i));
            }else if(!st.empty() && s.charAt(i)== ')'){
                if(st.peek()== '('){
                    st.pop();
                }else{
                    return false;
                }
            }else if(!st.empty() &&s.charAt(i)== '}'){
                if(st.peek()== '{'){
                    st.pop();
                }else{
                    return false;
                }
            }
            else if(!st.empty() &&s.charAt(i)== ']'){
                if(st.peek()== '['){
                    st.pop();
                }else{
                    return false;
                }
            }else
                return false;
        }
        if(st.empty()){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args){
        String s = "({}[])";
        String t = "([[)]";

        System.out.println(ValidParenthesis(s));
        System.out.println(ValidParenthesis(t));
    }
}