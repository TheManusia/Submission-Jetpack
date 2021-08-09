package xyz.themanusia.submissionjetpack2.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;

import xyz.themanusia.submissionjetpack2.R;
import xyz.themanusia.submissionjetpack2.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movie, R.string.tvshow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), getLifecycle());
        binding.vpHome.setAdapter(sectionPagerAdapter);

        new TabLayoutMediator(binding.tab, binding.vpHome,
                (tab, position) -> tab.setText(TAB_TITLES[position]))
                .attach();
    }
}