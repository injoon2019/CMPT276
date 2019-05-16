package john.com.sudoko.Model;
import java.util.ArrayList;
import java.util.Random;
import java.lang.*;

import john.com.sudoko.Controller.MainActivity;
import john.com.sudoko.View.SudokuCell;


public class SudokuGenerator {

    private static SudokuGenerator instance;

    private  ArrayList<ArrayList<Integer>> available_array = new ArrayList<ArrayList<Integer>>();
    public static boolean[][] selectedAsGrayWords;
    private SudokuGenerator() {}

    private Random rand = new Random(System.currentTimeMillis());

    public static SudokuGenerator getInstance() {
        if (instance == null) {
            instance = new SudokuGenerator();
        }
        return instance;
    }


    public int[][] generateGrid(){

        int currentPos = 0;

        if( MainActivity.getfourXfour()==true ){
            int[][] Sudoku = new int[4][4];

            clearGrid(Sudoku);

            while(currentPos < 16){
                if(currentPos < 0) currentPos = 0;
                if(available_array.get(currentPos).size() != 0){

                    int i= rand.nextInt(available_array.get(currentPos).size());
                    int number = available_array.get(currentPos).get(i);

                    if(!  GameEngine.getInstance().checkConflict(Sudoku,currentPos,number)){
                        int xPos = currentPos%4;
                        int yPos = currentPos/4;
                        Sudoku[xPos][yPos] = number;
                        available_array.get(currentPos).remove(i);
                        currentPos++;
                    }
                    else{
                        available_array.get(currentPos).remove(i);
                    }
                }
                else{
                    for(int i =1; i<=4; i++) {
                        available_array.get(currentPos).add(i);
                    }
                    currentPos--;
                }
            }
            return Sudoku;
    }
        else if (MainActivity.getsixXsix()==true ){
            int[][] Sudoku = new int[6][6];

            clearGrid(Sudoku);

            while(currentPos < 36){
                if(currentPos < 0) currentPos = 0;

                if(available_array.get(currentPos).size() != 0){

                    int i= rand.nextInt(available_array.get(currentPos).size());
                    int number = available_array.get(currentPos).get(i);

                    if(!  GameEngine.getInstance().checkConflict(Sudoku,currentPos,number)){
                        int xPos = currentPos%6;
                        int yPos = currentPos/6;
                        Sudoku[xPos][yPos] = number;
                        available_array.get(currentPos).remove(i);
                        currentPos++;
                    }
                    else{
                        available_array.get(currentPos).remove(i);
                    }
                }
                else{
                    for(int i =1; i<=6; i++) {
                        available_array.get(currentPos).add(i);
                    }
                    currentPos--;
                }
            }
            return Sudoku;
        }
        else if(MainActivity.gettwelveXtwelve()==true){
            int[][] Sudoku = new int[12][12];

            clearGrid(Sudoku);

            while(currentPos < 144){
                if(currentPos < 0) currentPos = 0;

                if(available_array.get(currentPos).size() != 0){

                    int i= rand.nextInt(available_array.get(currentPos).size());
                    int number = available_array.get(currentPos).get(i);

                    if(!  GameEngine.getInstance().checkConflict(Sudoku,currentPos,number)){
                        int xPos = currentPos%12;
                        int yPos = currentPos/12;
                        Sudoku[xPos][yPos] = number;
                        available_array.get(currentPos).remove(i);
                        currentPos++;
                    }
                    else{
                        available_array.get(currentPos).remove(i);
                    }
                }
                else{
                    for(int i =1; i<=12; i++) {
                        available_array.get(currentPos).add(i);
                    }
                    currentPos--;
                }
            }
            return Sudoku;
        }


        else{   //case 9X9
            int[][] Sudoku = new int[9][9];

            clearGrid(Sudoku);

            while(currentPos < 81){
                if(currentPos < 0) currentPos = 0;

                if(available_array.get(currentPos).size() != 0){

                    int i= rand.nextInt(available_array.get(currentPos).size());
                    int number = available_array.get(currentPos).get(i);

                    if(!  GameEngine.getInstance().checkConflict(Sudoku,currentPos,number)){
                        int xPos = currentPos%9;
                        int yPos = currentPos/9;
                        Sudoku[xPos][yPos] = number;
                        available_array.get(currentPos).remove(i);
                        currentPos++;
                    }
                    else{
                        available_array.get(currentPos).remove(i);
                    }
                }
                else{
                    for(int i =1; i<=9; i++) {
                        available_array.get(currentPos).add(i);
                    }
                    currentPos--;
                }
            }
            return Sudoku;
        }
    }
    //the number we need to fill.
    public int[][] removeElements(int[][] Sudoku,int cell_replace_num) {
        int i = 0;
        int x = 0;
        int y = 0;
        while (i < cell_replace_num) {
            if(MainActivity.getfourXfour()) {
                x = rand.nextInt(4);
                y = rand.nextInt(4);
                if (Sudoku[x][y] != 0) {
                    Sudoku[x][y] = 0;
                    SudokuCell.setTFBool_four(y,x);
                    selectedAsGrayWords[y][x] = true;
                    i++;
                }
            }

            if(MainActivity.getsixXsix()) {
                x = rand.nextInt(6);
                y = rand.nextInt(6);
                if (Sudoku[x][y] != 0) {
                    Sudoku[x][y] = 0;
                    SudokuCell.setTFBool_six(y,x);
                    selectedAsGrayWords[y][x] = true;
                    i++;
                }
            }

            if(MainActivity.getnineXnine()) {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
                if (Sudoku[x][y] != 0) {
                    Sudoku[x][y] = 0;
                    SudokuCell.setTFBool_nine(y,x);
                    selectedAsGrayWords[y][x] = true;
                    i++;
                }
            }

            if(MainActivity.gettwelveXtwelve()) {
                x = rand.nextInt(12);
                y = rand.nextInt(12);
                if (Sudoku[x][y] != 0) {
                    Sudoku[x][y] = 0;
                    SudokuCell.setTFBool_twelve(y,x);
                    selectedAsGrayWords[y][x] = true;
                    i++;
                }
            }

        }
        return Sudoku;
    }

