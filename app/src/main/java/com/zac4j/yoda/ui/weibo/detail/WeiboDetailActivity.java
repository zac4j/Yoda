package com.zac4j.yoda.ui.weibo.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.WeiboAdapter;
import com.zac4j.yoda.ui.adapter.WeiboCommentAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.listener.EndlessRecyclerViewScrollListener;
import com.zac4j.yoda.ui.weibo.repost.WeiboRepostDialogFragment;
import com.zac4j.yoda.ui.widget.HeartView;
import com.zac4j.yoda.ui.widget.WeiboView;
import com.zac4j.yoda.util.WeiboAnimatorManager;
import com.zac4j.yoda.util.weibo.WeiboReader;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public class WeiboDetailActivity extends BaseActivity implements WeiboDetailView {

    public static final String EXTRA_WEIBO = "extra_weibo";
    private static final int DEFAULT_COMMENT_PAGE = 1;
    private static final int DEFAULT_COMMENT_COUNT = 10;
    @Inject
    WeiboDetailPresenter mPresenter;
    @Inject
    WeiboCommentAdapter mAdapter;
    @BindView(R.id.weibo_detail_weibo_container)
    FrameLayout mWeiboContainer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.weibo_detail_rv_comment_list)
    RecyclerView mCommentListView;
    private WeiboView mWeiboView;
    private int mCommentPage = DEFAULT_COMMENT_PAGE;
    private long mWeiboId;
    private String mToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_detail);

        getActivityComponent().inject(this);
        mPresenter.attach(this);
        ButterKnife.bind(this);

        // Set up ActionBar
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.weibo_detail);
        }

        mWeiboView = new WeiboView(this);
        mWeiboContainer.addView(mWeiboView);

        mToken = AccessTokenKeeper.readAccessToken(this).getToken();

        // Set up CommentView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCommentListView.setLayoutManager(layoutManager);
        mCommentListView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mCommentListView.setAdapter(mAdapter);

        Weibo weibo = (Weibo) getIntent().getSerializableExtra(EXTRA_WEIBO);
        if (weibo != null) {
            showWeiboInfo(weibo);
            mWeiboId = weibo.getId();
        } else {
            showEmptyView(true);
        }

        addUiListeners(layoutManager);
    }

    private void addUiListeners(LinearLayoutManager layoutManager) {
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (verticalOffset == 0) {
                // State.EXPANDED;
                mCollapsingToolbarLayout.setTitle(" ");
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                // State.COLLAPSED;
                mCollapsingToolbarLayout.setTitle("Timeline");
            } else {
                // State.IDLE;
                mCollapsingToolbarLayout.setTitle(" ");
            }
        });

        mWeiboView.setOnOperateWeiboListener(new WeiboView.OnOperateWeiboListener() {
            @Override
            public void onRepost(Weibo weibo) {
                repostWeibo(weibo.getIdstr());
            }

            @Override
            public void onComment(Weibo weibo) {

            }

            @Override
            public void onLike(HeartView heartView, Weibo weibo) {
                heartView.updateLikeState();
            }
        });

        mCommentListView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mCommentPage = ++page;
                mPresenter.getWeiboComments(mToken, mWeiboId, mCommentPage, DEFAULT_COMMENT_COUNT);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mWeiboId != 0L) {
            mPresenter.getWeiboComments(mToken, mWeiboId, DEFAULT_COMMENT_PAGE,
                DEFAULT_COMMENT_COUNT);
        }
    }

    /**
     * 转发按钮点击事件
     */
    private void repostWeibo(String weiboId) {
        WeiboRepostDialogFragment dialogFragment = WeiboRepostDialogFragment.newInstance(weiboId);
        dialogFragment.show(getSupportFragmentManager());
        dialogFragment.setOnRepostListener(new WeiboRepostDialogFragment.OnRepostListener() {
            @Override
            public void onSuccess(Weibo weibo1) {
                if (mWeiboId != 0L) {
                    mPresenter.getWeiboComments(mToken, mWeiboId, DEFAULT_COMMENT_PAGE,
                        DEFAULT_COMMENT_COUNT);
                }
            }

            @Override
            public void onFailure() {
                Toast.makeText(WeiboDetailActivity.this, "cancel repost weibo", Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }

    //private void updateLikeCounter(boolean isLike) {
    //    if (isLike) {
    //        mLikeCounter.setText(String.valueOf(++mLikeCount));
    //    } else {
    //        mLikeCounter.setCurrentText(String.valueOf(--mLikeCount));
    //    }
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress(boolean show) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showEmptyView(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeiboInfo(Weibo weibo) {

        if (weibo == null) {
            return;
        }

        final User user = weibo.getUser();

        if (user == null) {
            return;
        }

        mWeiboView.setAdapter(new WeiboAdapter(weibo));
    }

    @Override
    public void showWeiboComments(List<Comment> commentList) {
        mAdapter.addCommentList(commentList);
    }

    @Override
    public void showEmptyCommentView(boolean show) {

    }
}
