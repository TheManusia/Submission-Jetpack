package xyz.themanusia.submissionjetpack2.ui.tv;

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
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.databinding.ItemsBinding;
import xyz.themanusia.submissionjetpack2.ui.detail.DetailActivity;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private final List<TvEntity> listTv = new ArrayList<>();

    TvAdapter(List<TvEntity> tvEntities) {
        if (tvEntities == null) return;
        listTv.clear();
        listTv.addAll(tvEntities);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsBinding binding = ItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvEntity entity = listTv.get(position);
        holder.bind(entity);
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemsBinding binding;

        public ViewHolder(@NonNull ItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(TvEntity entity) {
            binding.tvTitle.setText(entity.getTitle());
            binding.tvRating.setText(String.valueOf(entity.getRating()));
            binding.tvOverview.setText(entity.getOverview());

            Glide.with(itemView.getContext())
                    .load(entity.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_baseline_warning_24)
                            .error(R.drawable.ic_baseline_warning_24))
                    .into(binding.imgPoster);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TV, entity.getTvId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}