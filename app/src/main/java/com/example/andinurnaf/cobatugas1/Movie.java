package com.example.andinurnaf.cobatugas1;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    int id;
    String title;
    String overview;
    String date;
    String url;
    int vote_count;
    double vote_average;
    double popularity;
    String adult;

    // Constructor
    public Movie(int id, String title, String overview, String date, String url, int vote_count, double vote_average, double popularity, String adult) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.date = date;
        this.url = "http://image.tmdb.org/t/p/w185/" + url;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.popularity = popularity;
        this.adult = adult;
    }

    // Getter
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getOverview() {
        return overview;
    }
    public String getDate() {
        return date;
    }
    public String getUrl() {
        return url;
    }
    public int getVote_count() {
        return vote_count;
    }
    public double getVote_average() {
        return vote_average;
    }
    public double getPopularity() {
        return popularity;
    }
    public String isAdult() {
        return adult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Making the class parcelable
    protected Movie(Parcel in){
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.url = in.readString();
        this.vote_count = in.readInt();
        this.vote_average = in.readDouble();
        this.popularity = in.readDouble();
        this.adult = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.date);
        parcel.writeString(this.url);
        parcel.writeInt(this.vote_count);
        parcel.writeDouble(this.vote_average);
        parcel.writeDouble(this.popularity);
        parcel.writeString(this.adult);
    }
}