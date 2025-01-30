import java.util.Arrays;

public class BooleanEvaluation {
    static final int MOD = 1000000007;
    public static int booleanEvaluationRecursion(String exp){
        return recursionHelper(0,exp.length()-1,exp,1);
    }
    private static int recursionHelper(int i, int j, String exp,int isTrue){
        if(i>j) return 0;
        if(i==j){
            if(isTrue==1) return exp.charAt(i)=='T'?1:0;
            else return exp.charAt(i)=='F'?1:0;
        }
        int ways = 0;
        for(int ind = i+1;ind<=j-1;ind+=2){
            int leftTrue = recursionHelper(i,ind-1,exp,1);
            int leftFalse = recursionHelper(i,ind-1,exp,0);
            int rightTrue = recursionHelper(ind+1,j,exp,1);
            int rightFalse = recursionHelper(ind+1,j,exp,0);
            if(exp.charAt(ind)=='&'){
                if(isTrue==1){
                    ways=(ways+(leftTrue*rightTrue))%MOD;
                }else {
                    ways=(ways+((leftTrue*rightFalse) + (leftFalse*rightTrue) + (leftFalse*rightFalse)))%MOD;
                }
            }else if(exp.charAt(ind)=='|'){
                if(isTrue==1){
                    ways=(ways+(leftTrue*rightFalse) + (leftFalse*rightTrue) + (leftTrue*rightTrue))%MOD;
                }else{
                    ways=(ways+(leftFalse*rightFalse))%MOD;
                }
            }else{
                if(isTrue==1){
                    ways=(ways+(leftTrue*rightFalse) + (leftFalse*rightTrue))%MOD;
                }else{
                    ways=(ways+(leftTrue*rightTrue) + (leftFalse*rightFalse))%MOD;
                }
            }
        }
        return ways;
    }
    public static int booleanEvaluationMemoization(String exp){
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        for(long[][] mat:dp){
            for(long[] row:mat){
                Arrays.fill(row,-1);
            }
        }
        return (int) memoizationHelper(0,exp.length()-1,exp,1,dp);
    }
    private static long memoizationHelper(int i, int j, String exp,int isTrue,long[][][] dp){
        if(i>j) return 0;
        if(i==j){
            if(isTrue==1) return exp.charAt(i)=='T'?1:0;
            else return exp.charAt(i)=='F'?1:0;
        }
        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }
        long ways = 0;
        for(int ind = i+1;ind<=j-1;ind+=2){
            long leftTrue = memoizationHelper(i,ind-1,exp,1,dp);
            long leftFalse = memoizationHelper(i,ind-1,exp,0,dp);
            long rightTrue = memoizationHelper(ind+1,j,exp,1,dp);
            long rightFalse = memoizationHelper(ind+1,j,exp,0,dp);
            if(exp.charAt(ind)=='&'){
                if(isTrue==1){
                    ways=(ways+(leftTrue*rightTrue))%MOD;
                }else {
                    ways=(ways+((leftTrue*rightFalse) + (leftFalse*rightTrue) + (leftFalse*rightFalse)))%MOD;
                }
            }else if(exp.charAt(ind)=='|'){
                if(isTrue==1){
                    ways=(ways+(leftTrue*rightFalse) + (leftFalse*rightTrue) + (leftTrue*rightTrue))%MOD;
                }else{
                    ways=(ways+(leftFalse*rightFalse))%MOD;
                }
            }else{
                if(isTrue==1){
                    ways=(ways+(leftTrue*rightFalse) + (leftFalse*rightTrue))%MOD;
                }else{
                    ways=(ways+(leftTrue*rightTrue) + (leftFalse*rightFalse))%MOD;
                }
            }
        }
        return dp[i][j][isTrue] = ways;
    }
    public static int booleanEvaluationTabulation(String exp){
        int n = exp.length();
        long[][][] dp = new long[n][n][2];
        for(int i = n-1;i>=0;i--){
            for(int j = i;j<n;j++){
                if(i==j){
                    if(exp.charAt(i)=='T') {
                        dp[i][j][1] = 1;
                        dp[i][j][0] = 0;
                    }else{
                        dp[i][j][0] = 1;
                        dp[i][j][1] = 0;
                    }
                }else{
                    for(int ind=i+1;ind<=j-1;ind+=2){
                        long leftTrue = dp[i][ind-1][1];
                        long leftFalse = dp[i][ind-1][0];
                        long rightTrue = dp[ind+1][j][1];
                        long rightFalse = dp[ind+1][j][0];
                        if(exp.charAt(ind)=='&'){
                            dp[i][j][1]=(dp[i][j][1]+(leftTrue*rightTrue))%MOD;
                            dp[i][j][0]=(dp[i][j][0]+((leftTrue*rightFalse) + (leftFalse*rightTrue) + (leftFalse*rightFalse)))%MOD;
                        }else if(exp.charAt(ind)=='|'){
                            dp[i][j][1]=(dp[i][j][1]+(leftTrue*rightFalse) + (leftFalse*rightTrue) + (leftTrue*rightTrue))%MOD;
                            dp[i][j][0]=(dp[i][j][0]+(leftFalse*rightFalse))%MOD;
                        }else{
                            dp[i][j][1]=(dp[i][j][1]+(leftTrue*rightFalse) + (leftFalse*rightTrue))%MOD;
                            dp[i][j][0]=(dp[i][j][0]+(leftTrue*rightTrue) + (leftFalse*rightFalse))%MOD;
                        }
                    }
                }
            }
        }
        return (int) dp[0][exp.length()-1][1];
    }
    public static void main(String[] args){
        String exp = "T^T^F";
        String exp1 = "F|T^F";
        // Using Recursion
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Recursion: ");
        System.out.println(booleanEvaluationRecursion(exp));
        System.out.println(booleanEvaluationRecursion(exp1));

        // Using Memoization
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Memoization: ");
        System.out.println(booleanEvaluationMemoization(exp));
        System.out.println(booleanEvaluationMemoization(exp1));

        // Using Tabulation
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Tabulation: ");
        System.out.println(booleanEvaluationTabulation(exp));
        System.out.println(booleanEvaluationTabulation(exp1));

        // Using Space Optimized
        // Time Complexity:
        // Space Complexity:
        System.out.println("Using Space Optimized: ");
        System.out.println();
        System.out.println();
    }
}