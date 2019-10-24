package com.example.subtwo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.subtwo.Model.TVShow;
import com.example.subtwo.R;
import com.example.subtwo.Fragment.TvFragment;

public class DetailShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        TVShow show = getIntent().getParcelableExtra(TvFragment.EXTRA_SHOW);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(show.getTitle());
        }

        TextView tvTitle = findViewById(R.id.tv_detail_show_title);
        tvTitle.setText(show.getTitle());
        TextView tvStatus = findViewById(R.id.tv_detail_show_status
        );
        tvStatus.setText(show.getStatus());
        TextView tvRating = findViewById(R.id.tv_detail_show_rating);
        tvRating.setText(show.getRating());
        TextView tvGenre = findViewById(R.id.tv_detail_show_genre);
        tvGenre.setText(show.getGenre());
        TextView tvDescription = findViewById(R.id.tv_detail_show_description);
        tvDescription.setText(show.getDescription());
        TextView tvLength = findViewById(R.id.tv_detail_show_length);
        tvLength.setText(show.getLength());


        ImageView ivMovie = findViewById(R.id.iv_detail_show);
        Glide.with(this).load(show.getImage()).into(ivMovie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
