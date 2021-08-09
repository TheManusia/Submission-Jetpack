package xyz.themanusia.submissionjetpack2.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import lombok.Setter;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;

public class DetailViewModel extends ViewModel {
    @Setter
    private int movieId;
    @Setter
    private int tvId;
    private final Repository repository;

    public DetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.isLoading();
    }

    public LiveData<MovieEntity> getMovieDetail() {
        return repository.getMovieDetail(movieId);
    }

    public LiveData<TvEntity> getTvDetail() {
        return repository.getTvDetail(tvId);
    }

    public LiveData<String> getErrorMsg() {
        return repository.getErrorMsg();
    }
}
