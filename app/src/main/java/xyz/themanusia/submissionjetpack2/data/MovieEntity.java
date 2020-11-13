package xyz.themanusia.submissionjetpack2.data;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieEntity {
    @SerializedName("id")
    private int movieId;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String image;

    @SerializedName("vote_average")
    private double rating;

    @SerializedName("release_date")
    private String year;
}
