package com.example.subfour.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.subfour.R;
import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Movie;
import com.example.subfour.model.Show;
import com.example.subfour.util.SnackbarUtil;
import com.example.subfour.viewmodel.DetailViewModel;

public class DetailView extends AppCompatActivity {
    private TextView tvRating, tvDate, tvOverview;
    private ImageView imageView;
    private CoordinatorLayout detailContainer;

    private Movie movie;
    private Show show;
    private MainHelper mainHelper;


    private boolean isExist, isMovie = false, isShow = false;
    private MenuItem favoriteMenuItem;

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
        detailContainer = findViewById(R.id.detail_container);

        DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        mainHelper = MainHelper.getInstance(getApplicationContext());
        mainHelper.open();

        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            isMovie = true;
            movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            Log.d("Movie", String.valueOf(movie.getIdMovie()));

            Movie m = mainHelper.getMovie(movie.getIdMovie());
            if (m != null) {
                movie.setId(m.getId());
                isExist = true;
            } else {
                isExist = false;
            }

            viewModel.setMovie(movie);
            viewModel.getMovie().observe(this, observerMovie);
            setTitle(movie.getTitle());
        } else if (getIntent().hasExtra(EXTRA_SHOW)) {
            isShow = true;
            show = getIntent().getParcelableExtra(EXTRA_SHOW);
            Log.d("Show", String.valueOf(show.getIdShow()));

            Show s = mainHelper.getShow(show.getIdShow());
            if (s != null) {
                show.setId(s.getId());
                isExist = true;
            } else {
                isExist = false;
            }

            viewModel.setShow(show);
            viewModel.getShow().observe(this, observerShow);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        favoriteMenuItem = menu.getItem(0);

        if (isExist) {
            favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_24dp));
        } else {
            favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_border_24dp));
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_favorite:
                if (isMovie) {
                    handleFavoriteMovie();
                } else if (isShow) {
                    handleFavoriteShow();
                }
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

    public void handleFavoriteMovie() {
        Movie m = mainHelper.getMovie(movie.getIdMovie());
        if (m != null) {
            long result = mainHelper.deleteMovie(movie.getId());
            if (result > 0) {
                favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_border_24dp));
                SnackbarUtil.showSnackbar(detailContainer, "Berhasil Menghapus dari Favorit");
            } else {
                SnackbarUtil.showSnackbar(detailContainer, "Gagal Menghapus dari Favorit");
            }
        } else {
            long result = mainHelper.insertMovie(movie);
            if (result > 0) {
                favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_24dp));
                movie.setId((int) result);
                SnackbarUtil.showSnackbar(detailContainer, "Berhasil Menambahkan ke Favorit");
            } else {
                SnackbarUtil.showSnackbar(detailContainer, "Gagal Menambahkan ke Favorit");
            }
        }
    }

    public void handleFavoriteShow() {
        Show s = mainHelper.getShow(show.getIdShow());
        if (s != null) {
            long result = mainHelper.deleteShow(show.getId());
            if (result > 0) {
                favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_border_24dp));
                SnackbarUtil.showSnackbar(detailContainer, "Berhasil Menghapus dari Favorit");
            } else {
                SnackbarUtil.showSnackbar(detailContainer, "Gagal Menghapus dari Favorit");
            }
        } else {
            long result = mainHelper.insertShow(show);
            if (result > 0) {
                favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_24dp));
                show.setId((int) result);
                SnackbarUtil.showSnackbar(detailContainer, "Berhasil Menambahkan ke Favorit");
            } else {
                SnackbarUtil.showSnackbar(detailContainer, "Gagal Menambahkan ke Favorit");
            }
        }
    }
}
