package com.safaa.theones2;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate; // import the LocalDate class
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    static int modenum=1;
    TextView timerTextView;
    long startTime = 0;
    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            int hours = minutes/60;
            minutes = minutes % 60;
            timerTextView.setText(String.format("%02d:%02d:%02d",hours, minutes, seconds));
            timerHandler.postDelayed(this, 500);}

    };
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }
    public void onResume(){
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fun1();
        TextView start=(TextView) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timerTextView=findViewById(R.id.maintext);
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
            }});
        TextView pause=(TextView) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });
        TextView resume=(TextView) findViewById(R.id.resume);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void fun1(){
        TextView v=(TextView) findViewById(R.id.maintext);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        v.setText(formattedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void fun2(){
        TextView v=(TextView) findViewById(R.id.maintext);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM");
        String formattedDate = myDateObj.format(myFormatObj);
        v.setText(formattedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void fun3(){
        TextView v=(TextView) findViewById(R.id.maintext);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("00:00:00");
        String formattedDate = myDateObj.format(myFormatObj);
        v.setText(formattedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void fun4(){
        LinearLayout timerlayout = (LinearLayout) findViewById(R.id.timerlayout);
        timerlayout.setVisibility(View.INVISIBLE);
        TextView v=(TextView) findViewById(R.id.maintext);
        v.setText("The One s2");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void mainfunA(View view){
        modenum+=1;
        switch (modenum){
            case 1:
                fun1();
                break;
            case 2:
                fun2();
                break;
            case 3:
                fun3();
                break;
            case 4:
                timerHandler.removeCallbacks(timerRunnable);
                fun4();
                break;
            default:
                modenum=1;
                fun1();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void mainfunC(View view){
        TextView v=(TextView) findViewById(R.id.maintext);
        switch (modenum){
            case 1:
                v.setText("Light");
                break;
            case 2:
                LocalDateTime myDateObj = LocalDateTime.now();
                String formattedDate = myDateObj.getDayOfWeek().toString();
                v.setText(formattedDate);
                break;
            case 3:
                timerHandler.removeCallbacks(timerRunnable);
                v.setText("00:00:00");
                break;
            case 4:
                break;
            default:

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void mainfunB(View view){
        TextView v=(TextView) findViewById(R.id.maintext);
        LocalDateTime myDateObj = LocalDateTime.now();
        switch (modenum){
            case 1:
                int hour=myDateObj.getHour();
                int minute=myDateObj.getMinute();
                String time =  ((hour > 12) ? hour % 12 : hour) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hour >= 12) ? "PM" : "AM");
                v.setText(time);
                break;
            case 2:
                v.setText(String.valueOf(myDateObj.getYear()));
                break;
            case 3:
                LinearLayout timerlayout = (LinearLayout) findViewById(R.id.timerlayout);
                timerlayout.setVisibility(View.VISIBLE);
                timerTextView=findViewById(R.id.maintext);
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                break;
            case 4:
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write("The One s2");
                    outputStreamWriter.close();
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
                break;
            default:

        }
    }

}