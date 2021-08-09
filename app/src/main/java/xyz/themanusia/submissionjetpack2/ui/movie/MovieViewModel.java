package xyz.themanusia.submissionjetpack2.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;

public class MovieViewModel extends ViewModel {
    private final Repository repository;

    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.isLoading();
    }

    public LiveData<List<MovieEntity>> getMovieList() {
        return repository.getMovieList();
    }

    public LiveData<String> getErrorMsg() {
        return repository.getErrorMsg();
    }
}
