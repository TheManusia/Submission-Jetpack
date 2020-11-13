package xyz.themanusia.submissionjetpack2.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import xyz.themanusia.submissionjetpack2.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xyz.themanusia.submissionjetpack2.databinding.ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), this);
        binding.vpHome.setAdapter(sectionPagerAdapter);
        binding.tab.setupWithViewPager(binding.vpHome);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}