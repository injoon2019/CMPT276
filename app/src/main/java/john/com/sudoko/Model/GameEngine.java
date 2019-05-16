package john.com.sudoko.Model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import john.com.sudoko.Controller.MainActivity;
import john.com.sudoko.View.GameGrid;
import john.com.sudoko.View.SudokuCell;


public class GameEngine {
    private static GameEngine instance;
    private GameGrid grid = null;
    int selectedPosX = -1;
    int selectedPosY = -1;
    private static String[] WordsToast;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context, int cell_replace_num, String[] EWords) {
        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        Sudoku = SudokuGenerator.getInstance().removeElements(Sudoku, cell_replace_num);
        grid = new GameGrid(context, EWords);
        grid.setGrid(Sudoku);
    }

    public GameGrid getGrid() {
        return grid;
    }

    public void setSelectedPositon(int x, int y) {
        selectedPosX = x;
        selectedPosY = y;
    }


    public void changeColor(int x, int y, int matrixSize, int position) {
        ArrayList<CoordinatorClass> selectedForLightYellow = new ArrayList<>();
        ArrayList<CoordinatorClass> selectedForCyan = new ArrayList<>();

        int textIndex = getGrid().getItem(position).getValue();
        if (textIndex != 0) {
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    if (getGrid().getItem(i, j).getValue() == textIndex) {
                        selectedForCyan.add(new CoordinatorClass(i, j));
                    }
                }
            }
        }
        for (int i = 0; i < matrixSize; i++) {
            selectedForLightYellow.add(new CoordinatorClass(i, y));
            selectedForLightYellow.add(new CoordinatorClass(x, i));
        }

        SudokuCell.setSpecialYellow(position);

        switch (matrixSize) {
            case 4:
                if (position == 0 || position == 1 || position == 4 || position == 5) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 0));
                    selectedForLightYellow.add(new CoordinatorClass(1, 0));
                    selectedForLightYellow.add(new CoordinatorClass(0, 1));
                    selectedForLightYellow.add(new CoordinatorClass(1, 1));
                } else if (position == 2 || position == 3 || position == 6 || position == 7) {
                    selectedForLightYellow.add(new CoordinatorClass(2, 0));
                    selectedForLightYellow.add(new CoordinatorClass(3, 0));
                    selectedForLightYellow.add(new CoordinatorClass(2, 1));
                    selectedForLightYellow.add(new CoordinatorClass(3, 1));
                } else if (position == 8 || position == 9 || position == 12 || position == 13) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 2));
                    selectedForLightYellow.add(new CoordinatorClass(1, 2));
                    selectedForLightYellow.add(new CoordinatorClass(0, 3));
                    selectedForLightYellow.add(new CoordinatorClass(1, 3));
                } else if (position == 10 || position == 11 || position == 14 || position == 15) {
                    selectedForLightYellow.add(new CoordinatorClass(2, 2));
                    selectedForLightYellow.add(new CoordinatorClass(3, 2));
                    selectedForLightYellow.add(new CoordinatorClass(2, 3));
                    selectedForLightYellow.add(new CoordinatorClass(3, 3));
                }
                break;

            case 6:
                if (position == 0 || position == 1 || position == 2 || position == 6 || position == 7 || position == 8) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 0));
                    selectedForLightYellow.add(new CoordinatorClass(0, 1));
                    selectedForLightYellow.add(new CoordinatorClass(1, 0));
                    selectedForLightYellow.add(new CoordinatorClass(1, 1));
                    selectedForLightYellow.add(new CoordinatorClass(2, 0));
                    selectedForLightYellow.add(new CoordinatorClass(2, 1));
                } else if (position == 12 || position == 13 || position == 14 || position == 18 || position == 19 || position == 20) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 2));
                    selectedForLightYellow.add(new CoordinatorClass(0, 3));
                    selectedForLightYellow.add(new CoordinatorClass(1, 2));
                    selectedForLightYellow.add(new CoordinatorClass(1, 3));
                    selectedForLightYellow.add(new CoordinatorClass(2, 2));
                    selectedForLightYellow.add(new CoordinatorClass(2, 3));
                } else if (position == 24 || position == 25 || position == 26 || position == 30 || position == 31 || position == 32) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 4));
                    selectedForLightYellow.add(new CoordinatorClass(0, 5));
                    selectedForLightYellow.add(new CoordinatorClass(1, 4));
                    selectedForLightYellow.add(new CoordinatorClass(1, 5));
                    selectedForLightYellow.add(new CoordinatorClass(2, 4));
                    selectedForLightYellow.add(new CoordinatorClass(2, 5));
                } else if (position == 27 || position == 28 || position == 29 || position == 33 || position == 34 || position == 35) {
                    selectedForLightYellow.add(new CoordinatorClass(3, 4));
                    selectedForLightYellow.add(new CoordinatorClass(3, 5));
                    selectedForLightYellow.add(new CoordinatorClass(4, 4));
                    selectedForLightYellow.add(new CoordinatorClass(4, 5));
                    selectedForLightYellow.add(new CoordinatorClass(5, 4));
                    selectedForLightYellow.add(new CoordinatorClass(5, 5));
                } else if (position == 15 || position == 16 || position == 17 || position == 21 || position == 22 || position == 23) {
                    selectedForLightYellow.add(new CoordinatorClass(3, 2));
                    selectedForLightYellow.add(new CoordinatorClass(3, 3));
                    selectedForLightYellow.add(new CoordinatorClass(4, 2));
                    selectedForLightYellow.add(new CoordinatorClass(4, 3));
                    selectedForLightYellow.add(new CoordinatorClass(5, 2));
                    selectedForLightYellow.add(new CoordinatorClass(5, 3));
                } else if (position == 3 || position == 4 || position == 5 || position == 9 || position == 10 || position == 11) {
                    selectedForLightYellow.add(new CoordinatorClass(3, 0));
                    selectedForLightYellow.add(new CoordinatorClass(3, 1));
                    selectedForLightYellow.add(new CoordinatorClass(4, 0));
                    selectedForLightYellow.add(new CoordinatorClass(4, 1));
                    selectedForLightYellow.add(new CoordinatorClass(5, 0));
                    selectedForLightYellow.add(new CoordinatorClass(5, 1));
                }
                break;

            case 9:
                if (position == 0 || position == 1 || position == 2 || position == 9 || position == 10 || position == 11 || position == 18|| position == 19 || position == 20) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 0));
                    selectedForLightYellow.add(new CoordinatorClass(0, 1));
                    selectedForLightYellow.add(new CoordinatorClass(0, 2));
                    selectedForLightYellow.add(new CoordinatorClass(1, 0));
                    selectedForLightYellow.add(new CoordinatorClass(1, 1));
                    selectedForLightYellow.add(new CoordinatorClass(1, 2));
                    selectedForLightYellow.add(new CoordinatorClass(2, 0));
                    selectedForLightYellow.add(new CoordinatorClass(2, 1));
                    selectedForLightYellow.add(new CoordinatorClass(2, 2));
                }

                if (position == 3 || position == 4 || position == 5 || position == 12 || position == 13 || position == 14 || position == 21|| position == 22 || position == 23) {
                    selectedForLightYellow.add(new CoordinatorClass(3, 0));
                    selectedForLightYellow.add(new CoordinatorClass(3, 1));
                    selectedForLightYellow.add(new CoordinatorClass(3, 2));
                    selectedForLightYellow.add(new CoordinatorClass(4, 0));
                    selectedForLightYellow.add(new CoordinatorClass(4, 1));
                    selectedForLightYellow.add(new CoordinatorClass(4, 2));
                    selectedForLightYellow.add(new CoordinatorClass(5, 0));
                    selectedForLightYellow.add(new CoordinatorClass(5, 1));
                    selectedForLightYellow.add(new CoordinatorClass(5, 2));
                }


                if (position == 6 || position == 7 || position == 8 || position == 15 || position == 16 || position == 17 || position == 24|| position == 25 || position == 26) {
                    selectedForLightYellow.add(new CoordinatorClass(6, 0));
                    selectedForLightYellow.add(new CoordinatorClass(6, 1));
                    selectedForLightYellow.add(new CoordinatorClass(6, 2));
                    selectedForLightYellow.add(new CoordinatorClass(7, 0));
                    selectedForLightYellow.add(new CoordinatorClass(7, 1));
                    selectedForLightYellow.add(new CoordinatorClass(7, 2));
                    selectedForLightYellow.add(new CoordinatorClass(8, 0));
                    selectedForLightYellow.add(new CoordinatorClass(8, 1));
                    selectedForLightYellow.add(new CoordinatorClass(8, 2));
                }

                if (position == 27 || position == 28 || position == 29 || position == 36 || position == 37 || position == 38 || position == 45|| position == 46 || position == 47) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 3));
                    selectedForLightYellow.add(new CoordinatorClass(0, 4));
                    selectedForLightYellow.add(new CoordinatorClass(0, 5));
                    selectedForLightYellow.add(new CoordinatorClass(1, 3));
                    selectedForLightYellow.add(new CoordinatorClass(1, 4));
                    selectedForLightYellow.add(new CoordinatorClass(1, 5));
                    selectedForLightYellow.add(new CoordinatorClass(2, 3));
                    selectedForLightYellow.add(new CoordinatorClass(2, 4));
                    selectedForLightYellow.add(new CoordinatorClass(2, 5));
                }

                if (position == 30 || position == 31 || position == 32 || position == 39 || position == 40 || position == 41 || position == 48|| position == 49 || position == 50) {
                    selectedForLightYellow.add(new CoordinatorClass(3, 3));
                    selectedForLightYellow.add(new CoordinatorClass(3, 4));
                    selectedForLightYellow.add(new CoordinatorClass(3, 5));
                    selectedForLightYellow.add(new CoordinatorClass(4, 3));
                    selectedForLightYellow.add(new CoordinatorClass(4, 4));
                    selectedForLightYellow.add(new CoordinatorClass(4, 5));
                    selectedForLightYellow.add(new CoordinatorClass(5, 3));
                    selectedForLightYellow.add(new CoordinatorClass(5, 4));
                    selectedForLightYellow.add(new CoordinatorClass(5, 5));
                }

                if (position == 33 || position == 34 || position == 35 || position == 42 || position == 43 || position == 44 || position == 51|| position == 52 || position == 53) {
                    selectedForLightYellow.add(new CoordinatorClass(6, 3));
                    selectedForLightYellow.add(new CoordinatorClass(6, 4));
                    selectedForLightYellow.add(new CoordinatorClass(6, 5));
                    selectedForLightYellow.add(new CoordinatorClass(7, 3));
                    selectedForLightYellow.add(new CoordinatorClass(7, 4));
                    selectedForLightYellow.add(new CoordinatorClass(7, 5));
                    selectedForLightYellow.add(new CoordinatorClass(8, 3));
                    selectedForLightYellow.add(new CoordinatorClass(8, 4));
                    selectedForLightYellow.add(new CoordinatorClass(8, 5));
                }


                if (position == 54 || position == 55 || position == 56 || position == 63 || position == 64 || position == 65 || position == 72|| position == 73 || position == 74) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 6));
                    selectedForLightYellow.add(new CoordinatorClass(0, 7));
                    selectedForLightYellow.add(new CoordinatorClass(0, 8));
                    selectedForLightYellow.add(new CoordinatorClass(1, 6));
                    selectedForLightYellow.add(new CoordinatorClass(1, 7));
                    selectedForLightYellow.add(new CoordinatorClass(1, 8));
                    selectedForLightYellow.add(new CoordinatorClass(2, 6));
                    selectedForLightYellow.add(new CoordinatorClass(2, 7));
                    selectedForLightYellow.add(new CoordinatorClass(2, 8));
                }

                if (position == 57 || position == 58 || position == 59 || position == 66 || position == 67 || position == 68 || position == 75|| position == 67 || position == 77) {
                    selectedForLightYellow.add(new CoordinatorClass(3, 6));
                    selectedForLightYellow.add(new CoordinatorClass(3, 7));
                    selectedForLightYellow.add(new CoordinatorClass(3, 8));
                    selectedForLightYellow.add(new CoordinatorClass(4, 6));
                    selectedForLightYellow.add(new CoordinatorClass(4, 7));
                    selectedForLightYellow.add(new CoordinatorClass(4, 8));
                    selectedForLightYellow.add(new CoordinatorClass(5, 6));
                    selectedForLightYellow.add(new CoordinatorClass(5, 7));
                    selectedForLightYellow.add(new CoordinatorClass(5, 8));
                }

                if (position == 60 || position == 61 || position == 62 || position == 69 || position == 70 || position == 71 || position == 78|| position == 79 || position == 80) {
                    selectedForLightYellow.add(new CoordinatorClass(6, 6));
                    selectedForLightYellow.add(new CoordinatorClass(6, 7));
                    selectedForLightYellow.add(new CoordinatorClass(6, 8));
                    selectedForLightYellow.add(new CoordinatorClass(7, 6));
                    selectedForLightYellow.add(new CoordinatorClass(7, 7));
                    selectedForLightYellow.add(new CoordinatorClass(7, 8));
                    selectedForLightYellow.add(new CoordinatorClass(8, 6));
                    selectedForLightYellow.add(new CoordinatorClass(8, 7));
                    selectedForLightYellow.add(new CoordinatorClass(8, 8));
                }
                break;

            case 12:
                if (position == 0 || position == 1 || position == 2 || position == 3 || position == 12 || position == 13 || position == 14 || position == 15|| position == 24 || position == 25|| position == 26|| position == 27) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 0));
                    selectedForLightYellow.add(new CoordinatorClass(0, 1));
                    selectedForLightYellow.add(new CoordinatorClass(0, 2));
                    selectedForLightYellow.add(new CoordinatorClass(1, 0));
                    selectedForLightYellow.add(new CoordinatorClass(1, 1));
                    selectedForLightYellow.add(new CoordinatorClass(1, 2));
                    selectedForLightYellow.add(new CoordinatorClass(2, 0));
                    selectedForLightYellow.add(new CoordinatorClass(2, 1));
                    selectedForLightYellow.add(new CoordinatorClass(2, 2));
                    selectedForLightYellow.add(new CoordinatorClass(3, 0));
                    selectedForLightYellow.add(new CoordinatorClass(3, 1));
                    selectedForLightYellow.add(new CoordinatorClass(3, 2));
                }

                if (position == 4 || position == 5 || position == 6 || position == 7 || position == 16 || position == 17 || position == 18 || position == 19|| position == 28 || position == 29|| position == 30|| position == 31) {
                    selectedForLightYellow.add(new CoordinatorClass(4, 0));
                    selectedForLightYellow.add(new CoordinatorClass(4, 1));
                    selectedForLightYellow.add(new CoordinatorClass(4, 2));
                    selectedForLightYellow.add(new CoordinatorClass(5, 0));
                    selectedForLightYellow.add(new CoordinatorClass(5, 1));
                    selectedForLightYellow.add(new CoordinatorClass(5, 2));
                    selectedForLightYellow.add(new CoordinatorClass(6, 0));
                    selectedForLightYellow.add(new CoordinatorClass(6, 1));
                    selectedForLightYellow.add(new CoordinatorClass(6, 2));
                    selectedForLightYellow.add(new CoordinatorClass(7, 0));
                    selectedForLightYellow.add(new CoordinatorClass(7, 1));
                    selectedForLightYellow.add(new CoordinatorClass(7, 2));
                }
                if (position == 8 || position == 9 || position == 10 || position == 11 || position == 20 || position == 21 || position == 22 || position == 22|| position == 32 || position == 33|| position == 34|| position == 35) {
                    selectedForLightYellow.add(new CoordinatorClass(8, 0));
                    selectedForLightYellow.add(new CoordinatorClass(8, 1));
                    selectedForLightYellow.add(new CoordinatorClass(8, 2));
                    selectedForLightYellow.add(new CoordinatorClass(9, 0));
                    selectedForLightYellow.add(new CoordinatorClass(9, 1));
                    selectedForLightYellow.add(new CoordinatorClass(9, 2));
                    selectedForLightYellow.add(new CoordinatorClass(10, 0));
                    selectedForLightYellow.add(new CoordinatorClass(10, 1));
                    selectedForLightYellow.add(new CoordinatorClass(10, 2));
                    selectedForLightYellow.add(new CoordinatorClass(11, 0));
                    selectedForLightYellow.add(new CoordinatorClass(11, 1));
                    selectedForLightYellow.add(new CoordinatorClass(11, 2));
                }

                if (position == 36 || position == 37 || position == 38 || position == 39 || position == 48 || position == 49 || position == 50 || position == 51|| position == 60 || position == 61|| position == 62|| position == 63) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 3));
                    selectedForLightYellow.add(new CoordinatorClass(0, 4));
                    selectedForLightYellow.add(new CoordinatorClass(0, 5));
                    selectedForLightYellow.add(new CoordinatorClass(1, 3));
                    selectedForLightYellow.add(new CoordinatorClass(1, 4));
                    selectedForLightYellow.add(new CoordinatorClass(1, 5));
                    selectedForLightYellow.add(new CoordinatorClass(2, 3));
                    selectedForLightYellow.add(new CoordinatorClass(2, 4));
                    selectedForLightYellow.add(new CoordinatorClass(2, 5));
                    selectedForLightYellow.add(new CoordinatorClass(3, 3));
                    selectedForLightYellow.add(new CoordinatorClass(3, 4));
                    selectedForLightYellow.add(new CoordinatorClass(3, 5));
                }

                if (position == 40 || position == 41 || position == 42 || position == 43 || position == 52 || position == 53 || position == 54 || position == 55 || position == 64 || position == 65|| position == 66|| position == 67) {
                    selectedForLightYellow.add(new CoordinatorClass(4, 3));
                    selectedForLightYellow.add(new CoordinatorClass(4, 4));
                    selectedForLightYellow.add(new CoordinatorClass(4, 5));
                    selectedForLightYellow.add(new CoordinatorClass(5, 3));
                    selectedForLightYellow.add(new CoordinatorClass(5, 4));
                    selectedForLightYellow.add(new CoordinatorClass(5, 5));
                    selectedForLightYellow.add(new CoordinatorClass(6, 3));
                    selectedForLightYellow.add(new CoordinatorClass(6, 4));
                    selectedForLightYellow.add(new CoordinatorClass(6, 5));
                    selectedForLightYellow.add(new CoordinatorClass(7, 3));
                    selectedForLightYellow.add(new CoordinatorClass(7, 4));
                    selectedForLightYellow.add(new CoordinatorClass(7, 5));
                }
                if (position == 44 || position == 45 || position == 46 || position == 47 || position == 56 || position == 57 || position == 58 || position == 59|| position == 60|| position == 61|| position == 62|| position == 63) {
                    selectedForLightYellow.add(new CoordinatorClass(8, 3));
                    selectedForLightYellow.add(new CoordinatorClass(8, 4));
                    selectedForLightYellow.add(new CoordinatorClass(8, 5));
                    selectedForLightYellow.add(new CoordinatorClass(9, 3));
                    selectedForLightYellow.add(new CoordinatorClass(9, 4));
                    selectedForLightYellow.add(new CoordinatorClass(9, 5));
                    selectedForLightYellow.add(new CoordinatorClass(10, 3));
                    selectedForLightYellow.add(new CoordinatorClass(10, 4));
                    selectedForLightYellow.add(new CoordinatorClass(10, 5));
                    selectedForLightYellow.add(new CoordinatorClass(11, 3));
                    selectedForLightYellow.add(new CoordinatorClass(11, 4));
                    selectedForLightYellow.add(new CoordinatorClass(11, 5));
                }

                if (position == 72 || position == 73 || position == 74 || position == 75 || position == 84 || position == 85 || position == 86 || position == 87|| position == 96 || position == 97|| position == 98|| position == 99) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 6));
                    selectedForLightYellow.add(new CoordinatorClass(0, 7));
                    selectedForLightYellow.add(new CoordinatorClass(0, 8));
                    selectedForLightYellow.add(new CoordinatorClass(1, 6));
                    selectedForLightYellow.add(new CoordinatorClass(1, 7));
                    selectedForLightYellow.add(new CoordinatorClass(1, 8));
                    selectedForLightYellow.add(new CoordinatorClass(2, 6));
                    selectedForLightYellow.add(new CoordinatorClass(2, 7));
                    selectedForLightYellow.add(new CoordinatorClass(2, 8));
                    selectedForLightYellow.add(new CoordinatorClass(3, 6));
                    selectedForLightYellow.add(new CoordinatorClass(3, 7));
                    selectedForLightYellow.add(new CoordinatorClass(3, 8));
                }

                if (position == 76 || position == 77 || position == 78 || position == 79 || position == 88 || position == 89 || position == 90 || position == 91 || position == 100 || position == 101|| position == 102|| position == 103) {
                    selectedForLightYellow.add(new CoordinatorClass(4, 6));
                    selectedForLightYellow.add(new CoordinatorClass(4, 7));
                    selectedForLightYellow.add(new CoordinatorClass(4, 8));
                    selectedForLightYellow.add(new CoordinatorClass(5, 6));
                    selectedForLightYellow.add(new CoordinatorClass(5, 7));
                    selectedForLightYellow.add(new CoordinatorClass(5, 8));
                    selectedForLightYellow.add(new CoordinatorClass(6, 6));
                    selectedForLightYellow.add(new CoordinatorClass(6, 7));
                    selectedForLightYellow.add(new CoordinatorClass(6, 8));
                    selectedForLightYellow.add(new CoordinatorClass(7, 6));
                    selectedForLightYellow.add(new CoordinatorClass(7, 7));
                    selectedForLightYellow.add(new CoordinatorClass(7, 8));
                }
                if (position == 80 || position == 81 || position == 82 || position == 83 || position == 92 || position == 93 || position == 94 || position == 95|| position == 104|| position == 105|| position == 106|| position == 107) {
                    selectedForLightYellow.add(new CoordinatorClass(8, 6));
                    selectedForLightYellow.add(new CoordinatorClass(8, 7));
                    selectedForLightYellow.add(new CoordinatorClass(8, 8));
                    selectedForLightYellow.add(new CoordinatorClass(9, 6));
                    selectedForLightYellow.add(new CoordinatorClass(9, 7));
                    selectedForLightYellow.add(new CoordinatorClass(9, 8));
                    selectedForLightYellow.add(new CoordinatorClass(10, 6));
                    selectedForLightYellow.add(new CoordinatorClass(10, 7));
                    selectedForLightYellow.add(new CoordinatorClass(10, 8));
                    selectedForLightYellow.add(new CoordinatorClass(11, 6));
                    selectedForLightYellow.add(new CoordinatorClass(11, 7));
                    selectedForLightYellow.add(new CoordinatorClass(11, 8));
                }

                if (position == 108 || position == 109 || position == 110 || position == 111 || position == 120 || position == 121 || position == 122 || position == 123|| position == 132 || position == 133|| position == 134|| position == 135) {
                    selectedForLightYellow.add(new CoordinatorClass(0, 9));
                    selectedForLightYellow.add(new CoordinatorClass(0, 10));
                    selectedForLightYellow.add(new CoordinatorClass(0, 11));
                    selectedForLightYellow.add(new CoordinatorClass(1, 9));
                    selectedForLightYellow.add(new CoordinatorClass(1, 10));
                    selectedForLightYellow.add(new CoordinatorClass(1, 11));
                    selectedForLightYellow.add(new CoordinatorClass(2, 9));
                    selectedForLightYellow.add(new CoordinatorClass(2, 10));
                    selectedForLightYellow.add(new CoordinatorClass(2, 11));
                    selectedForLightYellow.add(new CoordinatorClass(3, 9));
                    selectedForLightYellow.add(new CoordinatorClass(3, 10));
                    selectedForLightYellow.add(new CoordinatorClass(3, 11));
                }

                if (position == 112 || position == 113 || position == 114 || position == 115 || position == 124 || position == 125 || position == 126 || position == 127 || position == 136 || position == 137|| position == 138|| position == 139) {
                    selectedForLightYellow.add(new CoordinatorClass(4, 9));
                    selectedForLightYellow.add(new CoordinatorClass(4, 10));
                    selectedForLightYellow.add(new CoordinatorClass(4, 11));
                    selectedForLightYellow.add(new CoordinatorClass(5, 9));
                    selectedForLightYellow.add(new CoordinatorClass(5, 10));
                    selectedForLightYellow.add(new CoordinatorClass(5, 11));
                    selectedForLightYellow.add(new CoordinatorClass(6, 9));
                    selectedForLightYellow.add(new CoordinatorClass(6, 10));
                    selectedForLightYellow.add(new CoordinatorClass(6, 11));
                    selectedForLightYellow.add(new CoordinatorClass(7, 9));
                    selectedForLightYellow.add(new CoordinatorClass(7, 10));
                    selectedForLightYellow.add(new CoordinatorClass(7, 11));
                }
                if (position == 116 || position == 117 || position == 118 || position == 119 || position == 128 || position == 129 || position == 130 || position == 131|| position == 140|| position == 141|| position == 142|| position == 143) {
                    selectedForLightYellow.add(new CoordinatorClass(8, 9));
                    selectedForLightYellow.add(new CoordinatorClass(8, 10));
                    selectedForLightYellow.add(new CoordinatorClass(8, 11));
                    selectedForLightYellow.add(new CoordinatorClass(9, 9));
                    selectedForLightYellow.add(new CoordinatorClass(9, 10));
                    selectedForLightYellow.add(new CoordinatorClass(9, 11));
                    selectedForLightYellow.add(new CoordinatorClass(10, 9));
                    selectedForLightYellow.add(new CoordinatorClass(10, 10));
                    selectedForLightYellow.add(new CoordinatorClass(10, 11));
                    selectedForLightYellow.add(new CoordinatorClass(11, 9));
                    selectedForLightYellow.add(new CoordinatorClass(11, 10));
                    selectedForLightYellow.add(new CoordinatorClass(11, 11));
                }

                break;
        }

        for (int j = 0; j < matrixSize * matrixSize; j++)
            getGrid().getItem(j).setColors(selectedForLightYellow, selectedForCyan);
    }

    public void setNumber(int number) {
        if (selectedPosX != -1 && selectedPosY != -1) {
            grid.setItem(selectedPosX, selectedPosY, number);
        }
        checkGame(number);
    }


    public boolean checkSudoku(int[][] Sudoku) {
        return (checkHorizontal(Sudoku) && checkVertical(Sudoku) && checkRegions(Sudoku));
    }

    //similar checker but for during game
    public boolean checkSudokuOnGame(int[][] Sudoku) {
        return (checkHorizontalOnGame(Sudoku) && checkVerticalOnGame(Sudoku) && checkRegionsOnGame(Sudoku));
    }

    private boolean checkHorizontal(int[][] Sudoku) {
        if (MainActivity.getfourXfour() == true) {
            for (int y = 0; y < 4; y++) {
                for (int xPos = 0; xPos < 4; xPos++) {
                    if (Sudoku[xPos][y] == 0) {
                        return false;
                    }
                    for (int x = xPos + 1; x < 4; x++) {
                        if (Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int y = 0; y < 6; y++) {
                for (int xPos = 0; xPos < 6; xPos++) {
                    if (Sudoku[xPos][y] == 0) {
                        return false;
                    }
                    for (int x = xPos + 1; x < 6; x++) {
                        if (Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int y = 0; y < 12; y++) {
                for (int xPos = 0; xPos < 12; xPos++) {
                    if (Sudoku[xPos][y] == 0) {
                        return false;
                    }
                    for (int x = xPos + 1; x < 12; x++) {
                        if (Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else {
            for (int y = 0; y < 9; y++) {
                for (int xPos = 0; xPos < 9; xPos++) {
                    if (Sudoku[xPos][y] == 0) {
                        return false;
                    }
                    for (int x = xPos + 1; x < 9; x++) {
                        if (Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }


    }

    private boolean checkHorizontalOnGame(int[][] Sudoku) {
        if (MainActivity.getfourXfour() == true) {
            for (int y = 0; y < 4; y++) {
                for (int xPos = 0; xPos < 4; xPos++) {
                    for (int x = xPos + 1; x < 4; x++) {
                        if ((Sudoku[xPos][y] == Sudoku[x][y])) {
                            if ((Sudoku[xPos][y] != 0) && (Sudoku[x][y] != 0))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int y = 0; y < 6; y++) {
                for (int xPos = 0; xPos < 6; xPos++) {
                    for (int x = xPos + 1; x < 6; x++) {
                        if ((Sudoku[xPos][y] == Sudoku[x][y])) {
                            if ((Sudoku[xPos][y] != 0) && (Sudoku[x][y] != 0))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int y = 0; y < 12; y++) {
                for (int xPos = 0; xPos < 12; xPos++) {
                    for (int x = xPos + 1; x < 12; x++) {
                        if ((Sudoku[xPos][y] == Sudoku[x][y])) {
                            if ((Sudoku[xPos][y] != 0) && (Sudoku[x][y] != 0))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else {
            for (int y = 0; y < 9; y++) {
                for (int xPos = 0; xPos < 9; xPos++) {
                    for (int x = xPos + 1; x < 9; x++) {
                        if ((Sudoku[xPos][y] == Sudoku[x][y])) {
                            if ((Sudoku[xPos][y] != 0) && (Sudoku[x][y] != 0))
                                return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    private boolean checkVertical(int[][] Sudoku) {

        if (MainActivity.getfourXfour() == true) {
            for (int x = 0; x < 4; x++) {
                for (int yPos = 0; yPos < 4; yPos++) {
                    if (Sudoku[x][yPos] == 0) {
                        return false;
                    }
                    for (int y = yPos + 1; y < 4; y++) {
                        if (Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int x = 0; x < 6; x++) {
                for (int yPos = 0; yPos < 6; yPos++) {
                    if (Sudoku[x][yPos] == 0) {
                        return false;
                    }
                    for (int y = yPos + 1; y < 6; y++) {
                        if (Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int x = 0; x < 12; x++) {
                for (int yPos = 0; yPos < 12; yPos++) {
                    if (Sudoku[x][yPos] == 0) {
                        return false;
                    }
                    for (int y = yPos + 1; y < 12; y++) {
                        if (Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } else {
            for (int x = 0; x < 9; x++) {
                for (int yPos = 0; yPos < 9; yPos++) {
                    if (Sudoku[x][yPos] == 0) {
                        return false;
                    }
                    for (int y = yPos + 1; y < 9; y++) {
                        if (Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    private boolean checkVerticalOnGame(int[][] Sudoku) {


        if (MainActivity.getfourXfour() == true) {
            for (int x = 0; x < 4; x++) {
                for (int yPos = 0; yPos < 4; yPos++) {
                    for (int y = yPos + 1; y < 4; y++) {
                        if ((Sudoku[x][yPos] == Sudoku[x][y])) {
                            if (((Sudoku[x][y] != 0) && (Sudoku[x][yPos] != 0)))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int x = 0; x < 6; x++) {
                for (int yPos = 0; yPos < 6; yPos++) {
                    for (int y = yPos + 1; y < 6; y++) {
                        if ((Sudoku[x][yPos] == Sudoku[x][y])) {
                            if (((Sudoku[x][y] != 0) && (Sudoku[x][yPos] != 0)))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int x = 0; x < 12; x++) {
                for (int yPos = 0; yPos < 12; yPos++) {
                    for (int y = yPos + 1; y < 12; y++) {
                        if ((Sudoku[x][yPos] == Sudoku[x][y])) {
                            if (((Sudoku[x][y] != 0) && (Sudoku[x][yPos] != 0)))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else {
            for (int x = 0; x < 9; x++) {
                for (int yPos = 0; yPos < 9; yPos++) {
                    for (int y = yPos + 1; y < 9; y++) {
                        if ((Sudoku[x][yPos] == Sudoku[x][y])) {
                            if (((Sudoku[x][y] != 0) && (Sudoku[x][yPos] != 0)))
                                return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    private boolean checkRegions(int[][] Sudoku) {

        if (MainActivity.getfourXfour() == true) {
            for (int xRegion = 0; xRegion < 2; xRegion++) {
                for (int yRegion = 0; yRegion < 2; yRegion++) {
                    if (!checkRegion(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int xRegion = 0; xRegion < 2; xRegion++) {
                for (int yRegion = 0; yRegion < 3; yRegion++) {
                    if (!checkRegion(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int xRegion = 0; xRegion < 3; xRegion++) {
                for (int yRegion = 0; yRegion < 4; yRegion++) {
                    if (!checkRegion(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            for (int xRegion = 0; xRegion < 3; xRegion++) {
                for (int yRegion = 0; yRegion < 3; yRegion++) {
                    if (!checkRegion(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private boolean checkRegionsOnGame(int[][] Sudoku) {


        if (MainActivity.getfourXfour() == true) {
            for (int xRegion = 0; xRegion < 2; xRegion++) {
                for (int yRegion = 0; yRegion < 2; yRegion++) {
                    if (!checkRegionOnGame(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int xRegion = 0; xRegion < 2; xRegion++) {
                for (int yRegion = 0; yRegion < 3; yRegion++) {
                    if (!checkRegionOnGame(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int xRegion = 0; xRegion < 3; xRegion++) {
                for (int yRegion = 0; yRegion < 4; yRegion++) {
                    if (!checkRegionOnGame(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            for (int xRegion = 0; xRegion < 3; xRegion++) {
                for (int yRegion = 0; yRegion < 3; yRegion++) {
                    if (!checkRegionOnGame(Sudoku, xRegion, yRegion)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private boolean checkRegion(int[][] Sudoku, int xRegion, int yRegion) {


        if (MainActivity.getfourXfour() == true) {
            for (int xPos = xRegion * 2; xPos < xRegion * 2 + 2; xPos++) {
                for (int yPos = yRegion * 2; yPos < yRegion * 2 + 2; yPos++) {
                    for (int x = xPos; x < xRegion * 2 + 2; x++) {
                        for (int y = yPos; y < yRegion * 2 + 2; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y]) || Sudoku[x][y] == 0) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int xPos = xRegion * 3; xPos < xRegion * 3 + 3; xPos++) {
                for (int yPos = yRegion * 2; yPos < yRegion * 2 + 2; yPos++) {
                    for (int x = xPos; x < xRegion * 3 + 3; x++) {
                        for (int y = yPos; y < yRegion * 2 + 2; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y]) || Sudoku[x][y] == 0) {  //(x != xPos || y != yPos) &&
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int xPos = xRegion * 4; xPos < xRegion * 4 + 4; xPos++) {
                for (int yPos = yRegion * 3; yPos < yRegion * 3 + 3; yPos++) {
                    for (int x = xPos; x < xRegion * 4 + 4; x++) {
                        for (int y = yPos; y < yRegion * 3 + 3; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y]) || Sudoku[x][y] == 0) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        } else {
            for (int xPos = xRegion * 3; xPos < xRegion * 3 + 3; xPos++) {
                for (int yPos = yRegion * 3; yPos < yRegion * 3 + 3; yPos++) {
                    for (int x = xPos; x < xRegion * 3 + 3; x++) {
                        for (int y = yPos; y < yRegion * 3 + 3; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y]) || Sudoku[x][y] == 0) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean checkRegionOnGame(int[][] Sudoku, int xRegion, int yRegion) {

        if (MainActivity.getfourXfour() == true) {
            for (int xPos = xRegion * 2; xPos < xRegion * 2 + 2; xPos++) {
                for (int yPos = yRegion * 2; yPos < yRegion * 2 + 2; yPos++) {
                    for (int x = xPos; x < xRegion * 2 + 2; x++) {
                        for (int y = yPos; y < yRegion * 2 + 2; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y])) {
                                if (((Sudoku[xPos][yPos] != 0) && (Sudoku[x][y] != 0)))
                                    return false;
                            }
                        }
                    }
                }
            }

            return true;
        } else if (MainActivity.getsixXsix() == true) {
            for (int xPos = xRegion * 3; xPos < xRegion * 3 + 3; xPos++) {
                for (int yPos = yRegion * 2; yPos < yRegion * 2 + 2; yPos++) {
                    for (int x = xPos; x < xRegion * 3 + 3; x++) {
                        for (int y = yPos; y < yRegion * 2 + 2; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y])) {
                                if (((Sudoku[xPos][yPos] != 0) && (Sudoku[x][y] != 0)))
                                    return false;
                            }
                        }
                    }
                }
            }

            return true;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            for (int xPos = xRegion * 4; xPos < xRegion * 4 + 4; xPos++) {
                for (int yPos = yRegion * 3; yPos < yRegion * 3 + 3; yPos++) {
                    for (int x = xPos; x < xRegion * 4 + 4; x++) {
                        for (int y = yPos; y < yRegion * 3 + 3; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y])) {
                                if (((Sudoku[xPos][yPos] != 0) && (Sudoku[x][y] != 0)))
                                    return false;
                            }
                        }
                    }
                }
            }

            return true;
        } else {
            for (int xPos = xRegion * 3; xPos < xRegion * 3 + 3; xPos++) {
                for (int yPos = yRegion * 3; yPos < yRegion * 3 + 3; yPos++) {
                    for (int x = xPos; x < xRegion * 3 + 3; x++) {
                        for (int y = yPos; y < yRegion * 3 + 3; y++) {
                            if (((x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y])) {
                                if (((Sudoku[xPos][yPos] != 0) && (Sudoku[x][y] != 0)))
                                    return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    //if return ture, there is conflict.
    public boolean checkConflict(int[][] Sudoku, int currentPos, final int number) {

        if (MainActivity.getfourXfour() == true) {
            int xPos = currentPos % 4;
            int yPos = currentPos / 4;

            if (checkHorizontalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkVerticalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkRegionConflict(Sudoku, xPos, yPos, number))
                return true;

            return false;
        } else if (MainActivity.getsixXsix() == true) {
            int xPos = currentPos % 6;
            int yPos = currentPos / 6;

            if (checkHorizontalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkVerticalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkRegionConflict(Sudoku, xPos, yPos, number))
                return true;

            return false;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            int xPos = currentPos % 12;
            int yPos = currentPos / 12;

            if (checkHorizontalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkVerticalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkRegionConflict(Sudoku, xPos, yPos, number))
                return true;

            return false;
        } else {
            int xPos = currentPos % 9;
            int yPos = currentPos / 9;

            if (checkHorizontalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkVerticalConflict(Sudoku, xPos, yPos, number))
                return true;
            else if (checkRegionConflict(Sudoku, xPos, yPos, number))
                return true;

            return false;
        }
    }

    private boolean checkHorizontalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int x = xPos - 1; x >= 0; x--) {
            if (number == Sudoku[x][yPos])
                return true;
        }
        return false;
    }

    private boolean checkVerticalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int y = yPos - 1; y >= 0; y--) {
            if (number == Sudoku[xPos][y])
                return true;
        }
        return false;

    }

    private boolean checkRegionConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        if (MainActivity.getfourXfour() == true) {
            int xRegion = xPos / 2;
            int yRegion = yPos / 2;

            for (int x = xRegion * 2; x < xRegion * 2 + 2; x++) {
                for (int y = yRegion * 2; y < yRegion * 2 + 2; y++) {
                    if ((x != xPos || y != yPos) && number == Sudoku[x][y])
                        return true;
                }
            }
            return false;
        } else if (MainActivity.getsixXsix() == true) {
            int xRegion = xPos / 3;
            int yRegion = yPos / 2;

            for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
                for (int y = yRegion * 2; y < yRegion * 2 + 2; y++) {
                    if ((x != xPos || y != yPos) && number == Sudoku[x][y])
                        return true;
                }
            }
            return false;
        } else if (MainActivity.gettwelveXtwelve() == true) {
            int xRegion = xPos / 4;
            int yRegion = yPos / 3;

            for (int x = xRegion * 4; x < xRegion * 4 + 4; x++) {
                for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                    if ((x != xPos || y != yPos) && number == Sudoku[x][y])
                        return true;
                }
            }
            return false;
        } else {
            int xRegion = xPos / 3;
            int yRegion = yPos / 3;

            for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
                for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                    if ((x != xPos || y != yPos) && number == Sudoku[x][y])
                        return true;
                }
            }
            return false;
        }
    }


    public static void getWordsToast(String[] WT){
        WordsToast = WT;
    }
    //check the word player inputed is correct or not.
    public void checkGame(int number) {
        int[][] sudukoGrid = new int[9][9];
        boolean IsFinished = true;


        if (MainActivity.getfourXfour() == true) {
            sudukoGrid = new int[4][4];
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    sudukoGrid[x][y] = grid.getItem(x, y).getValue();
                    if (sudukoGrid[x][y] == 0)
                        IsFinished = false;
                }
            }
        } else if (MainActivity.getsixXsix() == true) {
            sudukoGrid = new int[6][6];
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    sudukoGrid[x][y] = grid.getItem(x, y).getValue();
                    if (sudukoGrid[x][y] == 0)
                        IsFinished = false;
                }
            }
        } else if (MainActivity.gettwelveXtwelve() == true) {
            sudukoGrid = new int[12][12];
            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 12; y++) {
                    sudukoGrid[x][y] = grid.getItem(x, y).getValue();
                    if (sudukoGrid[x][y] == 0)
                        IsFinished = false;
                }
            }
        } else {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    sudukoGrid[x][y] = grid.getItem(x, y).getValue();
                    if (sudukoGrid[x][y] == 0)
                        IsFinished = false;
                }
            }
        }

        if (checkSudoku(sudukoGrid) && number != 0) {
            MainActivity.setAddPointsView(number);
            Toast.makeText(grid.context, "(Correct) " + WordsToast[number] + ".  Congratulation! You solved the puzzle!", Toast.LENGTH_LONG).show();
        } else if (number != 0){
            if (IsFinished) { //If every cells are filled, but is is not answer, show fail_message
                String mistake = "(Incorrect) " +WordsToast[number] +".  This is the last piece. Try again!";
                MainActivity.setDeductPointsView(number);
                final Toast toast = Toast.makeText(grid.context, mistake, Toast.LENGTH_SHORT);
                toast.show();

            } else {    //shows hint during the game
                if (!checkSudokuOnGame(sudukoGrid)) {

                    String mistake = "(Incorrect) " + WordsToast[number];
                    MainActivity.setDeductPointsView(number);
                    final Toast toast = Toast.makeText(grid.context, mistake, Toast.LENGTH_SHORT);
                    toast.show();


                } else if (checkSudokuOnGame(sudukoGrid)) {

                    String correct = "(Correct Possibly) " + WordsToast[number];
                    MainActivity.setAddPointsView(number);
                    final Toast toast = Toast.makeText(grid.context, correct, Toast.LENGTH_SHORT);
                    toast.show();

                }

            }
        }
    }
    public class CoordinatorClass {
        private  int x;
        private  int y;

        CoordinatorClass(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public  int getX() {
            return x;

        }

        public  int getY() {
            return y;
        }

    }



}
