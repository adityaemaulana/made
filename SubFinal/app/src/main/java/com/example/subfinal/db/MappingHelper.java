package com.example.subfinal.db;

import android.database.Cursor;

import com.example.subfinal.db.DatabaseContract.MovieColumns;
import com.example.subfinal.db.DatabaseContract.ShowColumns;
import com.example.subfinal.model.Movie;
import com.example.subfinal.model.Show;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<Movie> mapCursorToMovies(Cursor cursor) {
        ArrayList<Movie> movies = new ArrayList<>();

        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns._ID)));
            movie.setIdMovie(cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns.ID_MOVIE)));
            movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.TITLE)));
            movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.DATE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.DESCRIPTION)));
            movie.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(MovieColumns.RATING)));
            movie.setImage(cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.IMAGE)));

            movies.add(movie);
        }

        return movies;
    }

    public static Movie mapCursorToObjMovie(Cursor cursor) {
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

    public static ArrayList<Show> mapCursorToShows(Cursor cursor) {
        ArrayList<Show> shows = new ArrayList<>();

        while (cursor.moveToNext()) {
            Show show = new Show();
            show.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ShowColumns._ID)));
            show.setIdShow(cursor.getInt(cursor.getColumnIndexOrThrow(ShowColumns.ID_SHOW)));
            show.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.TITLE)));
            show.setFirstAired(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.DATE)));
            show.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.DESCRIPTION)));
            show.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(ShowColumns.RATING)));
            show.setImage(cursor.getString(cursor.getColumnIndexOrThrow(ShowColumns.IMAGE)));

            shows.add(show);
        }

        return shows;
    }

    public static Show mapCursorToObjShow(Cursor cursor) {
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
