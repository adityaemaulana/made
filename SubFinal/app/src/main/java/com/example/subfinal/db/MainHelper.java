package com.example.subfinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.subfinal.db.DatabaseContract.MovieColumns;
import com.example.subfinal.db.DatabaseContract.ShowColumns;
import com.example.subfinal.model.Show;

import java.util.ArrayList;

import static com.example.subfinal.db.DatabaseContract.TABLE_MOVIE;
import static com.example.subfinal.db.DatabaseContract.TABLE_SHOW;

public class MainHelper {
    private static final String DATABASE_TABLE_MOVIE = TABLE_MOVIE;
    private static final String DATABASE_TABLE_SHOW = TABLE_SHOW;
    private static DatabaseHelper databaseHelper;
    private static MainHelper INSTANCE;

    private static SQLiteDatabase database;

    private MainHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MainHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    /**
     *
     * THIS IS MOVIE MODEL HELPER
     *
    **/

    public Cursor getAllMovies() {
        return database.query(DATABASE_TABLE_MOVIE, null, null, null,
                null, null, MovieColumns._ID + " ASC", null);
    }

    public long insertMovie(ContentValues values) {
        return database.insert(DATABASE_TABLE_MOVIE, null, values);
    }

    public int deleteMovie(int id) {
        return database.delete(DATABASE_TABLE_MOVIE, MovieColumns._ID + " = '" + id + "'", null);
    }

    public Cursor getMovie(int id) {
        return database.query(DATABASE_TABLE_MOVIE, null, MovieColumns.ID_MOVIE + " = '" + id + "'", null,
                null, null, null, null);
    }

    /**
     *
     * THIS IS SHOW MODEL HELPER
     *
     **/
    public Cursor getAllShows() {
//        ArrayList<Show> arrayList = new ArrayList<>();
        return database.query(DATABASE_TABLE_SHOW, null, null, null,
                null, null, ShowColumns._ID + " ASC", null);
    }

    public long insertShow(ContentValues values) {

        return database.insert(DATABASE_TABLE_SHOW, null, values);
    }

    public int deleteShow(int id) {
        return database.delete(DATABASE_TABLE_SHOW, ShowColumns._ID + " = '" + id + "'", null);
    }

    public Cursor getShow(int id) {
        return database.query(DATABASE_TABLE_SHOW, null, ShowColumns.ID_SHOW + " = '" + id + "'", null,
                null, null, null, null);
    }
}