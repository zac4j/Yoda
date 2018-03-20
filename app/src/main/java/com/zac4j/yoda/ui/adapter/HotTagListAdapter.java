package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Tag;
import com.zac4j.yoda.di.ActivityContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter for hot tag list
 * Created by zaccc on 6/1/2017.
 */

public class HotTagListAdapter extends BaseAdapter {

    public static final int HOT_TAG_COUNT = 10;

    private Context mContext;
    private List<Tag> mTagList;

    @Inject
    public HotTagListAdapter(@ActivityContext Context context) {
        mContext = context;
        mTagList = new ArrayList<>(HOT_TAG_COUNT);
    }

    public void addTagList(List<Tag> tagList) {
        mTagList.addAll(tagList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTagList.isEmpty() ? 0 : HOT_TAG_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return mTagList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView =
                LayoutInflater.from(mContext).inflate(R.layout.list_item_hot_tag, parent, false);

            holder = new ViewHolder();

            holder.tagView = convertView.findViewById(R.id.home_search_list_tv_tag);
            holder.tagCountView = convertView.findViewWithTag(R.id.home_search_list_tv_tag_count);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String tag = mTagList.get(position).getValue();
        holder.tagView.setText(mContext.getString(R.string.hot_tag_item, tag));

        return convertView;
    }

    private static class ViewHolder {
        TextView tagView;
        TextView tagCountView;
    }
}
