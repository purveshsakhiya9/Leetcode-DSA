import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static int RottenOranges(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int visited[][] = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int freshOrange = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) freshOrange++;
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        if (freshOrange == 0) return 0;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int flag = 0;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                visited[r][c] = 1;

                if (r + 1 < m && grid[r + 1][c] == 1 && visited[r + 1][c] != 1) {
                    queue.add(new int[]{r + 1, c});
                    grid[r + 1][c] = 2;
                    freshOrange--;
                    flag = 1;
                }

                if (r - 1 >= 0 && grid[r - 1][c] == 1 && visited[r - 1][c] != 1) {
                    queue.add(new int[]{r - 1, c});
                    grid[r - 1][c] = 2;
                    freshOrange--;
                    flag = 1;
                }

                if (c + 1 < n && grid[r][c + 1] == 1 && visited[r][c + 1] != 1) {
                    queue.add(new int[]{r, c + 1});
                    grid[r][c + 1] = 2;
                    freshOrange--;
                    flag = 1;
                }

                if (c - 1 >= 0 && grid[r][c - 1] == 1 && visited[r][c - 1] != 1) {
                    queue.add(new int[]{r, c - 1});
                    grid[r][c - 1] = 2;
                    freshOrange--;
                    flag = 1;
                }
            }
            if (flag == 1) {
                count++;
            }
        }

        if (freshOrange > 0) {
            return -1;
        }

        return count;
    }

    public static void main(String[] args){
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(RottenOranges(grid));

        int[][] grid1 = {{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(RottenOranges(grid1));

        int[][] grid2 = {{0,2}};
        System.out.println(RottenOranges(grid2));
    }
}
