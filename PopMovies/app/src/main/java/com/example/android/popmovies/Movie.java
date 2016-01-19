package com.example.android.popmovies;

/**
 * Created by Administrator on 10/4/2015 0004.
 */
public class Movie {
    private String original_title;
    private String poster_path;
    private String overview;
    private double vote_average;
    private String release_date;

    public Movie(String original_title, String overview, String release_date, String poster_path, double vote_average){
        this.original_title=original_title;
        this.overview=overview;
        this.release_date=release_date;
        this.poster_path=poster_path;
        this.vote_average=vote_average;
    }

    public String getmTitle(){return original_title;}
    public String getmOverview(){return overview;}
    public String getmReleaseDate(){return release_date;}
    public String getmImageURL(){return poster_path;}
    public Double getmRating(){return vote_average;}
}
