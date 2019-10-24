package com.example.subfour.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.subfour.asynctask.LoadMovieAsync;
import com.example.subfour.asynctask.LoadMoviesCallback;
import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Movie;

import java.util.ArrayList;

public class FavoriteMovieViewModel extends ViewModel implements LoadMoviesCallback {
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();

    public void setMovies(MainHelper dbHelper) {
        dbHelper.open();
        new LoadMovieAsync(dbHelper, this).execute();
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movieList;
    }

    @Override
    public void postExecute(ArrayList<Movie> movies) {
        if (movies != null) {
            movieList.setValue(movies);
        }
    }
}