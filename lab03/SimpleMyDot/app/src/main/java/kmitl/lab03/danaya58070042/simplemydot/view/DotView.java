package kmitl.lab03.danaya58070042.simplemydot.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.graphics.PaintCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.danaya58070042.simplemydot.model.Dot;

/**
 * Created by student on 8/25/2017 AD.
 */

public class DotView extends View {

    private Paint paint;
    private ArrayList<Dot> dots;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            for (Dot d : dots) {
                paint.setColor(Color.rgb(d.getR(), d.getG(), d.getB()));
//                paint.setColor(Color.RED);
                canvas.drawCircle(d.getCenterX(), d.getCenterY(), 50, paint);
            }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
        dots = new ArrayList<>();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        dots = new ArrayList<>();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        dots = new ArrayList<>();
    }

    public void setDot(Dot dot) {
        this.dots.add(dot);
    }
    public void clrDot(){
        dots.clear();
    }
}
