package kmitl.lab03.danaya58070042.simplemydot.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import android.Manifest;

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
            new Dot(this, (int) event.getX(), (int) event.getY()-230, 50, r, g, b);
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

    /*
    public void capture(View view){

        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
//        dotView.setDrawingCacheEnabled(true);
        Bitmap bm = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
//        dotView.setDrawingCacheEnabled(false);

        ImageView imageView = (ImageView) findViewById(R.id.showScreen);
        imageView.setImageBitmap(bm);

        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
        } catch (NullPointerException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
            startActivity(intent);
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile());

        startActivity(intent);
    }
    */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onShare(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else{
            String dirPath = Environment.getExternalStorageDirectory() +"/SimpleMyDot";
            String fileName = "Screenshot.png";

            storageImage(getScreenshot(dotView), dirPath, fileName);
            shareImage(new File(dirPath, fileName));

        }
    }

    public Bitmap getScreenshot(View view){

        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bm = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        Canvas cv = new Canvas(bm);

        cv.drawColor(Color.WHITE);
        rootView.draw(cv);
        return bm;
    }

    public void storageImage(Bitmap bitmap, String dirPath, String fileName) {
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File file = new File(dirPath, fileName);

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shareImage(File file){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(intent.EXTRA_STREAM, Uri.fromFile(file));

        startActivity(Intent.createChooser(intent,"Share via.."));
    }


}
