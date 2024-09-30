public class SearchMatrix {
    public static boolean SearchMatrix(int[][] matrix, int target){
        int i = matrix.length-1;
        int j = matrix[0].length-1;
        if(target>matrix[i][j] || target<matrix[0][0]){
            return false;
        }
        while(i>=0 && j>=0){
            System.out.println(matrix[i][j]);
            if(matrix[i][j]>=target && matrix[i][0]<=target){
                if(matrix[i][j]==target){
                    return true;
                }else{
                    j--;
                }
            }else{
                i--;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(SearchMatrix(matrix,16));
    }
}