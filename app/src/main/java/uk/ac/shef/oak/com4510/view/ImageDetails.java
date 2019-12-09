package uk.ac.shef.oak.com4510.view;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import uk.ac.shef.oak.com4510.R;

public class ImageDetails extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

//        CLICK FOR FullScreenActivity
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent =new Intent(getApplicationContext(),FullScreenActivity.class);
//                intent.putExtra("id", position);
//                startActivity(intent);
//            }
//        });
    }
}
