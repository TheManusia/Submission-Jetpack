package xyz.themanusia.submissionjetpack.ui.tv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NoArgsConstructor;
import xyz.themanusia.submissionjetpack.R;
import xyz.themanusia.submissionjetpack.data.TvEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

@NoArgsConstructor
public class TvFragment extends Fragment {
    @BindView(R.id.rvTv)
    RecyclerView rvTv;
    @BindView(R.id.pbTv)
    ProgressBar pbTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            List<TvEntity> tvEntities = DataDummy.generateTvData();
            pbTv.setVisibility(View.GONE);

            rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTv.setHasFixedSize(true);
            rvTv.setAdapter(new TvAdapter(tvEntities));
        }
    }
}