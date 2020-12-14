package au.edu.sydney.comp5216.makeup;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPoiClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000 * 60;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private GoogleMap mMap = null;
    private LocationRequest mLocationRequest;

    private LocationCallback callback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            LatLng sydney = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(sydney)
                    .title("address"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
                    List<Address> addresses;
                    try {
                        addresses = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
                        String address = addresses.get(0).getAddressLine(0);
                        Intent intent = new Intent();
                        intent.putExtra("address", address);
                        intent.putExtra("lat", marker.getPosition().latitude);
                        intent.putExtra("log", marker.getPosition().longitude);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });

        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        PermissionUtils.checkMultiplePermissions(MapActivity.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(String... permission) {

                boolean sdDenied = false;
                for (int i = 0; i < permission.length; i++) {
                    if (permission[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        sdDenied = true;
                        break;
                    }
                }
                if (sdDenied){

                }else{

                }


            }

            @Override
            public void alwaysDenied(String... permission) {
                for (int i = 0; i < permission.length; i++) {
                }
                PermissionUtils.requestMultiplePermissions(MapActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }


        });
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

        mLocationRequest =new  LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS) ;
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) ;
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, callback, Looper.myLooper());
        mMap.setOnPoiClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
    }






    @Override
    public void onPoiClick(PointOfInterest poi) {
        Intent intent =new  Intent();
        intent.putExtra("address", poi.name);
        intent.putExtra("lat",poi.latLng.latitude);
        intent.putExtra("log",poi.latLng.longitude);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney)
                .title("address"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Toast.makeText(this, "Current location:"+location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        LatLng sydney = new LatLng(latLng.latitude, latLng.longitude);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(sydney)
                .title("address"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = addresses.get(0).getAddressLine(0);
        LatLng latLng = marker.getPosition();
        Intent intent =new  Intent();
        intent.putExtra("address", address);
        intent.putExtra("lat",latLng.latitude);
        intent.putExtra("log",latLng.longitude);
        setResult(Activity.RESULT_OK, intent);
        finish();
        return true;
    }
}
