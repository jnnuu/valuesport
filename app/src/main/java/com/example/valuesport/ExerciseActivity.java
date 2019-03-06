package com.example.valuesport;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//importing the custom LocationService class
import com.example.valuesport.LocationService.LocationService;

public class ExerciseActivity extends AppCompatActivity {

    /*
        variables, constants ans objects used for location and timer purposes
    */
    private LocationService mLocationService;
    TextView distanceView;
    TextView timerView;
    long startTime = 0;

    //creates timer for app
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int)(millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;

            //sets time and distance traveled into the views
            distanceView.setText(String.format("%1$4.0f", mLocationService.getfullDistance()));
            timerView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        /*
        Permission for location usage has already been granted when accessing this activity
         */
        mLocationService = new LocationService(this, this);       //new instance of LocationService with passing this activity and context as an argument
        mLocationService.startListeningLoc();                     //starts listening location

        //sets up views
        timerView = (TextView)findViewById(R.id.timerView);
        distanceView = (TextView)findViewById(R.id.distanceView);
        //starts the timer
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

    }

    //stops exercise and returns to MainActivity
    public void StopExc(View view) {
        //stops listening location updates
        timerHandler.removeCallbacks(timerRunnable);
        mLocationService.stopListeningLoc();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
