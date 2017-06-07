package com.zac4j.yoda.ui.home.hot;

import com.zac4j.yoda.data.model.Tag;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * View for Hot Tag
 * Created by zaccc on 6/1/2017.
 */

public interface HotTagView extends MvpView {

  void showHotTags(List<Tag> tagList);
}
