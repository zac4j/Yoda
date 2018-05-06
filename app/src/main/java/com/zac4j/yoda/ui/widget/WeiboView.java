package com.zac4j.yoda.ui.widget;

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
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.WeiboAdapter;
import com.zac4j.yoda.util.image.MediaType;
import com.zac4j.yoda.util.weibo.WeiboReader;

/**
 * A weibo view model
 * Created by Zac on 2018/3/28.
 */

public class WeiboView extends RelativeLayout {

    public interface OnMediaClickListener {
        void onClick(MediaType type, Weibo weibo);
    }

    public interface OnOperateWeiboListener {
        void onRepost(Weibo weibo);

        void onComment(Weibo weibo);

        void onLike(Weibo weibo);
    }

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
    View mButtonContainer;
    @BindView(R.id.weibo_tv_repost)
    TextView mRepostButton;
    @BindView(R.id.weibo_tv_comment)
    TextView mCommentButton;
    @BindView(R.id.weibo_tv_like)
    TextView mLikeButton;

    private OnMediaClickListener mOnMediaClickListener;

    private OnOperateWeiboListener mOnOperateWeiboListener;

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

    public void setOnMediaClickListener(OnMediaClickListener listener) {
        mOnMediaClickListener = listener;
    }

    public void setOnOperateWeiboListener(OnOperateWeiboListener listener) {
        mOnOperateWeiboListener = listener;
    }

    private void populateWeibo(Weibo weibo) {

        if (weibo == null || weibo.getUser() == null) {
            return;
        }

        User user = weibo.getUser();
        WeiboReader reader = WeiboReader.getInstance();
        // user info
        reader.readAvatar(mAvatarView, user.getProfileImageUrl());
        reader.readNickname(mNicknameView, user.getScreenName());
        reader.readUsername(mUsernameView, user.getDomain());
        // post time
        reader.readPostTime(mPostTimeView, weibo.getCreatedAt());
        // post from
        reader.readPostSource(mPostSourceView, weibo.getSource());
        // weibo content
        reader.readTextContent(mContentView, weibo.getText());
        // weibo media content
        //todo parse media content into diff type.
        reader.parseWeiboMedia(mMediaContainer, weibo);
        addWeiboMediaClickListener(mMediaContainer, weibo);
        // weibo repost
        reader.readRepostContent(mRepostContainer, weibo.getRepostWeibo());
        // repost number
        reader.readRepostNumber(mRepostButton, weibo.getRepostsCount());
        // comment number
        reader.readCommentsNumber(mCommentButton, weibo.getCommentsCount());
        // favorite number&state
        reader.readLikeState(mLikeButton, weibo.getFavorited());
        reader.readLikeNumber(mLikeButton, weibo.getAttitudesCount());
        addOperateWeiboListener(mRepostContainer, mCommentButton, mLikeButton, weibo);
    }

    private void addOperateWeiboListener(ViewGroup repostContainer, TextView commentButton,
        TextView likeButton, Weibo weibo) {
        repostContainer.setOnClickListener(v -> mOnOperateWeiboListener.onRepost(weibo));
        commentButton.setOnClickListener(v -> mOnOperateWeiboListener.onComment(weibo));
        likeButton.setOnClickListener(v -> mOnOperateWeiboListener.onLike(weibo));
    }

    private void addWeiboMediaClickListener(ViewGroup mediaContainer, Weibo weibo) {
        mediaContainer.setOnClickListener(
            v -> mOnMediaClickListener.onClick(MediaType.PICTURE, weibo));
    }
}
