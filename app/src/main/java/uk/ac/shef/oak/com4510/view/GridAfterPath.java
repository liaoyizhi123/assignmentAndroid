package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import uk.ac.shef.oak.com4510.adapter.GridAfterPathAdapter;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.viewModel.GridAfterPathViewModel;

public class GridAfterPath extends AppCompatActivity {

    GridView gridView;
    TextView textView;
    //livedata of all the images
    LiveData<List<Image>> imagesToDisplay;
    //the viewModel
    private GridAfterPathViewModel gridAfterPathViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_after_path);
        gridView=(GridView) findViewById(R.id.GridView);
        textView=(TextView)findViewById(R.id.TitleInPath);

        //Get Title
        final String Title =getIntent().getExtras().getString("Title");
        //Get pathId of the images
        int pathId =getIntent().getExtras().getInt("pathId");

        //add observation to the viewModel
        gridAfterPathViewModel = ViewModelProviders.of(this).get(GridAfterPathViewModel.class);
        gridAfterPathViewModel.getImageLiveByPathId(pathId).observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                int size = images.size();
                final Image[] myDataset= new Image[size];
                for(int i=0 ; i<size ; i++){
                    myDataset[i] = images.get(i);
                }
                gridView.setAdapter(new GridAfterPathAdapter(GridAfterPath.this,myDataset));

                //click for ImageDetails
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent =new Intent(getApplicationContext(),ImageDetails.class);

                        intent.putExtra("id", myDataset[position].getId());
                        intent.putExtra("pathId", myDataset[position].getPathId());
                        startActivity(intent);
                    }
                });

            }
        });




        textView.setText(Title);

    }
}