package kmitl.lab03.danaya58070042.simplemydot;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import kmitl.lab03.danaya58070042.simplemydot.activity.MainActivity;
import kmitl.lab03.danaya58070042.simplemydot.model.Colors;
import kmitl.lab03.danaya58070042.simplemydot.model.Dot;
import kmitl.lab03.danaya58070042.simplemydot.model.Dots;
import kmitl.lab03.danaya58070042.simplemydot.view.DotView;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainFragment extends Fragment implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener {

    private DotView dotView;

    Button mButtonDialog;
    private int WhereToShare;
    private Dots dots;


    public mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        dotView = (DotView) rootView.findViewById(R.id.dotView); //เอาdotViewไปทับที่ id
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        Button btnRan = (Button) rootView.findViewById(R.id.btnRandom);
        Button btnclr = (Button) rootView.findViewById(R.id.btnClear);
        Button btnShr = (Button) rootView.findViewById(R.id.btnShare);

        btnRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRandomDot();
            }
        });

        btnclr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dots.clearAll();
            }
        });

        btnShr.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                onShare();
            }
        });

        return rootView;
    }


    public void onRandomDot() {
        Random random = new Random();

        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());

        Dot dot = new Dot(centerX, centerY, 50, new Colors().getColor());

        dots.addDot(dot);

    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDot(dots);
        dotView.invalidate();

//        TextView centerXTextView = (TextView)findViewById(R.id.centerXTextView);
//        TextView centerYTextView = (TextView)findViewById(R.id.centerYTextView);
//        centerXTextView.setText(String.valueOf(dot.getCenterX()));
//        centerYTextView.setText(String.valueOf(dot.getCenterY()));
    }


    /////////////////// SHARING ON FACEBOOK /////////////////////

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
    public void onShare() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Share with ...");
            builder.setPositiveButton("Whole Screen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    WhereToShare = 0;
                    String dirPath = Environment.getExternalStorageDirectory() + "/SimpleMyDot";
                    String fileName = "Screenshot.png";
                    storageImage(getScreenshot(dotView, WhereToShare), dirPath, fileName);
                    shareImage(new File(dirPath, fileName));
                }

            });

            builder.setNegativeButton("Just Dot", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    WhereToShare = 1;
                    String dirPath = Environment.getExternalStorageDirectory() + "/SimpleMyDot";
                    String fileName = "Screenshot.png";
                    storageImage(getScreenshot(dotView, WhereToShare), dirPath, fileName);
                    shareImage(new File(dirPath, fileName));
                }
            });
            builder.show();


        }

    }

    public Bitmap getScreenshot(View view, int W) {
        Bitmap bm = null;

        View rootView = getActivity().findViewById(android.R.id.content).getRootView();
        if (W == 0) {
            rootView.setDrawingCacheEnabled(true);
            bm = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
        } else if (W == 1) {
            dotView.setDrawingCacheEnabled(true);
            bm = Bitmap.createBitmap(dotView.getDrawingCache());
            dotView.setDrawingCacheEnabled(false);
        }


        Canvas cv = new Canvas(bm);

        cv.drawColor(Color.WHITE);
        rootView.draw(cv);
        return bm;
    }

    public void storageImage(Bitmap bitmap, String dirPath, String fileName) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
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

    public void shareImage(File file) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(intent.EXTRA_STREAM, Uri.fromFile(file));

        startActivity(Intent.createChooser(intent, "Share via.."));
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, 50, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }

}
