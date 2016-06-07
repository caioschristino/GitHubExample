package com.example.caios.desafio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caios.desafio.DetailActivity;
import com.example.caios.desafio.InfiniteScrollAdapter;
import com.example.caios.desafio.Model.Item;
import com.example.caios.desafio.R;
import com.squareup.picasso.Picasso;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by caios on 6/7/16.
 */
public class GitListAdapter extends InfiniteScrollAdapter {
    private ArrayList<Item> items;
    private Context mContext;

    public GitListAdapter(Context context) {
        super(context);
        mContext = context;
        items = new ArrayList<Item>();
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
        View v = inflater.inflate(R.layout.list_item, null);
        ViewHolder viewHolder = new ViewHolder(v);

        final Item item = items.get(position);
        if (item != null) {
            viewHolder.name.setText(item.getName());
            viewHolder.fullname.setText(item.getFullName());
            viewHolder.repo.setText(item.getOwner().getLogin());
            viewHolder.description.setText(item.getDescription());

            Picasso.with(mContext)
                    .load(item.getOwner().getAvatar())
                    .error(R.drawable.user_default)
                    .into(viewHolder.thumbnail);

            viewHolder.llContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, DetailActivity.class);
                    i.putExtra("name", item.getName());
                    i.putExtra("login", item.getOwner().getLogin());
                    mContext.startActivity(i);
                }
            });
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
        protected final TextView fullname;
        protected final TextView repo;
        protected final TextView description;
        protected final LinearLayout llContainer;

        public final ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            name = (TextView) v.findViewById(R.id.name);
            fullname = (TextView) v.findViewById(R.id.fullname);
            repo = (TextView) v.findViewById(R.id.repo);
            description = (TextView) v.findViewById(R.id.description);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            llContainer = (LinearLayout) v.findViewById(R.id.llContainer);
        }
    }
}