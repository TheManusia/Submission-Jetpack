package xyz.themanusia.submissionjetpack.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NoArgsConstructor;
import xyz.themanusia.submissionjetpack.R;
import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

@NoArgsConstructor
public class MovieFragment extends Fragment {
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    @BindView(R.id.pbMovie)
    ProgressBar pbMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
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
            List<MovieEntity> movies = DataDummy.generateMovieData();
            pbMovie.setVisibility(View.GONE);

            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(new MovieAdapter(movies));
        }
    }
}