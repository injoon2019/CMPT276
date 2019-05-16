package john.com.sudoko.Controller;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import john.com.sudoko.Model.GameEngine;

public class ButtonActivity extends android.support.v7.widget.AppCompatButton implements OnClickListener {

    private int number;

    public ButtonActivity(Context context, AttributeSet attrs){
        super(context,attrs);
        setOnClickListener(this);
        int spSize=7;
        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spSize, getResources().getDisplayMetrics());

        setTextSize(scaledSizeInPixels);

    }

    @Override
    public void onClick(View v){
        GameEngine.getInstance().setNumber(number);
    }

    public void setNumber(int number){
        this.number = number;
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spSize=7;

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // TODO: add logic for landscape mode here
            spSize=11;
        }
        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spSize, getResources().getDisplayMetrics());

        setTextSize(scaledSizeInPixels);
    }
}

