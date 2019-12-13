package uk.ac.shef.oak.com4510.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.PathBrowsingAdapter;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.viewModel.PathBrowsingViewModel;

import static uk.ac.shef.oak.com4510.Util.Util.TimeStampToString;


public class PathBrowsing extends AppCompatActivity {
    RecyclerView recycleView;
    PathBrowsingAdapter pathBrowsingAdapter;

    private PathBrowsingViewModel pathBrowsingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_browsing);
        recycleView =findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        pathBrowsingViewModel = ViewModelProviders.of(this).get(PathBrowsingViewModel.class);
        pathBrowsingViewModel.getAllImagesLive().observe(this, new Observer<List<Path>>() {
            @Override
            public void onChanged(List<Path> paths) {
                int size = paths.size();
                Path[] myDataset= new Path[size];
                for(int i=0 ; i<paths.size() ; i++){
                    paths.get(i).setStartTimestamp(TimeStampToString(paths.get(i).getStartTimestamp()));
                    myDataset[i] = paths.get(i);
                }
                System.out.println("paths=="+paths.size());
                pathBrowsingAdapter = new PathBrowsingAdapter(myDataset);
                recycleView.setAdapter(pathBrowsingAdapter);
            }
        });



    }


}
