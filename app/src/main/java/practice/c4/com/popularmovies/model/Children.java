package practice.c4.com.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Children {
    @SerializedName("results")
    public List<MovieInfo> movieInfo;
}
