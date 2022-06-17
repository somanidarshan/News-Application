package com.example.newsapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.categoryClickInterface{
    // ea39a79a90e044fb98ce7f58e6394c2d
    private NewsAdapter adapter;
    ProgressBar progressBar,categoryPB;
    private RecyclerView recyclerView, categoryRV;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVmodal> categoryArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    ImageView shareImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.idRecyclerView);
        categoryRV = findViewById(R.id.idCategoryRV);
        progressBar = findViewById(R.id.ProgressBar);
        categoryPB = findViewById(R.id.CategoryProgressBar);
        articlesArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        adapter = new NewsAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryArrayList, this,this::onCategoryClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        getNews("All");
        categoryRV.getRecycledViewPool().clear();
        recyclerView.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
    }
    private void getCategories(){
        categoryArrayList.add(new CategoryRVmodal("All","https://dm0qx8t0i9gc9.cloudfront.net/thumbnails/video/SKD-bqAkQjhvrjayf/videoblocks-news-intro-graphic-animation-with-grid-and-world-map-abstract-background-elegant-and-luxury-dynamic-style-for-news-and-business-template_ry_hdemhl_thumbnail-1080_01.png"));
        categoryArrayList.add(new CategoryRVmodal("Technology","https://www.deaf-interpreter.com/wp-content/uploads/2016/02/digital_diagram_business_and_technology_by_prophotostock-d711fj6.jpg"));
        categoryArrayList.add(new CategoryRVmodal("Science","https://getwallpapers.com/wallpaper/full/b/0/b/1407474-full-size-science-backgrounds-3840x2160-for-full-hd.jpg"));
        categoryArrayList.add(new CategoryRVmodal("Sports","https://lifestyle.iresearchnet.com/wp-content/uploads/2017/03/sports-psychology-26.jpg"));
        categoryArrayList.add(new CategoryRVmodal("General","http://www.panaynews.net/wp-content/uploads/2018/08/DSC_8930-2.jpg"));
        categoryArrayList.add(new CategoryRVmodal("Business","https://headsinternational.com/site/wp-content/uploads/business-professional-service.jpg"));
        categoryArrayList.add(new CategoryRVmodal("Entertainment","https://cpanews.net/wp-content/uploads/2021/12/Live-Party-Entertainment-Ideas-For-Your-Next-Celebration.jpg"));
        categoryArrayList.add(new CategoryRVmodal("Health","https://viridianadvisors.com/wp-content/uploads/2015/07/health-background.jpg"));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){
        categoryRV.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        categoryPB.setVisibility(View.VISIBLE);

        articlesArrayList.clear();
        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=in&category="+ category + "&apikey=ea39a79a90e044fb98ce7f58e6394c2d";
        String url = "https://newsapi.org/v2/top-headlines?country=in&language=en&apiKey=ea39a79a90e044fb98ce7f58e6394c2d";

        String Base_Url = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }
        else{
            call = retrofitAPI.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                progressBar.setVisibility(View.GONE);
                categoryPB.setVisibility(View.GONE);
                categoryRV.setVisibility(View.VISIBLE);

                ArrayList<Articles> articles = newsModal.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),
                            articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent(),articles.get(i).getSource(), articles.get(i).getMpublishedAt()));
                    Log.i("MainActivity", "time = "+ articlesArrayList.get(i).getMpublishedAt());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryArrayList.get(position).getCategory();
        getNews(category);
    }

}