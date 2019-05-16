package john.com.sudoko.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import john.com.sudoko.Controller.MainActivity;
import john.com.sudoko.R;
import john.com.sudoko.Controller.ButtonActivity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    public String[] KWords;

    public RecyclerAdapter(Context context, String[] KWords) {
        this.context = context;
        this.KWords = KWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.button, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        int pos = 0;
        if(MainActivity.getfourXfour()) {pos = position %5;}
        if(MainActivity.getsixXsix()) {pos = position %7;}
        if(MainActivity.getnineXnine()) {pos = position %10;}
        if(MainActivity.gettwelveXtwelve()) {pos = position %13;}

        if (pos == 0) {
            viewHolder.button.setText("Del.");
            viewHolder.button.setNumber(0);
        } else {
            String setTexts = KWords[pos];
            viewHolder.button.setText(setTexts);
            viewHolder.button.setNumber(pos);
        }
    }

    @Override
    public int getItemCount()
    {
        int ret = 0;
        if(MainActivity.getfourXfour()==true){
            ret= 4+1;
        }
        else if( MainActivity.getsixXsix()==true){
            ret= 6+1;
        }
        else if(MainActivity.gettwelveXtwelve()==true){
            ret= 12+1;
        }
        else if(MainActivity.getnineXnine()){
            ret= 9+1;
        }
        return ret;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ButtonActivity button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }


}

