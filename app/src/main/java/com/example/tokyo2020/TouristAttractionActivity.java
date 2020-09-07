package com.example.tokyo2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tokyo2020.Model.TouristAttraction;
import com.example.tokyo2020.admin.addTouristAttraction;

import java.util.List;

public class TouristAttractionActivity extends AppCompatActivity {

    List<TouristAttraction> touristAttractions;
    ListView touristAttractionListView;
    TouristAttractionListViewAdapter touristAttractionListViewAdapter;
    Toolbar adminToolbar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction);

        adminToolbar = findViewById(R.id.adminToolbar);
        setSupportActionBar(adminToolbar);

        touristAttractionListView = findViewById(R.id.touristAttractionListView);
        touristAttractions = LoginActivity.myDatabase.touristAttractionDAO().getTouristAttractions();
        touristAttractionListViewAdapter = new TouristAttractionListViewAdapter(this,R.layout.tourist_attraction_list,touristAttractions);
        touristAttractionListView.setAdapter(touristAttractionListViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedMenuItem = item.getItemId();
        switch (selectedMenuItem) {
            case R.id.addTouristAttraction:
                intent = new Intent(TouristAttractionActivity.this, addTouristAttraction.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(TouristAttractionActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}
