package xyz.themanusia.submissionjetpack.ui.tv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lombok.NoArgsConstructor;
import xyz.themanusia.submissionjetpack.R;

@NoArgsConstructor
public class TvFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }
}