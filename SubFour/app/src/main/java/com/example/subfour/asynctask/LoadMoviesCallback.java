package com.example.subfour.asynctask;

import com.example.subfour.model.Movie;

import java.util.ArrayList;

public interface LoadMoviesCallback {
    void postExecute(ArrayList<Movie> movies);
}
