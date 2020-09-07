package com.example.tokyo2020;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tokyo2020.Model.Rating;
import com.example.tokyo2020.Model.TouristAttraction;
import com.example.tokyo2020.Model.User;
import com.example.tokyo2020.Model.Wishlist;

import java.util.List;

public class TouristAttractionDetailViewActivity extends AppCompatActivity {

    ImageView wishlistImage, taimage;
    TextView taname, taaddress, tadescription;
    Toolbar tatoolbar;
    User user;
    TouristAttraction touristAttraction;
    ActionBar actionBar;
    RatingBar ratingBar;
    List<Rating> ratings;
    int ratingListSize, wishListSize;
    Rating ratingObject;
    Wishlist wishlist;
    List<Wishlist> wishlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_detail_view);

        tatoolbar = findViewById(R.id.tatoolbar);
        setSupportActionBar(tatoolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        touristAttraction = (TouristAttraction) getIntent().getSerializableExtra("touristAttraction");
        user = (User) getIntent().getSerializableExtra("user");
        taname = findViewById(R.id.taname);
        taaddress = findViewById(R.id.taaddress);
        tadescription = findViewById(R.id.tadescription);
        ratingBar = findViewById(R.id.ratingBar);
        taimage = findViewById(R.id.taimage);
        wishlistImage = findViewById(R.id.wishlistImage);

        ratings = LoginActivity.myDatabase.ratingDAO().getRating(user.getId(), touristAttraction.getId());
        wishlists = LoginActivity.myDatabase.wishlistDAO().getWishlist(user.getId(), touristAttraction.getId());
        ratingListSize = ratings.size();
        wishListSize = wishlists.size();

        if(ratingListSize==0) {
            ratingBar.setRating(0);
        }
        else {
            ratingBar.setRating(ratings.get(0).getRating());
        }

        if (wishListSize==0) {
            wishlistImage.setImageResource(R.drawable.blankheart);
        }
        else {
            wishlistImage.setImageResource(R.drawable.filledheart);
        }

        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tokyo2020/"+touristAttraction.getTouristAttractionName()+".png");
        taimage.setImageBitmap(bitmap);
        taname.setText(touristAttraction.getTouristAttractionName());
        taaddress.setText(touristAttraction.getTouristAttractionAddress());
        tadescription.setText(touristAttraction.getTouristAttractionDescription());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingObject = new Rating(user.getId(), touristAttraction.getId(), (int)rating);
                if(ratingListSize==0) {
                    LoginActivity.myDatabase.ratingDAO().addRating(ratingObject);
                }
                else {
                    LoginActivity.myDatabase.ratingDAO().editRating(user.getId(), touristAttraction.getId(), (int)rating);
                }
            }
        });

        wishlistImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlists = LoginActivity.myDatabase.wishlistDAO().getWishlist(user.getId(), touristAttraction.getId());
                wishListSize = wishlists.size();
                if(wishListSize==0) {
                    wishlist = new Wishlist(user.getId(), touristAttraction.getId());
                    LoginActivity.myDatabase.wishlistDAO().addWishlist(wishlist);
                    wishlistImage.setImageResource(R.drawable.filledheart);
                }
                else {
                    LoginActivity.myDatabase.wishlistDAO().deleteWishlist(wishlists.get(0));
                    wishlistImage.setImageResource(R.drawable.blankheart);
                }
            }
        });
    }
}
