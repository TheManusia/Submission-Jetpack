package xyz.themanusia.submissionjetpack2.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import lombok.NoArgsConstructor;
import xyz.themanusia.submissionjetpack2.databinding.FragmentMovieBinding;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.viewmodel.ViewModelFactory;

@NoArgsConstructor
public class MovieFragment extends Fragment {
    private FragmentMovieBinding binding;
    private MovieViewModel viewModel;

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
            ViewModelFactory factory = ViewModelFactory.getInstance(new ApiConfig());
            viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);
            getDatas();

            viewModel.getIsLoading().observe(getActivity(), aBoolean -> binding.swpMovie.setRefreshing(aBoolean));

            viewModel.getErrorMsg().observe(getViewLifecycleOwner(), msg -> {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                binding.swpMovie.setRefreshing(false);
            });

            binding.swpMovie.setOnRefreshListener(this::getDatas);
        }
    }

    private void getDatas() {
        viewModel.getMovieList().observe(getViewLifecycleOwner(), movieEntities -> {
            binding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvMovie.setHasFixedSize(true);
            binding.rvMovie.setAdapter(new MovieAdapter(movieEntities));
        });
    }
}