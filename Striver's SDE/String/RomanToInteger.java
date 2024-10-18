public class RomanToInteger {
    public static int BruteForceRomanToInteger(String s){
        int value = 0;
        for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i)== 'M'){
                if(i>0 && s.charAt(i-1)=='C'){
                    value+=900;
                    i--;
                }else{
                    value += 1000;
                }
            }else if(s.charAt(i)== 'D'){
                if(i>0 && s.charAt(i-1)== 'C'){
                    value+=400;
                    i--;
                }else{
                    value += 500;
                }
            }else if(s.charAt(i)== 'C'){
                if(i>0 && s.charAt(i-1)== 'X'){
                    value+=90;
                    i--;
                }else{
                    value += 100;
                }
            }else if(s.charAt(i)== 'L'){
                if(i>0 && s.charAt(i-1)== 'X'){
                    value+=40;
                    i--;
                }else{
                    value += 50;
                }
            }else if(s.charAt(i)== 'X'){
                if(i>0 && s.charAt(i-1)== 'I'){
                    value+=9;
                    i--;
                }else{
                    value += 10;
                }
            }else if(s.charAt(i)== 'V'){
                if(i>0 && s.charAt(i-1)== 'I'){
                    value+=4;
                    i--;
                }else{
                    value += 5;
                }
            }else if(s.charAt(i)=='I'){
                value += 1;
            }
        }
        return value;
    }
    public static void main(String[] args){
        String s = "III";
        System.out.println(BruteForceRomanToInteger(s));
    }
}