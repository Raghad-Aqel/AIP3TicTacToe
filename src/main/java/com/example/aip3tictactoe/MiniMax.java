package com.example.aip3tictactoe;

public class MiniMax {

    final static char ai = 'o';
    final static char person = 'x';

    static int minimax(char board[][], Boolean isMaximizing) {
        int score = checkWinner(board);

        if (score != 0)
            return score;

        if (hasMoves(board) == false)
            return 0;

        if (isMaximizing) {
            int bestScore = -Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board[row][col] == ' ') {
                        board[row][col] = ai;
                        bestScore = Math.max(bestScore, minimax(board, false));
                        board[row][col] = ' ';
                    }
                }
            }
            return bestScore;
        }

        else {
            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board[row][col] == ' ') {
                        board[row][col] = person;
                        bestScore = Math.min(bestScore, minimax(board, true));
                        board[row][col] = ' ';
                    }
                }
            }
            return bestScore;
        }
    }
    static Boolean hasMoves(char board[][]) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (board[row][col] == ' ')
                    return true;
        return false;
    }
    private static int[] mapping(int n) {
        int[] arr = {n%3,  n/3};
        return arr;
    }

    static int checkWinner(char board[][]) {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int i = 0; i < winningCombinations.length; i++) {
            int[] position1 = mapping(winningCombinations[i][0]);
            int[] position2 = mapping(winningCombinations[i][1]);
            int[] position3 = mapping(winningCombinations[i][2]);
            if (board[position1[0]][position1[1]] != ' ' && board[position1[0]][position1[1]] == board[position2[0]][position2[1]] && board[position2[0]][position2[1]] == board[position3[0]][position3[1]]) {
                if (board[position1[0]][position1[1]] == ai) return 10;
                else return -10;
            }
        }

        return 0;
    }

    public static Move findBestMove(char board[][]) {
        int bestVal = -Integer.MAX_VALUE;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board[row][col] == ' ') {

                    board[row][col] = ai;

                    int moveVal = minimax(board, false);

                    board[row][col] = ' ';

                    if (moveVal > bestVal) {
                        bestMove.row = row;
                        bestMove.col = col;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }
}