package xyz.themanusia.submissionjetpack2.ui.tv;

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
import xyz.themanusia.submissionjetpack2.databinding.FragmentTvBinding;
import xyz.themanusia.submissionjetpack2.network.api.ApiConfig;
import xyz.themanusia.submissionjetpack2.viewmodel.ViewModelFactory;

@NoArgsConstructor
public class TvFragment extends Fragment {
    private FragmentTvBinding binding;
    private TvViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(new ApiConfig());
            viewModel = new ViewModelProvider(this, factory).get(TvViewModel.class);
            getDatas();

            viewModel.getIsLoading().observe(getViewLifecycleOwner(), aBoolean -> binding.swpTv.setRefreshing(aBoolean));

            viewModel.getErrorMsg().observe(getViewLifecycleOwner(), msg -> {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                binding.swpTv.setRefreshing(false);
            });

            binding.swpTv.setOnRefreshListener(this::getDatas);
        }
    }

    private void getDatas() {
        viewModel.getTvList().observe(getViewLifecycleOwner(), tvEntities -> {
            binding.rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvTv.setHasFixedSize(true);
            binding.rvTv.setAdapter(new TvAdapter(tvEntities));
        });
    }
}