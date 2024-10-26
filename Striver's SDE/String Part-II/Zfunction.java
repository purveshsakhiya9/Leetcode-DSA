public class Zfunction {
    public static int Zfunction(String haystack, String needle) {
        if(haystack.contains(needle)){
            int flag;
            for(int i = 0; i< haystack.length();i++){
                flag = 0;
                for(int j = 0;j<needle.length();j++){
                    if(haystack.charAt(i+j)!=needle.charAt(j)){
                        flag = 1;
                        break;
                    }
                }
                if(flag ==0){
                    return i;
                }

            }
        }
        return -1;
    }
    public static void main(String[] args){
        String haystack = "sadbutsad";
        String needle = "sad";
        System.out.println(Zfunction(haystack,needle));
    }
}