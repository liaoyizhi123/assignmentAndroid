package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;

public class FullScreenActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView =(ImageView)findViewById(R.id.fullScreen);
        getSupportActionBar().hide();
        getSupportActionBar().setTitle("Full Image");

        Intent i =getIntent();
        String url =i.getExtras().getString("url");
        //String title = i.getExtras().getString("title");
//        GridAfterPathAdapter gridAfterPathAdapter =new GridAfterPathAdapter(this);
//        imageView.setImageResource(gridAfterPathAdapter.imageArray[position]);
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        imageView.setImageBitmap(bitmap);
    }
}
