package uk.ac.shef.oak.com4510.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import uk.ac.shef.oak.com4510.PressureSensor;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.TempSensor;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.viewModel.MapsViewModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    private GoogleMap mMap;
    private static final int ACCESS_FINE_LOCATION = 123;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private MapView mapView;
    private Button mButtonStart;
    private Button mButtonEnd;
    private Location mCurrentLocation;
    private String mLastUpdateTime;
    private List<LatLng> latLngs = new ArrayList<LatLng>();
    private PressureSensor pressureSensor;
    private TempSensor tempSensor;
    private Map<String, LatLng> latLngMap = new LinkedHashMap<>();
    private StringBuilder stringBuilder = new StringBuilder();
    private List<LatLng> latLngList = new LinkedList<>();


    private FloatingActionButton mButtonCamera;
    private MapsViewModel mapsViewModel;
    private int pathId;
    private String title;
//    private Barometer barometer;
//    private Accelerometer accelerometer;
//    private TextView textView;
//    private SensorManager sensorManager;
//    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        pathId = intent.getIntExtra("pathId", 0);
        System.out.println(title + "-----------");
        System.out.println(pathId + "------------");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        pressureSensor = new PressureSensor(this);
        tempSensor = new TempSensor(this);
        // startLocationUpdates();

//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
//        barometer = new Barometer(this);
//        accelerometer = new Accelerometer(this, barometer);
        mButtonStart = (Button) findViewById(R.id.button_start);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationUpdates();


//                if (mButtonEnd != null)
//                    mButtonEnd.setEnabled(true);
//                mButtonStart.setEnabled(false);
                pressureSensor.startTempSensor();
                tempSensor.startTempSensor();


            }
        });
//        mButtonStart.setEnabled(true);
        mButtonEnd = (Button) findViewById(R.id.button_end);
        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLocationUpdates();
//                if (mButtonStart != null)
//                    mButtonStart.setEnabled(true);
//                mButtonEnd.setEnabled(false);
                pressureSensor.stopTempSensor();
                tempSensor.stopTempSensor();
                Intent intentMainFrame = new Intent(MapsActivity.this, MyView.class);
                startActivity(intentMainFrame);
            }
        });
//        mButtonEnd.setEnabled(false);


        //camera
        mButtonCamera = (FloatingActionButton) findViewById(R.id.button_camera);
        mButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initEasyImage();
                EasyImage.openCamera(MapsActivity.this, 0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                onPhotosReturned(imageFiles);
            }
        });

    }

    private void onPhotosReturned(List<File> imageFiles) {

        File file = imageFiles.get(0);
        String imageURL = file.getAbsolutePath();

        //
        LatLng latestLatLng = getLatestLatLng();
        String latitude = String.valueOf(latestLatLng.latitude);
        String longitude = String.valueOf(latestLatLng.longitude);

        String timestamp = String.valueOf(System.currentTimeMillis());

        String lasttemp1 = tempSensor.getLasttemp();
        String lastpressure = pressureSensor.getLasttemp();

        Image image = new Image(imageURL, longitude, latitude, timestamp, lastpressure, lasttemp1, pathId);
        mapsViewModel.addImage(image);

        addmarker(latestLatLng);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_FINE_LOCATION);
            }
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null /* Looper */);
    }

    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    protected void onResume() {
        super.onResume();
        //accelerometer.startAccelerometerRecording();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        startLocationUpdates();
        //sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_GAME);
    }

    Polyline polyline;
    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            mCurrentLocation = locationResult.getLastLocation();
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            Log.i("MAP", "new location " + mCurrentLocation.toString());
            if (mMap != null) {
                // latLngs.add(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(new Date());
                LatLng newlocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                latLngMap.put(date, newlocation);
                latLngs.add(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
                Double x = mCurrentLocation.getLatitude();
                Double y = mCurrentLocation.getLongitude();
                String tempx = String.valueOf(x);
                String tempy = String.valueOf(y);
                stringBuilder.append(tempx + "," + tempy + ";");
                System.out.println("stringBuilder" + stringBuilder);
                System.out.println(date);
                Log.i("stringBuilder", String.valueOf(stringBuilder));
            }
//            mMap.addMarker(new MarkerOptions().position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
//            );
            polyline = mMap.addPolyline(new PolylineOptions().
                    addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()), 14.0f));

        }

    };


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                            mLocationCallback, null /* Looper */);
                } else {
                }
                return;
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info  Windows", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onPause() {
        super.onPause();
//        accelerometer.stopAccelerometer();
//        sensorManager.unregisterListener(this);
    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        float[] values = event.values;
//        float temp = values[0];
//        System.out.println("my temp" + temp);
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }


    private void addmarker(LatLng latLng) {
        String temptitle = "Sheffield";
        mMap.addMarker(new MarkerOptions().position(latLng).title(temptitle).snippet("Population: 4,137,400"));
    }

    private LatLng getLatestLatLng() {
        LatLng finalLatlng = new LatLng(53.212046, -1.532907);
        Set<String> keys = latLngMap.keySet();
        for (String key : keys) {
            latLngList.add(latLngMap.get(key));
            // System.out.println(" value值：" + latLngMap.get(key));
        }
        finalLatlng = latLngList.get(latLngList.size() - 1);
        System.out.println("finalLatlng" + finalLatlng);
        return finalLatlng;
    }


    private void initEasyImage() {
        EasyImage.configuration(this)
                .setImagesFolderName("EasyImage sample")
                .setCopyTakenPhotosToPublicGalleryAppFolder(true)
                .setCopyPickedImagesToPublicGalleryAppFolder(false)
                .setAllowMultiplePickInGallery(true);
    }

}


