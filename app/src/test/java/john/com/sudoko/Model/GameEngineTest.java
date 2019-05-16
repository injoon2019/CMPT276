package john.com.sudoko.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameEngineTest {
    int [][] Sudoku = new int [9][9];

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getInstance() {
        SudokuGenerator instance;
        instance = SudokuGenerator.getInstance();
        assertEquals(instance,SudokuGenerator.getInstance());

    }


    @Test
    public void getGrid() {
        assertEquals(null,GameEngine.getInstance().getGrid());
    }

    @Test
    public void setSelectedPositon() {
        GameEngine.getInstance().setSelectedPositon(1,1);
        assertEquals(1,GameEngine.getInstance().selectedPosX);
        assertEquals(1,GameEngine.getInstance().selectedPosY);

    }


    @Test
    public void checkSudoku() {
        assertEquals(false,GameEngine.getInstance().checkSudoku(Sudoku));
    }

    @Test
    public void checkSudokuOnGame() {
        assertEquals(true,GameEngine.getInstance().checkSudokuOnGame(Sudoku));
    }

    @Test
    public void checkConflict() {
        assertEquals(false,GameEngine.getInstance().checkConflict(Sudoku,1,1));
    }


}