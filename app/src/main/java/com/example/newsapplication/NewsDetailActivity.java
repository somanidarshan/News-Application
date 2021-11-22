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
    String title,desc,content,imageUrl,url;
   private  TextView titletv,subdesc,contenttv;
   private ImageView newsIv;
   private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        content=getIntent().getStringExtra("content");
        url=getIntent().getStringExtra("url");
        imageUrl=getIntent().getStringExtra("image");

        titletv=findViewById(R.id.idTVTitle);
        subdesc=findViewById(R.id.idTVSubdescription);
        contenttv=findViewById(R.id.idTVContent);
        newsIv =findViewById(R.id.idIVNews);
        btn=findViewById(R.id.idBtnreadnews);
        titletv.setText(title);
        subdesc.setText(desc);
        contenttv.setText(content);
        Picasso.get().load(imageUrl).into(newsIv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(Intent.ACTION_VIEW);
                inten.setData(Uri.parse(url));
                startActivity(inten);
            }
        });
    }
}