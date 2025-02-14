public class CountSquareSubmatricesWithAllOnes {
    public static int countSquareSubmatricesWithAllOnes(int n, int m, int[][] arr){
        int[][] dp = new int[n][m];
        int sum = 0;
        for(int j=0;j<m;j++){
            dp[0][j] = arr[0][j];
        }
        for(int i = 0;i<n;i++){
            dp[i][0] = arr[i][0];
        }
        for(int i = 1;i<n;i++){
            for(int j = 1;j<m;j++){
                if(arr[i][j]==1){
                    dp[i][j] = 1+ Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]));
                }else{
                    dp[i][j] =0;
                }
            }
        }
        for(int i = 0;i<n;i++){
            for(int j =0;j<m;j++){
                sum+=dp[i][j];
            }
        }
        return sum;
    }
    public static void main(String[] args){
        int[][] arr = {{0, 1, 1, 0},{1, 1, 1, 0},{0, 0, 1, 0}};
        int n = 3;
        int m = 4;
        System.out.println(countSquareSubmatricesWithAllOnes(n,m,arr));
    }
}