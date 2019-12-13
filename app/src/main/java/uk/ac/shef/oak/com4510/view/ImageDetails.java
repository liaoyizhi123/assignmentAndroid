package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.LinkedList;
import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.viewModel.ImageDetailsViewModel;

public class ImageDetails extends AppCompatActivity implements OnMapReadyCallback {
    ImageView imageView1;
    TextView ImageDetailTitle;
    String id = "";
    Image image;
    private ImageDetailsViewModel imageDetailsViewModel;
    LiveData<List<Image>> imagesToDisplay;
    private GoogleMap mMap;

    private TextView title;
    private TextView temp;
    private TextView pressure;
    private List<LatLng> latLngs = new LinkedList<>();
    private List<LatLng> latLngMap = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imageView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        LatLng latLng = new LatLng(53.1674983, -1.5232383);
        LatLng latLng0 = new LatLng(53.163733, -1.4920889);
        LatLng latLng1 = new LatLng(53.167784, -1.4826486);
        LatLng latLng2 = new LatLng(53.1607811, -1.4811495);
        LatLng latLng3 = new LatLng(53.1603687, -1.4808168);
        LatLng latLng4 = new LatLng(53.1603124, -1.4807714);
        latLngs.add(latLng);
        latLngs.add(latLng0);
        latLngs.add(latLng1);
        latLngs.add(latLng2);
        latLngs.add(latLng3);
        latLngs.add(latLng4);
        latLngMap.add(latLng);
        latLngMap.add(latLng0);
        latLngMap.add(latLng1);
        latLngMap.add(latLng2);
        latLngMap.add(latLng3);
        latLngMap.add(latLng4);
        //addMapLine(latLngMap);


        ImageDetailTitle = (TextView) findViewById(R.id.ImageDetailTitle);
        imageView1 = (ImageView) findViewById(R.id.ImageDetailImage);
        title = (TextView) findViewById(R.id.ImageDetailTitle);
        temp = (TextView) findViewById(R.id.ImageDetailTemp);
        pressure = (TextView) findViewById(R.id.ImageDetailPressure);
//        imageView1.setAdapter(new DateAscendingAdapter(this));

        //Load details
        int id = getIntent().getIntExtra("id", 0);


        int pathId =getIntent().getIntExtra("pathId",0);
        //GridAfterPathAdapter gridAfterPathAdapter =new GridAfterPathAdapter(this);

        //imageView1.setImageResource(gridAfterPathAdapter.imageArray[position]);

        imageDetailsViewModel = ViewModelProviders.of(this).get(ImageDetailsViewModel.class);
        imageDetailsViewModel.getImage(id).observe(this, new Observer<Image>() {
            @Override
            public void onChanged(Image image) {
                Bitmap bitmap = BitmapFactory.decodeFile(image.getUrl());
                imageView1.setImageBitmap(bitmap);
                temp.setText("Temp: " + image.getTemp() + "C");
                pressure.setText("Pressure: " + image.getPressure() + "mbars");

            }
        });


        imageDetailsViewModel.getPath(pathId).observe(this, new Observer<Path>() {
            @Override
            public void onChanged(Path path) {

                title.setText("Title: " + path.getTitle());


            }
        });


        //CLICK FOR FullScreenActivity
//        imageView1.setOnClickListener(new AdapterView.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getApplicationContext(),FullScreenActivity.class);
//
//                int position =getIntent().getExtras().getInt("id");
//
//           intent.putExtra("id", position);
//
//                startActivity(intent);
//            }
//
//
//        });

//        addallMarkers(latLngs);
//        addMapLine(latLngMap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMapLine(latLngMap);
        addallMarkers(latLngs);
    }

    public void addallMarkers(List<LatLng> latLngs) {
        for (LatLng latLng : latLngs) {
            mMap.addMarker(new MarkerOptions().position(latLng).title("hello").snippet("10000").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
        mMap.addMarker(new MarkerOptions().position(latLngs.get(latLngs.size() - 1)).title("hello").snippet("10000").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(latLngs.size() - 1), 14.0f));
    }

    private Polyline polyline;

    public void addMapLine(List<LatLng> latLngsMap) {
        polyline = mMap.addPolyline(new PolylineOptions().addAll(latLngsMap).width(10).color(Color.BLUE));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngsMap.get(latLngsMap.size() - 1), 14.0f));

    }

}