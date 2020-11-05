package xyz.themanusia.submissionjetpack.ui.detail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.themanusia.submissionjetpack.R;
import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.data.TvEntity;

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