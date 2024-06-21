package com.example.alevtinafragment;

import static com.example.alevtinafragment.CoatOfArmsFragment.ARG_INDEX;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CoatOfArmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_of_arms);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return;
        }

        // Если эта activity запускается первый раз (с каждым новым гербом первый раз),
        // то перенаправим параметр фрагменту и запустим фрагмент
        City city = (City) getIntent().getExtras().getParcelable(ARG_INDEX);
        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.coat_of_arms_fragment_container, CoatOfArmsFragment.newInstance(city))
                    .commit();
    }
}
