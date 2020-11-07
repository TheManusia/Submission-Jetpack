package xyz.themanusia.submissionjetpack2.ui.tv;

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
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.databinding.FragmentTvBinding;

@NoArgsConstructor
public class TvFragment extends Fragment {
    private FragmentTvBinding binding;

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
            TvViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvViewModel.class);
            List<TvEntity> tvEntities = viewModel.getTvList();
            binding.pbTv.setVisibility(View.GONE);

            binding.rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvTv.setHasFixedSize(true);
            binding.rvTv.setAdapter(new TvAdapter(tvEntities));
        }
    }
}