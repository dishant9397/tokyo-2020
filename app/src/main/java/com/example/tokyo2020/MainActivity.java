package com.example.tokyo2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tokyo2020.Model.TouristAttraction;
import com.example.tokyo2020.Model.User;
import com.example.tokyo2020.Model.Wishlist;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    Intent intent;
    TextView username;
    View view;
    User user;
    List<Wishlist> wishlists;
    List<TouristAttraction> touristAttractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        view = navigationView.getHeaderView(0);
        intent = getIntent();
        user = (User) getIntent().getSerializableExtra("user");
        username = view.findViewById(R.id.username);
        username.setText("Hello, "+user.getUsername());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeActivity()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id==R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeActivity()).commit();
        }
        else if(id==R.id.touristAttraction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new TouristAttractionListActivity(user, false)).commit();
        }
        else if(id==R.id.reminder) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ReminderActivity()).commit();
        }
        else if(id==R.id.schedule) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ScheduleActivity()).commit();
        }
        else if(id==R.id.contactUs) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ContactUsActivity()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.wishlist) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new TouristAttractionListActivity(user, true)).commit();
        }
        else if(id==R.id.logout) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
