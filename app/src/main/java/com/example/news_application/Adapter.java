package com.example.news_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<News> mData;
    private NewsItemClick listner;

    public Adapter(ArrayList<News> data,NewsItemClick listner)
    {
        this.mData =  data;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        // Create and return a new instance of MyViewHolder
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onitemClicked(mData.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News item = mData.get(position);
        holder.textView.setText(item.author);
    }

    public void updateNews(ArrayList<News> updateNews)
    {
        mData.clear();
        mData.addAll(updateNews);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView textView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
    }
}

interface NewsItemClick
{
    void onitemClicked(News item);
}
