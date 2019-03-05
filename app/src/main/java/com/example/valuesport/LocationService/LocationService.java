package com.example.valuesport.LocationService;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

//Location service is a custom class meant to provide the logic
// for using the location provider and services
//made by Arttu Seuna

public class LocationService {

    private LocationManager mLocationManager;

    private Context mContext;
    private Activity mActivity;

    private static final String TAG = "TESTGPS";            //TAG for log debug entries
    private static final int LOCATION_INTERVAL = 3000;      //time interval for location updates 3 sec
    private static final float LOCATION_DISTANCE = 50f;     //distance interval for location updates

    private float fullDistance;
    private Location mLastLocation;     //location object to store the information on the latest location
    private boolean gpsEnabled = false; //flag for checking the gps status

    public LocationService(Activity activity, Context context){
        this.mLocationManager = null;   //location manager object needed for getting the location from gps
        this.fullDistance = 0;          //full distance traveled
        this.mContext = context;        //current context passed by current activity
        this.mActivity = activity;      //current activity passed as an argument
        this.mLastLocation = null;      //location object for storing the last known location
    }

    //location listener interface and its methods
    private LocationListener mLocationListener = new LocationListener(){

        //method that is called when location changes
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "Adding distance between last and current location to full distance");
            //adds the distance between last location and the current location to the full distance
            //and sets the current location to last location
            if(mLastLocation != null) {
                fullDistance = fullDistance + location.distanceTo(mLastLocation);
                mLastLocation.set(location);
            }
            else{
                mLastLocation = location;
            }
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(mContext, "Provider enabled", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(mContext, "Provider disabled", Toast.LENGTH_LONG).show();
        }
    };


    //Method to initialize Location Manager
    private void initializeLocationManager(){
        if (mLocationManager == null){
            Log.d(TAG, "Initializing LocationManager");
            mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            Log.d(TAG,"LocationManager initialized");
        }

        //try-catch to check if gps is enabled
        try {
            gpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch(Exception ex){
            Log.d(TAG, "Exception happened!");
        }

        //tells user to enable location
        if(!gpsEnabled){
            Toast.makeText(mContext, "Enable location", Toast.LENGTH_LONG).show();
        }

    }

    //method for starting location updates
    public void startListeningLoc(){

        initializeLocationManager();
        try {
            Log.d(TAG,"starting to listen location");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListener);
            Log.d(TAG,"listening to location");
        }
        catch (SecurityException e){
            //in case of security exception i.e. permission not given, the application closes
            //!!this shouldn't happen!!
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Log.d(TAG, "app closes");
                mActivity.finishAndRemoveTask();
            } else {
                Log.d(TAG, "app closes");
                mActivity.finish();
            }
        }
    }

    //method for stopping listening for location updates
    public void stopListeningLoc(){
        try{
            mLocationManager.removeUpdates(mLocationListener);  //removes the updates on the location listener
            mLocationManager = null;                            //sets location manager to null
            mLastLocation = null;                               //sets last location to null
            fullDistance = 0;                                   //sets fullDistance to 0
        }
        catch (Exception e){
            Log.d(TAG, "Exception occurred, removing location updates failed");
        }
    }


    //methods to get position and full distance traveled
    public float getfullDistance(){
        return this.fullDistance;
    }

    public double getLat(){
        if(mLastLocation != null){
            return mLastLocation.getLatitude();
        }
        else {
            return 0;
        }
    }

    public double getLong(){
        if(mLastLocation != null) {
            return mLastLocation.getLongitude();
        }
        else{
            return 0;
        }
    }
}
