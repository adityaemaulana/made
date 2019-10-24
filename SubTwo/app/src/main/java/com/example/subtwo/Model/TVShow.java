package com.example.subtwo.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String title;
    private String status;
    private String description;
    private String genre;
    private String rating;
    private String image;
    private String length;

    public TVShow(String title, String status, String description, String genre, String rating, String image, String length) {
        this.title = title;
        this.status = status;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.image = image;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.status);
        dest.writeString(this.description);
        dest.writeString(this.genre);
        dest.writeString(this.rating);
        dest.writeString(this.image);
        dest.writeString(this.length);
    }

    protected TVShow(Parcel in) {
        this.title = in.readString();
        this.status = in.readString();
        this.description = in.readString();
        this.genre = in.readString();
        this.rating = in.readString();
        this.image = in.readString();
        this.length = in.readString();
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
