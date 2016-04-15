package com.hart.simpleretrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hart.simpleretrofit.dto.Result;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Esteban on 4/14/16.
 */
public class MainRecyclerAdapter extends ArrayRecyclerAdapter<Result, RecyclerView.ViewHolder>{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_result, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(holder.getItemViewType()){
            case 0:
                Result r = getItem(position);
                ((ResultHolder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't do anything, but need Click Listener to get that sweet Ripple
                    }
                });

                Picasso.with(holder.itemView.getContext())
                        .load(r.picture.large)
                        .placeholder(R.drawable.img_placeholder)
                        .into(((ResultHolder)holder).picture);

                ((ResultHolder)holder).name.setText(r.name.getFormattedName());
                ((ResultHolder)holder).email.setText(r.email);
                break;
        }
    }

    public class ResultHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.linear_layout) LinearLayout linearLayout;
        @Bind(R.id.picture) ImageView picture;
        @Bind(R.id.name) TextView name;
        @Bind(R.id.email) TextView email;

        public ResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
