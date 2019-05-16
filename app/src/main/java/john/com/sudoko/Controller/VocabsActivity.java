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
import java.util.Arrays;

import john.com.sudoko.R;

public class VocabsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<String> chapterNames=new ArrayList<>();
    private ArrayList<ArrayList<String>> chapterWords=new ArrayList<>();
    private VocabsAdapter vocabsAdapter;
    private Button save,cancel;
    private ArrayList<String> selections=new ArrayList<>();
    private Intent intent;

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
        ArrayList<String> arrayList= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.KWords1)));
        ArrayList<String> arrayList1= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.EWords1)));
        ArrayList<String> words=new ArrayList<>();
        for(int i=0;i<arrayList.size();i++)
        {
            words.add(arrayList.get(i)+":"+arrayList1.get(i));
        }
        chapterWords.add(words);
        chapterNames.add("chapter1: Family");

        words=new ArrayList<>();
        arrayList.clear();
        arrayList1.clear();
        arrayList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.KWords2)));
        arrayList1= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.EWords2)));
        for(int i=0;i<arrayList.size();i++)
        {
            words.add(arrayList.get(i)+":"+arrayList1.get(i));
        }
        chapterWords.add(words);
        chapterNames.add("chapter2: Weather");

//        words=new ArrayList<>();
//        arrayList.clear();
//        arrayList1.clear();
//        arrayList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.KWords3)));
//        arrayList1= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.EWords3)));
//        for(int i=0;i<arrayList.size();i++)
//        {
//            words.add(arrayList.get(i)+":"+arrayList1.get(i));
//        }
//        chapterWords.add(words);
//        chapterNames.add("chapter3: Time");

        vocabsAdapter=new VocabsAdapter(chapterNames,chapterWords,this,selections);
        recyclerView.setAdapter(vocabsAdapter);
    }

    @Override
    public void onClick(View view) {
        int max_selection_size = 0;
        if(MainActivity.getfourXfour() ){ max_selection_size = 4; }

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
                String[] kwords=new String[selections.size()+1];
                String[] ewords=new String[selections.size()+1];
                kwords[0]="Del.";
                ewords[0]="Del.";
                for(int i=0;i<selections.size();i++)
                {
                    kwords[i+1]=selections.get(i).split(":")[0];
                    ewords[i+1]=selections.get(i).split(":")[1];
                }
                intent=new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ewords",ewords);
                intent.putExtra("kwords",kwords);
                startActivity(intent);
            break;
            case R.id.cancel:
                this.finish();
                break;
        }
    }
}
