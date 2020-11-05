package xyz.themanusia.submissionjetpack.ui.movie;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import xyz.themanusia.submissionjetpack.data.MovieEntity;

import static org.junit.Assert.*;

public class MovieViewModelTest {
    private MovieViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new MovieViewModel();
    }

    //Memastikan Movie List Tidak Null
    //Memastikan Jumlah Movie List Benar
    @Test
    public void getMovieList() {
        List<MovieEntity> movies = viewModel.getMovieList();
        assertNotNull(movies);
        assertEquals(10, movies.size());
    }
}