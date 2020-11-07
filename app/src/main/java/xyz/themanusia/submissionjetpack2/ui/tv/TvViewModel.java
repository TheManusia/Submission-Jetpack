package xyz.themanusia.submissionjetpack2.ui.tv;

import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.themanusia.submissionjetpack2.data.TvEntity;
import xyz.themanusia.submissionjetpack2.utils.DataDummy;

public class TvViewModel extends ViewModel {

    public List<TvEntity> getTvList() {
        return DataDummy.generateTvData();
    }
}
