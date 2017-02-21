package com.sup.mehandi.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.sup.mehandi.R;

import static android.location.LocationManager.GPS_PROVIDER;

/**
 * Created by umakant.angadi on 15-03-2016.
 */
public class LocationUtility {
    private LocationManager locationManager;
    private Context mCtx;
    private static LocationUtility sLocationUtility;
    private boolean isGPSEnabled, isNetworkEnabled;

    private LocationUtility(Context ctx) {
        mCtx = ctx;
        locationManager = (LocationManager) ctx
                .getSystemService(Context.LOCATION_SERVICE);
    }

    public static LocationUtility getInstance(Context ctx) {
        if (sLocationUtility == null) {
            sLocationUtility = new LocationUtility(ctx);
        }
        return sLocationUtility;
    }

    AlertDialog.Builder alertDialog;
    DialogInterface dialog1;
    public void showSettingsAlert(final Activity ctx){
        alertDialog = new AlertDialog.Builder(ctx, R.style.MaterialDialog);

        Resources res = ctx.getResources();
        alertDialog.setTitle("Enable Location!");
        alertDialog.setMessage("Please enable Location Services to access the feature");
        alertDialog.setPositiveButton("ENABLE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                ctx.startActivityForResult(intent,1);

            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        alertDialog.show();
    }
    private void SavePreferences(String key, Boolean value, Context ctx){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

    public DialogInterface getDialog1(){
        return dialog1;
    }


    public boolean isGPSEnabled(){
        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(GPS_PROVIDER);

        // getting network status
        /*isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);*/
        return isGPSEnabled ;

    }

    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static Dialog checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                return GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
                Log.i("LocationUtility", "This device is not supported.");
                return GooglePlayServicesUtil.getErrorDialog(ConnectionResult.SERVICE_MISSING, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST);
            }
        }
        return null;
    }

}
