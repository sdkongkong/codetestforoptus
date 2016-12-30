package com.kevinsong.optus.codetestoptus.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kevinsong.optus.codetestoptus.R;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String LOCATION_NAME = "locationName";

    private GoogleMap mMap;
    private double mLongitude;
    private double mLatitude;
    private String mLocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mLongitude = getIntent().getDoubleExtra(LONGITUDE, 0.0);
        mLatitude = getIntent().getDoubleExtra(LATITUDE, 0.0);
        mLocationName = getIntent().getStringExtra(LOCATION_NAME);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(new LatLng(mLatitude, mLongitude)).title(mLocationName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLatitude, mLongitude), 13));
    }
}
