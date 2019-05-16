package john.com.sudoko.View;

import android.content.Context;

import john.com.sudoko.Controller.MainActivity;
import john.com.sudoko.Model.GameEngine;
import john.com.sudoko.R;

public class GameGrid {
    private SudokuCell[][] Sudoku;

    public Context context;
    public String[] EWords;

    public GameGrid(Context context, String[] EWords) {
        this.context = context;
        this.EWords = EWords;
        if (MainActivity.getfourXfour()) {
            Sudoku = new SudokuCell[4][4];
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    Sudoku[x][y] = new SudokuCell(context, EWords, x, y);
                }
            }

        } else if (MainActivity.getsixXsix()) {
            Sudoku = new SudokuCell[6][6];
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    Sudoku[x][y] = new SudokuCell(context, EWords, x, y);
                }
            }
        } else if (MainActivity.gettwelveXtwelve()) {
            Sudoku = new SudokuCell[12][12];
            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 12; y++) {
                    Sudoku[x][y] = new SudokuCell(context, EWords, x, y);
                }
            }
        } else if (MainActivity.getnineXnine()) {
            Sudoku = new SudokuCell[9][9];
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    Sudoku[x][y] = new SudokuCell(context, EWords, x, y);
                }
            }
        }

    }

    public SudokuCell getItem(int position) {
        int x = 0;
        int y = 0;
        if (MainActivity.getfourXfour()) {
            x = position % 4;
            y = position / 4;

            return Sudoku[x][y];
        }

        if (MainActivity.getsixXsix()) {
            x = position % 6;
            y = position / 6;

            return Sudoku[x][y];
        }

        if (MainActivity.getnineXnine()) {
            x = position % 9;
            y = position / 9;

            return Sudoku[x][y];
        }

        if (MainActivity.gettwelveXtwelve()) {
            x = position % 12;
            y = position / 12;
        }

        return Sudoku[x][y];
    }

    public SudokuCell getItem(int x, int y) {
        return Sudoku[x][y];
    }

    public void setItem(int x, int y, int number) {
        Sudoku[x][y].setValue(number);
        int[][] sudukoGrid = new int[12][12];
        if (MainActivity.getfourXfour() == true) {
            sudukoGrid = new int[4][4];
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    sudukoGrid[a][b] = getItem(a, b).getValue();
                }
            }

        } else if (MainActivity.getsixXsix() == true) {
            sudukoGrid = new int[6][6];
            for (int a = 0; a < 6; a++) {
                for (int b = 0; b < 6; b++) {
                    sudukoGrid[a][b] = getItem(a, b).getValue();
                }
            }
        } else if (MainActivity.gettwelveXtwelve() == true) {
            sudukoGrid = new int[12][12];
            for (int a = 0; a < 12; a++) {
                for (int b = 0; b < 12; b++) {
                    sudukoGrid[a][b] = getItem(a, b).getValue();
                }
            }
        } else if (MainActivity.getnineXnine()) {
            sudukoGrid = new int[9][9];
            for (int a = 0; a < 9; a++) {
                for (int b = 0; b < 9; b++) {
                    sudukoGrid[a][b] = getItem(a, b).getValue();
                }
            }
        }

        GameEngine.getInstance().checkSudokuOnGame(sudukoGrid);
    }

    public void setGrid(int[][] grid) {
        if (MainActivity.getfourXfour() == true) {
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    Sudoku[x][y].setInitValue(grid[x][y]);

                    if (grid[x][y] != 0) {
                        Sudoku[x][y].setNotModifiable();
                    } else {
                        Sudoku[x][y].setBackgroundDrawable(context.getDrawable(R.drawable.cell));
                    }
                }
            }
        } else if (MainActivity.getsixXsix() == true) {
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    Sudoku[x][y].setInitValue(grid[x][y]);
                    if (grid[x][y] != 0) {
                        Sudoku[x][y].setNotModifiable();
                    } else {
                        Sudoku[x][y].setBackgroundDrawable(context.getDrawable(R.drawable.cell));
                    }
                }
            }
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 12; y++) {
                    Sudoku[x][y].setInitValue(grid[x][y]);

                    if (grid[x][y] != 0) {
                        Sudoku[x][y].setNotModifiable();
                    } else {
                        Sudoku[x][y].setBackgroundDrawable(context.getDrawable(R.drawable.cell));
                    }
                }
            }
        } else {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    Sudoku[x][y].setInitValue(grid[x][y]);

                    if (grid[x][y] != 0) {
                        Sudoku[x][y].setNotModifiable();
                    } else {
                        Sudoku[x][y].setBackgroundDrawable(context.getDrawable(R.drawable.cell));
                    }
                }
            }
        }
    }
}
