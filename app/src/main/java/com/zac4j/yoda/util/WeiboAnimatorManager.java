package com.zac4j.yoda.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import com.zac4j.yoda.R;

public class WeiboAnimatorManager {

    public static void playHeartAnimator(ImageView heartView) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(heartView, "scaleX", 0.2f, 1.2f);
        bounceAnimX.setDuration(300);
        bounceAnimX.setInterpolator(new OvershootInterpolator());

        ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(heartView, "scaleY", 0.2f, 1.2f);
        bounceAnimY.setDuration(300);
        bounceAnimY.setInterpolator(new OvershootInterpolator());
        bounceAnimY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                heartView.setImageResource(R.drawable.ic_weibo_liked);
            }
        });
        animatorSet.play(bounceAnimX).with(bounceAnimY);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                heartView.setScaleX(1.f);
                heartView.setScaleY(1.f);
            }
        });
        animatorSet.start();
    }

}
