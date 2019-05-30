package slorah.com.hw7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    final static String TAG = "MAPACTIVITY";
    private FusedLocationProviderClient mLocationClient;
    private boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted=true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, 1);
        }
    }// end getLocationPermission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        mLocationPermissionGranted = false;
        switch(requestCode) {
            case 1: {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mLocationPermissionGranted=true;
                    getLocation();
                }
            }
        }
    }// end onRequestPermissionsResult()

    @SuppressLint("MissingPermission")
    private void getLocation() {
        //if you don't test this, you get error
        if(mLocationPermissionGranted){
            //this returns a task
            //this can fail if user hasn't provided permission
            Task location = mLocationClient.getLastLocation();

            location.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location actualLoction = task.getResult();
                    if (actualLoction != null) {
                        String latLong = String.format("Lat: %f, Long: %f",
                                actualLoction.getLatitude(), actualLoction.getLongitude());

                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(true);

                        // my location
                        LatLng here = new LatLng(actualLoction.getLatitude(), actualLoction.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(here).title("Current locations"));

//                        //camera location
//                        Intent intent = getIntent();
//                        String[] myCam = intent.getStringArrayExtra(RecyclerViewActivity.RESULT);
//                        Double camLatitude= Double.parseDouble(myCam[0]);
//                        Double camLongitude = Double.parseDouble(myCam[1]);
//                        String camLabel = myCam[2];
//                        Log.e(TAG, camLabel + camLatitude + camLongitude);
//
//                        LatLng camera = new LatLng(camLatitude, camLongitude);
//                        mMap.addMarker(new MarkerOptions().position(camera).title(camLabel));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 10));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                    }else{
                        Log.e(TAG, "Location is null..");
                    }
                }
            });
            location.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            });
        }
    }//end getLocation()

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        getLocationPermission();
        getLocation();

        //camera location
        Intent intent = getIntent();
        String[] myCam = intent.getStringArrayExtra(RecyclerViewActivity.RESULT);
        Double camLatitude= Double.parseDouble(myCam[0]);
        Double camLongitude = Double.parseDouble(myCam[1]);
        String camLabel = myCam[2];
        String camUrl = myCam[3];

        Log.e(TAG, camLabel + camLatitude + camLongitude);

        LatLng camera = new LatLng(camLatitude, camLongitude);
        Marker myCamMarker = mMap.addMarker(new MarkerOptions()
                .position(camera)
                .title(camLabel));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camera, 12));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }
}