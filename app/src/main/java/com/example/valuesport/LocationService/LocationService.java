package com.example.valuesport.LocationService;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


/**
 * LocationService is the class for allowing user to access location through Global Positioning System.
 *
 * @author Arttu Seuna
 */

public class LocationService {

    private LocationManager mLocationManager;

    private final Context mContext;
    private final Activity mActivity;

    private static final String TAG = "TESTGPS";            //TAG for log debug entries
    private static final int LOCATION_INTERVAL = 3000;      //time interval for location updates 3 sec
    private static final float LOCATION_DISTANCE = 50f;     //distance interval for location updates

    private float fullDistance;
    private Location mLastLocation;     //location object to store the information on the latest location

    /**
     * Defines starting values for LocationManager object, full distance and Last location.
     * They are set to null and zero until values are given in corresponding methods.
     *
     * @param activity activity needed for context
     * @param context  current context to be used with LocationManager and toast widget
     */

    public LocationService(Activity activity, Context context) {
        this.mLocationManager = null;   //location manager object needed for getting the location from gps
        this.fullDistance = 0;          //full distance traveled
        this.mContext = context;        //current context passed by current activity
        this.mActivity = activity;      //current activity passed as an argument
        this.mLastLocation = null;      //location object for storing the last known location
    }

    //location listener interface and its methods
    private final LocationListener mLocationListener = new LocationListener() {

        //method that is called when location changes
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "Adding distance between last and current location to full distance");
            //adds the distance between last location and the current location to the full distance
            //and sets the current location to last location
            if (mLastLocation != null) {
                fullDistance = fullDistance + location.distanceTo(mLastLocation);
                mLastLocation.set(location);
            } else {
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


    /**
     * Initializes LocationManager object that is used in getting the location from LocationListener.
     */
    private void initializeLocationManager() {
        if (mLocationManager == null) {
            Log.d(TAG, "Initializing LocationManager");
            mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            Log.d(TAG, "LocationManager initialized");
        }

        //flag for checking the gps status
        boolean gpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //tells user to enable location
        if (!gpsEnabled) {
            Toast.makeText(mContext, "Enable location", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Calls initialization of LocationManager. Starts to listen location updates using LocationManager
     * and LocationListener.
     *
     * @throws SecurityException if permission has not been granted for listening location updates.
     */
    public void startListeningLoc() {

        initializeLocationManager();
        try {
            Log.d(TAG, "starting to listen location");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE, mLocationListener);
            Log.d(TAG, "listening to location");
        } catch (SecurityException e) {
            //in case of security exception, permission not given, the application closes
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

    /**
     * Stops location updates. Sets LocationManager and location to null and traveled distance to 0.
     */
    public void stopListeningLoc() {
        Log.d(TAG, "stopping location listening");
        mLocationManager.removeUpdates(mLocationListener);  //removes the updates on the location listener
        mLocationManager = null;                            //sets location manager to null
        mLastLocation = null;                               //sets last location to null
        fullDistance = 0;                                   //sets fullDistance to 0
    }


    /**
     * Returns full distance the user has traveled
     *
     * @return <code>fullDistance</code> the added up distance the user has traveled
     */
    public float getfullDistance() {
        return this.fullDistance;
    }
}
