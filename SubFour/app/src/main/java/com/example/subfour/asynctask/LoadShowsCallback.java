package com.example.subfour.asynctask;

import com.example.subfour.model.Show;

import java.util.ArrayList;

public interface LoadShowsCallback {
    void postExecute(ArrayList<Show> shows);
}
