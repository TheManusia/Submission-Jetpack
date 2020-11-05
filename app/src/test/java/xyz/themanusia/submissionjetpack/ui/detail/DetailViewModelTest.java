package xyz.themanusia.submissionjetpack.ui.detail;

import org.junit.Before;
import org.junit.Test;

import xyz.themanusia.submissionjetpack.data.MovieEntity;
import xyz.themanusia.submissionjetpack.data.TvEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

import static org.junit.Assert.*;

public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private final MovieEntity dummyMovie = DataDummy.generateMovieData().get(0);
    private final TvEntity dummyTv = DataDummy.generateTvData().get(0);
    private final String movieId = dummyMovie.getMovieId();
    private final String tvId = dummyTv.getTvId();

    @Before
    public void setUp() {
        viewModel = new DetailViewModel();
        viewModel.setTvId(tvId);
        viewModel.setMovieId(movieId);
    }

    //Memastikan Movie Detail Tidak Null
    //Memastikan Isi Movie Detail Benar
    @Test
    public void getMovieDetail() {
        MovieEntity movie = viewModel.getMovieDetail();
        assertNotNull(movie);
        assertEquals(movie.getMovieId(), dummyMovie.getMovieId());
        assertEquals(movie.getTitle(), dummyMovie.getTitle());
        assertEquals(movie.getOverview(), dummyMovie.getOverview());
        assertEquals(movie.getImage(), dummyMovie.getImage());
        assertEquals(movie.getRating(), dummyMovie.getRating());
        assertEquals(movie.getYear(), dummyMovie.getYear());
    }

    //Memastikan Tv Detail Tidak Null
    //Memastikan isi Tv Detail Benar
    @Test
    public void getTvDetail() {
        TvEntity tv = viewModel.getTvDetail();
        assertNotNull(tv);
        assertEquals(tv.getTvId(), dummyTv.getTvId());
        assertEquals(tv.getTitle(), dummyTv.getTitle());
        assertEquals(tv.getOverview(), dummyTv.getOverview());
        assertEquals(tv.getImage(), dummyTv.getImage());
        assertEquals(tv.getRating(), dummyTv.getRating());
        assertEquals(tv.getYear(), dummyTv.getYear());
    }
}