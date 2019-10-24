package com.example.favoritecatalogue.viewmodel;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.favoritecatalogue.asynctask.LoadShowAsync;
import com.example.favoritecatalogue.asynctask.LoadShowsCallback;
import com.example.favoritecatalogue.model.Show;

import java.util.ArrayList;

import static com.example.favoritecatalogue.db.DatabaseContract.ShowColumns.SHOW_URI;

public class FavoriteShowViewModel extends ViewModel implements LoadShowsCallback {
    private MutableLiveData<ArrayList<Show>> showList = new MutableLiveData<>();

    public void setShows(Context context) {
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver myObserver = new DataObserver(handler, context, this);
        context.getContentResolver().registerContentObserver(
                SHOW_URI, true, myObserver);

        new LoadShowAsync(context, this).execute();
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

    public static class DataObserver extends ContentObserver {
        final Context context;
        final LoadShowsCallback callback;

        public DataObserver(Handler handler, Context context, LoadShowsCallback callback) {
            super(handler);
            this.context = context;
            this.callback = callback;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadShowAsync(context, callback).execute();
        }
    }
}