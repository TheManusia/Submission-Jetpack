package xyz.themanusia.submissionjetpack.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import xyz.themanusia.submissionjetpack.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*new Handler().postDelayed((Runnable) () ->
                        startActivity(new Intent(MainActivity.this, HomeActivity.class)),
                5000);
                */
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }
}