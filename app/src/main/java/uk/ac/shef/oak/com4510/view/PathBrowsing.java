package uk.ac.shef.oak.com4510.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.PathBrowsingAdapter;
import uk.ac.shef.oak.com4510.entities.Path;


public class PathBrowsing extends AppCompatActivity {
    RecyclerView recycleView;
    PathBrowsingAdapter pathBrowsingAdapter;
    private Path[] myDataset= new Path[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_browsing);
        recycleView =findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));





        myDataset[0]=new Path("Walk to the 7 bridges",null,null,null,null);
        myDataset[1]=new Path("Walk to the other bridges", null,null,null,null);
        pathBrowsingAdapter = new PathBrowsingAdapter(myDataset);
        recycleView.setAdapter(pathBrowsingAdapter);

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
