package com.example.valuesport;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    /*
        variables, constants ans objects used for location purposes
    */
    private static final String TAG = "TESTGPS";                       //TAG for log debug entries
    public static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;    //my permission for fine location

    //start/stop imagebutton and flag for checking if exercise in on
    ImageButton startButton;
    boolean isExerciseOn = false;
    static boolean isStartedBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("debug", "onCreate() called");
        WalletSingleton walletInstance = WalletSingleton.getInstance();
        if (isStartedBefore == false) {
            loadData();
        }


        //wallet käyttöön esim. näin:
        //
        //walletInstance.setCredits(500);
        //walletInstance.addCouponToWallet(/*tähän lisättävä kuponki*/);
        //Log.d("debug", String.valueOf(walletInstance.getCredits()));

        /*
            Setting up imageButton and onclick listener
         */
            startButton = (ImageButton)findViewById(R.id.startButton);
            startButton.setImageResource(R.drawable.start);

            startButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View viewButton) {
                    ImageButton startButton = (ImageButton) viewButton;

                    if(!isExerciseOn){
                        startButton.setImageResource(R.drawable.stop);
                        isExerciseOn = true;
                    }
                    else{
                        startButton.setImageResource(R.drawable.start);
                        isExerciseOn = false;
                    }

                }
            });
    }

    public void showPopup(View v) { //Menu buttonin onClick funktio, avaa pudotusvalikon
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.mainmenu);
        popup.show();
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
            default:
                return false;
        }
    }

    public void toStore() {
        Intent intent = new Intent(this, BuyCouponActivity.class);
        startActivity(intent);
    }

    public void toWallet() {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }

    /*
     * This following section of the code contains the methods needed for handling permission cases
     * It also defines a dialogue box to communicate the purpose of location to the user
     */
     //Method for asking permission for location if needed and starts next activity if not
    public void checkPermission(){
        //Checks if the application has permission for fine location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSION_ACCESS_FINE_LOCATION);
        }
        else{
            Log.d(TAG,"permission is already granted for location");
            //StartExc(); //starts exercise activity
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
        Log.d(TAG,"inside onRequestPermissionResult");
        switch (requestCode){
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                    Log.d(TAG, "You gave permission to location");
                    //StartExc();     //starts exercise activity
                }
                else {
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

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(WalletSingleton.ownedCoupons);
        editor.putString("coupons", json);
        editor.apply();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "onPause() called");
        saveData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("coupon", null);
        Type type = new TypeToken<ArrayList<Coupon>>() {}.getType();
        WalletSingleton walletSingleton = WalletSingleton.getInstance();
        ArrayList<Coupon> temp = new ArrayList<>();
        if (gson.fromJson(json, type) == null) {
            walletSingleton.setOwnedCoupons(temp);
        } else {
            temp = gson.fromJson(json, type);
            walletSingleton.setOwnedCoupons(temp);
        }
        Log.d("debug", "data loaded");
        isStartedBefore = true;
    }


    /*
    //starts exercise activity
    public void StartExc() {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }*/

    /*
     * Methods related to location permissions end here
     */

}
