package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;
import uk.ac.shef.oak.com4510.R;

public class GridAfterPath extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_after_path);
        gridView=(GridView) findViewById(R.id.GridView);
        gridView.setAdapter(new GridAfterPathAdapter(this));

        //click for FullScreenActivity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),FullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
        //click for ImageDETAILS
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent =new Intent(getApplicationContext(),ImageDetails.class);
//                intent.putExtra("id", position);
//                startActivity(intent);
//            }
//        });
    }
}
