package com.example.favoritecatalogue.asynctask;


import com.example.favoritecatalogue.model.Movie;

import java.util.ArrayList;

public interface LoadMoviesCallback {
    void postExecute(ArrayList<Movie> movies);
}
