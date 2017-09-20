package kmitl.lab03.danaya58070042.simplemydot.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.PaintCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.danaya58070042.simplemydot.R;
import kmitl.lab03.danaya58070042.simplemydot.model.Colors;
import kmitl.lab03.danaya58070042.simplemydot.model.Dot;
import kmitl.lab03.danaya58070042.simplemydot.model.Dots;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View{

    private Paint paint;
    private Dots allDot;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.allDot != null) {
            for (Dot dot: allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(
                        dot.getCenterX(),
                        dot.getCenterY(), 50, paint);
            }
        }
//        super.onDraw(canvas);
//        for (Dot d : dots) {
//            paint.setColor(d.getColor());
////                paint.setColor(Color.RED);
//            canvas.drawCircle(d.getCenterX(), d.getCenterY(), 50, paint);
//        }
    }


    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {

        this.onDotViewPressListener = onDotViewPressListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.onDotViewPressListener
                        .onDotViewPressed(
                                (int)event.getX(),
                                (int)event.getY());
                return true;
        }
        return false;

    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDot(Dots dots) {
        this.allDot = dots;
    }

//    public void clrDot() {
//        dots.clear();
//    }


}
