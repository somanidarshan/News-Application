package com.example.newsapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>{
    private categoryClickInterface categoryClickInterface;
    private ArrayList<CategoryRVmodal> categoryRVmodals;
    private Context context;

    public CategoryRVAdapter(ArrayList<CategoryRVmodal> categoryRVmodals, Context context, CategoryRVAdapter.categoryClickInterface categoryClickInterface) {
        this.categoryClickInterface = categoryClickInterface;
        this.categoryRVmodals = categoryRVmodals;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryRVmodal categoryRVmodal = categoryRVmodals.get(holder.getAdapterPosition());
        holder.categoryTV.setText(categoryRVmodal.getCategory());
        Picasso.get().load(categoryRVmodal.getCategoryImgUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRVmodals.size();
    }

    public interface categoryClickInterface{
        void onCategoryClick(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIV = itemView.findViewById(R.id.categoryIV);
            categoryTV = itemView.findViewById(R.id.categoriesTV);
        }
    }
}