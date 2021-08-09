package xyz.themanusia.submissionjetpack2.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.remote.DataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.RemoteDataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.Response;
import xyz.themanusia.submissionjetpack2.utils.ApiHelper;
import xyz.themanusia.submissionjetpack2.utils.EspressoIdlingResource;

public class Repository implements DataSource {
    private volatile static Repository INSTANCE = null;
    private final RemoteDataSource remoteDataSource;

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMsg = new MutableLiveData<>();

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
        EspressoIdlingResource.increment();
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        remoteDataSource.getMovieList(new ApiHelper.LoadMovieListCallback() {
            @Override
            public void onLoadMovieList(List<Response> movieRespons) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (Response responses : movieRespons) {
                    movieEntities.add(new MovieEntity(
                            responses.getId(),
                            responses.getTitle(),
                            responses.getOverview(),
                            responses.getImage(),
                            responses.getRating(),
                            responses.getYear()
                    ));
                }
                movies.postValue(movieEntities);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }

            @Override
            public void onFailure(String message) {
                errorMsg.postValue(message);
            }
        });
        return movies;
    }

    @Override
    public LiveData<MovieEntity> getMovieDetail(int movieId) {
        EspressoIdlingResource.increment();
        MutableLiveData<MovieEntity> movie = new MutableLiveData<>();
        remoteDataSource.getMovieDetail(movieId, new ApiHelper.LoadMovieDetailCallback() {
            @Override
            public void onLoadMovieDetail(Response responses) {
                movie.postValue(new MovieEntity(
                        responses.getId(),
                        responses.getTitle(),
                        responses.getOverview(),
                        responses.getImage(),
                        responses.getRating(),
                        responses.getYear()
                ));
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }

            @Override
            public void onFailure(String message) {
                errorMsg.postValue(message);
            }
        });
        return movie;
    }

    @Override
    public LiveData<List<TvEntity>> getTvList() {
        EspressoIdlingResource.increment();
        MutableLiveData<List<TvEntity>> tvs = new MutableLiveData<>();
        remoteDataSource.getTvList(new ApiHelper.LoadTvListCallback() {
            @Override
            public void onLoadTvListovieList(List<Response> respons) {
                List<TvEntity> tvEntities = new ArrayList<>();
                for (Response response : respons) {
                    tvEntities.add(new TvEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getOverview(),
                            response.getImage(),
                            response.getRating(),
                            response.getYear()
                    ));
                }
                tvs.postValue(tvEntities);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }

            @Override
            public void onFailure(String message) {
                errorMsg.postValue(message);
            }
        });
        return tvs;
    }

    @Override
    public LiveData<TvEntity> getTvDetail(int tvId) {
        EspressoIdlingResource.increment();
        MutableLiveData<TvEntity> tvs = new MutableLiveData<>();
        remoteDataSource.getTvDetail(tvId, new ApiHelper.LoadTvDetailCallback() {
            @Override
            public void onLoadTvDetail(Response response) {
                tvs.postValue(new TvEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getOverview(),
                        response.getImage(),
                        response.getRating(),
                        response.getYear()
                ));
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onLoading(boolean status) {
                isLoading.postValue(status);
            }

            @Override
            public void onFailure(String message) {
                errorMsg.postValue(message);
            }
        });
        return tvs;
    }

    @Override
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    @Override
    public LiveData<String> getErrorMsg() {
        return errorMsg;
    }
}