    private void clearGrid(int[][] Sudoku){

        if( MainActivity.getfourXfour()==true ){
            available_array.clear();
            for(int y=0;y<4;y++){
                for(int x=0;x<4;x++){
                    Sudoku[x][y] = -1;
                }
            }
            for(int x=0; x<16; x++){
                available_array.add(new ArrayList<Integer>());
                for(int i=1;i<=4;i++){
                    available_array.get(x).add(i);
                }
            }
        }
        else if( MainActivity.getsixXsix()==true ){
            available_array.clear();
            for(int y=0;y<6;y++){
                for(int x=0;x<6;x++){
                    Sudoku[x][y] = -1;
                }
            }
            for(int x=0; x<36; x++){
                available_array.add(new ArrayList<Integer>());
                for(int i=1;i<=6;i++){
                    available_array.get(x).add(i);
                }
            }
        }
        else if( MainActivity.gettwelveXtwelve()==true ){
            available_array.clear();
            for(int y=0;y<12;y++){
                for(int x=0;x<12;x++){
                    Sudoku[x][y] = -1;
                }
            }
            for(int x=0; x<144; x++){
                available_array.add(new ArrayList<Integer>());
                for(int i=1;i<=12;i++){
                    available_array.get(x).add(i);
                }
            }
        }
        else{
            available_array.clear();
            for(int y=0;y<9;y++){
                for(int x=0;x<9;x++){
                    Sudoku[x][y] = -1;
                }
            }
            for(int x=0; x<81; x++){
                available_array.add(new ArrayList<Integer>());
                for(int i=1;i<=9;i++){
                    available_array.get(x).add(i);
                }
            }
        }
    }


}
