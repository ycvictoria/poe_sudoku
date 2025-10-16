package com.example.sudoku.views;

import com.example.sudoku.models.SudokuGenerator;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class SudokuBoard extends GridPane {

    private int size;

    private static final int SIZE = 6;
    private static final int MAX_NUM_HINTS_LEFT= 2;
   // private SudokuCell[][] cells = new SudokuCell[SIZE][SIZE];
   private SudokuCell[][] cells;
   private int[][] generatedSudoku;
   private int[][] numbersSudoku;
   private int numUsedHints;
   private int numInitialShowNumbers;

    public SudokuBoard(int size) {
        this.size = size;
        cells = new SudokuCell[SIZE][SIZE];
        generateBoard(size);
        //Only shows 5 numbers at start
        numInitialShowNumbers= 5;

    }

    public void generateBoard(int size) {

        this.getChildren().clear();
        //Generates a new valid sudoku int[][] y stores in numbersSudoku
        SudokuGenerator sudokuGenerator = new SudokuGenerator(SIZE);
        //generated valid sudoku
        generatedSudoku= sudokuGenerator.getBoard();

        //Creates a copy of the sudoku to play
        numbersSudoku = copyBoard(generatedSudoku);

        //hide the numbers on the cells, let the other as hints
        hideRandomCells(numbersSudoku,(size*size) - numInitialShowNumbers);

        for (int row = 0; row < numbersSudoku.length; row++) {
            for (int col = 0; col < numbersSudoku.length; col++) {
                /** int value = Math.random() < 0.3 ? (int) (Math.random() * 6 + 1) : 0; // 30% con números fijos*/
                int value = numbersSudoku[row][col];
                boolean editable = value == 0;

                SudokuCell cell = new SudokuCell(row, col, editable, value);
                cells[row][col] = cell;
                this.add(cell, col, row);
            }
        }

    }
    public static void hideRandomCells(int[][] board, int cellsToHide) {
        Random rand = new Random();
        int hidden = 0;

        while (hidden < cellsToHide) {
            int row = rand.nextInt(6);
            int col = rand.nextInt(6);

            // If cell is not empty yet, we hide it
            if (board[row][col] != 0) {
                board[row][col] = 0;  // 0 represents empty
                hidden++;
            }
        }
    }


    public int[][] getSudokuNumbers(){
        return numbersSudoku;
    }

    public void getHint(){
        Random rand = new Random();
        boolean found=false;
        //If has already used a lot of hints and only MAX_NUM_HINTS_LEFT, do not display more hints
        boolean stillHasHints= ((size*size) - numInitialShowNumbers- numUsedHints) > MAX_NUM_HINTS_LEFT;
        //System.out.println("hints "+stillHasHints);

        if(stillHasHints){
        while (!found) {
            int row = rand.nextInt(6);
            int col = rand.nextInt(6);

            // If cell is not empty yet, we hide it
            int randomCell= cells[row][col].getValue();

            if (randomCell==0 ) {
                int hint= generatedSudoku[row][col];
                System.out.println("hint"+hint);
                //Set the initial value of the cell of the generated sudoku
                cells[row][col].setValue(hint);  //
                cells[row][col].showHint(hint);
                found=true;
            }
        }
            numUsedHints++;
        }
    }


    //To create copy of board before starts game
    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
        }
        return copy;
    }

}
