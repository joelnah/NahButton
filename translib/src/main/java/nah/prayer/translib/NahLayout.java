package nah.prayer.translib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import nah.prayer.translib.status.ClickStat;
import nah.prayer.translib.status.ParentType;

public class NahLayout extends FrameLayout {

    private View view;
    private float scale;
    private Util util;
    private ParentType parentType;
    private TransAnimation trans;
    private InfoModel model;
    private ClickStat stat;
    boolean check;
    int downX, downY;
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
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.trans_layout, this, false);
        addView(view);
        view.setClickable(true);
        util = new Util();
        util.setLocation(view);
        trans = new TransAnimation();
        model = new InfoModel();
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahLayout);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahLayout, defStyleAttr, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typeArray) {

        scale = typeArray.getFloat(R.styleable.NahLayout_layout_scale, 1f);

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

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stat = ClickStat.DOWN;
                downX = x;
                downY = y;
                util.isViewInBounds(this, x, y);

                setView(true);
                break;
            case MotionEvent.ACTION_MOVE:
                stat = ClickStat.MOVE;
                switch (parentType) {
                    case H:
                        if (Math.abs(x - downX) > 30 || !util.isViewInBounds(this, x, y))
                            setView(false);
                        break;
                    case V:
                        if (Math.abs(y - downY) > 30 || !util.isViewInBounds(this, x, y))
                            setView(false);
                        break;
                    case NOTHING:
                        if (util.isViewInBounds(x, y)) {
                            setView(true);
                        } else {
                            setView(false);
                        }
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                stat = ClickStat.UP;
                if (check) {
                    if (transTouchListener != null) {
                        transTouchListener.onTouch();
                    }
                }
                setView(false);
                break;
        }

        return view.dispatchTouchEvent(event);
    }


    private void setView(boolean bool) {
        if (bool) {
            check = true;
            trans.setScale(model, stat);
        } else {
            check = false;
            trans.setScale(model, stat);
        }

    }

    public void setOnTouchListerer(TransTouchListener listerer) {
        this.transTouchListener = listerer;
    }
}
