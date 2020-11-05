package xyz.themanusia.submissionjetpack.ui.movie;

import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

public class MovieViewModel extends ViewModel {

    public List<MovieEntity> getMovieList() {
        return DataDummy.generateMovieData();
    }
}
