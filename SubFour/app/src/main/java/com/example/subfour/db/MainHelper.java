package com.example.subfour.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.subfour.db.DatabaseContract.MovieColumns;
import com.example.subfour.db.DatabaseContract.ShowColumns;
import com.example.subfour.model.Movie;
import com.example.subfour.model.Show;

import java.util.ArrayList;

import static com.example.subfour.db.DatabaseContract.TABLE_MOVIE;
import static com.example.subfour.db.DatabaseContract.TABLE_SHOW;

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

    // Movie Helper
    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE_MOVIE, null, null, null,
                null, null, MovieColumns._ID + " ASC", null);
        cursor.moveToFirst();

        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns._ID)));
                movie.setIdMovie(cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns.ID_MOVIE)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.TITLE)));
                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.DATE)));
                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.DESCRIPTION)));
                movie.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(MovieColumns.RATING)));
                movie.setImage(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.IMAGE)));

                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(MovieColumns.ID_MOVIE, movie.getIdMovie());
        args.put(MovieColumns.TITLE, movie.getTitle());
        args.put(MovieColumns.DATE, movie.getDate());
        args.put(MovieColumns.DESCRIPTION, movie.getDescription());
        args.put(MovieColumns.RATING, movie.getRating());
        args.put(MovieColumns.IMAGE, movie.getImage());

        return database.insert(DATABASE_TABLE_MOVIE, null, args);
    }

    public int deleteMovie(int id) {
        return database.delete(DATABASE_TABLE_MOVIE, MovieColumns._ID + " = '" + id + "'", null);
    }

    public Movie getMovie(int id) {
        Cursor cursor = database.query(DATABASE_TABLE_MOVIE, null, MovieColumns.ID_MOVIE + " = '" + id + "'", null,
                null, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            Movie movie = new Movie();
            movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns._ID)));
            movie.setIdMovie(cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns.ID_MOVIE)));
            movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.TITLE)));
            movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.DATE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.DESCRIPTION)));
            movie.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(MovieColumns.RATING)));
            movie.setImage(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.IMAGE)));

            cursor.close();
            return movie;
        } else {
            cursor.close();
            return null;
        }
    }

    // TV Show Helper
    public ArrayList<Show> getAllShows() {
        ArrayList<Show> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE_SHOW, null, null, null,
                null, null, ShowColumns._ID + " ASC", null);
        cursor.moveToFirst();

        Show show;
        if (cursor.getCount() > 0) {
            do {
                show = new Show();
                show.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ShowColumns._ID)));
                show.setIdShow(cursor.getInt(cursor.getColumnIndexOrThrow(ShowColumns.ID_SHOW)));
                show.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.TITLE)));
                show.setFirstAired(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.DATE)));
                show.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.DESCRIPTION)));
                show.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(ShowColumns.RATING)));
                show.setImage(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.IMAGE)));

                arrayList.add(show);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insertShow(Show show) {
        ContentValues args = new ContentValues();
        args.put(ShowColumns.ID_SHOW, show.getIdShow());
        args.put(ShowColumns.TITLE, show.getTitle());
        args.put(ShowColumns.DATE, show.getFirstAired());
        args.put(ShowColumns.DESCRIPTION, show.getDescription());
        args.put(ShowColumns.RATING, show.getRating());
        args.put(ShowColumns.IMAGE, show.getImage());

        return database.insert(DATABASE_TABLE_SHOW, null, args);
    }

    public int deleteShow(int id) {
        return database.delete(DATABASE_TABLE_SHOW, ShowColumns._ID + " = '" + id + "'", null);
    }

    public Show getShow(int id) {
        Cursor cursor = database.query(DATABASE_TABLE_SHOW, null, ShowColumns.ID_SHOW + " = '" + id + "'", null,
                null, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            Show show = new Show();
            show.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ShowColumns._ID)));
            show.setIdShow(cursor.getInt(cursor.getColumnIndexOrThrow(ShowColumns.ID_SHOW)));
            show.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.TITLE)));
            show.setFirstAired(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.DATE)));
            show.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.DESCRIPTION)));
            show.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(ShowColumns.RATING)));
            show.setImage(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.IMAGE)));

            cursor.close();
            return show;
        } else {
            cursor.close();
            return null;
        }
    }
}
