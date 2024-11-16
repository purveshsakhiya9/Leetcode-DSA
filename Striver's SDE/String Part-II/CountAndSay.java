public class CountAndSay {
    public static String CountAndSay(int n){
        String ans = "1";
        for(int i = 2; i<=n; i++){
            ans = calculate(ans);
        }
        return ans;
    }

    private static String calculate(String ans) {
        StringBuilder rle = new StringBuilder();
        char a = ans.charAt(0);
        int count = 1;

        for(int i = 1; i<ans.length(); i++){
            if(ans.charAt(i)==a){
                count++;
            }else{
                rle.append(count);
                rle.append(a);
                a = ans.charAt(i);
                count = 1;
            }
        }
        rle.append(count);
        rle.append(a);
        return rle.toString();
    }

    public static void main(String[] args){
        for(int i = 1; i<5; i++){
            System.out.println(CountAndSay(i));
        }
    }

}