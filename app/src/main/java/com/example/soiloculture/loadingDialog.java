package com.example.soiloculture;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class loadingDialog {
    private Activity activity;
    private AlertDialog dialog;

    public loadingDialog(Activity myActivity){
        activity = myActivity;

    }
    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }



}
