package com.example.caios.desafio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caios.desafio.DetailActivity;
import com.example.caios.desafio.InfiniteScrollAdapter;
import com.example.caios.desafio.Model.Item;
import com.example.caios.desafio.Model.Pulls;
import com.example.caios.desafio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by caios on 6/7/16.
 */
public class GitDetailAdapter extends InfiniteScrollAdapter {
    private ArrayList<Pulls> items;
    private Context mContext;

    public GitDetailAdapter(Context context) {
        super(context);
        mContext = context;
        items = new ArrayList<Pulls>();
    }

    @Override
    public ArrayList getItems() {
        return items;
    }

    @Override
    public void addItems(Collection items) {
        if (items.size() > 0) {
            this.items.addAll(items);
        } else {
            super.setDoneLoading();
        }
        notifyDataSetChanged();
    }

    @Override
    public Object getRealItem(int position) {
        return items.get(position);
    }

    @Override
    public View getRealView(LayoutInflater inflater, int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.detail_item, null);
        ViewHolder viewHolder = new ViewHolder(v);

        final Pulls item = items.get(position);
        if (item != null) {
            viewHolder.name.setText(item.getUser().getLogin());
            viewHolder.repo.setText(item.getTitle());
            viewHolder.about.setText(String.format("Pull #%s | %s", item.getNumber(), item.getState()));

            Picasso.with(mContext)
                    .load(item.getUser().getAvatarUrl())
                    .error(R.drawable.user_default)
                    .into(viewHolder.thumbnail);
        }
        return v;
    }

    @Override
    public View getLoadingView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.list_loading, null);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected final TextView title;
        protected final TextView name;
        protected final TextView repo;
        protected final TextView about;
        protected final LinearLayout llContainer;

        public final ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            name = (TextView) v.findViewById(R.id.name);
            repo = (TextView) v.findViewById(R.id.repo);
            about = (TextView) v.findViewById(R.id.about);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            llContainer = (LinearLayout) v.findViewById(R.id.llContainer);
        }
    }
}