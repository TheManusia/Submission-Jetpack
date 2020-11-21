package xyz.themanusia.submissionjetpack2.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.themanusia.submissionjetpack2.data.TvEntity;

@Getter
@Setter
@AllArgsConstructor
public class TvApiResponse {
    @SerializedName("results")
    private List<TvEntity> tvList;
}
