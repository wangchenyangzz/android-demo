package cn.yy.demo.view.custom;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 派对悬浮窗动画
 */
public class AudioDrawable extends Drawable implements Animatable {
    private static final float SCALE = 1.0f;

    @NonNull
    private Paint mPaint;

    @NonNull
    private ValueAnimator mAnimator;
    private int width, height;

    @NonNull
    private float[] scaleYFloats = new float[]{SCALE, SCALE, SCALE, SCALE, SCALE};

    private final float STATE_VALUE_START = 1f;
    private final float STATE_VALUE_END = .3f;
    private final float HALF_PROGRESS = .5f;
    private final float WHOLE_PROGRESS = 1f;

    private int lineCount;

    public AudioDrawable() {
        this(3);
    }

    public AudioDrawable(int lineCount) {
        this.lineCount = lineCount;
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        float[] delays = new float[]{0.5f, 0.25f, 0, 0.25f, 0.5f};
        mAnimator = ValueAnimator.ofFloat(1f, 0.3f, 1f);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        FloatEvaluator evaluator = new FloatEvaluator();

        mAnimator.addUpdateListener(animation -> {
            float fraction, value, delay;
            for (int i = 0; i < lineCount; i++) {
                delay = delays[i];
                fraction = animation.getAnimatedFraction() - delay;
                if (fraction < 0) {
                    fraction += WHOLE_PROGRESS;
                }
                if (fraction == HALF_PROGRESS) {
                    value = STATE_VALUE_END;
                } else if (fraction < HALF_PROGRESS) {
                    value = evaluator.evaluate(fraction * 2, STATE_VALUE_START, STATE_VALUE_END);
                } else {
                    value = evaluator.evaluate(fraction * 2 - WHOLE_PROGRESS, STATE_VALUE_END, STATE_VALUE_START);
                }
                scaleYFloats[i] = value;
            }
            invalidateSelf();
        });
    }


    @Override
    public void start() {
        mAnimator.start();
    }

    @Override
    public void stop() {
        mAnimator.cancel();
    }


    @Override
    public boolean isRunning() {
        return mAnimator.isRunning();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        width = bounds.width();
        height = bounds.height();
    }

    private final RectF rectF = new RectF();

    @Override
    public void draw(@NonNull Canvas canvas) {
        float translateX = width / 11f;
        float translateY = height / 2f;
        for (int i = 0; i < lineCount; i++) {
            canvas.save();
            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
            canvas.scale(SCALE, scaleYFloats[i]);

            rectF.left = -translateX / (lineCount <= 3 ? 2f : 3f);
            rectF.top = -height / 2.5f;
            rectF.right = translateX / (lineCount <= 3 ? 2f : 3f);
            rectF.bottom = height / 2.5f;

            canvas.drawRoundRect(rectF, 5, 5, mPaint);
            canvas.restore();
        }
    }


    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
