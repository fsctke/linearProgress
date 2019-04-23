package com.example.linearprogressbar;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    Handler myHandler;
    Runnable myRunnable;
    Timer myTimer;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                dialog = new ProgressDialog(MainActivity.this,R.style.linearProgress);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setIndeterminate(false); //change to true for indeterminate, false for determinate

                dialog.setTitle("Progress Dialog");
                dialog.setMessage("Please wait...");
                dialog.show();
                dialog.setProgress(0);
                dialog.setMax(100);
                myHandler = new Handler();
                myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        i = i + 5; //declare i=0; outside the method and inside the class
                        if (i <= 100) {

                            dialog.setProgress(i);
                        } else {

                            myTimer.cancel();
                            dialog.cancel();
                            i = 0;
                        }
                    }
                };
                myTimer = new Timer();
                myTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        myHandler.post(myRunnable);
                    }
                }, 8000, 500);
                //dialog.cancel();

    }
}
