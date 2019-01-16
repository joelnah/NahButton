package nah.prayer.translib;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.util.Log;

import nah.prayer.translib.status.ClickStat;

class TransAnimation {

    private boolean isMove = true;
    private final int duration = 200;

    void colorAniSingle(final InfoModel model, ClickStat stat) {
        int startColor = 0, endColor = 0;
        switch (stat) {
            case DOWN:
                isMove=true;
                startColor = model.startColor;
                endColor = model.endColor;
                break;
            case MOVE:
                if (isMove) {
                    isMove = !isMove;
                    startColor = model.endColor;
                    endColor = model.startColor;
                } else
                    return;
                break;
            case UP:
                isMove=true;
                startColor = model.endColor;
                endColor = model.startColor;
                break;
        }
        ObjectAnimator.ofObject(model.gradient, "color", new ArgbEvaluator(), startColor, endColor)
                .setDuration(model.duration).start();
    }

    ValueAnimator colorAniGradient(final InfoModel model) {

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        ValueAnimator animator = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(model.duration);
        animator.setRepeatCount(model.count);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float fraction = animation.getAnimatedFraction();
                int newStart = (int) evaluator.evaluate(fraction, model.startColor, model.endColor);
                int newMid = (int) evaluator.evaluate(fraction, model.midColor, model.startColor);
                int newEnd = (int) evaluator.evaluate(fraction, model.endColor, model.midColor);
                int[] colors = {newStart, newMid, newEnd};
                model.gradient.setColors(colors);
            }
        });

        return animator;

    }

    void colorAniGradientEnd(final InfoModel model) {
        final ArgbEvaluator evaluator = new ArgbEvaluator();
        ValueAnimator animator = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(duration);
        animator.setRepeatCount(ValueAnimator.RESTART);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float fraction = animation.getAnimatedFraction();
                int start;
                if (model.upEffect) {
                    start = (int) evaluator.evaluate(fraction, model.startColor, model.effectColor);
                } else {
                    start = (int) evaluator.evaluate(fraction, model.startColor, model.startColor);
                }
                int[] colors = {start, start};
                model.gradient.setColors(colors);
            }
        });

        animator.start();
    }


    void upEffect(final InfoModel model) {
        ObjectAnimator.ofObject(model.gradient, "color", new ArgbEvaluator(), model.effectColor, model.startColor)
                .setDuration(duration).start();
    }

    void setScale(final InfoModel model, ClickStat stat) {
        float scale = 1f;
        switch (stat) {
            case DOWN:
                scale = model.scale;
                isMove = true;
                break;
            case MOVE:
                if (isMove) {
                    isMove = !isMove;
                } else
                    return;
                break;
            case UP:
                isMove = true;
                break;
            default:
                return;
        }
        Log.d("nah", stat.name());
        ObjectAnimator.ofPropertyValuesHolder(model.view,
                PropertyValuesHolder.ofFloat("scaleX", scale),
                PropertyValuesHolder.ofFloat("scaleY", scale))
                .setDuration(duration)
                .start();
    }
}
