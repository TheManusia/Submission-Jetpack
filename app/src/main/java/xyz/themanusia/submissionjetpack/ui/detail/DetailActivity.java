package xyz.themanusia.submissionjetpack.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import xyz.themanusia.submissionjetpack.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}