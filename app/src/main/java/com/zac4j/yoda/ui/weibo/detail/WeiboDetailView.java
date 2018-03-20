package com.zac4j.yoda.ui.weibo.detail;

import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * View for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public interface WeiboDetailView extends MvpView {

    void showWeiboInfo(Weibo weibo);

    void showWeiboComments(List<Comment> commentList);

    void showEmptyCommentView(boolean show);
}
