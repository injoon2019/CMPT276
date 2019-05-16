package john.com.sudoko.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;

import john.com.sudoko.R;

public class VocabsAdapter extends RecyclerView.Adapter<VocabsAdapter.ViewHolder> {
    ArrayList<String> chapterNames=new ArrayList<>();
    ArrayList<String> selections=new ArrayList<>();
    ArrayList<ArrayList<String>> chapterWords=new ArrayList<>();
    boolean flagSelect=false;
    Context context;

    public VocabsAdapter(ArrayList<String> chapterNames, ArrayList<ArrayList<String>> chapterWords, Context context, ArrayList<String> selections) {
        this.chapterNames = chapterNames;
        this.chapterWords = chapterWords;
        this.selections=selections;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vocabs, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv=new TextView(context);
        tv.setLayoutParams(lparams);
        tv.setText(chapterNames.get(i));
        viewHolder.linearLayout.addView(tv);
        final HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        ArrayList<String> words=chapterWords.get(i);
        int j=0;
        for(String options:words)
        {
            final CheckBox checkBox=new CheckBox(context);
            checkBox.setText(options);
            checkBox.setTag(new Integer(j));
            checkBox.setLayoutParams(lparams);
            checkBoxMap.put(checkBox,options);
            viewHolder.linearLayout.addView(checkBox);
            j+=1;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int max_selection_size = 0;
                    if(MainActivity.getfourXfour()){ max_selection_size = 4; }

                    if(MainActivity.getsixXsix()){ max_selection_size = 6; }

                    if(MainActivity.getnineXnine()){ max_selection_size = 9; }

                    if(MainActivity.gettwelveXtwelve()){ max_selection_size = 12; }


                    if(selections.size()>=max_selection_size && b)
                    {
                        if(!flagSelect) {
                            compoundButton.setChecked(false);
                            String str = "You cannot select more than " + max_selection_size + " words";
                            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                        }

                    }
                    else if(!b)
                    {
                        if(selections.contains(compoundButton.getText().toString()))
                        {
                            selections.remove(compoundButton.getText().toString());
                        }
                    }
                    else if(b)
                    {
                         if(!selections.contains(compoundButton.getText().toString()))
                        selections.add(compoundButton.getText().toString());
                    }

                    Log.i("selected",selections.toString());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return chapterNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.ll);
        }
    }
}
