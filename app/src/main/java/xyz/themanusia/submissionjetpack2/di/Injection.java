package xyz.themanusia.submissionjetpack2.di;

import xyz.themanusia.submissionjetpack2.data.source.Repository;
import xyz.themanusia.submissionjetpack2.data.source.remote.RemoteDataSource;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.utils.ApiHelper;

public class Injection {
    public static Repository provideRepository(ApiConfig apiConfig) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new ApiHelper(apiConfig));

        return Repository.getInstance(remoteDataSource);
    }
}
