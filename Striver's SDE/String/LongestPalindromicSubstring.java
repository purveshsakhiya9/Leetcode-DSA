
public class LongestPalindromicSubstring {
    private static String LongestPalindromicSubstring(String s){
        if (s.isEmpty()) return "";
        int start = 0; int end = 0;
        for(int i = 0; i<s.length(); i++){
            int oddLength = expand(s,i,i);
            int evenLength = expand(s,i,i+1);
            int maxLen = Math.max(oddLength, evenLength);
            if(maxLen>end-start){
                start = i-(maxLen-1)/2;
                end = i+(maxLen/2);
            }
        }
        return s.substring(start,end+1);
    }
    private static int expand(String s, int left, int right){
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
    public static void main(String[] args){
        String s= "babad";
        System.out.println(LongestPalindromicSubstring(s));
        s="cbbd";
        System.out.println(LongestPalindromicSubstring(s));
    }
}