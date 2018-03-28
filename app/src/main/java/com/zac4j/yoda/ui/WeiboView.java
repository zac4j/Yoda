package com.zac4j.yoda.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.WeiboAdapter;
import com.zac4j.yoda.util.weibo.WeiboReader;

/**
 * A weibo view model
 * Created by Zac on 2018/3/28.
 */

public class WeiboView extends RelativeLayout {

    @BindView(R.id.weibo_main_container)
    RelativeLayout mMainContainer;
    @BindView(R.id.weibo_iv_avatar)
    ImageView mAvatarView;
    @BindView(R.id.weibo_tv_nickname)
    TextView mNicknameView;
    @BindView(R.id.weibo_tv_username)
    TextView mUsernameView;
    @BindView(R.id.weibo_tv_post_time)
    TextView mPostTimeView;
    @BindView(R.id.weibo_tv_post_from)
    TextView mPostSourceView;
    @BindView(R.id.weibo_tv_content)
    TextView mContentView;
    @BindView(R.id.weibo_repost_container)
    ViewGroup mRepostContainer;
    @BindView(R.id.weibo_media_container)
    ViewGroup mMediaContainer;
    @BindView(R.id.weibo_action_button_container)
    View mButttonContainer;
    @BindView(R.id.weibo_tv_repost)
    TextView mRepostButton;
    @BindView(R.id.weibo_list_item_tv_comment)
    TextView mCommentButton;
    @BindView(R.id.weibo_list_item_tv_like)
    TextView mLikeButton;

    public WeiboView(Context context) {
        this(context, null);
    }

    public WeiboView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeiboView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = LayoutInflater.from(context);
        View weiboView = inflater.inflate(R.layout.layout_weibo_main, this, true);

        ButterKnife.bind(WeiboView.this, weiboView);
    }

    public void setAdapter(WeiboAdapter adapter) {
        Weibo weibo = adapter.getWeibo();
        populateWeibo(weibo);
    }

    private void populateWeibo(Weibo weibo) {
        // 发送时间
        WeiboReader.readPostTime(mPostTimeView, weibo.getCreatedAt());

        // 发送来源
        WeiboReader.readPostSource(mPostSourceView, weibo.getSource());

        // 微博内容
        WeiboReader.readContent(mContentView, weibo.getText());

        // 转发数
        WeiboReader.readRepostNumber(mRepostButton, weibo.getRepostsCount());
        // 评论数
        WeiboReader.readCommentsNumber(mCommentButton, weibo.getCommentsCount());
        // 点赞数
        WeiboReader.readLikeState(mLikeButton, weibo.getFavorited());
        WeiboReader.readLikeNumber(mLikeButton, weibo.getAttitudesCount());
    }
}
