//package com.yamin.session1.activities;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.IntentSender;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.net.Uri;
//import android.os.Looper;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.common.api.ResolvableApiException;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.LocationSettingsStatusCodes;
//import com.google.android.gms.location.SettingsClient;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.yamin.session1.R;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener{
//
//
//
//    /**
//     * Provides access to the Fused Location Provider API.
//     */
//    private FusedLocationProviderClient mFusedLocationClient;
//
//    /**
//     * Provides access to the Location Settings API.
//     */
//    private SettingsClient mSettingsClient;
//
//    /**
//     * Stores parameters for requests to the FusedLocationProviderApi.
//     */
//    private LocationRequest mLocationRequest;
//
//    /**
//     * Stores the types of location services the client is interested in using. Used for checking
//     * settings to determine if the device has optimal location settings.
//     */
//    private LocationSettingsRequest mLocationSettingsRequest;
//
//    /**
//     * Callback for Location events.
//     */
//    private LocationCallback mLocationCallback;
//
//    /**
//     * Represents a geographical location.
//     */
//    private Location mCurrentLocation;
//
//    /**
//     * Tracks the status of the location updates request. Value changes when the user presses the
//     * Start Updates and Stop Updates buttons.
//     */
//    private Boolean mRequestingLocationUpdates;
//
//
//
//    private GoogleMap mMap;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        mSettingsClient = LocationServices.getSettingsClient(this);
//
//
//        // Kick off the process of building the LocationCallback, LocationRequest, and
//        // LocationSettingsRequest objects.
//        createLocationCallback();
//        createLocationRequest();
//        buildLocationSettingsRequest();
//
//
//        startLocationUpdates();
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//
//
//    /**
//     * Sets up the location request. Android has two location request settings:
//     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
//     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
//     * the AndroidManifest.xml.
//     * <p/>
//     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
//     * interval (5 seconds), the Fused Location Provider API returns location updates that are
//     * accurate to within a few feet.
//     * <p/>
//     * These settings are appropriate for mapping applications that show real-time location
//     * updates.
//     */
//    private void createLocationRequest() {
//        mLocationRequest = LocationRequest.create();
//
//        // Sets the desired interval for active location updates. This interval is
//        // inexact. You may not receive updates at all if no location sources are available, or
//        // you may receive them slower than requested. You may also receive updates faster than
//        // requested if other applications are requesting location at a faster interval.
//        mLocationRequest.setInterval(5000);
//
//        // Sets the fastest rate for active location updates. This interval is exact, and your
//        // application will never receive updates faster than this value.
//        mLocationRequest.setFastestInterval(5000/2);
//
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }
//
//    /**
//     * Creates a callback for receiving location events.
//     */
//    private void createLocationCallback() {
//        mLocationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//                mCurrentLocation = locationResult.getLastLocation();
//                updateLocationUI();
//            }
//        };
//    }
//
//    /**
//     * Uses a {@link com.google.android.gms.location.LocationSettingsRequest.Builder} to build
//     * a {@link com.google.android.gms.location.LocationSettingsRequest} that is used for checking
//     * if a device has the needed location settings.
//     */
//    private void buildLocationSettingsRequest() {
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
//        builder.addLocationRequest(mLocationRequest);
//        mLocationSettingsRequest = builder.build();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            // Check for the integer request code originally supplied to startResolutionForResult().
//            case 1:
//                switch (resultCode) {
//                    case Activity.RESULT_OK:
//                        // Nothing to do. startLocationupdates() gets called in onResume again.
////                        if (!mRequestingLocationUpdates) {
////                            mRequestingLocationUpdates = true;
//                            startLocationUpdates();
////                        }
//                        break;
//                    case Activity.RESULT_CANCELED:
////                        Log.i(TAG, "User chose not to make required location settings changes.");
////                        mRequestingLocationUpdates = false;
////                        updateUI();
//                        break;
//                }
//                break;
//        }
//    }
//
//    /**
//     * Requests location updates from the FusedLocationApi. Note: we don't call this unless location
//     * runtime permission has been granted.
//     */
//    private void startLocationUpdates() {
//        // Begin by checking if the device has the necessary location settings.
//        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
//                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
//                    @SuppressLint("MissingPermission")
//                    @Override
//                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//
////                        if (checkPermissions()) {
//                            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
//                                    mLocationCallback, Looper.myLooper());
//
////                        }
//
//                        updateLocationUI();
//                    }
//                })
//                .addOnFailureListener(this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        int statusCode = ((ApiException) e).getStatusCode();
//                        switch (statusCode) {
//                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//
//                                try {
//                                    // Show the dialog by calling startResolutionForResult(), and check the
//                                    // result in onActivityResult().
//                                    ResolvableApiException rae = (ResolvableApiException) e;
//                                    rae.startResolutionForResult(MapsActivity.this, 1);
//                                } catch (IntentSender.SendIntentException sie) {
//                                }
//                                break;
//                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                                String errorMessage = "Location settings are inadequate, and cannot be " +
//                                        "fixed here. Fix in Settings.";
//
//                                Toast.makeText(MapsActivity.this, errorMessage, Toast.LENGTH_LONG).show();
////                                mRequestingLocationUpdates = false;
//                        }
//
//
//                        updateLocationUI();
//                    }
//                });
//    }
//
//
//
//    /**
//     * Sets the value of the UI fields for the location latitude, longitude and last update time.
//     */
//    private void updateLocationUI() {
//        if (mCurrentLocation != null ) {
////            mRequestingLocationUpdates = false;
//            LatLng here = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(here).title("Here we Are"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 14));
//        }
//
//    }
//
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        mMap.setOnMarkerClickListener(this);
////         Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
////        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//
//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        String google_direction = "google.navigation:q=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
//        Uri gmmIntentUri = Uri.parse(google_direction);
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);
//
//        return false;
//    }
//
//
//
//
//
//}
