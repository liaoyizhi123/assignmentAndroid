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

import static uk.ac.shef.oak.com4510.Util.Util.stringToLinkList;

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
    private int pathId;
    private String titleStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imageView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        ImageDetailTitle = (TextView) findViewById(R.id.ImageDetailTitle);
        imageView1 = (ImageView) findViewById(R.id.ImageDetailImage);
        title = (TextView) findViewById(R.id.ImageDetailTitle);
        temp = (TextView) findViewById(R.id.ImageDetailTemp);
        pressure = (TextView) findViewById(R.id.ImageDetailPressure);
//        imageView1.setAdapter(new DateAscendingAdapter(this));

        //Load details
        final int id = getIntent().getIntExtra("id", 0);


        pathId =getIntent().getIntExtra("pathId",0);
        //GridAfterPathAdapter gridAfterPathAdapter =new GridAfterPathAdapter(this);

        //imageView1.setImageResource(gridAfterPathAdapter.imageArray[position]);

        imageDetailsViewModel = ViewModelProviders.of(this).get(ImageDetailsViewModel.class);
        //add observation to the viewModel
        imageDetailsViewModel.getImage(id).observe(this, new Observer<Image>() {
            @Override
            public void onChanged(final Image image) {
                Bitmap bitmap = BitmapFactory.decodeFile(image.getUrl());
                imageView1.setImageBitmap(bitmap);
                temp.setText("Temp: " + image.getTemp() + "C");
                pressure.setText("Pressure: " + image.getPressure() + "mbars");

                imageView1.setOnClickListener(new AdapterView.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(getApplicationContext(),FullScreenActivity.class);
                        intent.putExtra("url", image.getUrl());
                        //intent.putExtra("title", titleStr);
                        startActivity(intent);
                    }
                });

            }
        });

        //add observation to the viewModel
        imageDetailsViewModel.getPath(pathId).observe(this, new Observer<Path>() {
            @Override
            public void onChanged(Path path) {

                title.setText("Title: " + path.getTitle());
                titleStr = path.getTitle();
                //System.out.println("location"+path.getLocation());
                latLngMap = stringToLinkList(path.getLocation());
                addMapLine(latLngMap);


            }
        });

        //add observation to the viewModel
        imageDetailsViewModel.getAllImageLiveByPathId(pathId).observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                int index=0;
                for(Image i :images){
                    latLngs.add(new LatLng(Double.parseDouble(i.getLatitude()),Double.parseDouble(i.getLongitude())));
                    if(i.getId() == id){
                        index = images.indexOf(i);
                    }
                }
                addallMarkers(latLngs,index);
            }
        });


        //CLICK FOR FullScreenActivity



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        //addallMarkers(latLngs,0);
    }

    public void addallMarkers(List<LatLng> latLngs,int index) {
        for (LatLng latLng : latLngs) {
            mMap.addMarker(new MarkerOptions().position(latLng).title(titleStr).snippet("10000").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
        mMap.addMarker(new MarkerOptions().position(latLngs.get(index)).title(titleStr).snippet("10000").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(index), 14.0f));
    }

    private Polyline polyline;

    public void addMapLine(List<LatLng> latLngsMap) {
        System.out.println("latlngsize="+latLngsMap.size());
        polyline = mMap.addPolyline(new PolylineOptions().addAll(latLngsMap).width(10).color(Color.BLUE));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngsMap.get(latLngsMap.size() - 1), 14.0f));

    }

}