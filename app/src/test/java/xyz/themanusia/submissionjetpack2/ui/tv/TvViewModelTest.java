package xyz.themanusia.submissionjetpack2.ui.tv;

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

import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.data.source.Repository;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;

@RunWith(MockitoJUnitRunner.class)
public class TvViewModelTest {
    private TvViewModel viewModel;
    private final List<TvEntity> dummyTvs = DataDummy.generateTvData();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Repository repository;

    @Mock
    private Observer<List<TvEntity>> listObserver;

    @Before
    public void setUp() {
        viewModel = new TvViewModel(repository);
    }

    // Memastikan Tv List Tidak Null
    // Memastikan Jumlah Tv List Benar
    // Memastikan function pada kelas repository terpanggil
    // Memanipulasi data ketika pemanggilan data tv pada kelas repository
    @Test
    public void getTvList() {
        MutableLiveData<List<TvEntity>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(dummyTvs);
        when(repository.getTvList()).thenReturn(mutableLiveData);

        List<TvEntity> tvs = viewModel.getTvList().getValue();
        assertNotNull(tvs);
        assertEquals(10, tvs.size());

        viewModel.getTvList().observeForever(listObserver);
        verify(listObserver).onChanged(dummyTvs);
    }
}