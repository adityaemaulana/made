package com.example.favoritecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Show implements Parcelable {
    private int id;
    private int idShow;
    private String title;
    private String firstAired;
    private String description;
    private double rating;
    private String image;

    private final String POSTER_PATH = "https://image.tmdb.org/t/p/w342/";

    public Show() {

    }

    public Show(JSONObject object) {
        try {
            int idShow = object.getInt("id");
            String title = object.getString("name");
            String firstAired = object.getString("first_air_date");
            String description = object.getString("overview");
            double rating = object.getDouble("vote_average");
            String image = POSTER_PATH + object.getString("poster_path");

            this.idShow = idShow;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
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
        dest.writeInt(this.id);
        dest.writeInt(this.idShow);
    }

    protected Show(Parcel in) {
        this.title = in.readString();
        this.firstAired = in.readString();
        this.description = in.readString();
        this.rating = in.readDouble();
        this.image = in.readString();
        this.id = in.readInt();
        this.idShow = in.readInt();
    }

    public static final Creator<Show> CREATOR = new Creator<Show>() {
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
