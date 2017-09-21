package kmitl.lab03.danaya58070042.simplemydot.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import kmitl.lab03.danaya58070042.simplemydot.R;
import kmitl.lab03.danaya58070042.simplemydot.model.Colors;
import kmitl.lab03.danaya58070042.simplemydot.model.Dot;
import kmitl.lab03.danaya58070042.simplemydot.view.DotView;
import kmitl.lab03.danaya58070042.simplemydot.view.EditView;

/**
 * A simple {@link Fragment} subclass.
 */
public class editFragment extends Fragment {

    private EditView editView;
    private Dot dot;
    private DotView dotView;
    private int width;
    private int height;
    private EditDotFragmentListener listener = null;

    public editFragment() {
        // Required empty public constructor
    }
    public void setListener(EditDotFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_edit, container, false);

        editView = (EditView) rootview.findViewById(R.id.editView);
        dot = getArguments().getParcelable("dot");
        width = getArguments().getInt("width");
        height = getArguments().getInt("height");
        dotView = (DotView) rootview.findViewById(R.id.dotView);

        editView.setColor(dot.getColor());
        editView.setRadius(dot.getRadius());

        Button btnRandColor = (Button) rootview.findViewById(R.id.btnRandColor);
        Button btnRandPosition = (Button) rootview.findViewById(R.id.btnRandPosition);
        Button btnInR = (Button) rootview.findViewById(R.id.btnIncRadius);
        Button btnDeR = (Button) rootview.findViewById(R.id.btnDecRadius);
        Button btnSub = (Button) rootview.findViewById(R.id.btnSubmit);

        btnRandColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dot.setColor(new Colors().getColor());
                editView.setColor(dot.getColor());
                editView.invalidate();
            }
        });

        btnRandPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rm = new Random();
                int centerX = rm.nextInt(width);
                int centerY = rm.nextInt(height);
                dot.setCenterX(centerX);
                dot.setCenterY(centerY);
            }
        });

        btnInR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current= dot.getRadius()+10;
                dot.setRadius(current);
                editView.setRadius(current);
                editView.invalidate();
            }
        });
        btnDeR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current= dot.getRadius()-10;
                dot.setRadius(current);
                editView.setRadius(current);
                editView.invalidate();
            }
        });


        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.EditDotFinished(dot);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(editFragment.this)
                        .commit();
            }
        });



//        dot.setRadius(Integer.valueOf(editRadius.getText().toString()));




//        editRadius.setText();


        return rootview;
    }


    public static editFragment newInstance(Dot dot, mainFragment mainFragment, int width, int height) {

        Bundle args = new Bundle();

        editFragment fragment = new editFragment();
        fragment.setListener(mainFragment);
        args.putParcelable("dot", dot);
        args.putInt("width", width);
        args.putInt("height", height);
        fragment.setArguments(args);
        return fragment;
    }
    public interface EditDotFragmentListener {

        public void EditDotFinished(Dot dot);
    }
}
