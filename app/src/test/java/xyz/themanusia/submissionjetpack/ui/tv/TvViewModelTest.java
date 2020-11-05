package xyz.themanusia.submissionjetpack.ui.tv;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import xyz.themanusia.submissionjetpack.data.TvEntity;

import static org.junit.Assert.*;

public class TvViewModelTest {
    private TvViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TvViewModel();
    }

    //Memastikan Tv List Tidak Null
    //Memastikan Jumlah Tv List Benar
    @Test
    public void getTvList() {
        List<TvEntity> tvs = viewModel.getTvList();
        assertNotNull(tvs);
        assertEquals(10, tvs.size());
    }
}