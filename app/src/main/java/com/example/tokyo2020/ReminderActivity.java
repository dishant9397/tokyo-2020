package com.example.tokyo2020;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class ReminderActivity extends Fragment {

    String gamesList[] = {"Badminton", "Basketball", "Boxing", "Fencing", "Karate"};
    Spinner games;
    Button remindme;
    ArrayAdapter<String> arrayAdapter;
    String CHANNEL_ID = "Tokyo2020";
    Intent intent;
    PendingIntent pendingIntent;
    Date currentDate;
    Date eventDate;
    long remainingtime;
    DateFormat dateFormat;
    SimpleDateFormat simpleDateFormat;
    AlertDialog.Builder popupBox;
    int notificationId = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_reminder, container, false);
        games = view.findViewById(R.id.games);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, gamesList);
        games.setAdapter(arrayAdapter);
        createNotificationChannel();

        remindme = view.findViewById(R.id.remindme);
        remindme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    currentDate = simpleDateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String game = games.getSelectedItem().toString();
                switch (game) {
                    case "Badminton":
                        try {
                            eventDate = simpleDateFormat.parse("2020-04-01");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Basketball":
                        try {
                            eventDate = simpleDateFormat.parse("2020-04-02");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Boxing":
                        try {
                            eventDate = simpleDateFormat.parse("2020-04-03");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Fencing":
                        try {
                            eventDate = simpleDateFormat.parse("2020-04-04");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Karate":
                        try {
                            eventDate = simpleDateFormat.parse("2020-04-05");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                Toast.makeText(getContext(), currentDate.toString(), Toast.LENGTH_SHORT).show();
                if(currentDate.compareTo(eventDate)>=0) {
                    popupBox = new AlertDialog.Builder(getContext());
                    popupBox.setTitle("Tokyo 2020 Summer Olympics");
                    popupBox.setMessage("The event is already completed!!");
                    popupBox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    popupBox.show();
                }
                else {
                    remainingtime = eventDate.getTime()-Calendar.getInstance().getTimeInMillis();
                    intent = new Intent(getActivity(), ReminderBroadcast.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+remainingtime, pendingIntent);
                    Toast.makeText(getContext(), "You will receive the notification on the day of the event.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel";
            String description = "My Code";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(getContext(), NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
