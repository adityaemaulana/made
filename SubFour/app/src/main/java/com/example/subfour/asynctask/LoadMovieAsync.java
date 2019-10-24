package com.example.subfour.asynctask;

import android.os.AsyncTask;

import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LoadMovieAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {
    private final WeakReference<MainHelper> weakMovieHelper;
    private final WeakReference<LoadMoviesCallback> weakCallback;

    public LoadMovieAsync(MainHelper mainHelper, LoadMoviesCallback moviesCallback) {
        weakMovieHelper = new WeakReference<>(mainHelper);
        weakCallback = new WeakReference<>(moviesCallback);
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... voids) {
        return weakMovieHelper.get().getAllMovies();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        if (movies != null) {
            weakCallback.get().postExecute(movies);
        }
    }
}
