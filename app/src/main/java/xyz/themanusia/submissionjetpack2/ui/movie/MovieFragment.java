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

import lombok.NoArgsConstructor;
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
            viewModel.getMovieList().observe(getViewLifecycleOwner(), movieEntities -> {
                binding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvMovie.setHasFixedSize(true);
                binding.rvMovie.setAdapter(new MovieAdapter(movieEntities));
            });

            viewModel.getIsLoading().observe(getActivity(), aBoolean -> {
                if (aBoolean) {
                    binding.pbMovie.setVisibility(View.VISIBLE);
                } else {
                    binding.pbMovie.setVisibility(View.GONE);
                }
            });
        }
    }
}