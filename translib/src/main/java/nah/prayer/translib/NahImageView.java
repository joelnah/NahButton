package nah.prayer.translib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import nah.prayer.translib.status.ClickStat;
import nah.prayer.translib.status.ParentType;

public class NahImageView extends AppCompatImageView {

    private AppCompatImageView view;
    private float scale;
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

    public NahImageView(Context context) {
        super(context);
        init();
    }

    public NahImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
    }

    public NahImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        scale = typeArray.getFloat(R.styleable.NahImageView_image_scale, 1f);
        color = typeArray.getColor(R.styleable.NahImageView_image_colorFilter, Color.TRANSPARENT);
        img = typeArray.getDrawable(R.styleable.NahImageView_image_img);
        imgPress = typeArray.getDrawable(R.styleable.NahImageView_image_imgPress);
        if(img!=null)
            view.setImageDrawable(img);

        model.view = this;
        model.scale = scale;
        model.duration = typeArray.getInt(R.styleable.NahImageView_image_duration, 0);
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

            if(parentType==ParentType.NOTHING){
                if(stat == ClickStat.UP){
                    offSet();
                }
            }else{
                offSet();
            }
            check = false;
        }

    }

    private void offSet(){
        view.setColorFilter(Color.TRANSPARENT);
        trans.setScale(model, stat);
    }



    public void setOnTouchListerer(TransTouchListener listerer){
        this.transTouchListener = listerer;
    }
}
