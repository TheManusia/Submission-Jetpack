package xyz.themanusia.submissionjetpack2.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;

public class TvViewModel extends ViewModel {
    private Repository repository;

    public TvViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.isLoading();
    }

    public LiveData<List<TvEntity>> getTvList() {
        return repository.getTvList();
    }
}
