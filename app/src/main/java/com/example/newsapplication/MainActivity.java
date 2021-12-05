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

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategorClickInterface{
//e629a7ac538b42319369e3dd4412d980

    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV=findViewById(R.id.idRVNews);
        categoryRV=findViewById(R.id.idRVCategories);
        loadingPB=findViewById(R.id.idPLoading);
        articlesArrayList=new ArrayList<>();
        categoryRVModalArrayList=new ArrayList<>();
        newsRVAdapter=new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter=new CategoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        getNews("ALL");
        newsRVAdapter.notifyDataSetChanged();
    }
    private void getCategories(){
        categoryRVModalArrayList.add(new CategoryRVModal("ALL","https://kashmirobserver.net/wp-content/uploads/2020/11/Online-News.jpg"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://www.israel21c.org/wp-content/uploads/2018/03/terahertz-computer-chip.jpg"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://lh3.googleusercontent.com/v7YS0RTr51SGILmKKJYnYbQl-Zu_IqbYU3MW5TscpGKbI6sutre9S6shkOKr_l2OEckn"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://www.springfieldjcc.org/wp-content/uploads/2019/08/Sports-Rec.jpg"));
        categoryRVModalArrayList.add(new CategoryRVModal("General","https://i.ytimg.com/vi/uLuvY9mbgf4/maxresdefault.jpg"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://infoshri.com/wp-content/uploads/2021/11/675312-entertainment-news-npd-pattern-tracker-from-cookies-cream-chickpeas-to-katsu-curry-rice.jpg"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://schweikert.house.gov/sites/schweikert.house.gov/files/featured_image/issues/Healthcare.jpg"));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.GONE);
        articlesArrayList.clear();
        String categoryUrl="https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=e629a7ac538b42319369e3dd4412d980";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=e629a7ac538b42319369e3dd4412d980";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<NewsModel> call;
        if(category.equals("ALL")){
            call=retrofitAPI.getAllNews(url);
        }
        else{
            call=retrofitAPI.getNewsByCategory(categoryUrl);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel=response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articless=newsModel.getArticles();
                int s=articless.size();
                for(int i=0;i<s;i++){
                    articlesArrayList.add(new Articles(articless.get(i).getTittle(),articless.get(i).getDescription(),articless.get(i).getUrlToImage(),
                            articless.get(i).getUrl(),articless.get(i).getContent()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get News!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category=categoryRVModalArrayList.get(position).getCatrgory();
        getNews(category);
    }
}