package com.example.subfinal.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import com.example.subfinal.R;
import com.example.subfinal.db.DatabaseContract.MovieColumns;
import com.example.subfinal.db.DatabaseContract.ShowColumns;
import com.example.subfinal.db.MappingHelper;
import com.example.subfinal.model.Movie;
import com.example.subfinal.model.Show;
import com.example.subfinal.util.SnackbarUtil;
import com.example.subfinal.viewmodel.DetailViewModel;

import static com.example.subfinal.db.DatabaseContract.MovieColumns.MOVIE_URI;
import static com.example.subfinal.db.DatabaseContract.ShowColumns.SHOW_URI;

public class DetailView extends AppCompatActivity {
    private TextView tvRating, tvDate, tvOverview;
    private ImageView imageView;
    private CoordinatorLayout detailContainer;

    private Movie movie, movieFromDB;
    private Show show, showFromDB;
    private Uri uriWithId;

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

        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            isMovie = true;
            movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

            movieFromDB = null;
            uriWithId = Uri.parse(MOVIE_URI + "/" + movie.getIdMovie());
            if (uriWithId != null) {
                Cursor cursor = getContentResolver().query(uriWithId, null, null, null, null);
                if (cursor != null) {
                    movieFromDB = MappingHelper.mapCursorToObjMovie(cursor);
                }
            }

            if (movieFromDB != null) {
                Log.d("DetailView", "movie exist");
                movie.setId(movieFromDB.getId());
                isExist = true;
            } else {
                Log.d("DetailView", "movie null");
                isExist = false;
            }

            viewModel.setMovie(movie);
            viewModel.getMovie().observe(this, observerMovie);
            setTitle(movie.getTitle());

        } else if (getIntent().hasExtra(EXTRA_SHOW)) {
            isShow = true;
            show = getIntent().getParcelableExtra(EXTRA_SHOW);

            showFromDB = null;
            uriWithId = Uri.parse(SHOW_URI + "/" + show.getIdShow());
            if (uriWithId != null) {
                Cursor cursor = getContentResolver().query(uriWithId, null, null, null, null);
                if (cursor != null) {
                    showFromDB = MappingHelper.mapCursorToObjShow(cursor);
                }
            }

            if (showFromDB != null) {
                show.setId(showFromDB.getId());
                isExist = true;
            } else {
                isExist = false;
            }

            viewModel.setShow(show);
            viewModel.getShow().observe(this, observerShow);
            setTitle(show.getTitle());
        }
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
        uriWithId = Uri.parse(MOVIE_URI + "/" + movie.getIdMovie());
        if (uriWithId != null) {
            Cursor cursor = getContentResolver().query(uriWithId, null, null, null, null);
            if (cursor != null) {
                movieFromDB = MappingHelper.mapCursorToObjMovie(cursor);
            } else {
                movieFromDB = null;
            }
        }

        if (movieFromDB != null) {
            uriWithId = Uri.parse(MOVIE_URI + "/" + movie.getId());
            int result = getContentResolver().delete(uriWithId, null, null);

            if (result > 0) {
                favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_border_24dp));
                SnackbarUtil.showSnackbar(detailContainer, "Berhasil Menghapus dari Favorit");
            } else {
                SnackbarUtil.showSnackbar(detailContainer, "Gagal Menghapus dari Favorit");
            }
        } else {
            ContentValues values = new ContentValues();
            values.put(MovieColumns.ID_MOVIE, movie.getIdMovie());
            values.put(MovieColumns.TITLE, movie.getTitle());
            values.put(MovieColumns.DATE, movie.getDate());
            values.put(MovieColumns.DESCRIPTION, movie.getDescription());
            values.put(MovieColumns.RATING, movie.getRating());
            values.put(MovieColumns.IMAGE, movie.getImage());

            Uri resultUri = getContentResolver().insert(MOVIE_URI, values);
            long result = Integer.valueOf(resultUri.getLastPathSegment());

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
        uriWithId = Uri.parse(SHOW_URI + "/" + show.getIdShow());
        if (uriWithId != null) {
            Cursor cursor = getContentResolver().query(uriWithId, null, null, null, null);
            if (cursor != null) {
                showFromDB = MappingHelper.mapCursorToObjShow(cursor);
            } else {
                showFromDB = null;
            }
        }

        if (showFromDB != null) {
            uriWithId = Uri.parse(SHOW_URI + "/" + show.getId());
            int result = getContentResolver().delete(uriWithId, null, null);

            if (result > 0) {
                favoriteMenuItem.setIcon(ContextCompat.getDrawable(DetailView.this, R.drawable.ic_favorite_border_24dp));
                SnackbarUtil.showSnackbar(detailContainer, "Berhasil Menghapus dari Favorit");
            } else {
                SnackbarUtil.showSnackbar(detailContainer, "Gagal Menghapus dari Favorit");
            }
        } else {
            ContentValues values = new ContentValues();
            values.put(ShowColumns.ID_SHOW, show.getIdShow());
            values.put(ShowColumns.TITLE, show.getTitle());
            values.put(ShowColumns.DATE, show.getFirstAired());
            values.put(ShowColumns.DESCRIPTION, show.getDescription());
            values.put(ShowColumns.RATING, show.getRating());
            values.put(ShowColumns.IMAGE, show.getImage());

            Uri resultUri = getContentResolver().insert(SHOW_URI, values);
            long result = Integer.valueOf(resultUri.getLastPathSegment());

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
