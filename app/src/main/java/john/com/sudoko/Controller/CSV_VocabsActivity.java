package john.com.sudoko.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import john.com.sudoko.R;

public class CSV_VocabsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<String> chapterNames=new ArrayList<>();
    private ArrayList<ArrayList<String>> chapterWords=new ArrayList<>();
    private VocabsAdapter vocabsAdapter;
    private Button save,cancel;
    private ArrayList<String> selections=new ArrayList<>();
    private static String[] KWORDS;
    private static String[] EWORDS;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabs);
        recyclerView=findViewById(R.id.rv_vocabs);
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> words=new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        String[] chapterName = bundle.getStringArray("CHNAME");
        String[] Ewords = bundle.getStringArray("EWORD");
        String[] Kwords = bundle.getStringArray("KWORD");

        String ChapterName = null;
        String LastChapterName = null;
        for(int i=0;i < Ewords.length; i++)
        {
            if(i == 0) {
                ChapterName = chapterName[i];
                words.add(Kwords[i]+":"+Ewords[i]);
            }
            else
            {
                if(ChapterName.equals(chapterName[i]))
                {
                    words.add(Kwords[i]+":"+Ewords[i]);
                }
                else
                {
                    chapterWords.add(words);
                    words=new ArrayList<>();

                    chapterNames.add(ChapterName);
                    ChapterName = chapterName[i];

                    words.add(Kwords[i]+":"+Ewords[i]);

                }
            }

            LastChapterName = chapterName[i];
        }

        if(ChapterName.equals(LastChapterName)){
            chapterWords.add(words);
            chapterNames.add(LastChapterName);
        } else {
            chapterWords.add(words);
        }


        vocabsAdapter=new VocabsAdapter(chapterNames,chapterWords,this,selections);
        recyclerView.setAdapter(vocabsAdapter);
    }

    @Override
    public void onClick(View view) {
        int max_selection_size = 0;
        if(MainActivity.getfourXfour()){ max_selection_size = 4; }

        if(MainActivity.getsixXsix()){ max_selection_size = 6; }

        if(MainActivity.getnineXnine()){ max_selection_size = 9; }

        if(MainActivity.gettwelveXtwelve()){ max_selection_size = 12; }


        switch (view.getId())
        {
            case R.id.save:
                if(selections.size()!= max_selection_size)
                {
                    String str = "Please select " + max_selection_size + "words";
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    return;
                }
                KWORDS=new String[selections.size()+1];
                EWORDS=new String[selections.size()+1];
                KWORDS[0]="Del.";
                EWORDS[0]="Del.";
                for(int i=0;i<selections.size();i++)
                {
                    KWORDS[i+1]=selections.get(i).split(":")[0];
                    EWORDS[i+1]=selections.get(i).split(":")[1];

                }
                Intent intent=new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords",EWORDS);
                intent.putExtra("kwords",KWORDS);
                startActivity(intent);
                break;
            case R.id.cancel:

                this.finish();
                break;
        }
    }
}
