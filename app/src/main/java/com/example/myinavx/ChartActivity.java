package com.example.myinavx;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anna on 2017/4/25.
 */

public class ChartActivity extends Activity {

    @Bind(R.id.main_fl_container)
    FrameLayout mFlContainer;
    @Bind(R.id.main_fl_card_back)
    FrameLayout mFlCardBack;
    @Bind(R.id.main_fl_card_front)
    FrameLayout mFlCardFront;

    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private AnimatorSet mRightInSet; // 右入动画
    private AnimatorSet mLeftOutSet; // 左出动画

    private boolean mIsShowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
    }

    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_right_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_left_in);

        mLeftOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_left_out);
        mRightInSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_right_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mFlContainer.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFlContainer.setClickable(true);
            }
        });

        // 设置点击事件
        mLeftOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mFlContainer.setClickable(false);
            }
        });
        mRightInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFlContainer.setClickable(true);
            }
        });
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
    }

    // 翻转卡片
    public void flipCard(View view) {
        // 正面朝上
        if (!mIsShowBack) {
            mRightInSet.setTarget(mFlCardFront);
            mLeftOutSet.setTarget(mFlCardBack);
            mRightInSet.start();
            mLeftOutSet.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOutSet.setTarget(mFlCardBack);
            mLeftInSet.setTarget(mFlCardFront);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
