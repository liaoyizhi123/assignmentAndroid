//package uk.ac.shef.oak.com4510.view;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import uk.ac.shef.oak.com4510.R;
//import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
//import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
//
//public class ImageDetails extends AppCompatActivity {
//    ImageView imageView;
//    String id="";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        ImageView imageView;
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_details);
//        if (getIntent() != null)
//            id = getIntent().getStringExtra("id");
//        imageView=(ImageView)findViewById(R.id.ImageDetailImage);
//        //imageView.setAdapter(new DateAscendingAdapter(this));
//
//        //Load details
//
//
//        //CLICK FOR FullScreenActivity
//        imageView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent =new Intent(getApplicationContext(),FullScreenActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//
//    }
//}