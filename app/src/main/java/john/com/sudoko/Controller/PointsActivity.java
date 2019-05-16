package john.com.sudoko.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import john.com.sudoko.R;
import john.com.sudoko.Controller.MainActivity;
public class PointsActivity extends Activity implements View.OnClickListener{

    private int[] pointsView = {100,100,100,100,100,100,100,100,100,100,100,100};
    private String[] Ewords = {"","","","","","","","","","","",""};
    private String[] Kwords = {"","","","","","","","","","","",""};
    private Button back_button;
    private static ArrayList<WordsPoints> wordPointsList = new ArrayList();

    public static class WordsPoints{
        private int points;
        private String Ewords;
        private String Kwords;
        public WordsPoints(int points, String EE, String KK){
            this.points = points;
            this.Ewords = EE;
            this.Kwords = KK;
        }
        public int getPoints() {
            return points;
        }
        public void setPoints(int points) {
            this.points = points;
        }
        public String getEwords() {
            return Ewords;
        }
        public void setEwords(String EE) {
            this.Ewords = EE;
        }
        public String getKwords() {
            return Kwords;
        }
        public void setKwords(String KK) {
            this.Kwords = KK;
        }

        public String toString(){
            return this.Kwords + ":" + this.Ewords + " = " + this.points;
        }

        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + this.points;
            result = prime * result + ((this.Ewords == null) ? 0 : this.Ewords.hashCode());
            result = prime * result + ((this.Kwords == null) ? 0 : this.Kwords.hashCode());
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            WordsPoints other = (WordsPoints) obj;
            if (this.Ewords != other.Ewords)
                return false;
            if (this.Ewords == null) {
                if (other.Ewords != null)
                    return false;
            } else if (!this.Ewords.equals(other.Ewords))
                return false;
            return true;
        }

        public boolean compareString(String EE){
            return this.Ewords.equals(EE);
        }

        public boolean comparePoints(int pp){
            return this.points == pp;
        }

    }

    public static int GetWordPointsList(String EE)
    {
        for (int jCount = 0; jCount < wordPointsList.size(); jCount++) {
            if (wordPointsList.get(jCount).compareString(EE)){
                return wordPointsList.get(jCount).getPoints();
            }
        }
        return 777;
    }
    public static void addNewWords(int[] pointView, String[] Eword, String[] Kword)
    {
        int flag_wordAdd = 0;
        int index = 0;

        if(wordPointsList.size() == 0)
        {
            for(int iCount = 0; iCount < Eword.length; iCount++)
            {
                if(Eword[iCount].equals("Del.") == false) {
                    wordPointsList.add(new WordsPoints(pointView[iCount], Eword[iCount], Kword[iCount]));
                }
            }
        } else
        {
            for(int iCount = 0; iCount < Eword.length; iCount++) {
                for (int jCount = 0; jCount < wordPointsList.size(); jCount++) {
                    flag_wordAdd = 0;
                    if (wordPointsList.get(jCount).compareString(Eword[iCount])) {
                        if(wordPointsList.get(jCount).comparePoints(pointView[iCount]) == false)
                        {
                            index = jCount;
                            flag_wordAdd = 2;
                            break;
                        }
                        flag_wordAdd = 1;
                        break;
                    }
                }

                if (flag_wordAdd == 0)
                {
                    if(Eword[iCount].equals("Del.") == false) {
                        wordPointsList.add(new WordsPoints(pointView[iCount], Eword[iCount], Kword[iCount]));
                    }
                }
                if (flag_wordAdd == 2) {
                    if(Eword[iCount].equals("Del.") == false) {
                        wordPointsList.remove(index);
                        wordPointsList.add(new WordsPoints(pointView[iCount], Eword[iCount], Kword[iCount]));
                    }
                }
            }
        }
    }
//
    public void toPointsViewString(ArrayList<WordsPoints> WPL, ArrayList<String> pointsViewString) {
        pointsViewString.add("Correct selection gets +1. Incorrect selection gets -3.");
        pointsViewString.add("You have " + WPL.size() + " words below.");
        for (int i = 0; i < WPL.size(); i++) {
            int index = i + 1;
            pointsViewString.add("(" + index + ") " + WPL.get(i).toString());
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_view);
        back_button=findViewById(R.id.back_button);
        back_button.setOnClickListener(this);

        ArrayList<String> pointsViewString = new ArrayList();
        pointsView = getIntent().getIntArrayExtra("pointsVeiw");
        Ewords = getIntent().getStringArrayExtra("ewords");
        Kwords = getIntent().getStringArrayExtra("kwords");
        addNewWords(pointsView, Ewords, Kwords);

        Collections.sort(wordPointsList, new Comparator<WordsPoints>(){
            public int compare(WordsPoints s1, WordsPoints s2){
                return Integer.valueOf(s1.getPoints()).compareTo(s2.getPoints());
            }
        });

        toPointsViewString(wordPointsList, pointsViewString);


        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pointsViewString);
        ListView pointsListView = (ListView) findViewById(R.id.points_listview);
        pointsListView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.back_button:
                this.finish();
                break;
        }

    }
}
