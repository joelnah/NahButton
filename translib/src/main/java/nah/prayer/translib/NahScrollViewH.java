package nah.prayer.translib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class NahScrollViewH extends HorizontalScrollView {

    public NahScrollViewH(Context context) {
        super(context);
    }

    public NahScrollViewH(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NahScrollViewH(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_MOVE:
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_CANCEL:
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_UP:
                return false;

            default:  break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return true;
    }
}