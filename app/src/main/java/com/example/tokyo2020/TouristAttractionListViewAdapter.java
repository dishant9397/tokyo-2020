package com.example.tokyo2020;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tokyo2020.Model.TouristAttraction;

import java.util.List;

class TouristAttractionListViewAdapter extends ArrayAdapter<TouristAttraction> {

    private List<TouristAttraction> touristAttractions;
    private Context context;
    private int resource;

    public TouristAttractionListViewAdapter(@NonNull Context context, int resource, @NonNull List<TouristAttraction> objects) {
        super(context, resource, objects);
        touristAttractions = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = touristAttractions.get(position).getTouristAttractionName();
        String address = touristAttractions.get(position).getTouristAttractionAddress();
        String description = touristAttractions.get(position).getTouristAttractionDescription();

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(this.resource,parent,false);

        TextView tanameandaddress = convertView.findViewById(R.id.touristAttractionListNameandAddress);
        TextView tadescription = convertView.findViewById(R.id.touristAttractionListDescription);
        ImageView taimage = convertView.findViewById(R.id.touristAttractionListImage);

        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Tokyo2020/"+name+".png");
        tanameandaddress.setText(name+" at "+address);
        tadescription.setText(description);
        taimage.setImageBitmap(bitmap);
        return convertView;
    }
}
