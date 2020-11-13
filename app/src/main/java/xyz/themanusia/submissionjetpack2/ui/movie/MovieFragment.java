package xyz.themanusia.submissionjetpack2.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import lombok.NoArgsConstructor;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.databinding.FragmentMovieBinding;

@NoArgsConstructor
public class MovieFragment extends Fragment {
    private FragmentMovieBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
            List<MovieEntity> movies = viewModel.getMovieList();
            binding.pbMovie.setVisibility(View.GONE);

            binding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvMovie.setHasFixedSize(true);
            binding.rvMovie.setAdapter(new MovieAdapter(movies));
        }
    }
}