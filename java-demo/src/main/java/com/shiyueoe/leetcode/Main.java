package com.shiyueoe.leetcode;

import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args) {
        char[][] board = {
                {'E','E','E','E','E'},
                {'E','E','M','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}
        };
        Solution solution = new Solution();
        solution.updateBoard(board, new int[]{3,0});
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];

        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            return board;
        }

        if(board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }

        if(board[i][j] == 'B'){
            return board;
        }

        if(board[i][j] == 'E'){
            //统计周围雷的数量
            int count = countM(board,i,j);
            if(count == 0){
                board[i][j] = 'B';
                //周围都是空的，递归揭露
                for(int k = 0;k < grid.length;k++){
                    int newI = i + grid[k][0];
                    int newJ = j + grid[k][1];
                    int[] newIndex = {newI,newJ};
                    return updateBoard(board,newIndex);
                }
            }
            //更新数字
            board[i][j] = (char)count;
            return board;
        }

        return board;

    }

    private int[][] grid = {
            {-1,-1},{-1,0},{-1,1},
            {0,-1},      {0,1},
            {1,-1},{1,0},{1,1}
    };
    private int countM(char[][] board,int i,int j){
        int sum = 0;
        for(int x = 0;x < grid.length;x++){
            int newI = i + grid[x][0];
            int newJ = j + grid[x][1];

            if(newI >= 0 && newJ >= 0 && newI < board.length && newJ < board[0].length){
                if(board[newI][newJ] == 'M'){
                    sum++;
                }
            }
        }
        return sum;
    }
}