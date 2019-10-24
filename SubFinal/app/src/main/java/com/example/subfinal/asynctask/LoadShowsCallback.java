package com.example.subfinal.asynctask;

import com.example.subfinal.model.Show;

import java.util.ArrayList;

public interface LoadShowsCallback {
    void postExecute(ArrayList<Show> shows);
}