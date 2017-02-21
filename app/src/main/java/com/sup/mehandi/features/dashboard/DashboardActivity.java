package com.sup.mehandi.features.dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sup.mehandi.R;
import com.sup.mehandi.adapter.CustomerDashboardCardViewAdapter;
import com.sup.mehandi.base.BaseActivity;
import com.sup.mehandi.model.User;
import com.sup.mehandi.utils.LocationUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DashboardActivity extends BaseActivity {
    private static final String TAG = "CustomerDashboard";

    private static final int MY_PERMISSIONS_REQUEST_LOCATION_PERMISSION = 1001;
    ReactiveLocationProvider locationProvider;
    @BindView(R.id.et_search_location)
    TextView etSearchLocation;
    @BindView(R.id.rv_designers)
    RecyclerView rvDesigners;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private RecyclerView.Adapter searchBookAdapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        Typeface tfHeader = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
        toolbarTitle.setText("Dashboard");
        setFontStyle();
        // etSearchLocation.setEnabled(false);
        manager = new LinearLayoutManager(getApplicationContext());
        rvDesigners.setLayoutManager(manager);
        getNearestMehandiWalas();
    }

    List<User> userList = new ArrayList<>();

    private void getNearestMehandiWalas() {
        userList.add(new User("Om Mehandi Designers", "Jaynagar 4th block, Bangalore", 4));
        userList.add(new User("Wedding Mehandi Designers", "Jaynagar 4th block, Bangalore", 3));
        userList.add(new User("Shri Sai Mehandi Designers", "Jaynagar 4th block, Bangalore", 2));
        userList.add(new User("Mehandiwala", "Jaynagar 4th block, Bangalore", 4));
        userList.add(new User("Preeti mehandiwala", "BTM 2nd Stage, Bangalore", 5));
        userList.add(new User("Priyanta Mehandi", "Mahalaxmi Layout, Bangalore", 5));

        searchBookAdapter = new CustomerDashboardCardViewAdapter(userList, this, DashboardActivity.this);
        rvDesigners.setAdapter(searchBookAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getGPSLocation();
    }

    private void getGPSLocation() {

        LocationUtility locationUtility = LocationUtility.getInstance(getApplicationContext());
        if (!locationUtility.isGPSEnabled()) {
            locationUtility.showSettingsAlert(DashboardActivity.this);
        }


        locationProvider = new ReactiveLocationProvider(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.i(TAG, "Location permission is missing");

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION_PERMISSION);
            return;
        }
        locationProvider.getLastKnownLocation()
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .subscribe(new Action1<Location>() {
                    @Override
                    public void call(Location location) {
                        //doSthImportantWithObtainedLocation(location);
                        Log.i(TAG, "Latitude and Longitude " + location.getLatitude() + " , " + location.getLongitude());
                       /* latToPass = location.getLatitude();
                        lonToPass = location.getLongitude();*/
                        getAddress(location.getLatitude(), location.getLongitude());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // openAutocompleteActivity();
                        throwable.printStackTrace();
                    }
                });


    }

    private void getAddress(double latitude, double longitude) {
        locationProvider.getReverseGeocodeObservable(latitude, longitude, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .subscribe(new Action1<List<Address>>() {
                    @Override
                    public void call(List<Address> addresses) {
                        Address address = addresses.get(0);
                        ArrayList<String> addressFragments = new ArrayList<String>();
                        System.out.println("Address is " + address);
                        System.out.println("Address is " + address.toString());
                        String msgAddress = "";
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                                /*   if (address.getAddressLine(i) != null)
                                                       addressFragments.add(address.getAddressLine(i));*/
                            if (!address.getAddressLine(i).equals("null"))
                                msgAddress = msgAddress + " " + address.getAddressLine(i);
                        }
                        etSearchLocation.setText(msgAddress.replace("null", ""));
                        System.out.println("Address location is  " + msgAddress);
                        // addressToPass = msgAddress;
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // openAutocompleteActivity();
                        throwable.printStackTrace();
                    }
                });


    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    // Toast.makeText(Splash.this, "Location Permissions are not enabled", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @OnClick({R.id.et_search_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search_location:
                break;

        }
    }
}
