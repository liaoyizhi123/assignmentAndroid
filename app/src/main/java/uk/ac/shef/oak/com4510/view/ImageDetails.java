package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.viewModel.ImageDetailsViewModel;

public class ImageDetails extends AppCompatActivity {
    ImageView imageView1;
    TextView ImageDetailTitle;
    String id="";
    Image image;
    private ImageDetailsViewModel imageDetailsViewModel;
    LiveData<List<Image>> imagesToDisplay;


    private TextView title;
    private TextView temp;
    private TextView pressure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imageView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        ImageDetailTitle=(TextView)findViewById(R.id.ImageDetailTitle);
        imageView1=(ImageView)findViewById(R.id.ImageDetailImage);
        title = (TextView) findViewById(R.id.ImageDetailTitle);
        temp = (TextView) findViewById(R.id.ImageDetailTemp);
        pressure = (TextView) findViewById(R.id.ImageDetailPressure);
//        imageView1.setAdapter(new DateAscendingAdapter(this));

        //Load details
        int id =getIntent().getIntExtra("id",0);

        int pathId =getIntent().getIntExtra("pathId",0);

        GridAfterPathAdapter gridAfterPathAdapter =new GridAfterPathAdapter(this);
        //imageView1.setImageResource(gridAfterPathAdapter.imageArray[position]);

        imageDetailsViewModel = ViewModelProviders.of(this).get(ImageDetailsViewModel.class);
        imageDetailsViewModel.getImage(id).observe(this, new Observer<Image>() {
            @Override
            public void onChanged(Image image) {
                //System.out.println("onchange"+image);
                Bitmap bitmap = BitmapFactory.decodeFile(image.getUrl());
                imageView1.setImageBitmap(bitmap);
                temp.setText(image.getTemp());
                pressure.setText(image.getPressure());

            }
        });


        imageDetailsViewModel.getPath(pathId).observe(this, new Observer<Path>() {
            @Override
            public void onChanged(Path path) {
                System.out.println("getPathPathId="+path);
                title.setText(path.getTitle());


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




    }
}