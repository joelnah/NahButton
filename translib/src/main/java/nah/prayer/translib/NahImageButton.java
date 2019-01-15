package nah.prayer.translib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import nah.prayer.translib.status.ClickStat;
import nah.prayer.translib.status.ParentType;

public class NahImageButton extends AppCompatImageButton {

    private AppCompatImageButton view;
    private float scale = 0.9f;
    private int color;
    private Drawable img, imgPress;
    private Util util;
    private ParentType parentType;
    boolean check;
    int downX,downY;
    private TransAnimation trans;
    private InfoModel model;
    private ClickStat stat;
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
        trans = new TransAnimation();
        model = new InfoModel();
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

        model.view = this;
        model.scale = scale;
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        parentType = util.getParentType(getParent());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int x = (int)event.getRawX();
        int y = (int)event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                stat = ClickStat.DOWN;
                downX = x;
                downY = y;
                util.isViewInBounds(view, x, y);
                setView(true);
                break;
            case MotionEvent.ACTION_MOVE:
                stat = ClickStat.MOVE;
                switch (parentType){
                    case H:
                        if(Math.abs(x-downX)>30 || !util.isViewInBounds(this, x, y))
                            setView(false);
                        break;
                    case V:
                        if(Math.abs(y-downY)>30 || !util.isViewInBounds(this, x, y))
                            setView(false);
                        break;
                    case NOTHING:
                        if(util.isViewInBounds(x, y)) {
                            setView(true);
                        }else{
                            setView(false);
                        }
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                stat = ClickStat.UP;
                if(check) {
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
            if(stat == ClickStat.DOWN)
                trans.setScale(model, stat);
            check = true;
            view.setColorFilter(color);
        }else{
            if(img!=null)
                view.setImageDrawable(img);
            if(stat == ClickStat.UP)
                trans.setScale(model, stat);
            trans.setScale(model, stat);
            check = false;
            view.setColorFilter(Color.TRANSPARENT);
        }

    }



    public void setOnTouchListerer(TransTouchListener listerer){
        this.transTouchListener = listerer;
    }
}
