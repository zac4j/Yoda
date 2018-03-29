package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.widget.WeiboView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Home Weibo List Adapter
 * Created by zac on 3/17/2017.
 */

public class TimelinesAdapter extends RecyclerView.Adapter<TimelinesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onClick(Weibo weibo);
    }

    public interface OnItemImageClickListener {
        void onClick(Weibo weibo);
    }

    private OnItemClickListener mItemClickListener;
    private OnItemImageClickListener mItemImageClickListener;
    private List<Weibo> mWeiboList;

    @Inject
    public TimelinesAdapter() {
        mWeiboList = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemImageClickListener(OnItemImageClickListener listener) {
        mItemImageClickListener = listener;
    }

    public void addWeiboList(final List<Weibo> weiboList) {
        List<Weibo> oldWeiboList = new ArrayList<>(mWeiboList);
        mWeiboList.addAll(weiboList);
        DiffUtil.DiffResult result =
            DiffUtil.calculateDiff(new DiffCallback(oldWeiboList, mWeiboList));
        result.dispatchUpdatesTo(TimelinesAdapter.this);
    }

    public void clear() {
        if (mWeiboList != null) {
            mWeiboList.clear();
        }
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mWeiboList == null || mWeiboList.isEmpty();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Add view holder item click listener
        Context context = parent.getContext();
        WeiboView weiboView = new WeiboView(context);
        final ViewHolder holder = new ViewHolder(weiboView);

        holder.itemView.setOnClickListener(v -> {
            // get adapter position in runtime.
            int position = holder.getAdapterPosition();
            Weibo weibo = mWeiboList.get(position);
            mItemClickListener.onClick(weibo);
        });

        weiboView.setOnImageClickListener(() -> {
            // get adapter position in runtime.
            int position = holder.getAdapterPosition();
            Weibo weibo = mWeiboList.get(position);
            mItemImageClickListener.onClick(weibo);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mWeiboList == null || mWeiboList.isEmpty()) {
            return;
        }

        holder.bindTo(mWeiboList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mWeiboList == null || mWeiboList.isEmpty()) {
            return 0;
        }
        return mWeiboList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

        void bindTo(Weibo weibo) {
            ((WeiboView) itemView).setAdapter(new WeiboAdapter(weibo));
        }
    }
}
