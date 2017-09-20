package kmitl.lab03.danaya58070042.simplemydot.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by student on 9/20/2017 AD.
 */

public class Colors {

    private ArrayList<Integer> colorList = new ArrayList<>();

    public Colors() {
        colorList.add(Color.rgb(253,173,150));
        colorList.add(Color.rgb(255,216,209));
        colorList.add(Color.rgb(255,146,165));
        colorList.add(Color.rgb(255,207,145));
        colorList.add(Color.rgb(255,237,153));
        colorList.add(Color.rgb(179,247,154));
        colorList.add(Color.rgb(169,221,199));
        colorList.add(Color.rgb(177,217,242));
        colorList.add(Color.rgb(185,187,226));
    }
    public int getColor() {
        return colorList.get(

                new Random().nextInt(colorList.size()));
    }
}
