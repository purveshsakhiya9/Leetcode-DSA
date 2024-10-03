public class UniequePath {

    public static int BruteForceUniquePath(int row, int column){
        if(row==1 || column==1){
            return 1;
        }
        return BruteForceUniquePath(row-1,column)+BruteForceUniquePath(row,column-1);
    }
    public static int OptimalUniequePath(int row, int column){
        int[][] dp = new int[row][column];
        for(int i = 0; i<row; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j<column; j++){
            dp[0][j] = 1;
        }
        for(int i = 1; i<row; i++){
            for(int j = 1; j<column; j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[row-1][column-1];
    }
    public static void main(String[] args){
        System.out.println(BruteForceUniquePath(3,7));
        System.out.println(OptimalUniequePath(3,7));
    }

}