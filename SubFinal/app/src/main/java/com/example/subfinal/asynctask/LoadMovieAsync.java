package com.example.subfinal.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.subfinal.db.MappingHelper;
import com.example.subfinal.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.subfinal.db.DatabaseContract.MovieColumns.MOVIE_URI;

public class LoadMovieAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {
    private final WeakReference<Context> weakContext;
    private final WeakReference<LoadMoviesCallback> weakCallback;

    public LoadMovieAsync(Context context, LoadMoviesCallback moviesCallback) {
        weakContext = new WeakReference<>(context);
        weakCallback = new WeakReference<>(moviesCallback);
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... voids) {
        Context context = weakContext.get();
        Cursor dataCursor = context.getContentResolver().query(MOVIE_URI, null, null, null, null);
        return MappingHelper.mapCursorToMovies(dataCursor);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        if (movies != null) {
            weakCallback.get().postExecute(movies);
        }
    }
}