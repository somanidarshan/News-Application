package com.example.newsapplication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_rv_item, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder,  int position) {
        Articles articles = articlesArrayList.get(position);
        String url = articles.getUrl();
        holder.subTitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        holder.TimeTV.setText(formatDate(articles.getMpublishedAt()));
        holder.SourceTV.setText(articles.getSource().getName());
        holder.shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,url);
                context.startActivity(Intent.createChooser(intent, "Sharing"));
            }
        });

        Picasso.get().load(articles.getUrlToImage()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("title",articles.getTitle());
                intent.putExtra("content",articles.getContent());
                intent.putExtra("desc",articles.getDescription());
                intent.putExtra("image",articles.getUrlToImage());
                intent.putExtra("url",articles.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView img,shareImg;
        TextView titleTV,subTitleTV,TimeTV,SourceTV;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.idImageView);
            titleTV = itemView.findViewById(R.id.idTitleTV);
            subTitleTV = itemView.findViewById(R.id.idSubTitleTV);
            TimeTV = itemView.findViewById(R.id.idTimeTV);
            SourceTV = itemView.findViewById(R.id.idSource);
            shareImg = itemView.findViewById(R.id.shareImg);
        }
    }

    public String formatDate(String date){
        StringBuilder sb = new StringBuilder();
        sb.append(date.substring(11,16));
        return sb.toString();
    }

}
