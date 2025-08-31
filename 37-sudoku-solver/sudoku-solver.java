class Solution {
    private boolean[][] row = new boolean[9][10];
    private boolean[][] col = new boolean[9][10];
    private boolean[][] box = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int d = board[r][c] - '0';
                    row[r][d] = col[c][d] = box[getBox(r, c)][d] = true;
                }
            }
        }
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int r, int c) {
        if (r == 9) return true; 

        int nr = (c == 8) ? r + 1 : r;
        int nc = (c + 1) % 9;

        if (board[r][c] != '.') {
            return backtrack(board, nr, nc);
        }

        int b = getBox(r, c);
        for (int d = 1; d <= 9; d++) {
            if (!row[r][d] && !col[c][d] && !box[b][d]) {
                board[r][c] = (char) ('0' + d);
                row[r][d] = col[c][d] = box[b][d] = true;

                if (backtrack(board, nr, nc)) return true;

                
                board[r][c] = '.';
                row[r][d] = col[c][d] = box[b][d] = false;
            }
        }
        return false;
    }

    private int getBox(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
