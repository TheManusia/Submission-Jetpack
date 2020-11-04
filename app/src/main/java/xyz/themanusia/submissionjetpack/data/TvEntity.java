package xyz.themanusia.submissionjetpack.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TvEntity {
    private String tvId;
    private String title;
    private String descriptiom;
    private String image;
    private String rating;
    private String year;
}
