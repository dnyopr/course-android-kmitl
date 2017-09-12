package kmitl.lab03.danaya58070042.simplemydot.model;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by student on 9/12/2017 AD.
 */

class DotSerializable implements Serializable {

    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
