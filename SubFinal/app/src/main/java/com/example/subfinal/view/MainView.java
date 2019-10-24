package com.example.subfinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.subfinal.R;
import com.example.subfinal.widget.ReminderReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainView extends AppCompatActivity {
    private static final String STATE_TITLE = "state_title";
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(mBottomNavItemSelected);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            title = getString(R.string.title_movie);
            loadFragment(new MovieView());
            getSupportActionBar().setTitle(title);
        } else {
            title = savedInstanceState.getString(STATE_TITLE);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_reminder_settings) {
            Intent intent = new Intent(MainView.this, ReminderPreferenceView.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, title);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mBottomNavItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_movie:
                    loadFragment(new MovieView());
                    title = getString(R.string.title_movie);
                    getSupportActionBar().setTitle(R.string.title_movie);
                    return true;
                case R.id.navigation_show:
                    loadFragment(new ShowView());
                    title = getResources().getString(R.string.title_show);
                    getSupportActionBar().setTitle(R.string.title_show);
                    return true;
                case R.id.navigation_favorite:
                    loadFragment(new MainFavoriteView());
                    title = getResources().getString(R.string.title_favorite);
                    getSupportActionBar().setTitle(R.string.title_favorite);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
