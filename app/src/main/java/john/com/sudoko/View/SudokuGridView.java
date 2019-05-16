package john.com.sudoko.View;

import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Locale;

import john.com.sudoko.Controller.MainActivity;
import john.com.sudoko.Model.GameEngine;


public class SudokuGridView extends GridView {

    private Context context;
    public int[][] sudukoGrid;
    private TextToSpeech mTTS;
    private GameGrid grid = null;
    TextView GridViewItems;

    public SudokuGridView(final Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);
        setAdapter(gridViewAdapter);

        setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int x;
                final int y;

                if (MainActivity.getfourXfour() == true) {
                    x = position % 4;
                    y = position / 4;

                    GameEngineColorChange(x, y, 4, position);

                } else if (MainActivity.getsixXsix() == true) {
                    x = position % 6;
                    y = position / 6;
                    GameEngineColorChange(x, y, 6, position);
                } else if (MainActivity.gettwelveXtwelve() == true) {
                    x = position % 12;
                    y = position / 12;
                    GameEngineColorChange(x, y, 12, position);
                } else {
                    x = position % 9;
                    y = position / 9;
                    GameEngineColorChange(x, y, 9, position);
                }

                GameEngine.getInstance().setSelectedPositon(x, y);

                if (MainActivity.getListeningMode()) {

                    mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status == TextToSpeech.SUCCESS) {
                                int result;
                                if (!MainActivity.getEnglishMode()) {
                                    result = mTTS.setLanguage(Locale.KOREAN);
                                } else {
                                    result = mTTS.setLanguage(Locale.US);
                                }

                                speak(x, y);

                                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                    Log.e("TTS", "Language not supported");
                                } else {
                                }
                            } else {
                                Log.e("TTS", "Initialization failed");
                            }
                        }
                    });
                }
            }
        });
    }

    void GameEngineColorChange(int x, int y, int matrixSize, int position) {
        GameEngine.getInstance().changeColor(x, y, matrixSize, position);
    }

    public void speak(int x, int y) {
        String text;

        if (MainActivity.getfourXfour() == true) {
            sudukoGrid = new int[4][4];
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    sudukoGrid[a][b] = GameEngine.getInstance().getGrid().getItem(a, b).getValue();
                }
            }
        } else if (MainActivity.getsixXsix() == true) {
            sudukoGrid = new int[6][6];
            for (int a = 0; a < 6; a++) {
                for (int b = 0; b < 6; b++) {
                    sudukoGrid[a][b] = GameEngine.getInstance().getGrid().getItem(a, b).getValue();
                }
            }
        } else if (MainActivity.gettwelveXtwelve() == true) {
            sudukoGrid = new int[12][12];
            for (int a = 0; a < 12; a++) {
                for (int b = 0; b < 12; b++) {
                    sudukoGrid[a][b] = GameEngine.getInstance().getGrid().getItem(a, b).getValue();
                }
            }
        } else {
            sudukoGrid = new int[9][9];
            for (int a = 0; a < 9; a++) {
                for (int b = 0; b < 9; b++) {
                    sudukoGrid[a][b] = GameEngine.getInstance().getGrid().getItem(a, b).getValue();
                }
            }
        }

        if (!MainActivity.getEnglishMode()) { //if korean listening mode

            text = MainActivity.getEwords()[sudukoGrid[x][y]];


        } else {                          //if English listening mode

            text = MainActivity.getKwords()[sudukoGrid[x][y]];
        }
        if (sudukoGrid[x][y] != 0) {
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public static class SudokuGridViewAdapter extends BaseAdapter {

        private Context context;

        public SudokuGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            int ret = 0;
            if (MainActivity.getfourXfour() == true) {
                ret = 16;
            } else if (MainActivity.getsixXsix() == true) {
                ret = 36;
            } else if (MainActivity.gettwelveXtwelve() == true) {
                ret = 144;
            } else if (MainActivity.getnineXnine()) {
                ret = 81;
            }
            return ret;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return GameEngine.getInstance().getGrid().getItem(position);
        }


    }

}

