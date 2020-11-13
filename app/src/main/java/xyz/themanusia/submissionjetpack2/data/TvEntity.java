package xyz.themanusia.submissionjetpack2.data;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TvEntity {
    @SerializedName("id")
    private int tvId;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String image;

    @SerializedName("vote_average")
    private double rating;

    @SerializedName("first_air_date")
    private String year;
}
