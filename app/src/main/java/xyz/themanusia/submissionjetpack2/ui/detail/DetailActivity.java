package xyz.themanusia.submissionjetpack2.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import xyz.themanusia.submissionjetpack2.R;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.databinding.ActivityDetailBinding;
import xyz.themanusia.submissionjetpack2.ui.home.HomeActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.detail));
        }

        DetailViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getInt(EXTRA_MOVIE) != 0) {
                int movieId = extras.getInt(EXTRA_MOVIE);
                viewModel.setMovieId(movieId);
                bindMovie(viewModel.getMovieDetail());
            } else if (extras.getInt(EXTRA_TV) != 0) {
                int tvId = extras.getInt(EXTRA_TV);
                viewModel.setTvId(tvId);
                bindTv(viewModel.getTvDetail());
            } else {
                Log.d(TAG, "onCreate: HAHA FOOL, NO ID HERE");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itmShare) {
            share();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return true;
    }

    private void share() {
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share now")
                .setText(getResources().getString(R.string.share_text, binding.tvTitle.getText()))
                .startChooser();
    }

    private void bindTv(TvEntity tvEntity) {
        binding.tvTitle.setText(tvEntity.getTitle());
        binding.tvOverview.setText(tvEntity.getOverview());
        binding.tvRating.setText(String.valueOf(tvEntity.getRating()));
        binding.tvYear.setText(tvEntity.getYear());

        Glide.with(this)
                .load(tvEntity.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_baseline_warning_24)
                        .error(R.drawable.ic_baseline_warning_24))
                .into(binding.imgPoster);
    }

    private void bindMovie(MovieEntity movieEntity) {
        binding.tvTitle.setText(movieEntity.getTitle());
        binding.tvOverview.setText(movieEntity.getOverview());
        binding.tvRating.setText(String.valueOf(movieEntity.getRating()));
        binding.tvYear.setText(movieEntity.getYear());

        Glide.with(this)
                .load(movieEntity.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_baseline_warning_24)
                        .error(R.drawable.ic_baseline_warning_24))
                .into(binding.imgPoster);
    }
}