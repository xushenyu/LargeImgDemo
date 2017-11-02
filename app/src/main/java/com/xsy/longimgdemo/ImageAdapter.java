package com.xsy.longimgdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by xsy on 2017/10/20.
 */

class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bitmap> mList;
    private ViewHolder viewHolder;

    public ImageAdapter(Context mContext, List<Bitmap> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageBitmap(mList.get(position));
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
    }
}
