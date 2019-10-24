package com.example.subtri.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.subtri.model.Movie;
import com.example.subtri.model.Show;

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
