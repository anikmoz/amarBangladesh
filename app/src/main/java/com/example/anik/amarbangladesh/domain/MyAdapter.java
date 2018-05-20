package com.example.anik.amarbangladesh.domain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anik.amarbangladesh.MainActivity;
import com.example.anik.amarbangladesh.R;
import com.example.anik.amarbangladesh.bashaAndolon;
import com.example.anik.amarbangladesh.details;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Domain domain = listData.get(position);

        //holder.id.setText(domain.getId());
        holder.name.setText(domain.getName());
        holder.publishDate.setText(domain.getPublishDate());
        String image = (String) domain.getImage();
        if(image != null) {
            byte[] data = Base64.decode(image, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            holder.imazeView.setImageBitmap(bmp);
        }

        holder.cardViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Domain domain = listData.get(position);

                String contentId = domain.getId();
                System.out.println(contentId);

                Intent intent = new Intent(context, details.class);
                intent.putExtra("id", contentId);
                System.out.println(intent);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //public TextView id;
        public ImageView imazeView;
        public TextView name;
        public TextView publishDate;
        public LinearLayout cardViewId;

        public ViewHolder(View itemView) {
            super(itemView);

            // id = (TextView) itemView.findViewById(R.id.textIdOne);
            imazeView = (ImageView) itemView.findViewById(R.id.imageViewMain);
            name = (TextView) itemView.findViewById(R.id.textDetails);
            publishDate = (TextView) itemView.findViewById(R.id.publishDate);
            cardViewId = (LinearLayout) itemView.findViewById(R.id.cardViewId);
        }
    }
}
