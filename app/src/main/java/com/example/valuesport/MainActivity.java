package com.example.valuesport;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.valuesport.LocationService.LocationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    //constants used for location purposes
    private static final String TAG = "Permission";                       //TAG for log debug entries
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;    //my permission for fine location

    //start/stop imageButton and flag for checking if exercise in on
    private ImageButton startButton;
    private boolean isExerciseOn = false;
    private static boolean isStartedBefore;

    //variables, constants and objects used for location and timer purposes
    private LocationService mLocationService;
    private TextView distanceView;
    private TextView timerView;
    private long startTime = 0;
    private String distFormatted;

    //creates timer for app
    private Handler timerHandler = new Handler();
    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;

            //sets time and distance traveled into the views
            if(mLocationService.getfullDistance() < 1000){
                distFormatted = String.format("%1$4.0f m", mLocationService.getfullDistance());
            }
            else{
                float num1 = mLocationService.getfullDistance() / 1000;
                distFormatted = String.format("%1$4.2f km", num1);
            }

            distanceView.setText(distFormatted);
            timerView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));


            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d("debug", "onCreate() called");
        WalletSingleton walletInstance = WalletSingleton.getInstance();
        if (!isStartedBefore) {
            loadData();
            loadCredits();
        }
        final int creditit = WalletSingleton.getCredits();
        Log.d("debug", "");
        final TextView credits = findViewById(R.id.credits);
        credits.setText(String.valueOf(creditit));


        /*
         * Following code creates LocationService instance, creates timer for the exercise,
         * and sets up needed views and image button to start and track exercise
         */
        //creating instance of LocationService
        mLocationService = new LocationService(this, this);

        //sets up views
        timerView = (TextView) findViewById(R.id.timerView);
        distanceView = (TextView) findViewById(R.id.distanceView);

        //Sets up imageButton and onclick listener
        startButton = (ImageButton) findViewById(R.id.startButton);
        startButton.setImageResource(R.drawable.start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewButton) {
                ImageButton startButton = (ImageButton) viewButton;

                if (!isExerciseOn) {
                    //starting exercise
                    //checks permission for location
                    checkPermission();
                    //exercise is only starts if user has/gives permission for location
                } else {
                    grantCredits();
                    saveCredits();
                    //exercise ending
                    //removing timer callbacks and location listening
                    //sets flag for exercise to be off
                    startButton.setImageResource(R.drawable.start);
                    timerHandler.removeCallbacks(timerRunnable);
                    startTime = 0;
                    mLocationService.stopListeningLoc();
                    isExerciseOn = false;

                }

            }
        });

        /*
        Set up for location, timer and related views and button ends here
         */
    }
/**
* Loads data from SharedPreferences. If data is not available (does not exist yet), function sets Owned Coupons to empty.
* If data is found, the function updates the Owned Coupon. On the first load, the function sets the value of
* isStartedBefore to true.
*/
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("coupons", null);
        Type type = new TypeToken<ArrayList<Coupon>>() {
        }.getType();
        WalletSingleton walletSingleton = WalletSingleton.getInstance();
        ArrayList<Coupon> temp = new ArrayList<>();
        if (gson.fromJson(json, type) == null) {
            walletSingleton.setOwnedCoupons(temp);
            Log.d("debug", "no data at preferences");
        } else {
            temp = gson.fromJson(json, type);
            walletSingleton.setOwnedCoupons(temp);
            Log.d("debug", "data loaded");
        }

        isStartedBefore = true;
    }
/**
* This function shows the main menu
*/
    public void showPopup(View v) { //Menu buttonin onClick funktio, avaa pudotusvalikon
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.mainmenu);
        popup.show();
    }
/**
* Grants credits based on the distance traveled and divided by 10.
*/
private void grantCredits() {
        float matka = mLocationService.getfullDistance();
        int matkaInt = (Math.round(matka)) / 10;
        Log.d("debug", String.valueOf(matka));
        WalletSingleton.credits = WalletSingleton.credits + matkaInt;
        Log.d("Debug", String.valueOf(WalletSingleton.credits));
        TextView credits = findViewById(R.id.credits);
        credits.setText(String.valueOf(WalletSingleton.getCredits()));
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.store:
                toStore();
                return true;
            case R.id.wallet:
                toWallet();
                return true;
            case R.id.exit:
                return true;
            default:
                return false;
        }
    }
/**
* Starts BuyCouponActivity
*/
private void toStore() {
        Intent intent = new Intent(this, BuyCouponActivity.class);
        startActivity(intent);
    }
/**
* Starts WalletActivity
*/
private void toWallet() {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }

    /*
     This following section of the code contains the methods needed for handling permission cases
     It also defines a dialogue box to communicate the purpose of location to the user
     */

    /**
     * Checks user permission for using the fine location service. If permission is granted,
     * exercise timer and location listening using LocationService starts. If permission is not already granted,
     * permission is asked from the user.
     */

    //Method for asking permission for location if needed
    private void checkPermission() {
        //Checks if the application has permission for fine location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION);
        } else {
            Log.d(TAG, "permission is already granted for location");
            //if permission is already granted, the button image will be set to stop
            //and exercise is deemed on
            startButton.setImageResource(R.drawable.stop);
            timerHandler.postDelayed(timerRunnable, 0);
            mLocationService.startListeningLoc();
            startTime = System.currentTimeMillis();
            isExerciseOn = true;
        }
    }

    //dialog box definition
    private void showMessage(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }

    //override method for request permission results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "inside onRequestPermissionResult");
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                    Log.d(TAG, "You gave permission to location");
                    //if permission is granted, the button image will be set to stop
                    //and exercise is deemed on
                    //listening location and timer start
                    startButton.setImageResource(R.drawable.stop);
                    timerHandler.postDelayed(timerRunnable, 0);
                    mLocationService.startListeningLoc();
                    startTime = System.currentTimeMillis();
                    isExerciseOn = true;
                } else {
                    //create a dialog box that informs user that the application need their permission for
                    //location for app to work on a fundamental level
                    Log.d(TAG, "you denied permission for location");
                    showMessage("You need to allow access to location for app to be able to use GPS",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //ok button does nothing but put dialog box away
                                }
                            });
                }
                return;
            }
        }
    }
    /*
     * Methods related to location permissions end here
     */

 /**
 * Loads credits from SharedPreferences. If data is not available (does not exist yet), function sets available credits to 0.
 * If credits are found, the function updates the users' credits. On the first load, the function sets the value of
 * isStartedBefore to true.
 */
    private void loadCredits() {
        SharedPreferences sharedPreferences = getSharedPreferences("credit preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("credits", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        WalletSingleton walletSingleton = WalletSingleton.getInstance();
        ArrayList<String> temp = new ArrayList<>();
        if (json == null) {
            walletSingleton.setCredits(0);
            Log.d("debug", "no credits at preferences");
        } else {
            //temp = gson.fromJson(json, type);
            walletSingleton.setCredits(Integer.parseInt(json));
            Log.d("debug", "credits loaded");
        }

        isStartedBefore = true;
    }


/**
* Saves credits into SharedPreferences as json with the help of Gson. Gson converts Java objects into json.
*
*/
    private void saveCredits() {
        SharedPreferences sharedPreferences = getSharedPreferences("credit preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        WalletSingleton walletSingleton = WalletSingleton.getInstance();
        String jsonc = String.valueOf(walletSingleton.getCredits());
        Log.d("debug", jsonc);
        editor.putString("credits", jsonc);
        editor.apply();
        Log.d("debug", "Credits SAVED!");
    }

}
