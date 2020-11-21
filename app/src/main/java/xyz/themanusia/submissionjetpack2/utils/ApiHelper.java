package xyz.themanusia.submissionjetpack2.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.MovieResponse;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.TvResponse;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.network.response.MovieApiResponse;
import xyz.themanusia.submissionjetpack2.network.response.TvApiResponse;

public class ApiHelper {

    private static final String TAG = ApiHelper.class.getSimpleName();

    private final ApiConfig apiConfig;

    public ApiHelper(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public void getMovieList(LoadMovieListCallback callback) {
        callback.onLoading(true);
        Call<MovieApiResponse> client = apiConfig.getApiService().getMovie();
        client.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieApiResponse> call, @NonNull Response<MovieApiResponse> response) {
                callback.onLoading(false);
                List<MovieResponse> movieResponses = new ArrayList<>();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (MovieEntity movieEntity : response.body().getMovieList()) {
                            movieResponses.add(new MovieResponse(
                                    movieEntity.getMovieId(),
                                    movieEntity.getTitle(),
                                    movieEntity.getOverview(),
                                    movieEntity.getImage(),
                                    movieEntity.getRating(),
                                    movieEntity.getYear()
                            ));
                        }
                        callback.onLoadMovieList(movieResponses);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieApiResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTvList(LoadTvListCallback callback) {
        callback.onLoading(true);
        Call<TvApiResponse> client = apiConfig.getApiService().getTv();
        client.enqueue(new Callback<TvApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvApiResponse> call, @NonNull Response<TvApiResponse> response) {
                callback.onLoading(false);
                List<TvResponse> tvs = new ArrayList<>();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (TvEntity tvEntity : response.body().getTvList()) {
                            tvs.add(new TvResponse(
                                    tvEntity.getTvId(),
                                    tvEntity.getTitle(),
                                    tvEntity.getOverview(),
                                    tvEntity.getImage(),
                                    tvEntity.getRating(),
                                    tvEntity.getYear()
                            ));
                        }
                        callback.onLoadTvListovieList(tvs);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvApiResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieDetail(int movieId, LoadMovieDetailCallback callback) {
        callback.onLoading(true);
        Call<MovieEntity> client = apiConfig.getApiService().getMovieDetail(movieId);
        client.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(@NonNull Call<MovieEntity> call, @NonNull Response<MovieEntity> response) {
                callback.onLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MovieEntity movieEntity = response.body();
                        callback.onLoadMovieDetail(new MovieResponse(
                                movieEntity.getMovieId(),
                                movieEntity.getTitle(),
                                movieEntity.getOverview(),
                                movieEntity.getImage(),
                                movieEntity.getRating(),
                                movieEntity.getYear()
                        ));
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieEntity> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTvDetail(int tvId, LoadTvDetailCallback callback) {
        callback.onLoading(true);
        Call<TvEntity> client = apiConfig.getApiService().getTvDetail(tvId);
        client.enqueue(new Callback<TvEntity>() {
            @Override
            public void onResponse(@NonNull Call<TvEntity> call, @NonNull Response<TvEntity> response) {
                callback.onLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        TvEntity tvEntity = response.body();
                        callback.onLoadTvDetail(new TvResponse(
                                tvEntity.getTvId(),
                                tvEntity.getTitle(),
                                tvEntity.getOverview(),
                                tvEntity.getImage(),
                                tvEntity.getRating(),
                                tvEntity.getYear()
                        ));
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvEntity> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public interface LoadMovieListCallback {
        void onLoadMovieList(List<MovieResponse> movieResponses);

        void onLoading(boolean status);
    }

    public interface LoadTvListCallback {
        void onLoadTvListovieList(List<TvResponse> tvResponses);

        void onLoading(boolean status);
    }

    public interface LoadMovieDetailCallback {
        void onLoadMovieDetail(MovieResponse movieResponse);

        void onLoading(boolean status);
    }

    public interface LoadTvDetailCallback {
        void onLoadTvDetail(TvResponse tvResponse);

        void onLoading(boolean status);
    }
}
