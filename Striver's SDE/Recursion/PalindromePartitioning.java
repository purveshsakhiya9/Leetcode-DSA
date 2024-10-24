import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static List<List<String>> PalindromePartitioning(String s){
        List<List<String>> result = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        track(0,s,result,ans);
        return result;
    }
    public static void track(int index, String s, List<List<String>> result, List<String> ans){
        if(index==s.length()){
            result.add(new ArrayList<>(ans));
            return;
        }
        for(int i=index;i<s.length();i++){
            if(isPalindrome(s,index,i)){
                ans.add(s.substring(index,i+1));
                track(i+1,s, result, ans);
                ans.remove(ans.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String s, int index, int i) {
        while(index<=i){
            if(s.charAt(index)!=s.charAt(i)){
                return false;
            }
            index++;
            i--;
        }
        return true;
    }

    public static void main(String[] args){
        String s = "aab";
        List<List<String>> result = PalindromePartitioning(s);
        System.out.print("[");
        for(int i = 0; i< result.size(); i++){
            System.out.print(result.get(i));
            if(result.size()-1 != i){
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}