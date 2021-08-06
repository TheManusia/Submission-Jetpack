package xyz.themanusia.submissionjetpack2.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import xyz.themanusia.submissionjetpack2.R;
import xyz.themanusia.submissionjetpack2.ui.movie.MovieFragment;
import xyz.themanusia.submissionjetpack2.ui.tv.TvFragment;

public class SectionPagerAdapter extends FragmentStateAdapter {

    public SectionPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MovieFragment();
            case 1:
                return new TvFragment();
            default:
                return new Fragment();
        }
    }
}
