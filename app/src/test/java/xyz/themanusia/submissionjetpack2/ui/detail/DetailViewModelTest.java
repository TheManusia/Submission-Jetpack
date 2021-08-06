package xyz.themanusia.submissionjetpack2.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import xyz.themanusia.submissionjetpack2.data.MovieEntity;
import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private final MovieEntity dummyMovie = DataDummy.generateMovieData().get(0);
    private final TvEntity dummyTv = DataDummy.generateTvData().get(0);
    private final int movieId = dummyMovie.getMovieId();
    private final int tvId = dummyTv.getTvId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Repository repository;

    @Mock
    private Observer<MovieEntity> movieEntityObserver;

    @Mock
    private Observer<TvEntity> tvEntityObserver;

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(repository);
        viewModel.setTvId(tvId);
        viewModel.setMovieId(movieId);
    }

    // Memastikan Movie Detail Tidak Null
    // Memastikan Isi Movie Detail Benar
    // Memastikan function pada kelas repository terpanggil
    // Memanipulasi data ketika pemanggilan data movie pada kelas repository
    @Test
    public void getMovieDetail() {
        MutableLiveData<MovieEntity> movieEntityMutableLiveData = new MutableLiveData<>();
        movieEntityMutableLiveData.setValue(dummyMovie);
        when(repository.getMovieDetail(movieId)).thenReturn(movieEntityMutableLiveData);
        MovieEntity movie = viewModel.getMovieDetail().getValue();
        assertNotNull(movie);
        assertEquals(movie.getMovieId(), dummyMovie.getMovieId());
        assertEquals(movie.getTitle(), dummyMovie.getTitle());
        assertEquals(movie.getOverview(), dummyMovie.getOverview());
        assertEquals(movie.getImage(), dummyMovie.getImage());
        assertEquals(movie.getRating(), dummyMovie.getRating(), 0);
        assertEquals(movie.getYear(), dummyMovie.getYear());

        viewModel.getMovieDetail().observeForever(movieEntityObserver);
        verify(movieEntityObserver).onChanged(dummyMovie);
    }

    // Memastikan Tv Detail Tidak Null
    // Memastikan isi Tv Detail Benar
    // Memastikan function pada kelas repository terpanggil
    // Memanipulasi data ketika pemanggilan data tv pada kelas repository
    @Test
    public void getTvDetail() {
        MutableLiveData<TvEntity> tvEntityMutableLiveData = new MutableLiveData<>();
        tvEntityMutableLiveData.setValue(dummyTv);
        when(repository.getTvDetail(tvId)).thenReturn(tvEntityMutableLiveData);
        TvEntity tv = viewModel.getTvDetail().getValue();
        assertNotNull(tv);
        assertEquals(tv.getTvId(), dummyTv.getTvId());
        assertEquals(tv.getTitle(), dummyTv.getTitle());
        assertEquals(tv.getOverview(), dummyTv.getOverview());
        assertEquals(tv.getImage(), dummyTv.getImage());
        assertEquals(tv.getRating(), dummyTv.getRating(), 0);
        assertEquals(tv.getYear(), dummyTv.getYear());

        viewModel.getTvDetail().observeForever(tvEntityObserver);
        verify(tvEntityObserver).onChanged(dummyTv);
    }
}