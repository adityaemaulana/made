package com.example.subtri.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Show implements Parcelable {
    private String title;
    private String firstAired;
    private String description;
    private double rating;
    private String image;

    private final String POSTER_PATH = "https://image.tmdb.org/t/p/w342/";

    public Show(JSONObject object) {
        try {
            String title = object.getString("name");
            String firstAired = object.getString("first_air_date");
            String description = object.getString("overview");
            double rating = object.getDouble("vote_average");
            String image = POSTER_PATH + object.getString("poster_path");

            this.title = title;
            this.firstAired = firstAired;
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

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
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

    public String getPOSTER_PATH() {
        return POSTER_PATH;
    }

    public static Creator<Show> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.firstAired);
        dest.writeString(this.description);
        dest.writeDouble(this.rating);
        dest.writeString(this.image);
        dest.writeString(this.POSTER_PATH);
    }

    protected Show(Parcel in) {
        this.title = in.readString();
        this.firstAired = in.readString();
        this.description = in.readString();
        this.rating = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}
