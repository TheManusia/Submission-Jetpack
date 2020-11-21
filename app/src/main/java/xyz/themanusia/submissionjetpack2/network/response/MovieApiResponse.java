package xyz.themanusia.submissionjetpack2.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;

@Getter
@Setter
@AllArgsConstructor
public class MovieApiResponse {
    @SerializedName("results")
    private List<MovieEntity> movieList;
}
