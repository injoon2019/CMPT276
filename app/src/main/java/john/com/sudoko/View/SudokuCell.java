package john.com.sudoko.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import john.com.sudoko.Controller.MainActivity;

import john.com.sudoko.Model.GameEngine.CoordinatorClass;
import john.com.sudoko.Model.GameEngine;
import john.com.sudoko.Model.SudokuGenerator;

import static android.content.Context.WINDOW_SERVICE;

public class SudokuCell extends View {

    private int colorForSelectedLightYellow = Color.parseColor("#FFFFE0");
    private int colorForSelectedDKYellow = Color.parseColor("#FFFF00");
    private int colorForSelectedCyan = Color.parseColor("#00f2ff");

    private static int specialYellow;
    private ArrayList<CoordinatorClass> selectedAsLightYellow = new ArrayList<>();
    ArrayList<CoordinatorClass> selectedForCyan = new ArrayList<>();

    private int value;
    private boolean modifiable = true;

    private Paint mPaint;
    private Paint lPaint;
    private String[] EWords;
    private int spSize;
    private static boolean[][] init_array_white_cells_four = new boolean[4][4];
    private static boolean[][] init_array_white_cells_six = new boolean[6][6];
    private static boolean[][] init_array_white_cells_nine = new boolean[9][9];
    private static boolean[][] init_array_white_cells_twelve = new boolean[12][12];
    private int XX = 0;
    private int YY = 0;
    private boolean flag_onClick;

    public SudokuCell(Context context, String[] EWords, int xx, int yy) {
        super(context);
        this.EWords = EWords;
        mPaint = new Paint();
        lPaint = new Paint();
        XX = xx;
        YY = yy;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLines(canvas);
        drawNumber(canvas);

//        if (!flag_onClick) {
//            setBackgroundColor(Color.WHITE);
//            flag_onClick = true;
//        }
        refreshView();
    }

