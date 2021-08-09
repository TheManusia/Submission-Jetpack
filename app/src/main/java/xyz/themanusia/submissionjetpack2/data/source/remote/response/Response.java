package xyz.themanusia.submissionjetpack2.data.source.remote.response;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Parcel
public class Response {
    int id;
    String title;
    String overview;
    String image;
    double rating;
    String year;
}
