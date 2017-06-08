package org.lion.pop;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mStatusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.hideStatusBar(this);
        mStatusBarHeight = StatusBarUtil.getStatusBarHeight(this);
        setStatusBarHeight(mStatusBarHeight);
        final View bar = findViewById(R.id.bar);

        findViewById(R.id.tv_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setTranslationY(bar.getTranslationY() + 10);
            }
        });

        findViewById(R.id.tv_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setTranslationY(bar.getTranslationY() - 10);
            }
        });

        findViewById(R.id.tv_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = bar.getMeasuredHeight();
                ValueAnimator animator = ValueAnimator.ofInt(h, -mStatusBarHeight, h);
                animator.setDuration(2000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        bar.setTranslationY(-animatedValue);
                    }
                });
                animator.start();
            }
        });
    }

    protected void setStatusBarHeight(int height) {
        View statusBar = findViewById(R.id.status_bar_view);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = height;
        statusBar.setLayoutParams(layoutParams);
    }
}
