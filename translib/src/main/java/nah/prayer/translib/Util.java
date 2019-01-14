package nah.prayer.translib;

import android.graphics.Rect;
import android.view.View;

public class Util {
    Rect outRect = new Rect();
    int[] location = new int[2];

    public void setLocation(View view){
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
    }
    public boolean isViewInBounds(int x, int y){
        return outRect.contains(x, y);
    }

    public boolean isViewInBounds(View view, int x, int y){
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

}
