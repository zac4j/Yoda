package com.zac4j.yoda.ui.weibo.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.ThumbUrl;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.WeiboAdapter;
import com.zac4j.yoda.ui.adapter.WeiboDetailAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.listener.EndlessRecyclerViewScrollListener;
import com.zac4j.yoda.ui.weibo.WeiboImageActivity;
import com.zac4j.yoda.ui.weibo.repost.WeiboRepostDialogFragment;
import com.zac4j.yoda.ui.widget.HeartView;
import com.zac4j.yoda.ui.widget.WeiboView;
import com.zac4j.yoda.util.image.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public class WeiboDetailActivity extends BaseActivity implements WeiboDetailView {

    public static final String EXTRA_WEIBO = "extra_weibo";
    private static final int DEFAULT_COMMENT_PAGE = 1;
    private static final int DEFAULT_COMMENT_COUNT = 20;
    @Inject
    WeiboDetailPresenter mPresenter;
    @Inject
    WeiboDetailAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.weibo_detail_rv_comment_list)
    RecyclerView mCommentListView;
    private WeiboView mWeiboView;
    private int mCommentPage = DEFAULT_COMMENT_PAGE;
    private long mWeiboId;
    private Weibo mWeibo;
    private String mToken;
    List<Object> mWeiboDetails;

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

        mToken = AccessTokenKeeper.readAccessToken(this).getToken();

        // Set up CommentView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCommentListView.setLayoutManager(layoutManager);
        mCommentListView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mCommentListView.setAdapter(mAdapter);
        mCommentListView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mCommentPage = ++page;
                mPresenter.getWeiboComments(mToken, mWeiboId, mCommentPage, DEFAULT_COMMENT_COUNT);
            }
        });

        mWeibo = (Weibo) getIntent().getSerializableExtra(EXTRA_WEIBO);

        if (mWeibo != null) {
            mWeiboId = mWeibo.getId();
            //addUiListeners(layoutManager);
        } else {
            showEmptyView(true);
        }
    }

    private void addUiListeners(LinearLayoutManager layoutManager) {
        mWeiboView.setOnMediaClickListener((type, weibo) -> {
            if (type == MediaType.PICTURE) {
                ArrayList<ThumbUrl> imgUrlList = new ArrayList<>();
                if (weibo.hasMultipleImage()) {
                    imgUrlList = (ArrayList<ThumbUrl>) weibo.getPicUrls();
                } else {
                    String imgUrl = weibo.getOriginalPic();
                    ThumbUrl thumbUrl = new ThumbUrl(imgUrl);
                    imgUrlList.add(thumbUrl);
                }
                Intent intent = new Intent(WeiboDetailActivity.this, WeiboImageActivity.class);
                intent.putExtra(WeiboImageActivity.EXTRA_IMAGE_LIST, imgUrlList);
                startActivity(intent);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchWeiboComments(mWeiboId);
    }

    private void fetchWeiboComments(long weiboId) {
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
    public void showWeiboComments(List<Comment> commentList) {

        if (mWeiboDetails == null) {
            mWeiboDetails = new ArrayList<>();
            WeiboView weiboView = new WeiboView(WeiboDetailActivity.this);
            weiboView.setAdapter(new WeiboAdapter(mWeibo));
            mWeiboDetails.add(weiboView);
        }

        mWeiboDetails.addAll(commentList);
        mAdapter.addWeiboDetails(mWeiboDetails);
    }

    @Override
    public void showEmptyCommentView(boolean show) {

    }
}
