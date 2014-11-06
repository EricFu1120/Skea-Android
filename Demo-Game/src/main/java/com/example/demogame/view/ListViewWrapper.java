package com.example.demogame.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.demogame.DensityUtils;
import com.example.demogame.R;


/**
 * Created by Ervin on 14/11/4.
 */
public class ListViewWrapper {


    private ListView listView;

    private Context context;

    private ViewGroup parent;

    public ListView getListView(ListView listView, ViewGroup parent) {
        this.listView = listView;
        this.parent = parent;
        this.context = listView.getContext();
        listView.addHeaderView(getHeaderOrFooterView(parent));
        listView.addFooterView(getHeaderOrFooterView(parent));
        return listView;
    }

    private View getHeaderOrFooterView(ViewGroup parent) {
        View view = new View(context);
        int width = DensityUtils.dip2px(context, 60);
        int height = parent.getHeight();
        Log.d("getHeaderOrFooterView", " height=" + height);
        ListView.LayoutParams lp = new ListView.LayoutParams(width, height);
        view.setLayoutParams(lp);
        view.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        return view;
    }

}
