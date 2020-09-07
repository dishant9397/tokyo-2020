package com.example.tokyo2020.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tokyo2020.LoginActivity;
import com.example.tokyo2020.Model.TouristAttraction;
import com.example.tokyo2020.R;
import com.example.tokyo2020.TouristAttractionActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class addTouristAttraction extends AppCompatActivity {

    EditText name, address, description;
    ImageView touristAttractionImage;
    Button addTouristAttractionButton;
    TouristAttraction touristAttraction;
    Toolbar adminToolbar;
    ActionBar actionBar;
    Intent intent;
    OutputStream outputStream;
    private static final int imageCode = 1000;
    private static final int permissionCode = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tourist_attraction);

        adminToolbar = findViewById(R.id.adminToolbar);
        name = findViewById(R.id.touristAttractionName);
        address = findViewById(R.id.touristAttractionAddress);
        description = findViewById(R.id.touristAttractionDescription);
        touristAttractionImage = findViewById(R.id.touristAttractionImage);
        addTouristAttractionButton = findViewById(R.id.addTouristAttractionButton);
        setSupportActionBar(adminToolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        touristAttractionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, permissionCode);
                    }
                    else {
                        selectImage();
                    }
                }
                else {
                    selectImage();
                }
            }
        });

        addTouristAttractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String touristAttractionName = name.getText().toString();
                String touristAttractionAddress = address.getText().toString();
                String touristAttractionDescription = description.getText().toString();
                touristAttraction = new TouristAttraction(touristAttractionName, touristAttractionAddress, touristAttractionDescription);
                LoginActivity.myDatabase.touristAttractionDAO().addTouristAttraction(touristAttraction);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) touristAttractionImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath()+"/Tokyo2020/");
                dir.mkdir();
                File file = new File(dir, touristAttractionName+".png");
                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent = new Intent(addTouristAttraction.this, TouristAttractionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void selectImage() {
        intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, imageCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permissionCode:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    selectImage();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == imageCode) {
            touristAttractionImage.setImageURI(data.getData());
        }
    }
}
