package com.example.subone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private TextView tvJudul, tvGenre, tvDurasi, tvRating, tvDeskripsi;
    private ImageView ivPhoto;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        prepareView();
        bindItem();
    }

    private void prepareView() {
        tvJudul = findViewById(R.id.tv_detail_judul);
        tvGenre = findViewById(R.id.tv_detail_genre);
        tvDurasi = findViewById(R.id.tv_detail_durasi);
        tvRating = findViewById(R.id.tv_detail_rating);
        tvDeskripsi = findViewById(R.id.tv_detail_deskripsi);
        ivPhoto = findViewById(R.id.iv_detail_photo);
    }

    private void bindItem() {
        tvJudul.setText(movie.getJudul());
        tvGenre.setText(movie.getGenre());
        tvDurasi.setText(movie.getDurasi());
        tvRating.setText(movie.getRating());
        tvDeskripsi.setText(movie.getDeskripsi());
        Glide.with(DetailActivity.this).load(movie.getPhoto()).into(ivPhoto);
    }
}
