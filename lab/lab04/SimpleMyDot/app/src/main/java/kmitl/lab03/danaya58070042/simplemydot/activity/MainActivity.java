package kmitl.lab03.danaya58070042.simplemydot.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import kmitl.lab03.danaya58070042.simplemydot.R;
import kmitl.lab03.danaya58070042.simplemydot.model.Dot;
import kmitl.lab03.danaya58070042.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
        implements Dot.OnDotChangedListener {

    private DotView dotView;
    private int r;
    private int g;
    private int b;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////

        dotView = (DotView) findViewById(R.id.dotView); //เอาdotViewไปทับที่ id
        random = new Random();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);
            new Dot(this, (int) event.getX(), (int) event.getY() - 230, 50, r, g, b);
        }
        return super.onTouchEvent(event);

    }

    public void onRandomDot(View view) {

        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        new Dot(this, centerX, centerY, 50, r, g, b);

    }

    public void clearDots(View view) {
        dotView.clrDot();
        dotView.invalidate();
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();

//        TextView centerXTextView = (TextView)findViewById(R.id.centerXTextView);
//        TextView centerYTextView = (TextView)findViewById(R.id.centerYTextView);
//        centerXTextView.setText(String.valueOf(dot.getCenterX()));
//        centerYTextView.setText(String.valueOf(dot.getCenterY()));
    }

    public void capture(View view){

        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
//        dotView.setDrawingCacheEnabled(true);
        Bitmap bm = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
//        dotView.setDrawingCacheEnabled(false);

        ImageView imageView = (ImageView) findViewById(R.id.showScreen);
        imageView.setImageBitmap(bm);

//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
//        Uri uri =
//        intent.putExtra(Intent.EXTRA_STREAM, imageUri);

//        startActivity(intent);


    }


}