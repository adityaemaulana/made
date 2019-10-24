package com.example.subfinal.asynctask;

import com.example.subfinal.model.Movie;

import java.util.ArrayList;

public interface LoadMoviesCallback {
    void postExecute(ArrayList<Movie> movies);
}
