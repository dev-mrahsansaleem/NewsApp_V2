package com.example.newsapp_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp_v2.API.NewsApi;
import com.example.newsapp_v2.Adapter.NewsAdapter;
import com.example.newsapp_v2.Model.NewsRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {
    private RecyclerView mainRecycler;
    private ProgressBar progressBar;
    private NewsRes newsRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecycler = findViewById(R.id.activity_main_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mainRecycler.setLayoutManager(linearLayoutManager);
        progressBar = findViewById(R.id.progress_circular);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        Call<NewsRes> newsResCall = newsApi.getArticals("us", "business", "ad0154b679f242e9b96663db42537c79");
        newsResCall.enqueue(new Callback<NewsRes>() {
            @Override
            public void onResponse(Call<NewsRes> call, Response<NewsRes> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                newsRes = response.body();
                NewsAdapter adapter = new NewsAdapter(newsRes, MainActivity.this);
                mainRecycler.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);
                mainRecycler.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<NewsRes> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "" + newsRes.getArticalList().get(position).getTitle(), Toast.LENGTH_LONG).show();

        Intent webActivity = new Intent(this,WebViewActivity.class);
        webActivity.putExtra("News_url",newsRes.getArticalList().get(position).getUrl());
        startActivity(webActivity);

    }
}
