package xyz.themanusia.submissionjetpack.ui.tv;

import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.themanusia.submissionjetpack.data.TvEntity;
import xyz.themanusia.submissionjetpack.utils.DataDummy;

public class TvViewModel extends ViewModel {

    public List<TvEntity> getTvList() {
        return DataDummy.generateTvData();
    }
}
