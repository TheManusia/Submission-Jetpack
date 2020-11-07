package xyz.themanusia.submissionjetpack2.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.themanusia.submissionjetpack2.R;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager vpHome;
    @BindView(R.id.tabs)
    TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), this);
        vpHome.setAdapter(sectionPagerAdapter);
        tab.setupWithViewPager(vpHome);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}