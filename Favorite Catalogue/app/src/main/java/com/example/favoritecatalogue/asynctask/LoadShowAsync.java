package com.example.favoritecatalogue.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.favoritecatalogue.helper.MappingHelper;
import com.example.favoritecatalogue.model.Show;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.favoritecatalogue.db.DatabaseContract.ShowColumns.SHOW_URI;


public class LoadShowAsync extends AsyncTask<Void, Void, ArrayList<Show>> {
    private final WeakReference<Context> weakContext;
    private final WeakReference<LoadShowsCallback> weakCallback;

    public LoadShowAsync(Context context, LoadShowsCallback showsCallback) {
        weakContext = new WeakReference<>(context);
        weakCallback = new WeakReference<>(showsCallback);
    }

    @Override
    protected ArrayList<Show> doInBackground(Void... voids) {
        Context context = weakContext.get();
        Cursor dataCursor = context.getContentResolver().query(SHOW_URI, null, null, null, null);
        return MappingHelper.mapCursorToShows(dataCursor);
    }

    @Override
    protected void onPostExecute(ArrayList<Show> shows) {
        super.onPostExecute(shows);
        if (shows != null) {
            weakCallback.get().postExecute(shows);
        }
    }
}
