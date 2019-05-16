package john.com.sudoko.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuGeneratorTest {


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
    public void generateGrid() {
        int i=0;
        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                if(Sudoku[x][y] != 0){
                    i++;
                }
            }
        }
        assertEquals(81,i);
    }

    @Test
    public void removeElements() { ;
        int i=0;
        int[][]Sudoku = SudokuGenerator.getInstance().generateGrid();
        SudokuGenerator.getInstance().removeElements(Sudoku,2);
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                if(Sudoku[x][y] == 0){
                    i++;
                }
            }
        }
        assertEquals(2,i);

    }
}
