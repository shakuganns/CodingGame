package com.example.phonebook2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by 圣麟 on 2015/5/23.
 */
public class SideBar extends View {
    public static final String TAG = SideBar.class.getName();
    private String[] letters;
    private OnLetterTouchListener letterTouchListener;

    public SideBar(Context context) {
        super(context);
        init(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context){

    }

    /**
     * 每一项的高度
     */
    private float itemHeight = -1;
    private Paint paint;
    private Bitmap letterBitmap;
    @Override
    protected void onDraw(Canvas canvas) {
        if(letters == null){
            return ;
        }
        if(itemHeight == -1){
            itemHeight = getHeight() /letters.length;
        }
        if(paint == null){
            paint = new Paint();
            paint.setTextSize(itemHeight - 4);

            paint.setColor(getResources().getColor(R.color.white));
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);

            Canvas mCanvas = new Canvas();
            letterBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                    Bitmap.Config.ARGB_8888);
            mCanvas.setBitmap(letterBitmap);
            float widthCenter = getMeasuredWidth() / 2.0F;
            for(int i = 0 ; i < letters.length; i ++){
                mCanvas.drawText(letters[i], widthCenter-paint.measureText(letters[i])/2,
                        itemHeight * i +itemHeight,paint);
            }
        }
        if(letterBitmap != null){
            canvas.drawBitmap(letterBitmap,0,0,paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(letterTouchListener == null || letters == null){
            return false;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                Log.v(TAG, "action down or move");
                int position = (int) (event.getY() / itemHeight + 1);
                if(position >= 0 && position < letters.length){
                    letterTouchListener.onLetterTouch(letters[position],position);
                }
                return true;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_UP:
                Log.v(TAG, "action up");
                letterTouchListener.onActionUp();
                return true;
        }
        return false;
    }

    /**
     * 设置显示在边栏上的字母
     */
    public void setShowString(String[] letters){
        this.letters = letters;
    }

    /**
     * 设置点击某个字母的时候
     */
    public void setOnLetterTouchListener (OnLetterTouchListener listener){
        this.letterTouchListener = listener;
    }

    public interface OnLetterTouchListener{

        /**
         * 某个字母被按下的时候
         */
        public void onLetterTouch(String letter, int position);

        /**
         * 触控手指离开的时候
         */
        public void onActionUp();
    }

}
