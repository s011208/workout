package bj4.yhh.workout.views;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yenhsunhuang on 2016/5/12.
 */
public class SimpleTextView extends View {
    private Layout mLayout;

    public SimpleTextView(Context context) {
        this(context, null);
    }

    public SimpleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long t1 = System.currentTimeMillis();
        super.onDraw(canvas);
        canvas.save();
        if (mLayout != null) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
            mLayout.draw(canvas);
        }

        canvas.restore();
        long t2 = System.currentTimeMillis();
        Log.i("TEST", "onDraw::" + (t2 - t1));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        long t1 = System.currentTimeMillis();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mLayout != null) {
            setMeasuredDimension(
                    getPaddingLeft() + getPaddingRight() + mLayout.getWidth(),
                    getPaddingTop() + getPaddingBottom() + mLayout.getHeight());
        }
        long t2 = System.currentTimeMillis();
        Log.i("TEST", "onMeasure::" + (t2 - t1));
    }


    public void setTextLayout(Layout layout) {
        mLayout = layout;
        requestLayout();
    }
}
