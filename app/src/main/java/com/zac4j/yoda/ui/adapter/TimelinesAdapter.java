package com.zac4j.yoda.ui.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zac4j.yoda.R;
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

    private OnItemClickListener mItemClickListener;
    private List<Weibo> mWeiboList;

    @Inject
    public TimelinesAdapter() {
        mWeiboList = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
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
        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //View view = inflater.inflate(R.layout.layout_weibo_main, parent, false);

        // Add view holder item click listener
        final ViewHolder holder = new ViewHolder(new WeiboView(parent.getContext()));
        holder.itemView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            mItemClickListener.onClick(mWeiboList.get(position));
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

    public interface OnItemClickListener {
        void onClick(Weibo weibo);
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
