package com.example.favoritecatalogue.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "movie_table";
    static String TABLE_SHOW = "show_table";

    public static final String AUTHORITY = "com.example.subfinal";
    private static final String SCHEME = "content";

    public static final class MovieColumns implements BaseColumns {
        public static String ID_MOVIE = "id_movie";
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String DESCRIPTION = "description";
        public static String RATING = "rating";
        public static String IMAGE = "image";

        public static final Uri MOVIE_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }

    public static final class ShowColumns implements BaseColumns {
        public static String ID_SHOW = "id_show";
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String DESCRIPTION = "description";
        public static String RATING = "rating";
        public static String IMAGE = "image";

        public static final Uri SHOW_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_SHOW)
                .build();
    }
}
