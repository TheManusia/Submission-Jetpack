package xyz.themanusia.submissionjetpack.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import xyz.themanusia.submissionjetpack.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setTitle("");
        }

        new Handler().postDelayed(() -> {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                },
                5000);
    }
}