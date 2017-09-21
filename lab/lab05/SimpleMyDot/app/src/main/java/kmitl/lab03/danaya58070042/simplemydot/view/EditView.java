package kmitl.lab03.danaya58070042.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kmitl.lab03.danaya58070042.simplemydot.model.Dot;

/**
 * Created by student on 9/21/2017 AD.
 */

public class EditView extends View {

    private Paint paint;
    private int color = Color.BLUE;
    private int radius =100;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);

    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public int getRadius() {
        return radius;
    }

    public EditView(Context context) {
        super(context);
        paint = new Paint();
    }

    public EditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public EditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public EditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();
    }
}
