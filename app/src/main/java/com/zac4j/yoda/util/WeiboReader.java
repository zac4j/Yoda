package com.zac4j.yoda.util;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.zac4j.yoda.R;
import com.zac4j.yoda.util.image.CircleTransformation;

/**
 * Weibo content read helper
 * Created by zaccc on 6/8/2017.
 */

public class WeiboReader {

  public static void readAvatar(Context context, ImageView avatarView, String avatarUrl) {
    if (TextUtils.isEmpty(avatarUrl)) {
      Glide.clear(avatarView);
    } else {
      Glide.with(context)
          .load(avatarUrl)
          .transform(new CircleTransformation(context))
          .into(avatarView);
    }
  }

  public static void readNickname(TextView nicknameView, String nickname) {
    nicknameView.setText(TextUtils.isEmpty(nickname) ? "" : nickname);
  }

  public static void readUsername(TextView usernameView, String username) {
    usernameView.setText(TextUtils.isEmpty(username) ? "" : "@" + username);
  }

  public static void readDescription(TextView descriptionView, String description) {
    descriptionView.setText(TextUtils.isEmpty(description) ? "这个人虽然勤快，但还是懒得写简介。" : description);
  }

  public static void readLocation(TextView locationView, String location) {
    locationView.setText(TextUtils.isEmpty(location) ? "Galactic Republic" : location);
  }

  public static void readPostTime(TextView postTimeView, String postTime) {
    String pattern = "E MMM dd HH:mm:ss Z yyyy";
    String timeAgo = TimeUtils.getTimeAgo(postTime, pattern);
    postTimeView.setText(TextUtils.isEmpty(timeAgo) ? "" : timeAgo);
  }

  public static void readPostSource(TextView sourceView, String source) {
    if (TextUtils.isEmpty(source)) {
      sourceView.setText("");
    } else {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        sourceView.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY));
      } else {
        sourceView.setText(Html.fromHtml(source));
      }
    }
  }

  public static void readContent(TextView contentView, String content) {
    if (TextUtils.isEmpty(content)) {
      contentView.setText("");
    } else {
      WeiboContentParser.setupText(contentView, content);
    }
  }

  public static void readRepostNumber(TextView repostBtn, long repostNumber) {
    repostBtn.setText(NumberUtils.getRelativeNumberSpanString(repostNumber));
  }

  public static void readCommentsNumber(TextView commentBtn, long commentsNumber) {
    commentBtn.setText(NumberUtils.getRelativeNumberSpanString(commentsNumber));
  }

  public static void readLikeNumber(TextView likeBtn, long likeNumber) {
    likeBtn.setText(NumberUtils.getRelativeNumberSpanString(likeNumber));
  }

  public static void readLikeState(TextView likeBtn, boolean likeState) {
    if (likeState) {
      likeBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_weibo_liked, 0, 0, 0);
    }
  }
}
