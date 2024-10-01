public class XPowerOfN {
    public static double XPowerOfN(double x, int n){
        //convert n into long first
        long longn = n;
        long result = 1;

        //if n is negative convert it into positive by doing reciprocal of x
        // and n into positive
        if(n<0){
            x=1/x;
            longn = -longn;
        }
        //now calculate power log(n
        while(longn>0){
            //check if n is odd add once
            if(longn%2==1){
                result*=x;
            }
            x*=x;
            longn/=2;
        }
        return result;
    }

    //brute force or normal method which can lead to tle or infinity
    public static double myPow(double x, int n) {
        double res=1;
        if(x==0){return 0;}
        for(int i=0; i<Math.abs(n); i++){
            if(n<0){
                res *= 1/x;
            }else{
                res*=x;
            }
        }
        return res;
    }

    public static void main(String[] args){
        double x = 2.0;
        int n = 2147483647;
        System.out.println(myPow(x,n));
        System.out.println(XPowerOfN(x,n));
    }
}