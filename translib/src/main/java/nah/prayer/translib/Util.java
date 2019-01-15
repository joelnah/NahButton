package nah.prayer.translib;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import nah.prayer.translib.status.ParentType;

class Util {
    private Rect outRect = new Rect();
    private int[] location = new int[2];
    private boolean check = true;

    void setLocation(View view){
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
    }
    boolean isViewInBounds(int x, int y){
        return outRect.contains(x, y);
    }

    boolean isViewInBounds(View view, int x, int y){
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

    ParentType getParentType(ViewParent parent){
        if(parent==null){
            return ParentType.NOTHING;
        }else{
            ViewParent tmp = parent;

            if(tmp instanceof ViewGroup){
                while (check) {
                    tmp = tmp.getParent();
                    if (tmp != null) {
                        if (tmp instanceof NahScrollViewV) {
                            check = false;
                            return ParentType.V;
                        } else if (tmp instanceof NahScrollViewH) {
                            check = false;
                            return ParentType.H;
                        }

                    } else {
                        check = false;
                        return ParentType.NOTHING;
                    }

                }
            }

        }
        return ParentType.NOTHING;
    }


}
