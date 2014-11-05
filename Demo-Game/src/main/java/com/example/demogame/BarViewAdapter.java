package com.example.demogame;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.demogame.view.BarViewWrapper;

import java.util.List;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarViewAdapter extends BaseAdapter {

    private Context mContext;

    private List<Bar> list;

    private boolean glow;

    public BarViewAdapter(Context context, boolean glow) {
        this.mContext = context;
        this.glow = glow;
        this.list = BarGenerator.getInstance().getBars();
        Log.d("generator bars ", list.toString());
    }

    @Override
    public int getViewTypeCount() {
        return BarConst.TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Bar getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        convertView = new BarViewWrapper().getImageView(mContext, type, glow);
        return convertView;
    }


}
