package com.jingjing.mytest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingjing.mytest.R;

import java.util.ArrayList;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/8 16:16
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class MydataAdapter extends RecyclerView.Adapter<MydataAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflate;
    private ArrayList<Integer> mImgList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onImgItemClick(int position, int restId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MydataAdapter(Context context) {
        super();
        this.mContext = context;
        mInflate = LayoutInflater.from(mContext);
    }

    public void appendData(ArrayList<Integer> imgs) {
        if (mImgList == null) {
            mImgList = new ArrayList<>();
        }
        mImgList.addAll(imgs);
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mImgList == null) {
            return;
        }
        mImgList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.view_image_hold, parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.mIv = (ImageView) view.findViewById(R.id.iv_pic);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mImgList==null?0:mImgList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final int item = mImgList.get(position);
        holder.mIv.setImageResource(item);
        holder.mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onImgItemClick(position, item);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

        ImageView mIv;
    }

}
