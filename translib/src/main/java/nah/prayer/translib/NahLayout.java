package nah.prayer.translib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class NahLayout extends FrameLayout {

    private View view;
    private float scale;
    private Util util;

    private TransTouchListener transTouchListener;


    public NahLayout(Context context) {
        super(context);
        init();
    }

    public NahLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
    }

    public NahLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getAttrs(attrs, defStyleAttr);
    }


    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.trans_layout, this, false);
        addView(view);
        view.setClickable(true);
        util = new Util();
        util.setLocation(view);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahLayout);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahLayout, defStyleAttr, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typeArray){

        scale = typeArray.getFloat(R.styleable.NahLayout_layout_scale, 1f);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int x = (int)event.getRawX();
        int y = (int)event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                util.isViewInBounds(this, x, y);
                setView(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if(util.isViewInBounds(x, y)) {
                    setView(true);
                }else{
                    setView(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(util.isViewInBounds(this, x, y)) {
                    if (transTouchListener != null) {
                        transTouchListener.onTouch();
                    }
                }
                setView(false);
                break;
        }

        return view.dispatchTouchEvent(event);
    }


    private void setView(boolean bool){
        if(bool){
            this.setScaleX(scale);
            this.setScaleY(scale);
        }else{
            this.setScaleX(1f);
            this.setScaleY(1f);
        }

    }

    public void setOnTouchListerer(TransTouchListener listerer){
        this.transTouchListener = listerer;
    }
}
