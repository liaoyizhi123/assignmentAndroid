package uk.ac.shef.oak.com4510.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.ac.shef.oak.com4510.MapsActivity;
import uk.ac.shef.oak.com4510.R;


public class PathBrowsing extends AppCompatActivity {
    RecyclerView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_browsing);
        recycleView =findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Load Path

    //From Path to GridAfterPath
    public void clickToStart(View view){
        Intent intent = new Intent();
        intent.setClass(PathBrowsing.this, GridAfterPath.class);
//        String account =getIntent().getStringExtra("username");
////        intent.putExtra("username",account);
        startActivity(intent);
    }
}
