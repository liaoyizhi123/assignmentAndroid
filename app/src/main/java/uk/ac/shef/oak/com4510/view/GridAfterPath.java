package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;
import uk.ac.shef.oak.com4510.R;

public class GridAfterPath extends AppCompatActivity {
    GridView gridView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_after_path);
        gridView=(GridView) findViewById(R.id.GridView);
        textView=(TextView)findViewById(R.id.TitleInPath);
        gridView.setAdapter(new GridAfterPathAdapter(this));

        //Get Title
        String Title =getIntent().getExtras().getString("Title");
        textView.setText(Title);
        //click for FullScreenActivity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent =new Intent(getApplicationContext(),ImageDetails.class);
                //Pass Title
                String Title =getIntent().getExtras().getString("Title");
                intent.putExtra("Title", Title);

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