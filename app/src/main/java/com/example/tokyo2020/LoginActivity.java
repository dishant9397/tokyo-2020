package com.example.tokyo2020;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.tokyo2020.Model.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText un, pw;
    CheckBox remember;
    Button login;
    String username, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor preferenceEditor;
    public static myDatabase myDatabase;
    List<User> users;
    User user;
    AlertDialog.Builder popupBox;
    Intent intent;
    public static final String preferenceName = "Tokyo 2020 Summer Olympics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDatabase = Room.databaseBuilder(getApplicationContext(),myDatabase.class, "tokyogame").allowMainThreadQueries().build();
        user = new User("admin", "admin", true);
        myDatabase.userDAO().addUser(user);

        un = findViewById(R.id.username);
        pw = findViewById(R.id.password);
        remember = findViewById(R.id.remember);
        login = findViewById(R.id.login);

        sharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        if(sharedPreferences.contains("username")) {
            un.setText(sharedPreferences.getString("username",""));
            pw.setText(sharedPreferences.getString("password",""));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = un.getText().toString();
                password = pw.getText().toString();

                if(username.equals("") || password.equals("")) {
                    popupBox = new AlertDialog.Builder(LoginActivity.this);
                    popupBox.setTitle("Tokyo 2020 Summer Olympics");
                    popupBox.setMessage("Details cannot be blank!");
                    popupBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(getIntent());
                        }
                    });
                    popupBox.show();
                }
                else {
                    users = myDatabase.userDAO().getUserByUsername(username);
                    if(users.size()==0) {
                        popupBox = new AlertDialog.Builder(LoginActivity.this);
                        popupBox.setTitle("Tokyo 2020 Summer Olympics");
                        popupBox.setMessage("Do you want to create a new user with the details provided?");
                        popupBox.setPositiveButton("Yes! Create a new User", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                user = new User(username, password, false);
                                myDatabase.userDAO().addUser(user);
                                Toast.makeText(LoginActivity.this, "User has been created with the entered details!!", Toast.LENGTH_LONG).show();
                            }
                        });
                        popupBox.setNegativeButton("No! I already have a username and a password", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(getIntent());
                            }
                        });
                        popupBox.show();
                    }
                    else {
                        user = users.get(0);
                        if(password.equals(user.getPassword())) {
                            if(user.isAdmin()==true) {
                                intent = new Intent(LoginActivity.this, TouristAttractionActivity.class);
                            }
                            else {
                                intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("user",user);
                            }
                            startActivity(intent);
                        }
                        else {
                            popupBox = new AlertDialog.Builder(LoginActivity.this);
                            popupBox.setTitle("Tokyo 2020 Summer Olympics");
                            popupBox.setMessage("Entered password is not correct!");
                            popupBox.setPositiveButton("Let me enter it again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    pw.getText().clear();
                                    startActivity(getIntent());
                                }
                            });
                            popupBox.show();
                        }
                    }
                }

                boolean checked = remember.isChecked();
                if(checked) {
                    preferenceEditor = sharedPreferences.edit();
                    preferenceEditor.putString("username", username);
                    preferenceEditor.putString("password", password);
                    preferenceEditor.apply();
                }
            }
        });
    }
}
