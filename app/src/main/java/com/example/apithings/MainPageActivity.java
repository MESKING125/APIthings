package com.example.apithings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity implements NewsAdapter.NewsAdapterListener {

    RecyclerView recyclerView;
    ArrayList<NewsModel> newsList;
    private NewsAdapter newsAdapter;

    public void getNewsOnline() {
        String url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=bec1101c6a9f42ada84285ed0b5472f3";
        AndroidNetworking.get(url)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("success", "onResponse: " + response.toString());
                        try {
                            JSONArray articles = response.getJSONArray("articles");
                            for (int i = 0; i < articles.length(); i++) {
                                JSONObject article = articles.getJSONObject(i);
                                NewsModel news = new NewsModel();
                                news.setSource(article.getJSONObject("source").getString("name"));
                                news.setAuthor(article.getString("author"));
                                news.setTitle(article.getString("title"));
                                news.setDesc(article.getString("description"));
                                news.setPublish(article.getString("publishedAt"));
                                news.setImage(article.getString("urlToImage"));
                                newsList.add(news);
                            }
                            recyclerView = findViewById(R.id.RecycleGoDown);
                            newsAdapter = new NewsAdapter(getApplicationContext(), newsList, MainPageActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(newsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("failed", "onError: " + anError.toString());
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_page);
        recyclerView = findViewById(R.id.RecycleGoDown);
        newsList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsAdapter);
        getNewsOnline();
    }

    @Override
    public void onContactSelected(NewsModel contact) {
        Intent intent = new Intent(MainPageActivity.this, DetailActivity.class);
        intent.putExtra("mynews", contact);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainPageActivity.this);
        builder.setTitle("!")
                .setMessage("Are you sure?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol OK diklik
                        newsList.remove(position);
                        newsAdapter.notifyItemRemoved(position);
                        Toast.makeText(MainPageActivity.this.getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika tombol Batal diklik
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();



    }

}
