import java.util.Arrays;

public class AssignCookies {
    public static int AssignCookies(int[] g, int[] s){
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0;
        int j=0;
        while(i<g.length && j<s.length){
            if(s[j]>=g[i]){
                i++;
                j++;
            }
            else{
                j++;
            }
        }
        return i;
    }
    public static void main(String[] args){
        int[] s = {1,1};
        int[] g = {1,2,3};
        System.out.println(AssignCookies(g,s));
    }
}