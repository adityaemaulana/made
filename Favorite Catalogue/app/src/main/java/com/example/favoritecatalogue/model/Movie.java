package com.example.favoritecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {
    private int id;
    private int idMovie;
    private String title;
    private String date;
    private String description;
    private double rating;
    private String image;

    private final String POSTER_PATH = "https://image.tmdb.org/t/p/w342/";

    public Movie() {

    }

    public Movie(JSONObject object) {
        try {
            int idMovie = object.getInt("id");
            String title = object.getString("title");
            String date = object.getString("release_date");
            String description = object.getString("overview");
            double rating = object.getDouble("vote_average");
            String image = POSTER_PATH + object.getString("poster_path");

            this.idMovie = idMovie;
            this.title = title;
            this.date = date;
            this.description = description;
            this.rating = rating;
            this.image = image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idImage) {
        this.idMovie = idImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.description);
        dest.writeDouble(this.rating);
        dest.writeString(this.image);
        dest.writeInt(this.id);
        dest.writeInt(this.idMovie);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.description = in.readString();
        this.rating = in.readDouble();
        this.image = in.readString();
        this.id = in.readInt();
        this.idMovie = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}