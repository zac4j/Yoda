package com.zac4j.yoda.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.util.manager.WeiboAnimatorManager;

/**
 * Created by zac on 2018/5/8.
 * Description:Weibo Zan Widget
 */
public class HeartView extends LinearLayout {

    @BindView(R.id.weibo_heart_view_heart)
    ImageView mHeartView;
    @BindView(R.id.weibo_heart_view_counter)
    TextSwitcher mLikeCounter;

    private Context mContext;
    private long mLikeCount;
    private boolean mIsLike;

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.layout_heart_view, this, true);
        ButterKnife.bind(view);
    }

    public void updateLikeState() {
        if (mIsLike) {
            mHeartView.setImageResource(R.drawable.ic_weibo_like);
        } else {
            WeiboAnimatorManager.playHeartAnimator(mHeartView);
        }
        mIsLike = !mIsLike;
        updateLikeCounter(mIsLike);
    }

    public void setLike(boolean like) {
        mHeartView.setImageDrawable(ContextCompat.getDrawable(mContext,
            like ? R.drawable.ic_weibo_liked : R.drawable.ic_weibo_like));
    }

    public void setLikeCount(String count) {
        if (TextUtils.isDigitsOnly(count)) {
            mLikeCount = Long.parseLong(count);
        }
        mLikeCounter.setText(count);
    }

    private void updateLikeCounter(boolean isLike) {
        if (isLike) {
            mLikeCounter.setText(String.valueOf(++mLikeCount));
        } else {
            mLikeCounter.setCurrentText(String.valueOf(--mLikeCount));
        }
    }
}
