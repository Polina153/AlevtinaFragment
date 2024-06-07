package com.example.alevtinafragment;

import android.content.ContentProvider;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        // Создаем фрагмент
        CitiesFragment citiesFragment = new CitiesFragment();
        // Вызываем FragmentManager
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, citiesFragment)
                .commit();*/
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.coat_of_arms_container, new CitiesFragment()).commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, new CitiesFragment()).commit();
        }

    }
}