package xyz.themanusia.submissionjetpack.ui.detail;

import androidx.lifecycle.ViewModel;

import lombok.Setter;
import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.data.TvEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

@Setter
public class DetailViewModel extends ViewModel {
    private String movieId;
    private String tvId;

    public MovieEntity getMovieDetail() {
        for (MovieEntity movieEntity : DataDummy.generateMovieData()) {
            if (movieEntity.getMovieId().equals(movieId)) {
                return movieEntity;
            }
        }
        return null;
    }

    public TvEntity getTvDetail() {
        for (TvEntity entity : DataDummy.generateTvData()) {
            if (entity.getTvId().equals(tvId)) {
                return entity;
            }
        }
        return null;
    }
}
