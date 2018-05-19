package com.example.anik.amarbangladesh.domain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anik.amarbangladesh.R;

import java.util.List;

/**
 * Created by Anik Mazumder on 5/20/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Domain> listData;
    private Context context;

    public MyAdapter(List<Domain> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Domain domain=listData.get(position);

        //holder.id.setText(domain.getId());
        holder.name.setText(domain.getName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //public TextView id;
        public ImageView imazeView;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

           // id = (TextView) itemView.findViewById(R.id.textIdOne);
            imazeView = (ImageView) itemView.findViewById(R.id.imageViewMain);
            name = (TextView) itemView.findViewById(R.id.textDetails);
        }
    }
}
