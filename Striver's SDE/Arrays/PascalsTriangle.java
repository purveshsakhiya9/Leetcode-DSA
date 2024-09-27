import java.util.ArrayList;
import java.util.List;

/**
 * Class to generate Pascal's Triangle for a given number of rows.
 */
public class PascalsTriangle {

    /**
     * Generates Pascal's Triangle up to the specified number of rows.
     * Each row in Pascal's Triangle is formed by summing adjacent values from the previous row.
     *
     * @param numRows The number of rows of Pascal's Triangle to generate.
     * @return A List of Lists representing Pascal's Triangle.
     */
    private static List<List<Integer>> PascalsTriangle(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }

        // Recursive call to get previous rows
        List<List<Integer>> triangle = PascalsTriangle(numRows - 1);
        List<Integer> curr = new ArrayList<>();
        curr.add(1); // First element is always 1

        // Add the middle elements of the row
        if (numRows > 1) {
            List<Integer> prev = triangle.get(triangle.size() - 1); // Previous row
            for (int i = 1; i < prev.size(); i++) {
                curr.add(prev.get(i) + prev.get(i - 1));
            }
            curr.add(1); // Last element is always 1
        }

        triangle.add(curr); // Add the current row to the triangle
        return triangle;
    }

    /**
     * Prints Pascal's Triangle row by row.
     *
     * @param res A List of Lists representing Pascal's Triangle.
     */
    private static void printlist(List<List<Integer>> res) {
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    /**
     * Main method to run the program and generate Pascal's Triangle.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int numRows = 5;  // Specify the number of rows to generate

        // Generate Pascal's Triangle
        List<List<Integer>> res = PascalsTriangle(numRows);

        // Print the result
        printlist(res);
    }
}
