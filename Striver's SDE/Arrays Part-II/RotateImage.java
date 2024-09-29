public class RotateImage {
    public static int[][] RotateImage(int[][] matrix){
        int rows = matrix.length;
        int column = matrix[0].length;
        for(int i = 0; i<rows; i++){
            for(int j = i; j<column; j++){
                if(i!=j){
                    int temp = matrix[i][j];
                    matrix[i][j]=matrix[j][i];
                    matrix[j][i]=temp;
                }
            }
        }
        for(int i = 0; i<rows;i++){
            for(int j = 0;j<column/2;j++){
                int temp = matrix[i][j];
                matrix[i][j]=matrix[i][column-1-j];
                matrix[i][column-1-j]=temp;
            }
        }
        return matrix;
    }

    private static void printmatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printmatrix(matrix);
        System.out.println();
        matrix = RotateImage(matrix);
        printmatrix(matrix);
    }
}