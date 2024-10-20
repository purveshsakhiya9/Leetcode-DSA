import java.util.Arrays;

public class LongestCommonprefix {
    public static String LongestCommonprefix(String[] arr){
        Arrays.sort(arr);
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<Math.min(arr[0].length(), arr[arr.length-1].length());i++){
            if(arr[0].charAt(i)!=arr[arr.length-1].charAt(i))
                break;
            s.append(arr[0].charAt(i));
        }
        return String.valueOf(s);
    }
    public static void main(String[] args){
        String[] arr = {"flower","flow","flight"};
        System.out.println(LongestCommonprefix(arr));
    }
}