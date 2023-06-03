package com.example.apithings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    Intent i;
    NewsModel newsModel;
    TextView viewSource, viewAuthor, viewTitle, viewDesc, viewPublish;
    ImageView viewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail);

        i = getIntent();
        newsModel = (NewsModel) i.getParcelableExtra("mynews");
        System.out.println("my team name : "+newsModel.getTitle());

        viewSource = findViewById(R.id.monSource);
        viewSource.setText(newsModel.getSource());
        viewAuthor = findViewById(R.id.monAuthor);
        viewAuthor.setText(newsModel.getAuthor());
        viewTitle = findViewById(R.id.monTitle);
        viewTitle.setText(newsModel.getTitle());
        viewDesc = findViewById(R.id.monDesc);
        viewDesc.setText(newsModel.getDesc());
        viewPublish = findViewById(R.id.monPublish);
        viewPublish.setText(newsModel.getPublish());
        viewImage = findViewById(R.id.monImage);
//        Glide.with(this).load("urlToImage" + NewsModel.getImage()).into(viewImage);
        Glide.with(this)
                .load(newsModel.getImage())
                .into(viewImage);
    }
}