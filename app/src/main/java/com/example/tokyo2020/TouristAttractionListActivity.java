package com.example.tokyo2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tokyo2020.Model.TouristAttraction;
import com.example.tokyo2020.Model.User;
import com.example.tokyo2020.Model.Wishlist;

import java.util.List;

public class TouristAttractionListActivity extends Fragment {

    List<TouristAttraction> touristAttractions;
    List<Wishlist> wishlists;
    TouristAttraction touristAttraction;
    ListView touristAttractionListView;
    TouristAttractionListViewAdapter touristAttractionListViewAdapter;
    Intent intent;
    View view;
    User user;
    boolean isWishlist;

    public TouristAttractionListActivity(User user, boolean isWishlist) {
        this.isWishlist = isWishlist;
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_tourist_attraction_list, container, false);

        touristAttractionListView = view.findViewById(R.id.touristAttractionListView);
        if(isWishlist) {
            touristAttractions = LoginActivity.myDatabase.touristAttractionDAO().getTouristAttractionByWishlist(user.getId());
        }
        else {
            touristAttractions = LoginActivity.myDatabase.touristAttractionDAO().getTouristAttractions();
        }
        touristAttractionListViewAdapter = new TouristAttractionListViewAdapter(getActivity(),R.layout.tourist_attraction_list,touristAttractions);
        touristAttractionListView.setAdapter(touristAttractionListViewAdapter);

        touristAttractionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), TouristAttractionDetailViewActivity.class);
                if(isWishlist) {
                    wishlists = LoginActivity.myDatabase.wishlistDAO().getWishlist();
                    touristAttractions = LoginActivity.myDatabase.touristAttractionDAO().getTouristAttractionsById(wishlists.get(position).getTouristAttractionId());
                    intent.putExtra("touristAttraction", touristAttractions.get(0));
                }
                else {
                    intent.putExtra("touristAttraction", touristAttractions.get(position));
                }
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        touristAttractionListViewAdapter.notifyDataSetChanged();
        return view;
    }
}
