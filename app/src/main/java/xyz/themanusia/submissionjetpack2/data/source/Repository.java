package xyz.themanusia.submissionjetpack2.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.remote.DataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.RemoteDataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.MovieResponse;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.TvResponse;
import xyz.themanusia.submissionjetpack2.utils.ApiHelper;

public class Repository implements DataSource {
    private volatile static Repository INSTANCE = null;
    private final RemoteDataSource remoteDataSource;

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private Repository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(RemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(remoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieEntity>> getMovieList() {
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        remoteDataSource.getMovieList(new ApiHelper.LoadMovieListCallback() {
            @Override
            public void onLoadMovieList(List<MovieResponse> movieResponses) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieResponse movieResponse : movieResponses) {
                    movieEntities.add(new MovieEntity(
                            movieResponse.getMovieId(),
                            movieResponse.getTitle(),
                            movieResponse.getOverview(),
                            movieResponse.getImage(),
                            movieResponse.getRating(),
                            movieResponse.getYear()
                    ));
                }
                movies.postValue(movieEntities);
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }
        });
        return movies;
    }

    @Override
    public LiveData<MovieEntity> getMovieDetail(int movieId) {
        MutableLiveData<MovieEntity> movie = new MutableLiveData<>();
        remoteDataSource.getMovieDetail(movieId, new ApiHelper.LoadMovieDetailCallback() {
            @Override
            public void onLoadMovieDetail(MovieResponse movieResponse) {
                movie.postValue(new MovieEntity(
                        movieResponse.getMovieId(),
                        movieResponse.getTitle(),
                        movieResponse.getOverview(),
                        movieResponse.getImage(),
                        movieResponse.getRating(),
                        movieResponse.getYear()
                ));
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }
        });
        return movie;
    }

    @Override
    public LiveData<List<TvEntity>> getTvList() {
        MutableLiveData<List<TvEntity>> tvs = new MutableLiveData<>();
        remoteDataSource.getTvList(new ApiHelper.LoadTvListCallback() {
            @Override
            public void onLoadTvListovieList(List<TvResponse> tvResponses) {
                List<TvEntity> tvEntities = new ArrayList<>();
                for (TvResponse tvResponse : tvResponses) {
                    tvEntities.add(new TvEntity(
                            tvResponse.getTvId(),
                            tvResponse.getTitle(),
                            tvResponse.getOverview(),
                            tvResponse.getImage(),
                            tvResponse.getRating(),
                            tvResponse.getYear()
                    ));
                }
                tvs.postValue(tvEntities);
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }
        });
        return tvs;
    }

    @Override
    public LiveData<TvEntity> getTvDetail(int tvId) {
        MutableLiveData<TvEntity> tvs = new MutableLiveData<>();
        remoteDataSource.getTvDetail(tvId, new ApiHelper.LoadTvDetailCallback() {
            @Override
            public void onLoadTvDetail(TvResponse tvResponse) {
                tvs.postValue(new TvEntity(
                        tvResponse.getTvId(),
                        tvResponse.getTitle(),
                        tvResponse.getOverview(),
                        tvResponse.getImage(),
                        tvResponse.getRating(),
                        tvResponse.getYear()
                ));
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }
        });
        return tvs;
    }

    @Override
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}
