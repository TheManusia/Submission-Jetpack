package xyz.themanusia.submissionjetpack2.ui.detail;

import androidx.lifecycle.ViewModel;

import lombok.Setter;
import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;

@Setter
public class DetailViewModel extends ViewModel {
    private int movieId;
    private int tvId;

    public MovieEntity getMovieDetail() {
        for (MovieEntity movieEntity : DataDummy.generateMovieData()) {
            if (movieEntity.getMovieId() == movieId) {
                return movieEntity;
            }
        }
        return null;
    }

    public TvEntity getTvDetail() {
        for (TvEntity entity : DataDummy.generateTvData()) {
            if (entity.getTvId() == tvId) {
                return entity;
            }
        }
        return null;
    }
}
