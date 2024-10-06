import java.util.HashSet;
import static java.lang.Math.max;

public class LongestSubstringWithoutRepeat {
    public static int LongestSubstringWithoutRepeat(String s){
        int length = 0;
        HashSet<Character> set = new HashSet<>();
        int j = 0;
        for(int i = 0; i<s.length(); i++){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
            }else{
                length = Math.max(length,set.size());
                while(s.charAt(j)!=s.charAt(i)){
                    set.remove(s.charAt(j));
                    j++;
                }
                j++;
            }
        }
        return Math.max(length,set.size());
    }
    public static void main(String[] args){
        String s = "abcabcbb";
        System.out.println(LongestSubstringWithoutRepeat(s));
    }
}