package com.example.subfour.asynctask;

import android.os.AsyncTask;

import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Show;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LoadShowAsync extends AsyncTask<Void, Void, ArrayList<Show>> {
    private final WeakReference<MainHelper> weakShowHelper;
    private final WeakReference<LoadShowsCallback> weakCallback;

    public LoadShowAsync(MainHelper mainHelper, LoadShowsCallback showsCallback) {
        weakShowHelper = new WeakReference<>(mainHelper);
        weakCallback = new WeakReference<>(showsCallback);
    }

    @Override
    protected ArrayList<Show> doInBackground(Void... voids) {
        return weakShowHelper.get().getAllShows();
    }

    @Override
    protected void onPostExecute(ArrayList<Show> shows) {
        super.onPostExecute(shows);
        if (shows != null) {
            weakCallback.get().postExecute(shows);
        }
    }
}
