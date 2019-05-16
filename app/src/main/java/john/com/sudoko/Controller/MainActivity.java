package john.com.sudoko.Controller;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

import john.com.sudoko.Model.GameEngine;
import john.com.sudoko.Model.SudokuGenerator;
import john.com.sudoko.R;
import john.com.sudoko.View.RecyclerAdapter;
import john.com.sudoko.View.SudokuCell;
import john.com.sudoko.View.SudokuGridView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private SudokuGridView sudokuGridView;
    private RecyclerView buttonsRecyclerView;
    private RecyclerAdapter recyclerAdapter;
    private SudokuGridView.SudokuGridViewAdapter sudokuGridViewAdapter;
    private static String[] EWords;
    private static String[] KWords;
    private Chronometer chronometer = null;
    private static int cell_replace_num = 2;
    private static int max_selection_size = 0;

    private static boolean English_mode = true;
    private static boolean Listening_mode = false;

    private static boolean fourXfour = false;
    private static boolean sixXsix = false;
    private static boolean nineXnine = true;
    private static boolean twelveXtwelve = false;

    private static String[] NumAdded = {"NULL", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    private static int[] pointsView = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getfourXfour()) {
            max_selection_size = 4;
            SudokuGenerator.selectedAsGrayWords=new boolean[4][4];
            setContentView(R.layout.activity_main4);
            sudokuGridView = findViewById(R.id.sudokuGridView4);
        }

        if (getsixXsix()) {
            max_selection_size = 6;
            SudokuGenerator.selectedAsGrayWords=new boolean[6][6];
            setContentView(R.layout.activity_main6);
            sudokuGridView = findViewById(R.id.sudokuGridView6);
        }

        if (getnineXnine()) {
            max_selection_size = 9;
            SudokuGenerator.selectedAsGrayWords=new boolean[9][9];
            setContentView(R.layout.activity_main);
            sudokuGridView = findViewById(R.id.sudokuGridView);
        }

        if (gettwelveXtwelve()) {
            max_selection_size = 12;
            SudokuGenerator.selectedAsGrayWords=new boolean[12][12];
            setContentView(R.layout.activity_main12);
            sudokuGridView = findViewById(R.id.sudokuGridView12);
        }

        chronometer = (Chronometer) super.findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        buttonsRecyclerView = findViewById(R.id.buttonsgridview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (getIntent().hasExtra("ewords")) {
            EWords = getIntent().getStringArrayExtra("ewords");
        } else {
            EWords = getResources().getStringArray(R.array.EWords);
        }
        if (getIntent().hasExtra("kwords")) {
            KWords = getIntent().getStringArrayExtra("kwords");
        } else {
            KWords = getResources().getStringArray(R.array.KWords);

        }
        for(int iCount = 0; iCount<EWords.length; iCount++)
        {
            int points = PointsActivity.GetWordPointsList(EWords[iCount]);
            if(points == 777){
                pointsView[iCount] = 100;
            } else {
                pointsView[iCount] = points;
            }
        }

        int max_length_Kwords = 0;
        int max_length_Ewords = 0;
        for (int i = 0; i < KWords.length; i++) {
            if (max_length_Kwords < KWords[i].length()) {
                max_length_Kwords = KWords[i].length();
            }
            if (max_length_Ewords < EWords[i].length()) {
                max_length_Ewords = EWords[i].length();
            }
        }

        if (English_mode == false && Listening_mode == false) {    //Normal Korean mode
            GameEngine.getInstance().createGrid(this, cell_replace_num, KWords);
            GameEngine.getWordsToast(KWords);
            if (max_length_Ewords < 7) {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            } else {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
            recyclerAdapter = new RecyclerAdapter(this, EWords);
            buttonsRecyclerView.setAdapter(recyclerAdapter);
        }
        if (English_mode == true && Listening_mode == true) {      //English Listening mode
            GameEngine.getInstance().createGrid(this, cell_replace_num, NumAdded);
            GameEngine.getWordsToast(EWords);

            if (max_length_Kwords < 7) {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            } else {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
            recyclerAdapter = new RecyclerAdapter(this, KWords);
            buttonsRecyclerView.setAdapter(recyclerAdapter);
        }
        if (English_mode == false && Listening_mode == true) {       //Korean Listening mode
            GameEngine.getInstance().createGrid(this, cell_replace_num, NumAdded);
            GameEngine.getWordsToast(KWords);

            if (max_length_Ewords < 7) {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            } else {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
            recyclerAdapter = new RecyclerAdapter(this, EWords);
            buttonsRecyclerView.setAdapter(recyclerAdapter);
        }
        if (English_mode == true && Listening_mode == false) {  //first opening page
            GameEngine.getInstance().createGrid(this, cell_replace_num, EWords);
            GameEngine.getWordsToast(EWords);

            if (max_length_Kwords < 7) {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            } else {
                buttonsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
            recyclerAdapter = new RecyclerAdapter(this, KWords);
            buttonsRecyclerView.setAdapter(recyclerAdapter);
        }

        sudokuGridViewAdapter = new SudokuGridView.SudokuGridViewAdapter(this);
        sudokuGridView.setAdapter(sudokuGridViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        switch (menuItem.getItemId()) {
            case R.id.fourXfour:
                English_mode = true;
                Listening_mode = false;
                fourXfour = true;
                sixXsix = false;
                nineXnine = false;
                twelveXtwelve = false;
                set_SudokuCell_white_space_arrays();
                pointsView = new int[]{100, 100, 100, 100, 100};
                intent = new Intent(this, VocabsActivity.class);
                startActivity(intent);
                break;
            case R.id.sixXsix:
                English_mode = true;
                Listening_mode = false;
                fourXfour = false;
                sixXsix = true;
                nineXnine = false;
                twelveXtwelve = false;
                set_SudokuCell_white_space_arrays();
                pointsView = new int[]{100, 100, 100, 100, 100, 100, 100};
                intent = new Intent(this, VocabsActivity.class);
                startActivity(intent);
                break;
            case R.id.nineXnine:
                English_mode = true;
                Listening_mode = false;
                fourXfour = false;
                sixXsix = false;
                nineXnine = true;
                twelveXtwelve = false;
                set_SudokuCell_white_space_arrays();
                pointsView = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
                intent = new Intent(this, VocabsActivity.class);
                startActivity(intent);
                break;
            case R.id.twelveXtwelve:
                English_mode = true;
                Listening_mode = false;
                fourXfour = false;
                sixXsix = false;
                nineXnine = false;
                twelveXtwelve = true;
                set_SudokuCell_white_space_arrays();
                pointsView = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
                intent = new Intent(this, VocabsActivity.class);
                startActivity(intent);
                break;
            case R.id.points_view:
                intent = new Intent(this, PointsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pointsVeiw", pointsView);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.easy_dif:
                if(MainActivity.getfourXfour()==true){
                    cell_replace_num =1;
                }
                else if( MainActivity.getsixXsix()==true){
                    cell_replace_num =7;
                }
                else if(MainActivity.gettwelveXtwelve()==true){
                    cell_replace_num =28;
                }
                else{
                    cell_replace_num =15;
                }

                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.medium_dif:
                if(MainActivity.getfourXfour()==true){
                    cell_replace_num =2;
                }
                else if( MainActivity.getsixXsix()==true){
                    cell_replace_num =15;
                }
                else if(MainActivity.gettwelveXtwelve()==true){
                    cell_replace_num =55;
                }
                else{
                    cell_replace_num =30;
                }

                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.very_dif:
                if(MainActivity.getfourXfour()==true){
                    cell_replace_num =3;
                }
                else if( MainActivity.getsixXsix()==true){
                    cell_replace_num =20;
                }
                else if(MainActivity.gettwelveXtwelve()==true){
                    cell_replace_num =86;
                }
                else{
                    cell_replace_num =50;
                }

                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.stringxml:
                English_mode = false;
                Listening_mode = false;
                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, VocabsActivity.class);
                startActivity(intent);
                break;
            case R.id.csvFile:
                English_mode = false;
                Listening_mode = false;
                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, CSVActivity.class);
                startActivity(intent);
                break;
            case R.id.switch_to_korean:
                English_mode = false;
                Listening_mode = false;
                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.switch_to_english:
                English_mode = true;
                Listening_mode = false;
                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.english_listening_mode:
                English_mode = true;
                Listening_mode = true;
                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
            case R.id.korean_listening_mode:
                English_mode = false;
                Listening_mode = true;
                set_SudokuCell_white_space_arrays();
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords", EWords);
                intent.putExtra("kwords", KWords);
                startActivity(intent);
                break;
        }

        return false;
    }


    public static boolean getEnglishMode() {
        return English_mode;
    }

    public static boolean getListeningMode() {
        return Listening_mode;
    }

    public static boolean getfourXfour() {
        return fourXfour;
    }

    public static boolean getsixXsix() {
        return sixXsix;
    }

    public static boolean getnineXnine() {
        return nineXnine;
    }

    public static boolean gettwelveXtwelve() {
        return twelveXtwelve;
    }

    public static String[] getEwords() {
        return KWords;
    }

    public static String[] getKwords() {
        return EWords;
    }

    public static void setDeductPointsView(int index) {
        pointsView[index] -= 3;
    }

    public static void setAddPointsView(int index) {
        pointsView[index] += 1;
    }

    private void set_SudokuCell_white_space_arrays() {
        if (getfourXfour()) SudokuCell.setBoolFour();
        if (getsixXsix()) SudokuCell.setBoolSix();
        if (getnineXnine()) SudokuCell.setBoolNine();
        if (gettwelveXtwelve()) SudokuCell.setBoolTwelve();
    }
}
