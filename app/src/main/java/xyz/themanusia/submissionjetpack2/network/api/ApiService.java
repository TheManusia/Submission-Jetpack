package xyz.themanusia.submissionjetpack2.network.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.themanusia.submissionjetpack2.BuildConfig;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.network.response.MovieApiResponse;
import xyz.themanusia.submissionjetpack2.network.response.TvApiResponse;

public interface ApiService {
    @GET("movie/top_rated?api_key=" + BuildConfig.APIKEY)
    Call<MovieApiResponse> getMovie();

    @GET("tv/top_rated?api_key=" + BuildConfig.APIKEY)
    Call<TvApiResponse> getTv();

    @GET("movie/{id}?api_key=" + BuildConfig.APIKEY)
    Call<MovieEntity> getMovieDetail(@Path("id") int id);

    @GET("tv/{id}?api_key=" + BuildConfig.APIKEY)
    Call<TvEntity> getTvDetail(@Path("id") int id);
}
