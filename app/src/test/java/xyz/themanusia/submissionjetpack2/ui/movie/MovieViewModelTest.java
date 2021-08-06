package xyz.themanusia.submissionjetpack2.ui.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {
    private MovieViewModel viewModel;
    private final List<MovieEntity> dummyMovies = DataDummy.generateMovieData();

    @Mock
    private Observer<List<MovieEntity>> listObserver;

    @Mock
    private Repository repository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(repository);
    }

    // Memastikan Movie List Tidak Null
    // Memastikan Jumlah Movie List Benar
    // Memastikan function pada kelas repository terpanggil
    // Memanipulasi data ketika pemanggilan data movie pada kelas repository
    @Test
    public void getMovieList() {
        MutableLiveData<List<MovieEntity>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(dummyMovies);

        when(repository.getMovieList()).thenReturn(mutableLiveData);
        List<MovieEntity> movies = viewModel.getMovieList().getValue();
        assertNotNull(movies);
        assertEquals(10, movies.size());

        viewModel.getMovieList().observeForever(listObserver);
        verify(listObserver).onChanged(dummyMovies);
    }
}