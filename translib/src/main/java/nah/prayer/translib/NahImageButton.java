package nah.prayer.translib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NahImageButton extends AppCompatImageButton {

    private AppCompatImageButton view;
    private float scale = 0.9f;
    private int color;
    private Drawable img, imgPress;
    private Util util;
    private TransTouchListener transTouchListener;

    public NahImageButton(Context context) {
        super(context);
        init();
    }

    public NahImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
    }

    public NahImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getAttrs(attrs, defStyleAttr);
    }


    private void init() {
        view = this;
        view.setClickable(true);
        util = new Util();
        util.setLocation(view);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahImageView);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahImageView, defStyleAttr, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typeArray){
        scale = typeArray.getFloat(R.styleable.NahImageView_imageview_scale, scale);
        color = typeArray.getColor(R.styleable.NahImageView_imageview_colorFilter, Color.TRANSPARENT);
        img = typeArray.getDrawable(R.styleable.NahImageView_imageview_img);
        imgPress = typeArray.getDrawable(R.styleable.NahImageView_imageview_imgPress);

        if(img!=null)
            view.setImageDrawable(img);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int x = (int)event.getRawX();
        int y = (int)event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                util.isViewInBounds(view, x, y);
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
                if(util.isViewInBounds(view, x, y)) {
                    if (transTouchListener != null) {
                        transTouchListener.onTouch();
                    }
                }
                setView(false);
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    private void setView(boolean bool){
        if(bool){
            if(imgPress!=null)
                view.setImageDrawable(imgPress);
            view.setColorFilter(color);
            this.setScaleX(scale);
            this.setScaleY(scale);
        }else{
            if(img!=null)
                view.setImageDrawable(img);
            view.setColorFilter(Color.TRANSPARENT);
            this.setScaleX(1f);
            this.setScaleY(1f);
        }

    }



    public void setOnTouchListerer(TransTouchListener listerer){
        this.transTouchListener = listerer;
    }
}
