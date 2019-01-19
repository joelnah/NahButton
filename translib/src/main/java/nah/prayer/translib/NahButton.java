package nah.prayer.translib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import nah.prayer.translib.helper.DrawableEnriched;
import nah.prayer.translib.helper.NahDrawableHelper;
import nah.prayer.translib.status.AnimationType;
import nah.prayer.translib.status.ClickStat;
import nah.prayer.translib.status.ParentType;

public class NahButton extends AppCompatButton  implements DrawableEnriched {

    private NahDrawableHelper mNahDrawableHelper;

    private AppCompatButton view;
    private GradientDrawable bgShape;
    private float scale;
    private int startColor;
    private int endColor;
    private int labelColor;
    private int labelColorClick;
    private boolean upEffect;
    private TransAnimation trans;
    private InfoModel model;
    private ValueAnimator animator;
    private ClickStat stat;
    private AnimationType aniType;
    private ParentType parentType;
    boolean check;
    private boolean isClick=false;
    int downX,downY;
    private Util util;

    private TransTouchListener transTouchListener;


    public NahButton(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public NahButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
        getAttrs(attrs);
    }

    public NahButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
        getAttrs(attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        view = this;
        view.setAllCaps(false);
        view.setBackgroundResource(R.drawable.rectangle_button);
        util = new Util();
        util.setLocation(view);
        trans = new TransAnimation();
        model = new InfoModel();

        mNahDrawableHelper = new NahDrawableHelper(context, attrs, defStyleAttr, defStyleRes);
        mNahDrawableHelper.apply(this);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahButton);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NahButton, defStyleAttr, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typeArray){
        int round = typeArray.getInt(R.styleable.NahButton_nah_round, 0);
        int effectColor = typeArray.getInt(R.styleable.NahButton_nah_effectColor, Color.WHITE);
        int midColor = typeArray.getInt(R.styleable.NahButton_nah_midColor, Color.WHITE);
        endColor = typeArray.getInt(R.styleable.NahButton_nah_endColor, Color.DKGRAY);
        int duration = typeArray.getInt(R.styleable.NahButton_nah_duration, 0);
        int labelSize = typeArray.getInt(R.styleable.NahButton_nah_labelSize, 10);
        int labelStyle = typeArray.getInt(R.styleable.NahButton_nah_labelStyle, 0);
        view.setTextSize(labelSize);
        String labelText = typeArray.getString(R.styleable.NahButton_nah_labelText);

        bgShape = (GradientDrawable)view.getBackground();


        float[]r = new float[8];
        for(int i = 0 ; i < r.length ;i++ ){
            r[i] = (float)round;
        }
        bgShape.setCornerRadii(r);

        startColor = typeArray.getInt(R.styleable.NahButton_nah_startColor, Color.GRAY);
        bgShape.setColor(startColor);

        int stroke = typeArray.getInt(R.styleable.NahButton_nah_stroke, 0);
        int strokeColor = typeArray.getInt(R.styleable.NahButton_nah_strokeColor, Color.GRAY);
        bgShape.setStroke(stroke, strokeColor);

        scale = typeArray.getFloat(R.styleable.NahButton_nah_scale, 1f);
        view.setText(labelText);

        labelColor = typeArray.getInt(R.styleable.NahButton_nah_labelColor, Color.BLACK);
        view.setTextColor(labelColor);

        labelColorClick = typeArray.getInt(R.styleable.NahButton_nah_labelColorClick, labelColor);

        upEffect = typeArray.getBoolean(R.styleable.NahButton_nah_upEffect, false);


        switch (labelStyle){
            case 0:
                view.setTypeface(null, Typeface.NORMAL);
                break;
            case 1:
                view.setTypeface(null, Typeface.BOLD);
                break;
            case 2:
                view.setTypeface(null, Typeface.ITALIC);
                break;
        }




        switch (typeArray.getInt(R.styleable.NahButton_nah_animationType, 2)){
            case 0:
                aniType = AnimationType.GRADIENT;
                break;
            case 1:
                aniType = AnimationType.SINGLE;
                break;
            case 2:
                aniType = AnimationType.NONE;
                break;
        }

        model.view = view;
        model.gradient = bgShape;
        model.duration = duration;
        model.effectColor = effectColor;
        model.startColor = startColor;
        model.midColor = midColor;
        model.endColor = endColor;
        model.round = round;
        model.scale = scale;
        model.labelColor = labelColor;
        model.labelColorClick = labelColorClick;
        model.upEffect = upEffect;
        switch (typeArray.getInt(R.styleable.NahButton_nah_count, ValueAnimator.RESTART)){
            case 0:
                model.count = ValueAnimator.INFINITE;
                break;
            case 1:
                model.count = ValueAnimator.RESTART;
                break;
            case 2:
                model.count = ValueAnimator.REVERSE;
                break;
        }

        switch (aniType){
            case GRADIENT:
                animator = trans.colorAniGradient(model);
                break;
        }

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
                downX = x;
                downY = y;
                stat = ClickStat.DOWN;
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
            if(stat == ClickStat.DOWN) {

                switch (aniType){
                    case GRADIENT:
                        trans.setScale(model, stat);
                        if (!animator.isRunning())
                            animator.start();
                        break;
                    case SINGLE:
                        trans.setScale(model, stat);
                        trans.colorAniSingle(model,stat);
                        break;
                    case NONE:
                        this.setScaleX(scale);
                        this.setScaleY(scale);
                        bgShape.setColor(endColor);
                        break;
                }
                isClick = true;
            }
            this.setTextColor(labelColorClick);
            check = true;
        }else{
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

        if(isClick) {
            isClick = false;
            switch (aniType) {
                case GRADIENT:
                    trans.setScale(model, stat);
                    if (animator != null) {
                        animator.end();
                    }
                    trans.colorAniGradientEnd(model);
                    break;
                case SINGLE:
                    trans.setScale(model, stat);
                    trans.colorAniSingle(model, stat);
                    break;
                case NONE:
                    this.setScaleX(1f);
                    this.setScaleY(1f);
                    bgShape.setColor(startColor);
                    break;
            }

            if (upEffect && aniType != AnimationType.GRADIENT) {

                trans.upEffect(model);
            }

            this.setTextColor(labelColor);
        }
    }

    public void setOnTouchListerer(TransTouchListener listerer){
        this.transTouchListener = listerer;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getCompoundDrawableHeight() {
        return mNahDrawableHelper.getCompoundDrawableHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCompoundDrawableWidth() {
        return mNahDrawableHelper.getCompoundDrawableWidth();
    }

    @Override
    public void setDrawableStartVectorId(@DrawableRes int id) {
        mNahDrawableHelper.setDrawableStartVectorId(id);
        mNahDrawableHelper.apply(this);
    }

    @Override
    public void setDrawableEndVectorId(@DrawableRes int id) {
        mNahDrawableHelper.setDrawableEndVectorId(id);
        mNahDrawableHelper.apply(this);
    }

    @Override
    public void setDrawableTopVectorId(@DrawableRes int id) {
        mNahDrawableHelper.setDrawableTopVectorId(id);
        mNahDrawableHelper.apply(this);
    }

    @Override
    public void setDrawableBottomVectorId(@DrawableRes int id) {
        mNahDrawableHelper.setDrawableBottomVectorId(id);
        mNahDrawableHelper.apply(this);
    }
}
