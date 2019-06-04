package com.meitu.sww.testRunText;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.meitu.sww.testform.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 跑马灯
 */
public class MarqueeView extends ViewFlipper {

    // 事件间隔
    private int interval = 2000;
    private int textSize = 14;
    private int textColor = 0xff000000;
    private boolean singleLine = true;
    private int gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
    @AnimRes
    private int inAnimResId = R.anim.anim_right_in;
    @AnimRes
    private int outAnimResId = R.anim.anim_left_out;

    // 播放组合的位置position
    private int position;
    private List<CharSequence> messages = new ArrayList<>();

    private boolean isAnimStart = false;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFlipInterval(interval);
    }

    /**
     * 根据字符串列表，启动翻页公告
     * @param messages     字符串列表
     */
    public void startWithList(List<CharSequence> messages) {
        this.messages = getTestList();
        post(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
    }

    private void start() {
        removeAllViews();
        clearAnimation();
        // 检测数据源
        if (messages == null || messages.isEmpty()) {
            throw new RuntimeException("The messages cannot be empty!");
        }
        position = 0;
        for (int index = 0; index < messages.size(); index++) {
            addView(createTextView(messages.get(index)));
        }
//        addView(createTextView(messages.get(position)));

        if (messages.size() > 1) {
            setInAndOutAnimation(inAnimResId, outAnimResId);
            startFlipping();
        }

        if (getInAnimation() != null) {
            getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    animation.cancel();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    View view = getChildAt(0);
                    removeViewAt(0);
                    addView(view);
                    /*position++;
                    if (position >= messages.size()) {
                        position = 0;
                    }
                    View view = createTextView(messages.get(position));
                    if (view.getParent() == null) {
                        addView(view);
                    }
                    isAnimStart = false;*/
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    private TextView createTextView(CharSequence marqueeItem) {
//        TextView textView = (TextView) getChildAt((getDisplayedChild() + 1) % 3);
        TextView textView;
        textView = new TextView(getContext());
        textView.setGravity(gravity | Gravity.CENTER_VERTICAL);
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);
        textView.setIncludeFontPadding(true);
        textView.setSingleLine(singleLine);
        if (singleLine) {
            textView.setMaxLines(1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        textView.setText(marqueeItem);
        return textView;
    }

    /**
     * 设置进入动画和离开动画
     *
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    private void setInAndOutAnimation(@AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        Animation inAnim = AnimationUtils.loadAnimation(getContext(), inAnimResId);
//        inAnim.setDuration(animDuration);
        inAnim.setInterpolator(new LinearInterpolator());
        setInAnimation(inAnim);
        Animation outAnim = AnimationUtils.loadAnimation(getContext(), outAnimResID);
        outAnim.setInterpolator(new LinearInterpolator());
        setOutAnimation(outAnim);
    }

    private List<CharSequence> getTestList() {
        List<CharSequence> list = new ArrayList<>();
        list.add("1、MarqueeView");
        list.add("2、滚动");
        list.add("3、从右到左");
        list.add("4、哈哈");
        return list;
    }

}
