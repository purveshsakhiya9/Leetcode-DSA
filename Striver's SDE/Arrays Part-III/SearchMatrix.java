public class SearchMatrix {
//    Search in a matrix with complexity O(M+N)
    public static boolean SearchMatrix(int[][] matrix, int target){
        int i = matrix.length-1;
        int j = matrix[0].length-1;
        if(target>matrix[i][j] || target<matrix[0][0]){
            return false;
        }
        while(i>=0 && j>=0){
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

//    Search in a matrix with complexity O(log(M*N))
    public static boolean optimalSearchMatrix(int[][] matrix,int target){
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0, right = (row*col)-1;
        while(left<=right){
            int mid = (right+left)/2;
            int rowidx = mid/col;
            int colidx = mid%col;
            if(matrix[rowidx][colidx]==target){
                return true;
            }else if(matrix[rowidx][colidx]<target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(SearchMatrix(matrix,1));
        System.out.println(optimalSearchMatrix(matrix,10));
    }
}