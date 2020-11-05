package xyz.themanusia.submissionjetpack.ui.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.themanusia.submissionjetpack.R;
import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.ui.detail.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieEntity> listMovie = new ArrayList<>();

    public MovieAdapter(List<MovieEntity> movies) {
        if (movies == null) return;
        listMovie.clear();
        listMovie.addAll(movies);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieEntity movie = listMovie.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvRating)
        TextView tvRating;
        @BindView(R.id.tvOverview)
        TextView tvOverview;
        @BindView(R.id.imgPoster)
        ShapeableImageView imgPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MovieEntity movie) {
            tvTitle.setText(movie.getTitle());
            tvRating.setText(movie.getRating());
            tvOverview.setText(movie.getOverview());

            Glide.with(itemView.getContext())
                    .load(movie.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_baseline_warning_24)
                            .error(R.drawable.ic_baseline_warning_24))
                    .into(imgPoster);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.getMovieId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}