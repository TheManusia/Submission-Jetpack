package xyz.themanusia.submissionjetpack2.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import xyz.themanusia.submissionjetpack2.data.source.remote.DataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.RemoteDataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.Response;
import xyz.themanusia.submissionjetpack2.utils.ApiHelper;

public class FakeRepository implements DataSource {
    private final RemoteDataSource remoteDataSource;

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public FakeRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<List<MovieEntity>> getMovieList() {
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
            public void onLoadMovieDetail(Response responses) {
                movie.postValue(new MovieEntity(
                        responses.getId(),
                        responses.getTitle(),
                        responses.getOverview(),
                        responses.getImage(),
                        responses.getRating(),
                        responses.getYear()
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
            public void onLoadTvDetail(Response response) {
                tvs.postValue(new TvEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getOverview(),
                        response.getImage(),
                        response.getRating(),
                        response.getYear()
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
