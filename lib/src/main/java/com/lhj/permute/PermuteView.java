package com.lhj.permute;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PermuteView extends View implements Permute{
    private static final String TAG = "PermuteView";
    private static final int CON_COLOR = Color.parseColor("#ffffff");
    private static final int BG_COLOR = Color.parseColor("#4E7EAE");
    private int width,height;
    private String content = "P";
    private int conColor;
    private int bgColor;
    private float corner;
    private float textSize;
    private boolean clickEnable;
    private boolean circle;
    private Paint paintContent,paintBg;

    public PermuteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG,"PermuteView");
        init();
    }

    private void init() {
        conColor = CON_COLOR;
        bgColor = BG_COLOR;
        paintContent = new Paint();
        paintContent.setAntiAlias(true);
        paintContent.setTextAlign(Paint.Align.CENTER);
        paintContent.setColor(conColor);
        paintBg = new Paint();
        paintBg.setAntiAlias(true);
        paintBg.setColor(bgColor);
        setClickable(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        textSize = height/1.5f;
        paintContent.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = 0;
        rectF.right = width;
        rectF.bottom = height;
        if(circle){
            float cxy = (width>height?height:width)/2.f;
            canvas.drawCircle(width/2.f,height/2.f,cxy,paintBg);
        }else{
            canvas.drawRoundRect(rectF,corner,corner,paintBg);
        }
        canvas.drawText(content,width/2.f,(height/2.f)+(textSize/2.5f),paintContent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!clickEnable){
            return false;
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            paintBg.setColor(conColor);
            paintContent.setColor(bgColor);
            invalidate();
        }else if(event.getAction() == MotionEvent.ACTION_UP
                ||event.getAction() == MotionEvent.ACTION_CANCEL){
            paintBg.setColor(bgColor);
            paintContent.setColor(conColor);
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
        invalidate();
    }

    @Override
    public void setContentColor(int color) {
        this.conColor = color;
        paintContent.setColor(conColor);
        invalidate();
    }

    @Override
    public void setBackGroundColor(int color) {
        this.bgColor = color;
        paintBg.setColor(bgColor);
        invalidate();
    }

    @Override
    public void setCorner(float corner) {
        this.corner = corner;
        invalidate();
    }

    @Override
    public void setClickEnable(boolean clickEnable) {
        this.clickEnable = clickEnable;
        setClickable(clickEnable);
    }

    @Override
    public void setCircle(boolean circle) {
        this.circle = circle;
        invalidate();
    }

}
