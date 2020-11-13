package xyz.themanusia.submissionjetpack2.ui.movie;

import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;

public class MovieViewModel extends ViewModel {

    public List<MovieEntity> getMovieList() {
        return DataDummy.generateMovieData();
    }
}
