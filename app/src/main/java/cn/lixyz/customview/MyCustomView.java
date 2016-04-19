package cn.lixyz.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LGB on 2016/4/19.
 */
public class MyCustomView extends View {

    private Paint paint;
    private ValueAnimator valueAnimator;
    private int backgroundColor;
    private int fontColor = Color.WHITE;
    private int score;
    private int value;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setSocre(int score) {
        init(score);
    }


    private void init(int score) {
        // ValueAnimator用来计算过度值，过度值用来更改背景颜色以及指针的轨迹
        valueAnimator = ValueAnimator.ofInt(0, score);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = (int) animation.getAnimatedValue();
                value = i;
                if (i < 10) {
                    backgroundColor = Color.RED;
                    invalidate();
                } else if (i >= 10 && i < 20) {
                    backgroundColor = Color.BLUE;
                    invalidate();
                } else if (i >= 20 && i < 30) {
                    backgroundColor = Color.RED;
                    invalidate();
                } else if (i >= 30 && i < 40) {
                    backgroundColor = Color.BLUE;
                    invalidate();
                } else if (i >= 40 && i < 50) {
                    backgroundColor = Color.RED;
                    invalidate();
                } else if (i >= 50 && i < 60) {
                    backgroundColor = Color.BLUE;
                    invalidate();
                } else if (i >= 60 && i < 70) {
                    backgroundColor = Color.RED;
                    invalidate();
                } else if (i >= 70 && i < 80) {
                    backgroundColor = Color.BLUE;
                    invalidate();
                } else if (i >= 80 && i < 90) {
                    backgroundColor = Color.RED;
                    invalidate();
                } else if (i >= 90 && i < 100) {
                    backgroundColor = Color.BLUE;
                    invalidate();
                } else if (i == 100) {
                    backgroundColor = Color.RED;
                    invalidate();
                }
            }
        });
        valueAnimator.start();

    }

    public MyCustomView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawColor(backgroundColor);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        canvas.drawLine(0, height / 2, width, height / 2, paint);
        canvas.drawLine(width / 2, 0, width / 2, height, paint);

        // 将圆点移动到屏幕中央
        canvas.translate(width / 2, height / 2);

        // 开始画圆,最外层，第一个圆
        canvas.drawArc(new RectF((0 - width / 4), (0 - width / 4), width / 4, width / 4), 180, 180, true, paint);
        // 第二个圆
        Paint p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(backgroundColor);
        canvas.drawArc(new RectF((0 - width / 4 + 5), (0 - width / 4 + 5), width / 4 - 5, width / 4 - 5), 180, 180,
                true, p2);
        // 第三个圆
        Paint p3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p3.setColor(Color.argb(255, 255, 240, 255));
        canvas.drawArc(new RectF((0 - width / 4 + 30), (0 - width / 4 + 30), width / 4 - 30, width / 4 - 30), 180, 180,
                true, p3);
        // 第四个圆
        Paint p4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p4.setColor(backgroundColor);
        canvas.drawArc(new RectF((0 - width / 4 + 55), (0 - width / 4 + 55), width / 4 - 55, width / 4 - 55), 180, 180,
                true, p4);

        // 开始画刻度
        Paint p5 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p5.setColor(Color.RED);
        Paint p6 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p6.setColor(Color.BLUE);
        p6.setTextSize(15);

        int count = 11;// 总刻度数目
        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                canvas.drawLine((0 - width / 4 + 5), 0, (0 - width / 4 + 30), 0, p5);
                canvas.drawText(String.valueOf(i * 10), (0 - width / 4 + 30), 0, p6);
            } else {
                canvas.drawLine((0 - width / 4 + 15), 0, (0 - width / 4 + 30), 0, p5);
                canvas.drawText(String.valueOf(i * 10), (0 - width / 4 + 30), 0, p6);
            }
            canvas.rotate(18);
        }

        canvas.rotate(-198);

        // 画最外层的指针
        Paint pp = new Paint(Paint.ANTI_ALIAS_FLAG);
        pp.setColor(Color.WHITE);
        pp.setStrokeWidth(5);
        for (int i = 0; i < value; i++) {
            canvas.drawPoint((0 - width / 4), 0, pp);
            canvas.rotate(1.8f);
        }
        canvas.rotate(0 - (value * 1.8f));
        // 画数字
        Paint ppp = new Paint(Paint.ANTI_ALIAS_FLAG);
        ppp.setTextSize(width / 10);

        Paint.FontMetrics fontMetrics = ppp.getFontMetrics();
        if (value < 10) {
            canvas.drawText(value + "", fontMetrics.top / 4, 0, ppp);
        } else {
            canvas.drawText(value + "", fontMetrics.top / 2, 0, ppp);
        }
    }
}
