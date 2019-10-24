package com.example.subfinal.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.subfinal.model.Movie;
import com.example.subfinal.model.Show;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private MutableLiveData<Show> show = new MutableLiveData<>();

    public void setMovie(Movie m) {
        movie.setValue(m);
    }

    public void setShow(Show s) {
        show.setValue(s);
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<Show> getShow() {
        return show;
    }
}
