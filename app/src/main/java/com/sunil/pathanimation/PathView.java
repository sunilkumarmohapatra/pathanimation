package com.sunil.pathanimation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PathView extends View {
    Path path;
    Paint paint;
    float length;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        path.moveTo(180, 400);
        path.lineTo(280, 100);
        path.lineTo(880, 100);
        path.lineTo(880, 100);

        path.lineTo(980, 400);
        path.lineTo(180, 400);
        path.lineTo(180, 800);
        path.lineTo(980, 800);
        path.lineTo(980, 400);

        path.moveTo(520, 800);
        path.lineTo(520, 600);
        path.lineTo(720, 600);
        path.lineTo(720, 800);

        path.moveTo(540, 800);
        path.lineTo(540, 620);
        path.lineTo(700, 620);
        path.lineTo(700, 800);

        path.moveTo(240, 480);
        path.lineTo(340, 480);
        path.lineTo(340, 540);
        path.lineTo(240, 540);
        path.lineTo(240, 480);

        path.moveTo(280, 100);
        path.lineTo(400, 400);

        path.moveTo(480, 100);
        path.lineTo(600, 400);

        path.moveTo(680, 100);
        path.lineTo(800, 400);

        path.moveTo(315, 200);
        path.lineTo(910, 200);

        path.moveTo(360, 300);
        path.lineTo(940, 300);

        path.moveTo(400, 400);
        path.lineTo(400, 800);

        // Measure the path
        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();
        float[] intervals = new float[]{length, length};
        ObjectAnimator animator = ObjectAnimator.ofFloat(PathView.this, "phase", 1.0f, 0.0f);
        animator.setDuration(5000);
        animator.start();
    }

    public void setPhase(float phase) {
        Log.d("pathview", "setPhase called with:" + String.valueOf(phase));
        paint.setPathEffect(createPathEffect(length, phase, 0.0f));
        invalidate();
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[]{pathLength, pathLength},
                Math.max(phase * pathLength, offset));
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawPath(path, paint);
    }
}