    private void drawNumber(Canvas canvas) {

        if(SudokuGenerator.selectedAsGrayWords[YY][XX]) {
            mPaint.setColor(Color.RED);
        } else {
            mPaint.setColor(Color.BLACK);
        }
        Rect bounds = new Rect();
        mPaint.getTextBounds(EWords[getValue()], 0, EWords[getValue()].length(), bounds);

        mPaint.setStrokeWidth(1);
        if (MainActivity.getfourXfour()) {
            if (EWords[getValue()].length() < 9) {
                spSize = 22;
            } else {
                spSize = 17;
            }
        }
        if (MainActivity.getsixXsix()) {
            if (EWords[getValue()].length() < 7) {
                spSize = 17;
            } else {
                spSize = 14;
            }
        }
        if (MainActivity.getnineXnine()) {
            if (EWords[getValue()].length() < 7) {
                spSize = 13;
            } else {
                spSize = 8;
            }
        }
        if (MainActivity.gettwelveXtwelve()) {
            if (EWords[getValue()].length() < 6) {
                spSize = 8;
            } else {
                spSize = 5;
            }
        }

        Display display = ((WindowManager) getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();

        if (orientation == Surface.ROTATION_90
                || orientation == Surface.ROTATION_270) {
            // TODO: add logic for landscape mode here
            mPaint.setStrokeWidth(2);
            if (MainActivity.getfourXfour()) {
                if (EWords[getValue()].length() < 8) {
                    spSize = 30;
                } else {
                    spSize = 22;
                }
            }

            if (MainActivity.getsixXsix()) {
                if (EWords[getValue()].length() < 7) {
                    spSize = 25;
                } else {
                    spSize = 20;
                }
            }

            if (MainActivity.getnineXnine()) {
                if (EWords[getValue()].length() < 7) {
                    spSize = 21;
                } else {
                    spSize = 14;
                }
            }

            if (MainActivity.gettwelveXtwelve()) {
                if (EWords[getValue()].length() < 7) {
                    spSize = 16;
                } else {
                    spSize = 12;
                }
            }

        }
        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spSize, getResources().getDisplayMetrics());

        mPaint.setTextSize(scaledSizeInPixels);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        if (getValue() != 0) {
            canvas.drawText(EWords[getValue()], (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
            setTag(EWords[getValue()]);
        }
    }

    private void drawLines(Canvas canvas) {

        lPaint.setColor(Color.BLACK);
        lPaint.setStyle(Paint.Style.STROKE);

        int strokWid = 7;

        if (MainActivity.getfourXfour()) {
            lPaint.setStrokeWidth(2);
            canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            canvas.drawLine(0, 0, getWidth(), 0, lPaint);

            if (YY == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 2) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 3) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, getHeight(), getWidth(), getHeight(), lPaint);
            }

            if (XX == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }
            if (XX == 2) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }
            if (XX == 3) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), lPaint);
            }
        }

        if (MainActivity.getsixXsix()) {
            lPaint.setStrokeWidth(2);
            canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            canvas.drawLine(0, 0, getWidth(), 0, lPaint);

            if (YY == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 2) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 4) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 5) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, getWidth(), getWidth(), getHeight(), lPaint);
            }


            if (XX == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }
            if (XX == 3) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }
            if (XX == 5) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), lPaint);
            }

        }

        if (MainActivity.getnineXnine()) {
            lPaint.setStrokeWidth(2);
            canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            canvas.drawLine(0, 0, getWidth(), 0, lPaint);

            if (YY == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 3) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 6) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }

            if (YY == 8) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, getHeight(), getWidth(), getHeight(), lPaint);
            }

            if (XX == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }
            if (XX == 3) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }

            if (XX == 6) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }

            if (XX == 8) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), lPaint);
            }

        }
        if (MainActivity.gettwelveXtwelve()) {
            lPaint.setStrokeWidth(2);
            canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            canvas.drawLine(0, 0, getWidth(), 0, lPaint);

            if (YY == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 3) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 6) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 9) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, getWidth(), 0, lPaint);
            }
            if (YY == 11) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, getHeight(), getWidth(), getHeight(), lPaint);
            }

            if (XX == 0) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }
            if (XX == 4) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }

            if (XX == 8) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(0, 0, 0, getHeight(), lPaint);
            }

            if (XX == 11) {
                lPaint.setStrokeWidth(strokWid);
                canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), lPaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable() {
        modifiable = false;
    }

    public void setInitValue(int value) {
        this.value = value;
        invalidate();
    }

    public void setValue(int value) {
        if (modifiable) {
            this.value = value;
        }
        invalidate();
    }

    public int getValue() {
        return value;
    }

    public static void setBoolFour() {
        init_array_white_cells_four = new boolean[4][4];
    }

    public static void setBoolSix() {
        init_array_white_cells_six = new boolean[6][6];
    }

    public static void setBoolNine() {
        init_array_white_cells_nine = new boolean[9][9];
    }

    public static void setBoolTwelve() {
        init_array_white_cells_twelve = new boolean[12][12];
    }

    public static void setTFBool_four(int y, int x) {
        init_array_white_cells_four[y][x] = true;
    }

    public static void setTFBool_six(int y, int x) {
        init_array_white_cells_six[y][x] = true;
    }

    public static void setTFBool_nine(int y, int x) {
        init_array_white_cells_nine[y][x] = true;
    }

    public static void setTFBool_twelve(int y, int x) {
        init_array_white_cells_twelve[y][x] = true;
    }

    public void setColors(ArrayList<GameEngine.CoordinatorClass> selectedAsLightYellow, ArrayList<CoordinatorClass> selectedForCyan) {
        this.selectedAsLightYellow.clear();
        this.selectedAsLightYellow.addAll(selectedAsLightYellow);
        this.selectedForCyan.clear();
        this.selectedForCyan.addAll(selectedForCyan);

        refreshView();
    }

    public static void setSpecialYellow(int spY){
        specialYellow = spY;
    }

    public void setBackgroundColorFourByFour(int x, int y) {
        if (x < 2 && y < 2) {
            setBackgroundColor(Color.LTGRAY);
        } else if (2 <= x && 2 <= y) {
            setBackgroundColor(Color.LTGRAY);
        }

        if (x < 2 && 2 <= y) {
            setBackgroundColor(Color.GREEN);
        } else if (2 <= x && y < 2) {
            setBackgroundColor(Color.GREEN);
        }
    }

    public void setBackgroundColorSixBySix(int x, int y) {
        if (x < 3 && (y < 2 || 4 <= y)) {
            setBackgroundColor(Color.LTGRAY);
        } else if (3 <= x && (2 <= y && y < 4)) {
            setBackgroundColor(Color.LTGRAY);
        }

        if (x < 3 && (2 <= y && y < 4)) {
            setBackgroundColor(Color.GREEN);
        } else if (3 <= x && (y < 2 || 4 <= y)) {
            setBackgroundColor(Color.GREEN);
        }
    }

    public void setBackgroundColorNineByNine(int x, int y) {

        if (x < 3 && y < 3) {
            setBackgroundColor(Color.GREEN);
        } else if (6 <= x && y < 3) {
            setBackgroundColor(Color.GREEN);
        } else if ((3 <= x && x < 6) && (y < 3)) {
            setBackgroundColor(Color.LTGRAY);
        }

        if ((x < 3) && (3 <= y && y < 6)) {
            setBackgroundColor(Color.LTGRAY);
        } else if ((3 <= x && x < 6) && (3 <= y && y < 6)) {
            setBackgroundColor(Color.GREEN);
        } else if (6 <= x && (3 <= y && y < 6)) {
            setBackgroundColor(Color.LTGRAY);
        }

        if ((3 <= x && x < 6) && (6 <= y)) {
            setBackgroundColor(Color.LTGRAY);
        } else if ((6 <= x) && (6 <= y)) {
            setBackgroundColor(Color.GREEN);
        } else if (x < 3 && 6 <= y) {
            setBackgroundColor(Color.GREEN);
        }
    }

    public void setBackgroundColorTwelveByTwelve(int x, int y) {

        if ((x < 4 || 8<= x) && (y < 3 || 9 <= y)) {
            setBackgroundColor(Color.GREEN);
        }
        if ((4 <= x && x < 8) && ((3 <= y && y < 6)||(9 <= y))) {
            setBackgroundColor(Color.GREEN);
        }

        if ((4 <= x && x < 8)  && (y < 3 || 9 <= y)) {
            setBackgroundColor(Color.LTGRAY);
        }
        if ( (x < 4 || 8<= x)&& ((3 <= y && y < 6)||(9 <= y))) {
            setBackgroundColor(Color.LTGRAY);
        }

//        if (8 <= x && (6 <= y && y < 9)) {
//            setBackgroundColor(Color.GREEN);
//        }

// || 9 <= y
//        if (8 <= x && (y < 3)) {
//            setBackgroundColor(Color.GREEN);
//        }
//        if (x < 4 && (3 <= y && y < 6)) {
//            setBackgroundColor(Color.LTGRAY);
//        }
//        if ((4 <= x && x < 8) && (6 <= y && y < 9)) {
//            setBackgroundColor(Color.WHITE);
//        }
//
//        if ((4 <= x && x < 8) && (y < 3 || 9 <= y)) {
//            setBackgroundColor(Color.LTGRAY);
//        }
//        if (8 <= x && (3 <= y && y < 6)) {
//            setBackgroundColor(Color.LTGRAY);
//        }
//        if (x < 4 && (6 <= y && y < 9)) {
//            setBackgroundColor(Color.LTGRAY);
//        }
    }

    private void refreshView() {
        int x =0 ;
        int y = 0;
        if (MainActivity.getfourXfour()) {
            XX = XX % 4;
            YY = YY % 4;

            x = specialYellow % 4;
            y = specialYellow / 4;
//            setBackgroundColorFourByFour( XX, YY);

        }
        if (MainActivity.getsixXsix()) {
            XX = XX % 6;
            YY = YY % 6;

            x = specialYellow % 6;
            y = specialYellow / 6;
//            setBackgroundColorSixBySix( XX, YY);

        }
        if (MainActivity.getnineXnine()) {
            XX = XX % 9;
            YY = YY % 9;

            x = specialYellow % 9;
            y = specialYellow / 9;

//            setBackgroundColorNineByNine(XX, YY);
        }
        if (MainActivity.gettwelveXtwelve()) {
            XX = XX % 12;
            YY = YY % 12;

            x = specialYellow % 12;
            y = specialYellow / 12;
//            setBackgroundColorTwelveByTwelve(XX, YY);
        }
        setBackgroundColor(Color.WHITE);

        for (int i = 0; i < selectedAsLightYellow.size(); i++) {
            if (selectedAsLightYellow.get(i).getX() == XX && selectedAsLightYellow.get(i).getY() == YY) {
                setBackgroundColor(colorForSelectedLightYellow);
            }

            if(x == XX && y == YY){
                setBackgroundColor(colorForSelectedDKYellow);
            } else {
                for (int j = 0; j < selectedForCyan.size(); j++) {
                    if (selectedForCyan.get(j).getX() == XX && selectedForCyan.get(j).getY() == YY) {
                        setBackgroundColor(colorForSelectedCyan);
                        return;
                    }
                }
            }



        }

    }




}