package com.example.alevtinafragment;

import android.content.ContentProvider;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
/*        // Создаем фрагмент
        CitiesFragment citiesFragment = new CitiesFragment();
        // Вызываем FragmentManager
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, citiesFragment)
                .commit();*/
/*        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.coat_of_arms_container, new CitiesFragment()).commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, new CitiesFragment()).commit();
        }*/
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new CitiesFragment())
                    .commit();
        }
    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_about) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container, new AboutFragment()).commit();
            return true;
        } else if (id == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}