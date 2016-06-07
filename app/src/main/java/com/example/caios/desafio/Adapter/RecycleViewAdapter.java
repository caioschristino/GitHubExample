package com.example.caios.desafio.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caios.desafio.Model.Pulls;
import com.example.caios.desafio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caios on 6/7/16.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final List<Pulls> values;
    private Context mContext;

    public RecycleViewAdapter(Context context) {
        values = new ArrayList<>();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Pulls pull = values.get(position);
        if (pull != null) {
            holder.name.setText(pull.getUser().getLogin());
            holder.repo.setText(pull.getTitle());
            holder.about.setText(String.format("Pull #%s | %s", pull.getNumber(), pull.getState()));

            Picasso.with(mContext)
                    .load(pull.getUser().getAvatarUrl())
                    .error(R.drawable.user_default)
                    .into(holder.thumbnail);
        }
    }

    public void appendItems(List<Pulls> pullsList) {
        values.addAll(pullsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected final TextView title;
        protected final TextView name;
        protected final TextView repo;
        protected final TextView about;
        public final ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            name = (TextView) v.findViewById(R.id.name);
            repo = (TextView) v.findViewById(R.id.repo);
            about = (TextView) v.findViewById(R.id.about);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        }
    }
}