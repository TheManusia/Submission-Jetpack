package xyz.themanusia.submissionjetpack2.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.source.remote.RemoteDataSource;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.MovieResponse;
import xyz.themanusia.submissionjetpack2.data.source.remote.response.TvResponse;
import xyz.themanusia.submissionjetpack2.utils.ApiHelper;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;
import xyz.themanusia.submissionjetpack2.utils.LiveDataTestUtils;

public class RepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteDataSource remoteDataSource = mock(RemoteDataSource.class);
    private final FakeRepository repository = new FakeRepository(remoteDataSource);

    private final List<MovieResponse> dummyMovies = DataDummy.generateMovieDataResponses();
    private final List<TvResponse> dummyTvs = DataDummy.generateTvDataResponses();
    private final MovieResponse dummyMovie = DataDummy.generateMovieDataResponses().get(0);
    private final TvResponse dummyTv = DataDummy.generateTvDataResponses().get(0);
    private final int movieId = DataDummy.generateMovieData().get(0).getMovieId();
    private final int tvId = DataDummy.generateTvData().get(0).getTvId();
    
    @Test
    public void getMovieList() {
        doAnswer(invocation -> {
            ((ApiHelper.LoadMovieListCallback) invocation.getArguments()[0])
                    .onLoadMovieList(dummyMovies);
            return null;
        }).when(remoteDataSource).getMovieList(any(ApiHelper.LoadMovieListCallback.class));
        List<MovieEntity> movieEntities = LiveDataTestUtils.getValue(repository.getMovieList());
        verify(remoteDataSource).getMovieList(any(ApiHelper.LoadMovieListCallback.class));
        assertNotNull(movieEntities);
        assertEquals(dummyMovies.size(), movieEntities.size());
    }

    @Test
    public void getMovieDetail() {
        doAnswer(invocation -> {
            ((ApiHelper.LoadMovieDetailCallback) invocation.getArguments()[1])
                    .onLoadMovieDetail(dummyMovie);
            return null;
        }).when(remoteDataSource).getMovieDetail(eq(movieId), any(ApiHelper.LoadMovieDetailCallback.class));

        MovieEntity movieEntity = LiveDataTestUtils.getValue(repository.getMovieDetail(movieId));
        verify(remoteDataSource).getMovieDetail(eq(movieId), any(ApiHelper.LoadMovieDetailCallback.class));

        assertNotNull(movieEntity);
        assertNotNull(movieEntity);
        assertEquals(movieEntity.getMovieId(), dummyMovie.getMovieId());
        assertEquals(movieEntity.getTitle(), dummyMovie.getTitle());
        assertEquals(movieEntity.getOverview(), dummyMovie.getOverview());
        assertEquals(movieEntity.getImage(), dummyMovie.getImage());
        assertEquals(movieEntity.getRating(), dummyMovie.getRating(), 0);
        assertEquals(movieEntity.getYear(), dummyMovie.getYear());
    }

    @Test
    public void getTvList() {
        doAnswer(invocation -> {
            ((ApiHelper.LoadTvListCallback) invocation.getArguments()[0])
                    .onLoadTvListovieList(dummyTvs);
            return null;
        }).when(remoteDataSource).getTvList(any(ApiHelper.LoadTvListCallback.class));

        List<TvEntity> tvEntities = LiveDataTestUtils.getValue(repository.getTvList());
        verify(remoteDataSource).getTvList(any(ApiHelper.LoadTvListCallback.class));
        assertNotNull(tvEntities);
        assertEquals(tvEntities.size(), dummyTvs.size());
    }

    @Test
    public void getTvDetail() {
        doAnswer(invocation -> {
            ((ApiHelper.LoadTvDetailCallback) invocation.getArguments()[1])
                    .onLoadTvDetail(dummyTv);
            return null;
        }).when(remoteDataSource).getTvDetail(eq(tvId), any(ApiHelper.LoadTvDetailCallback.class));

        TvEntity tvEntity = LiveDataTestUtils.getValue(repository.getTvDetail(tvId));
        verify(remoteDataSource).getTvDetail(eq(tvId), any(ApiHelper.LoadTvDetailCallback.class));
        assertNotNull(tvEntity);
        assertEquals(tvEntity.getTvId(), dummyTv.getTvId());
        assertEquals(tvEntity.getTitle(), dummyTv.getTitle());
        assertEquals(tvEntity.getOverview(), dummyTv.getOverview());
        assertEquals(tvEntity.getImage(), dummyTv.getImage());
        assertEquals(tvEntity.getRating(), dummyTv.getRating(), 0);
        assertEquals(tvEntity.getYear(), dummyTv.getYear());
    }
}
