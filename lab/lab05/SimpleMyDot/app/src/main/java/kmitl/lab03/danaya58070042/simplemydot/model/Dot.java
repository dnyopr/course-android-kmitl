package kmitl.lab03.danaya58070042.simplemydot.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

public class Dot implements Parcelable{



    private int centerX;
    private int centerY;
    private int radius;
    private int color;



    public Dot(int centerX, int centerY, int radius, int color) {

        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;

    }



    public int getColor() { return color; }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
