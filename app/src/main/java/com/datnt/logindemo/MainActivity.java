package com.datnt.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    String firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        firstTime = preferences.getString("FirstTimeInstall", "");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (firstTime.equals("Yes")) {
                    Intent intent = new Intent(getBaseContext(), ContentMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getBaseContext(), MainIntro.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("FirstTimeInstall", "Yes");
                    editor.apply();
                }
            }
        }, 2000);
    }
}