package com.example.sudoku.models;

import com.example.sudoku.controllers.CellChangeListener;
import com.example.sudoku.utils.AlertsSudoku;
import com.example.sudoku.views.SudokuCell;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import java.util.Random;

/**
 * Represents the main Sudoku board in the interface.
 * This class combines model logic with the graphical view.
 * It is responsible for generating, displaying, and validating the board, as well as
 * detecting user actions when filling in cells.
 * @author Victoria Yuan
 * @author Karen Hoyos
 * @version 1.0
 */
public class SudokuBoard extends GridPane implements CellChangeListener {

    private int size;
    private int boxSize;
    private static final int SIZE = 6;
    private static final int MAX_NUM_HINTS_LEFT = 2;
    private SudokuCell[][] cells;
    private int[][] generatedSudoku;
    private int[][] numbersSudoku;
    private int numInitialShowNumbers;
    private int numOccupiedCells;

    /**
     * Creates a new Sudoku board with the specified size.
     * @param size board size.
     */
    public SudokuBoard(int size) {
        this.size = size;
        this.boxSize = (int) Math.sqrt(size);
        this.numOccupiedCells = 0;
        cells = new SudokuCell[SIZE][SIZE];
        numInitialShowNumbers = 5;
        generateBoard(size);
    }
    /**
     * Generates a new dashboard and displays it on screen.
     * @param size Size of the dashboard to generate.
     */
    public void generateBoard(int size) {
        this.getChildren().clear();

        SudokuGenerator sudokuGenerator = new SudokuGenerator(SIZE);
        generatedSudoku = sudokuGenerator.getBoard();
        numbersSudoku = copyBoard(generatedSudoku);

        hideRandomCells(numbersSudoku, (size * size) - numInitialShowNumbers);

        for (int row = 0; row < numbersSudoku.length; row++) {
            for (int col = 0; col < numbersSudoku.length; col++) {
                int value = numbersSudoku[row][col];
                boolean editable = value == 0;
                SudokuCell cell = new SudokuCell(row, col, editable, value);
                String baseColor = editable ? "white" : "#e0e0e0";
                String top = "1", right = "1", bottom = "1", left = "1";

                if (row % 2 == 0 && row != 0) top = "2";
                if (row == size - 1) bottom = "2";

                if (col % 3 == 0 && col != 0) left = "2";
                if (col == size - 1) right = "2";

                cell.setStyle(String.format(
                        "-fx-background-color: %s; " +
                                "-fx-border-color: black; " +
                                "-fx-border-width: %spx %spx %spx %spx; " +
                                "-fx-border-style: solid; " +
                                "-fx-alignment: center;",
                        baseColor, top, right, bottom, left
                ));
                cells[row][col] = cell;
                cell.setChangeListener(this);
                this.add(cell, col, row);
            }
        }
    }
    /**
     * Hides random cells on the board.
     * @param board base board
     * @param cellsToHide number of cells to hide
     */
    public static void hideRandomCells(int[][] board, int cellsToHide) {
        Random rand = new Random();
        boolean[][] keepVisible = new boolean[6][6];

        int blockRows = 2;
        int blockCols = 3;

        for (int startRow = 0; startRow < 6; startRow += blockRows) {
            for (int startCol = 0; startCol < 6; startCol += blockCols) {
                int visibles = 0;
                while (visibles < 2) {
                    int row = startRow + rand.nextInt(blockRows);
                    int col = startCol + rand.nextInt(blockCols);
                    if (!keepVisible[row][col]) {
                        keepVisible[row][col] = true;
                        visibles++;
                    }
                }
            }
        }
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (!keepVisible[row][col]) {
                    board[row][col] = 0;
                }
            }
        }
    }

    /**
     * Provides a random hint to the player.
     */
    public void getHint() {
        Random rand = new Random();
        boolean found = false;
        numOccupiedCells = numberOccupiedCells();
        int hintsLeft = ((size * size) - numOccupiedCells);

        if (hintsLeft > MAX_NUM_HINTS_LEFT) {
            while (!found) {
                int row = rand.nextInt(6);
                int col = rand.nextInt(6);
                SudokuCell cell = cells[row][col];
                if (cell.getValue() == 0) {
                    int hint = generatedSudoku[row][col];
                    cell.setValue(hint);
                    cell.setEditable(false);
                    cell.setStyle("-fx-background-color: #FCFF26; -fx-border-color: #808080;"); // amarillo
                    found = true;
                }
            }
            updateCellColors();
        }
    }
    /**
     * Calculates how many cells on the board are occupied.
     * @return the number of cells with a non-zero value
     */
    public int numberOccupiedCells() {
        int occupied = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].getValue() != 0) occupied++;
            }
        }
        return occupied;
    }
    /**
     * Creates an exact copy of a two-dimensional board.
     * @param board original board.
     * @return copy of the board.
     */
    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
        }
        return copy;
    }

    /**
     * @param row the row of the cell that changed.
     * @param col the column of the cell that changed.
     * @param newValue the new value assigned to the cell
     */
    @Override
    public void onValueChanged(int row, int col, int newValue) {
        updateCellColors();
        checkCompletion();
    }
    /**
     * Updates the colors of all cells based on their validity.
     */
    private void updateCellColors() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                SudokuCell cell = cells[r][c];

                if (!cell.isEditable()) {
                    if (cell.getStyle().contains("#FCFF26")) continue;
                    setCellBackground(cell, "#e0e0e0"); // gris
                    continue;
                }

                int val = cell.getValue();

                String originalBorders = cell.getStyle()
                        .replaceAll("-fx-background-color:[^;]+;", "")
                        .replaceAll("-fx-border-color:[^;]+;", "")
                        .replaceAll("-fx-border-style:[^;]+;", "")
                        .replaceAll("-fx-alignment:[^;]+;", "");

                if (val == 0) {
                    setCellBackground(cell, "white");
                } else if (isValidMove(r, c, val)) {
                    setCellBackground(cell, "#9EFF75"); // verde
                } else {
                    setCellBackground(cell, "#EB9478"); // rojo
                }

            }
        }
    }
    /**
     * Checks if a value is valid in a given cell.
     * @param row The cell's row.
     * @param col The cell's column.
     * @param value The value to validate.
     * @return {@code true} if the transaction is valid.
     */
    private boolean isValidMove(int row, int col, int value) {
        for (int i = 0; i < size; i++) {
            if ((i != col && cells[row][i].getValue() == value) ||
                    (i != row && cells[i][col].getValue() == value))
                return false;
        }

        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != row || c != col) && cells[r][c].getValue() == value)
                    return false;
            }
        }

        return true;
    }
    /**
     * Generates a new, clean board.
     * @param size Size of the new board.
     */
    public void generateNewBoard(int size) {
        generateBoard(size);
        numOccupiedCells = 0;
    }
    /**
     * Checks if the Sudoku has been completed correctly.
     * If it is completed, displays an alert and blocks editing.
     */
    private void checkCompletion() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int val = cells[r][c].getValue();
                if (val == 0 || !isValidMove(r, c, val)) {
                    return; // el tablero aún no está completo o hay errores
                }
            }
        }
        AlertsSudoku newAlert = new AlertsSudoku();
        newAlert.showAlert(Alert.AlertType.INFORMATION, "¡Felicidades!", "Has completado el Sudoku correctamente.");

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c].setEditable(false);
            }
        }
    }
    /**
     * Validates the current Sudoku state when the "Resolver" button is pressed.
     * Shows an alert depending on the current state of the board.
     */
    public void validateBoardOnDemand() {
        boolean hasEmptyCells = false;
        boolean hasErrors = false;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int val = cells[r][c].getValue();
                if (val == 0) {
                    hasEmptyCells = true;
                } else if (!isValidMove(r, c, val)) {
                    hasErrors = true;
                }
            }
        }

        AlertsSudoku alert = new AlertsSudoku();

        if (hasErrors) {
            alert.showAlert(Alert.AlertType.ERROR, "Sudoku incorrecto", "Hay errores en el tablero. Revisa los números marcados en rojo.");
        } else if (hasEmptyCells) {
            alert.showAlert(Alert.AlertType.WARNING, "Sudoku incompleto", "Aún hay casillas vacías.");
        } else {
            checkCompletion();
        }
        updateCellColors();
    }
    /**
     * Changes the background color of a Sudoku cell without affecting borders or other styles.
     * @param cell The cell of type whose background color you want to change.
     * @param color The background color to apply.
     */
    private void setCellBackground(SudokuCell cell, String color) {
        String style = cell.getStyle();

        style = style.replaceAll("-fx-background-color:[^;]+;", "");

        cell.setStyle(style + "-fx-background-color: " + color + ";");
    }



}
