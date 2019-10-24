package com.example.subfour.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.subfour.asynctask.LoadShowAsync;
import com.example.subfour.asynctask.LoadShowsCallback;
import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Show;

import java.util.ArrayList;

public class FavoriteShowViewModel extends ViewModel implements LoadShowsCallback {
    private MutableLiveData<ArrayList<Show>> showList = new MutableLiveData<>();

    public void setShows(MainHelper dbHelper) {
        // Init Database Helper
        dbHelper.open();
        new LoadShowAsync(dbHelper, this).execute();
    }

    public LiveData<ArrayList<Show>> getShows() {
        return showList;
    }

    @Override
    public void postExecute(ArrayList<Show> shows) {
        if (shows != null) {
            showList.setValue(shows);
        }
    }
}


