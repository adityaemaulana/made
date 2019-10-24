package com.example.favoritecatalogue.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.favoritecatalogue.R;
import com.example.favoritecatalogue.model.Movie;
import com.example.favoritecatalogue.model.Show;
import com.example.favoritecatalogue.viewmodel.DetailViewModel;


public class DetailView extends AppCompatActivity {
    private TextView tvRating, tvDate, tvOverview;
    private ImageView imageView;

    private Movie movie;
    private Show show;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_SHOW = "extra_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        imageView = findViewById(R.id.iv_detail);
        tvRating = findViewById(R.id.tv_rating);
        tvDate = findViewById(R.id.tv_date);
        tvOverview = findViewById(R.id.tv_overview);

        DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

            viewModel.setMovie(movie);
            viewModel.getMovie().observe(this, observerMovie);
            setTitle(movie.getTitle());

        } else if (getIntent().hasExtra(EXTRA_SHOW)) {
            show = getIntent().getParcelableExtra(EXTRA_SHOW);

            viewModel.setShow(show);
            viewModel.getShow().observe(this, observerShow);
            setTitle(show.getTitle());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_settings:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private Observer<Movie> observerMovie = new Observer<Movie>() {
        @Override
        public void onChanged(@Nullable Movie m) {
            if (m != null) {
                movie = m;
                tvRating.setText(String.valueOf(m.getRating()));
                tvDate.setText(m.getDate());
                tvOverview.setText(m.getDescription());

                Glide.with(DetailView.this).load(m.getImage())
                        .into(imageView);
            }
        }
    };

    private Observer<Show> observerShow = new Observer<Show>() {
        @Override
        public void onChanged(@Nullable Show s) {
            if (s != null) {
                show = s;
                tvRating.setText(String.valueOf(s.getRating()));
                tvDate.setText(s.getFirstAired());
                tvOverview.setText(s.getDescription());

                Glide.with(DetailView.this).load(s.getImage())
                        .into(imageView);
            }
        }
    };
}
