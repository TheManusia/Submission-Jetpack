package xyz.themanusia.submissionjetpack2.data.source.remote;

import xyz.themanusia.submissionjetpack2.utils.ApiHelper;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private final ApiHelper apiHelper;

    private RemoteDataSource(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public static RemoteDataSource getInstance(ApiHelper apiHelper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(apiHelper);
        }
        return INSTANCE;
    }

    public void getMovieList(ApiHelper.LoadMovieListCallback callback) {
        apiHelper.getMovieList(callback);
    }

    public void getMovieDetail(int movieId, ApiHelper.LoadMovieDetailCallback callback) {
        apiHelper.getMovieDetail(movieId, callback);
    }

    public void getTvList(ApiHelper.LoadTvListCallback callback) {
        apiHelper.getTvList(callback);
    }

    public void getTvDetail(int tvId, ApiHelper.LoadTvDetailCallback callback) {
        apiHelper.getTvDetail(tvId, callback);
    }
}
