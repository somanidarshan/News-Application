package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    private TextView titleTV, subDescTV, contentTV;
    private ImageView newsIV;
    private Button readNewsBtn;
    String title, desc, content, imageUrl, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        titleTV = findViewById(R.id.titleTV);
        subDescTV = findViewById(R.id.SubDesc);
        contentTV = findViewById(R.id.TVcontent);
        newsIV = findViewById(R.id.NewsImage);
        readNewsBtn = findViewById(R.id.idReadNewsBtn);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");
        imageUrl = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageUrl).into(newsIV);

        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}