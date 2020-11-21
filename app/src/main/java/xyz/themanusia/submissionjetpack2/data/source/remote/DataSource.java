package xyz.themanusia.submissionjetpack2.data.source.remote;

import androidx.lifecycle.LiveData;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;

public interface DataSource {

    LiveData<List<MovieEntity>> getMovieList();

    LiveData<MovieEntity> getMovieDetail(int movieId);

    LiveData<List<TvEntity>> getTvList();

    LiveData<TvEntity> getTvDetail(int tvId);

    LiveData<Boolean> isLoading();
}
