package layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kmitl.lab05.danaya58070042.labworkfragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public static BlankFragment newInstance(String message) {
        // Required empty public constructor
        Bundle args = new Bundle();
        BlankFragment fragment = new BlankFragment();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;

    }

    String message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        TextView fragmentTextView = (TextView) rootView.findViewById(R.id.fragmentTextView);

        if(!message.isEmpty()){
            fragmentTextView.setText(message);
        }


//        return inflater.inflate(R.layout.fragment_blank, container, false);
        return rootView;
    }

}
