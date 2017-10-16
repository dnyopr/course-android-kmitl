package com.kmitl.danaya58070042.MyLazyinstagram.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kmitl.danaya58070042.MyLazyinstagram.api.UserProfile;
import com.kmitl.danaya58070042.lazyinstagram.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    UserProfile user = new UserProfile();


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);



        Button btn_android = (Button) rootview.findViewById(R.id.acc_android);
        Button btn_nature = (Button) rootview.findViewById(R.id.acc_nature);
        Button btn_cartoon = (Button) rootview.findViewById(R.id.acc_cartoon);

        btn_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(MainFragment.this)
                        .commit();
            }
        });
        btn_nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUser("nature");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(MainFragment.this)
                        .commit();
//                new MainActivity().recall();
            }
        });
        btn_cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUser("cartoon");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(MainFragment.this)
                        .commit();
            }
        });

        return rootview;
    }



    public interface OnFragmentInterfaceListener {
    }
}
