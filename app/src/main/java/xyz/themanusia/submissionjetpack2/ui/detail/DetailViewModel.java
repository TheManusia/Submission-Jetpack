package xyz.themanusia.submissionjetpack2.ui.detail;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;

public class DetailViewModel extends ViewModel {
    @Setter
    private int movieId;
    @Setter
    private int tvId;

    private static final String TAG = DetailViewModel.class.getSimpleName();

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private final MutableLiveData<MovieEntity> movie = new MutableLiveData<>();

    private final MutableLiveData<TvEntity> tv = new MutableLiveData<>();

    public LiveData<MovieEntity> getMovieDetail() {
        isLoading.setValue(true);
        Call<MovieEntity> client = ApiConfig.getApiService().getMovieDetail(movieId);
        client.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(@NonNull Call<MovieEntity> call, @NonNull Response<MovieEntity> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        movie.setValue(response.body());
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
        return movie;
    }

    public LiveData<TvEntity> getTvDetail() {
        isLoading.setValue(true);
        Call<TvEntity> client = ApiConfig.getApiService().getTvDetail(tvId);
        client.enqueue(new Callback<TvEntity>() {
            @Override
            public void onResponse(@NonNull Call<TvEntity> call, @NonNull Response<TvEntity> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tv.setValue(response.body());
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
        return tv;
    }
}
