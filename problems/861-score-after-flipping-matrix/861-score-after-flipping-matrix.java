
class Solution {
    public int matrixScore(int[][] grid) {

        int rows = grid.length, cols = grid[0].length;

        // Step1: flip all rows that is starting with a zero
        for (int r = 0; r < rows; r++) {

            if (grid[r][0] == 0) {

                for (int c = 0; c < cols; c++) {
                    grid[r][c] = grid[r][c] == 1 ? 0 : 1;
                }
            }
        }

        // Step 2: flip all columns where the number of ONE is less than the number of ZERO
        for (int c = 1; c < cols; c++) {

            int oneCount = 0;

            for (int r = 0; r < rows; r++) {
                oneCount += grid[r][c];
            }

            if (oneCount * 2 < rows) {

                for (int r = 0; r < rows; r++) {
                    grid[r][c] = grid[r][c] == 1 ? 0 : 1;
                }
            }
        }

        // Step 3: Calculate the total score
        int result = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

//                if (grid[r][c]==1)
//                    result += (int) Math.pow(2, (cols-c-1));

                result += grid[r][c] << (cols - c - 1);
            }
        }

        return result;
    }
}