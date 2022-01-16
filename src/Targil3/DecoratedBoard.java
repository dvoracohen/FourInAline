package Targil3;

public class DecoratedBoard extends BoardDecorator {
    public DecoratedBoard()
    {
        super();
    }

    // Imagine that there is a database of images, here the index of the selected image is kept
    public int backgroundNum;

    public void setBackgroundNum(int backgroundNum) {
        this.backgroundNum = backgroundNum;
    }
}
