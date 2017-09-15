package kmitl.lab05.danaya58070042.labworkfragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import layout.BlankFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        getFragmentManager(); //android
        */
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager
//                .beginTransaction()
//                .add(R.id.fragmentContainer, new BlankFragment().newInstance("Activity From Access"))
//                .addToBackStack("arrayName")
//                .commit();

        Button accessFragment = (Button) findViewById(R.id.fragmentAccess);

        accessFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.fragmentContainer, new BlankFragment().newInstance("Activity From Access"))
                        .addToBackStack("arrayName")
                        .commit();
            }
        });
    }
}
