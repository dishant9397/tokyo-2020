package com.example.tokyo2020;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class ScheduleByDayActivity extends Fragment {

    Button date;
    WebView webView;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_schedule_by_day, container, false);
        date = view.findViewById(R.id.date);
        webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment(webView);
                datePicker.show(getActivity().getSupportFragmentManager(),"datePicker");
            }
        });
        return view;
    }
}
