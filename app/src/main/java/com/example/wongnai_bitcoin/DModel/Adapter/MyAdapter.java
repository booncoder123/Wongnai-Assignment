package com.example.wongnai_bitcoin.DModel.Adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import com.example.wongnai_bitcoin.DModel.POJO.coin;
import com.example.wongnai_bitcoin.DModel.URLoader.GlideApp;
import com.example.wongnai_bitcoin.DModel.URLoader.GlideRequests;
import com.example.wongnai_bitcoin.R;


import java.io.File;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private List<coin> mCoins;



    public MyAdapter(Context mContext, List<coin> mCoins) {
        this.mContext = mContext;
        this.mCoins = mCoins;
        System.out.println("Adapter work");
        System.out.println(mCoins);


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        coin each_coin = mCoins.get(position);
        System.out.println(each_coin.getName());
        holder.Name.setText(each_coin.getName());


        try {
            holder.detail.setText(android.text.Html.fromHtml(mCoins.get(position).getDescription()).toString());
        }
        catch (Exception e){
            holder.detail.setText(mCoins.get(position).getDescription());
        }


        //GlideApp.with(mContext).load(mCoins.get(position).getIconUrl()).apply(new RequestOptions().override(80,80)).into(holder.image);
        GlideApp.with(mContext).load(mCoins.get(position).getIconUrl()).apply(new RequestOptions().override(100,100)).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return mCoins.size();
    }


    public class ViewHolder  extends  RecyclerView.ViewHolder{
        public TextView Name;
        public ImageView image;
        public TextView detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.nameview);
            image = itemView.findViewById(R.id.logoview);
            detail = itemView.findViewById(R.id.detailview);

        }
    }


}
