package xyz.themanusia.submissionjetpack2.ui.tv;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.network.response.TvResponse;

public class TvViewModel extends ViewModel {

    private static final String TAG = TvViewModel.class.getSimpleName();

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<List<TvEntity>> tv = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<List<TvEntity>> getTvList() {
        isLoading.setValue(true);
        Call<TvResponse> client = ApiConfig.getApiService().getTv();
        client.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tv.setValue(response.body().getTvList());
                    }
                } else {
                    Log.e(TAG, "onResponse: "+response.message() );
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
        return tv;
    }
}
