package xyz.themanusia.submissionjetpack2.ui.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import xyz.themanusia.submissionjetpack2.R;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.databinding.ItemsBinding;
import xyz.themanusia.submissionjetpack2.ui.detail.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<MovieEntity> listMovie = new ArrayList<>();

    public MovieAdapter(List<MovieEntity> movies) {
        if (movies == null) return;
        listMovie.clear();
        listMovie.addAll(movies);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsBinding binding = ItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemsBinding binding;

        public ViewHolder(@NonNull ItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(MovieEntity movie) {
            binding.tvTitle.setText(movie.getTitle());
            binding.tvRating.setText(movie.getRating());
            binding.tvOverview.setText(movie.getOverview());

            Glide.with(itemView.getContext())
                    .load(movie.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_baseline_warning_24)
                            .error(R.drawable.ic_baseline_warning_24))
                    .into(binding.imgPoster);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.getMovieId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}