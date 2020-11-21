package xyz.themanusia.submissionjetpack2.ui.movie;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.network.response.MovieApiResponse;

public class MovieViewModel extends ViewModel {
    private Repository repository;

    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.isLoading();
    }

    public LiveData<List<MovieEntity>> getMovieList() {
        return repository.getMovieList();
    }
}
