package xyz.themanusia.submissionjetpack2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.themanusia.submissionjetpack2.data.source.Repository;
import xyz.themanusia.submissionjetpack2.di.Injection;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.ui.detail.DetailViewModel;
import xyz.themanusia.submissionjetpack2.ui.movie.MovieViewModel;
import xyz.themanusia.submissionjetpack2.ui.tv.TvViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final Repository repository;

    private ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory getInstance(ApiConfig apiConfig) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(apiConfig));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(repository);
        } else if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(repository);
        } else if (modelClass.isAssignableFrom(TvViewModel.class)) {
            return (T) new TvViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
