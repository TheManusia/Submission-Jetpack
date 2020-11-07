package xyz.themanusia.submissionjetpack.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.themanusia.submissionjetpack.R;
import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.data.TvEntity;
import xyz.themanusia.submissionjetpack.ui.home.HomeActivity;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.tvYear)
    TextView tvYear;
    @BindView(R.id.tvOverview)
    TextView tvOverview;
    @BindView(R.id.imgPoster)
    ShapeableImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.detail));
        }

        DetailViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getString(EXTRA_MOVIE) != null) {
                String movieId = extras.getString(EXTRA_MOVIE);
                viewModel.setMovieId(movieId);
                bindMovie(viewModel.getMovieDetail());
            } else if (extras.getString(EXTRA_TV) != null) {
                String tvId = extras.getString(EXTRA_TV);
                viewModel.setTvId(tvId);
                bindTv(viewModel.getTvDetail());
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
                .setText(getResources().getString(R.string.share_text, tvTitle.getText()))
                .startChooser();
    }

    private void bindTv(TvEntity tvEntity) {
        tvTitle.setText(tvEntity.getTitle());
        tvOverview.setText(tvEntity.getOverview());
        tvRating.setText(tvEntity.getRating());
        tvYear.setText(tvEntity.getYear());

        Glide.with(this)
                .load(tvEntity.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_baseline_warning_24)
                        .error(R.drawable.ic_baseline_warning_24))
                .into(imgPoster);
    }

    private void bindMovie(MovieEntity movieEntity) {
        tvTitle.setText(movieEntity.getTitle());
        tvOverview.setText(movieEntity.getOverview());
        tvRating.setText(movieEntity.getRating());
        tvYear.setText(movieEntity.getYear());

        Glide.with(this)
                .load(movieEntity.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_baseline_warning_24)
                        .error(R.drawable.ic_baseline_warning_24))
                .into(imgPoster);
    }
}