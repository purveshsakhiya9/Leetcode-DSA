/**
 * The SetMatrixZeroes class provides a solution to set entire rows and columns 
 * to zero if any element in a matrix is zero.
 */
public class SetMatrixZeroes {

    /**
     * Sets the entire row and column to zero if any element in the matrix is zero.
     *
     * @param matrix The input 2D integer array (matrix).
     * @return The modified matrix where rows and columns containing zeroes 
     * have been set to zero.
     */
    public static int[][] SetMatrixZeroes(int[][] matrix) {
        int col = 1;  // Tracks if the first column should be zeroed
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;  // Mark the first element of the row as zero
                    if (j == 0) {
                        col = 0;  // Mark the column if a zero is found in the first column
                    } else {
                        matrix[0][j] = 0;  // Mark the first element of the column as zero
                    }
                }
            }
        }

        // Iterate again to update non-zero elements based on the marked row/column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // Handle the first row if necessary
        if (matrix[0][0] == 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        // Handle the first column if necessary
        if (col == 0) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][0] = 0;
            }
        }
        return matrix;
    }

    /**
     * Utility method to print the contents of a 2D matrix.
     *
     * @param matrix The matrix to be printed.
     */
    private static void printmatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Main method to execute and test the SetMatrixZeroes functionality.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int matrix[][] = { { 1, 2, 3, 4 }, { 5, 0, 7, 8 }, { 0, 10, 11, 12 }, { 13, 14, 15, 0 } };

        // Print the initial matrix
        System.out.println("Original Matrix:");
        printmatrix(matrix);
        System.out.println();

        // Apply SetMatrixZeroes and print the result
        int result[][] = SetMatrixZeroes(matrix);

        // Print the modified matrix
        System.out.println("Modified Matrix:");
        printmatrix(result);
    }
}
