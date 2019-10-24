package com.example.subtri.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.subtri.R;
import com.example.subtri.model.Movie;
import com.example.subtri.model.Show;
import com.example.subtri.viewmodel.DetailViewModel;

public class DetailView extends AppCompatActivity {
    private TextView tvRating, tvDate, tvOverview;
    private ImageView imageView;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_SHOW = "extra_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
            Movie m = getIntent().getParcelableExtra(EXTRA_MOVIE);
            viewModel.setMovie(m);
            viewModel.getMovie().observe(this, observerMovie);
            setTitle(m.getTitle());
        } else if (getIntent().hasExtra(EXTRA_SHOW)) {
            Show s = getIntent().getParcelableExtra(EXTRA_SHOW);
            viewModel.setShow(s);
            viewModel.getShow().observe(this, observerShow);
            setTitle(s.getTitle());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private Observer<Movie> observerMovie = new Observer<Movie>() {
        @Override
        public void onChanged(@Nullable Movie movie) {
            if (movie != null) {
                tvRating.setText(String.valueOf(movie.getRating()));
                tvDate.setText(movie.getDate());
                tvOverview.setText(movie.getDescription());

                Glide.with(DetailView.this).load(movie.getImage())
                        .into(imageView);
            }
        }
    };

    private Observer<Show> observerShow = new Observer<Show>() {
        @Override
        public void onChanged(@Nullable Show show) {
            if (show != null) {
                tvRating.setText(String.valueOf(show.getRating()));
                tvDate.setText(show.getFirstAired());
                tvOverview.setText(show.getDescription());

                Glide.with(DetailView.this).load(show.getImage())
                        .into(imageView);
            }
        }
    };
}
