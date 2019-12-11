package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;

public class ImageDetails extends AppCompatActivity {
    ImageView imageView1;
    String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imageView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView1=(ImageView)findViewById(R.id.ImageDetailImage);
//        imageView1.setAdapter(new DateAscendingAdapter(this));

        //Load details
        int id =getIntent().getIntExtra("id",0);

        GridAfterPathAdapter gridAfterPathAdapter =new GridAfterPathAdapter(this);
        //imageView1.setImageResource(gridAfterPathAdapter.imageArray[position]);

        //CLICK FOR FullScreenActivity
        imageView1.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),FullScreenActivity.class);

                int position =getIntent().getExtras().getInt("id");

           intent.putExtra("id", position);

                startActivity(intent);
            }


        });




    }
}