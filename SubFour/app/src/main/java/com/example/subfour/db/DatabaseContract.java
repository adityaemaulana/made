package com.example.subfour.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "movie_table";
    static String TABLE_SHOW = "show_table";

    static final class MovieColumns implements BaseColumns {
        static String ID_MOVIE = "id_movie";
        static String TITLE = "title";
        static String DATE = "date";
        static String DESCRIPTION = "description";
        static String RATING = "rating";
        static String IMAGE = "image";
    }

    static final class ShowColumns implements BaseColumns {
        static String ID_SHOW = "id_show";
        static String TITLE = "title";
        static String DATE = "date";
        static String DESCRIPTION = "description";
        static String RATING = "rating";
        static String IMAGE = "image";
    }
}
