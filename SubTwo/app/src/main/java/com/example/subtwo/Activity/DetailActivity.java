package com.example.subtwo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.subtwo.Model.Movie;
import com.example.subtwo.R;
import com.example.subtwo.Fragment.MovieFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = getIntent().getParcelableExtra(MovieFragment.EXTRA_MOVIE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(movie.getTitle());
        }

        TextView tvTitle = findViewById(R.id.tv_detail_title);
        tvTitle.setText(movie.getTitle());
        TextView tvDate = findViewById(R.id.tv_detail_date);
        tvDate.setText(movie.getDate());
        TextView tvRating = findViewById(R.id.tv_detail_rating);
        tvRating.setText(movie.getRating());
        TextView tvGenre = findViewById(R.id.tv_detail_genre);
        tvGenre.setText(movie.getGenre());
        TextView tvDescription = findViewById(R.id.tv_detail_description);
        tvDescription.setText(movie.getDescription());
        TextView tvLength = findViewById(R.id.tv_detail_length);
        tvLength.setText(movie.getLength());


        ImageView ivMovie = findViewById(R.id.iv_detail_movie);
        Glide.with(this).load(movie.getImage()).into(ivMovie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
