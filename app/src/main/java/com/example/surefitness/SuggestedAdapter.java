package com.example.surefitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestedAdapter extends RecyclerView.Adapter< SuggestedAdapter.MyViewHolder> {
    ArrayList<SuggestedModel> suggested;
    Context context;

    public SuggestedAdapter(ArrayList<SuggestedModel> suggested, Context context) {
        this.suggested = suggested;
        this.context = context;
    }

    @NonNull
    @Override
    public SuggestedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.suggested_recycler,parent,false);

        return new SuggestedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedAdapter.MyViewHolder holder, int position) {
      holder.TitleText.setText(suggested.get(position).getName());
      holder.DescriptionText.setText(suggested.get(position).getDescription());
      holder.foodImage.setImageResource(suggested.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return suggested.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TitleText, DescriptionText;
        ImageView foodImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleText = itemView.findViewById(R.id.textView);
            DescriptionText = itemView.findViewById(R.id.textView7);
            foodImage = itemView.findViewById(R.id.imageView7);
        }

    }

}
