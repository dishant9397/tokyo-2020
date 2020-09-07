package com.example.tokyo2020;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    WebView webView;

    public DatePickerFragment(WebView webView) {
        this.webView = webView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new DatePickerDialog(getActivity(),this, 2020, 1, 1);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date;
        if(Integer.toString(dayOfMonth).length()==1) {
            date = Integer.toString(year)+"0"+Integer.toString(month+1)+"0"+Integer.toString(dayOfMonth);
        }
        else {
            date = Integer.toString(year)+"0"+Integer.toString(month+1)+Integer.toString(dayOfMonth);
        }
        String url = "https://tokyo2020.org/en/games/schedule/olympic/"+date+".html";
        if(year==2020 && month==6 && dayOfMonth>=22 && dayOfMonth<=31) {
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient());
        }
        else if(year==2020 && month==7 && dayOfMonth<=9) {
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient());
        }
        else {
            AlertDialog.Builder popupBox = new AlertDialog.Builder(getActivity());
            popupBox.setTitle("Tokyo 2020 Summer Olympics");
            popupBox.setMessage("There are no events on the selected date. Try to select a different date");
            popupBox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            popupBox.show();
        }
    }
}
