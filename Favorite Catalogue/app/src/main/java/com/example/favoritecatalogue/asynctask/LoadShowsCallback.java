package com.example.favoritecatalogue.asynctask;

import com.example.favoritecatalogue.model.Show;

import java.util.ArrayList;

public interface LoadShowsCallback {
    void postExecute(ArrayList<Show> shows);
}