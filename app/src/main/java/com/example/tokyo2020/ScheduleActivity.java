package com.example.tokyo2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ScheduleActivity extends Fragment {

    Button overallSchedule, scheduleByDay;
    Intent intent;
    FragmentTransaction fragmentTransaction;

    public ScheduleActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_schedule, container, false);
        overallSchedule = view.findViewById(R.id.overallSchedule);
        overallSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.scheduleFragment, new OverallScheduleActivity()).commit();
            }
        });

        scheduleByDay = view.findViewById(R.id.scheduleByDay);
        scheduleByDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.scheduleFragment, new ScheduleByDayActivity()).commit();
            }
        });
        return view;
    }
}
