package com.sds.tigher;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Joker on 2017/3/15.
 */

public class MutiProgressView extends View {

    float width;
    float mutiRadius;

    Paint paint;

    Context context;
    /**
     * 节点间隔
     */
    int nodeSpace;

    /**
     * 边距
     */
    int left = 20;
    int top = 30;

    int dWidth;
    private Paint mPaint;
    private TextPaint textPaint;


    public MutiProgressView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MutiProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MutiProgressView);
        width = typedArray.getDimension(R.styleable.MutiProgressView_width, 5);
        mutiRadius = typedArray.getDimension(R.styleable.MutiProgressView_mutiRadius, 10);
        init();
    }

    public MutiProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.gray_color));
        paint.setAntiAlias(true);

        nodeSpace = dip2px(context, 80);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));

        textPaint = new TextPaint();
        textPaint.setTextSize(45);
        textPaint.setAntiAlias(true);
    }

    private int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    MutiProgressAdapter mutiProgressAdapter;

    public void setMutiProgressAdapter(MutiProgressAdapter mutiProgressAdapter) {
        this.mutiProgressAdapter = mutiProgressAdapter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mutiProgressAdapter == null || mutiProgressAdapter.getCount() == 0)
            return;
        setMeasuredDimension(widthMeasureSpec, mutiProgressAdapter.getCount() * nodeSpace + top);

        dWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mutiProgressAdapter == null || mutiProgressAdapter.getCount() == 0)
            return;
        List<String> data = mutiProgressAdapter.getData();
        canvas.drawRect((dWidth - width) / 2, top + nodeSpace / 3, (dWidth + width) / 2, mutiProgressAdapter.getCount() * nodeSpace + top, paint);
        for (int i = 0; i < mutiProgressAdapter.getCount(); i++) {
            if (i % 2 == 0) {
                if (i == 0) {
                    textPaint.setColor(getResources().getColor(R.color.colorAccent));
                    StaticLayout layout = new StaticLayout(data.get(i), textPaint, (int) (dWidth * 0.4), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                    canvas.save();
                    canvas.translate(left * 2 + mutiRadius * 2 + 10, i * nodeSpace + top + (nodeSpace / 4));
                    layout.draw(canvas);
                    canvas.restore();

                    canvas.drawCircle(dWidth / 2, i * nodeSpace + top + nodeSpace / 3, mutiRadius + 2, mPaint);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeWidth(8);
                    mPaint.setAlpha(88);
                    canvas.drawCircle(dWidth / 2, i * nodeSpace + top + nodeSpace / 3, mutiRadius + 4, mPaint);
                } else {
                    canvas.drawCircle(dWidth / 2, i * nodeSpace + top + nodeSpace / 3, mutiRadius, paint);
                    textPaint.setColor(getResources().getColor(R.color.black));
                    StaticLayout layout = new StaticLayout(data.get(i), textPaint, (int) (dWidth * 0.4), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                    canvas.save();
                    canvas.translate(left * 2 + mutiRadius * 2 + 10, i * nodeSpace + top + (nodeSpace / 4));
                    layout.draw(canvas);
                    canvas.restore();
                }
            } else {
                canvas.drawCircle(dWidth / 2, i * nodeSpace + top + nodeSpace / 3, mutiRadius, paint);
                textPaint.setColor(getResources().getColor(R.color.black));
                StaticLayout layout = new StaticLayout(data.get(i), textPaint, (int) (dWidth * 0.4), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(dWidth / 2 + left * 2 + mutiRadius * 2 + 10, i * nodeSpace + top + (nodeSpace / 4));
                layout.draw(canvas);
                canvas.restore();
            }

        }

    }

    public interface MutiProgressAdapter {

        int getCount();

        List<String> getData();

    }
}
